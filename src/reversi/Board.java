package reversi;

import java.util.ArrayList;

public class Board {

	private int width;
	private int height;
	private Square[][] grid;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new Square[width][height];
	}
	
	public void displayMap() {
		System.out.println(this.toString());
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
	 }
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public boolean checkAlignment(int x, int y, CoinColor color) {
		Boolean res = false;
		if(this.checkHAlignment(x, y, color) || this.checkLDAlignment(x, y, color) || this.checkVAlignment(x, y, color) || this.checkWDAlignment(x, y, color))
			res = true;
		return res;
	}
	//System.out.println("pos: " + x + "," + j + " color: " + this.grid[x][j].getRepresentation());
	private boolean checkHAlignment(int x, int y, CoinColor color) {
		boolean res = false;
		if(y > 0 && this.grid[x][y-1].getColor() != color && this.grid[x][y-1].isUsed()) 
			for(int j = y-1; j >= 0; j--) {
				if(this.grid[x][j].getColor() == color)
					res = true;
			}
		if(!res && y < (this.height-1) && this.grid[x][y+1].getColor() != color && this.grid[x][y+1].isUsed()) 
			for(int j = y; j < this.height; j++) {
				if(this.grid[x][j].getColor() == color)
					res = true;
			}
		return res;
	}

	public boolean checkLDAlignment(int x, int y, CoinColor color) {
		boolean res = false;
		if(x > 0 && y < (this.height - 1) && this.grid[x-1][y+1].getColor() != color && this.grid[x-1][y+1].isUsed()) {
			int i = x;
			int j = y;
			while(i >= 0 && j < this.height && !res) {
				if(this.grid[i][j].getColor() == color)
					res = true;
				i--;
				j++;
			}
		}
		if(!res && y > 0 && x < (this.width - 1) && this.grid[x+1][y-1].getColor() != color && this.grid[x+1][y-1].isUsed()) {
			int i = x;
			int j = y;
			while(j >= 0 && i < this.width && !res) {
				if(this.grid[i][j].getColor() == color)
					res = true;
				i++;
				j--;
			}
		}
		return res;
	}
	
	private boolean checkWDAlignment(int x, int y, CoinColor color) {
		boolean res = false;
		if(x > 0 && y > 0 && this.grid[x-1][y-1].getColor() != color && this.grid[x-1][y-1].isUsed()) {
			int i = x;
			int j = y;
			while(i >= 0 && j >= 0 && !res) {
				if(this.grid[i][j].getColor() == color)
					res = true;
				i--;
				j--;
			}
		}
		if(!res && y < (this.height - 1) && x < (this.width - 1) && this.grid[x+1][y+1].getColor() != color && this.grid[x+1][y+1].isUsed()) {
			int i = x;
			int j = y;
			while(j < this.height && i < this.width && !res) {
				if(this.grid[i][j].getColor() == color)
					res = true;
				i++;
				j++;
			}
		}
		return res;
	}
	
	private boolean checkVAlignment(int x, int y, CoinColor color) {
		boolean res = false;
		if(x > 0 && this.grid[x-1][y].getColor() != color && this.grid[x-1][y].isUsed()) 
			for(int j = x-1; j >= 0; j--) {
				if(this.grid[j][y].getColor() == color)
					res = true;
			}
		if(!res && x < (this.width-1) && this.grid[x+1][y].getColor() != color && this.grid[x+1][y].isUsed()) 
			for(int j = x; j < this.width; j++) {
				if(this.grid[j][y].getColor() == color)
					res = true;
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
