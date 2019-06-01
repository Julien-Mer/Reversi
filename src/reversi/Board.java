package reversi;

import java.util.ArrayList;

public class Board {

	private int width;
	private int height;
	private Square[][] grid;
	ArrayList<Square> changes;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new Square[width][height];
	}
	
	public void displayMap() {
		System.out.println(this.toString());
	}
	
	public int getNbrCoins(CoinColor color) {
		int count = 0;
		for(Square[] lines : grid)
			for(Square square : lines)
				if(square.getColor() == color)
					count++;
		return count;
	}
	
	public ArrayList<Square> getPossibilities(CoinColor color) {
		ArrayList<Square> possibilites = new ArrayList<Square>();
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(grid[i][j].isFree() && checkAlignment(i, j, color))
					possibilites.add(grid[i][j]);
			}
		}
		return possibilites;
	}
	
	public void setSquare(int x, int y, Square square) {
		this.grid[x][y] = square;
	}
	
	public boolean setCoin(int x, int y, CoinColor color) {
		boolean res = false;
		if(this.grid[x][y].isFree() && this.checkAlignment(x, y, color)) {
			System.out.println("OK");
			this.changeCoin(x,y,color);
			this.displayMap();
			res = true;
		}
		return res;
	}
	
	 public void changeCoin(int x, int y, CoinColor color) {
		 grid[x][y].setColor(color);
		 for(Square square : changes)
			 square.setColor(color);
	 }
	 
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public boolean checkAlignment(int x, int y, CoinColor color) {
		Boolean res = false;
		changes = new ArrayList<Square>();
		if(this.checkHAlignment(x, y, color) || this.checkLDAlignment(x, y, color) || this.checkVAlignment(x, y, color) || this.checkWDAlignment(x, y, color))
			res = true;
		return res;
	}
	//System.out.println("pos: " + x + "," + j + " color: " + this.grid[x][j].getRepresentation());
	private boolean checkHAlignment(int x, int y, CoinColor color) {
		boolean res = false;
		if(y > 0 && this.grid[x][y-1].getColor() != color && this.grid[x][y-1].isUsed()) {
			ArrayList<Square> localChanges = new ArrayList<Square>();
			for(int j = y-1; j >= 0; j--) {
				if(this.grid[x][j].getColor() == color) {
					res = true;
					this.changes.addAll(localChanges);
					localChanges = new ArrayList<Square>();
				} else 
					localChanges.add(this.grid[x][j]);
					
			}
		}
		if(y < (this.height-1) && this.grid[x][y+1].getColor() != color && this.grid[x][y+1].isUsed()) {
			ArrayList<Square> localChanges = new ArrayList<Square>();
			for(int j = y; j < this.height; j++) {
				if(this.grid[x][j].getColor() == color) {
					res = true;
					this.changes.addAll(localChanges);
					localChanges = new ArrayList<Square>();
				} else
					localChanges.add(this.grid[x][j]);
			}
		}
		return res;
	}
	
	public boolean checkLDAlignment(int x, int y, CoinColor color) {
		boolean res = false;
		
		if(x > 0 && y < (this.height - 1) && this.grid[x-1][y+1].getColor() != color && this.grid[x-1][y+1].isUsed()) {
			ArrayList<Square> localChanges = new ArrayList<Square>();
			int i = x;
			int j = y;
			while(i >= 0 && j < this.height) {
				if(this.grid[i][j].getColor() == color) {
					res = true;
					this.changes.addAll(localChanges);
					localChanges = new ArrayList<Square>();
				} else
					localChanges.add(this.grid[i][j]);
				i--;
				j++;
			}
		}
		if(y > 0 && x < (this.width - 1) && this.grid[x+1][y-1].getColor() != color && this.grid[x+1][y-1].isUsed()) {
			ArrayList<Square> localChanges = new ArrayList<Square>();
			int i = x;
			int j = y;
			while(j >= 0 && i < this.width) {
				if(this.grid[i][j].getColor() == color) {
					res = true;
					this.changes.addAll(localChanges);
					localChanges = new ArrayList<Square>();
				} else
					localChanges.add(this.grid[i][j]);
				i++;
				j--;
			}
		}
		return res;
	}
	
	private boolean checkWDAlignment(int x, int y, CoinColor color) {
		boolean res = false;
		if(x > 0 && y > 0 && this.grid[x-1][y-1].getColor() != color && this.grid[x-1][y-1].isUsed()) {
			ArrayList<Square> localChanges = new ArrayList<Square>();
			int i = x;
			int j = y;
			while(i >= 0 && j >= 0) {
				if(this.grid[i][j].getColor() == color) {
					res = true;
					this.changes.addAll(localChanges);
					localChanges = new ArrayList<Square>();
				} else
					localChanges.add(this.grid[i][j]);
				i--;
				j--;
			}
		}
		if(y < (this.height - 1) && x < (this.width - 1) && this.grid[x+1][y+1].getColor() != color && this.grid[x+1][y+1].isUsed()) {
			ArrayList<Square> localChanges = new ArrayList<Square>();
			int i = x;
			int j = y;
			while(j < this.height && i < this.width) {
				if(this.grid[i][j].getColor() == color) {
					res = true;
					this.changes.addAll(localChanges);
					localChanges = new ArrayList<Square>();
				} else
					localChanges.add(this.grid[i][j]);
				i++;
				j++;
			}
		}
		return res;
	}
	
	private boolean checkVAlignment(int x, int y, CoinColor color) {
		boolean res = false;
		if(x > 0 && this.grid[x-1][y].getColor() != color && this.grid[x-1][y].isUsed()) {
			ArrayList<Square> localChanges = new ArrayList<Square>();
			for(int j = x-1; j >= 0; j--) {
				if(this.grid[j][y].getColor() == color) {
					res = true;
					this.changes.addAll(localChanges);
					localChanges = new ArrayList<Square>();
				} else
					localChanges.add(this.grid[j][y]);
			}
		}
		if(x < (this.width-1) && this.grid[x+1][y].getColor() != color && this.grid[x+1][y].isUsed()) {
			ArrayList<Square> localChanges = new ArrayList<Square>();
			for(int j = x; j < this.width; j++) {
				if(this.grid[j][y].getColor() == color) {
					res = true;
					this.changes.addAll(localChanges);
					localChanges = new ArrayList<Square>();
				} else
					localChanges.add(this.grid[j][y]);
			}
		}
		return res;
	}
	
	public String toString() {
		String res = "";
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				res += grid[i][j].getRepresentation();
			}
			res += "\n";
		}
		return res;
	}
}
