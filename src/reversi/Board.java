package reversi;

import java.util.ArrayList;

import controllers.clickListener;
import view.GridTableFrame;

public class Board {

	private int width;
	private int height;
	private Square[][] grid;
	private GridTableFrame view;
	private CoinColor playingColor;
	private boolean terminal;
	private Game game;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new Square[width][height];
	}
	
	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}
	
	public boolean getTerminal() {
		return this.terminal;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void setPlayingColor(CoinColor color) {
		this.playingColor = color;
	}
	
	public CoinColor getPlayingColor() {
		return this.playingColor;
	}
	
	public Square[][] getGrid() {
		return this.grid;
	}
	
	public void setView(GridTableFrame view) {
		this.view = view;
	}
	
	public void setColorView(CoinColor color) {
		this.view.setColor(color);
	}
	
	public GridTableFrame getView() {
		return this.view;
	}
	
	public void displayMap(CoinColor color) {
		if(this.terminal)
			System.out.println(this.toString());
		else {
			view.refresh(grid, color);
			view.getTable().addMouseListener(new clickListener(this));
		}
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
			this.changeCoin(x,y,color);
			res = true;
		}
		return res;
	}
	
	public void changeCoin(int x, int y, CoinColor color) {
		 this.displayMap(color);
		 grid[x][y].setColor(color);
		 for(Square square : this.getChanges(x, y, color))
			 square.setColor(color);
	 }
	 
	public ArrayList<Square> getLocalChanges(int x, int y, int incX, int incY, CoinColor color) {
		ArrayList<Square> res = new ArrayList<Square>();
		ArrayList<Square> localChanges = new ArrayList<Square>();
		boolean emp = false;
		int i = x + incX, j = y + incY;
		boolean condX, condY;
		if(incX < 0) condX = i > 0;
		else condX = i < width-1;
		if(incY < 0) condY = j > 0;
		else condY = j < height-1;
		
		while(condX && condY && !emp) {
			if(grid[i][j].getColor() == color) {
				res.addAll(localChanges);
				localChanges = new ArrayList<Square>();
			} else if(grid[i][j].isUsed()) 
				localChanges.add(grid[i][j]);
			else
				emp = true;
			i = i + incX;
			j = j + incY;
			if(incX < 0) condX = i > 0;
			else condX = i < width-1;
			if(incY < 0) condY = j > 0;
			else condY = j < height-1;
		}
		return res;
	}
	
	public ArrayList<Square> getChanges(int x, int y, CoinColor color) {
		ArrayList<Square> changes = new ArrayList<Square>();
		changes.addAll(this.getLocalChanges(x, y, 1, 0, color)); // Bottom of the case
		changes.addAll(this.getLocalChanges(x, y, -1, 0, color)); // Top of the case
		changes.addAll(this.getLocalChanges(x, y, 0, -1, color)); // Left of the case
		changes.addAll(this.getLocalChanges(x, y, 0, 1, color)); // Rigth of the case
		changes.addAll(this.getLocalChanges(x, y, -1, -1, color)); // Top left diag of the case
		changes.addAll(this.getLocalChanges(x, y, -1, 1, color)); // Top right diag of the case
		changes.addAll(this.getLocalChanges(x, y, 1, -1, color)); // Bottom left diag of the case
		changes.addAll(this.getLocalChanges(x, y, 1, 1, color)); // Bottom right diag of the case
		return changes;
	}
	 
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public boolean globalAlignment(int x, int y, int incX, int incY, CoinColor color) {
		boolean res = false, emp = false;
		int i = x + incX, j = y + incY;
		boolean condX, condY;
		if(incX < 0) condX = i > 0;
		else condX = i < width-1;
		if(incY < 0) condY = j > 0;
		else condY = j < height-1;
		if(condX && condY && this.grid[i][j].getColor() != color && this.grid[i][j].isUsed()) {
			while(condX && condY && !res && !emp) {
				if(grid[i][j].getColor() == color) {
					res = true;
				} else if(!grid[i][j].isUsed()) 
					emp = true;
				i = i + incX;
				j = j + incY;
				if(incX < 0) condX = i > 0;
				else condX = i < width-1;
				if(incY < 0) condY = j > 0;
				else condY = j < height-1;
			}
		}
		return res;
	}
	
	public boolean checkAlignment(int x, int y, CoinColor color) {
		Boolean res = false;
		if(this.checkHAlignment(x, y, color) || this.checkLDAlignment(x, y, color) || this.checkVAlignment(x, y, color) || this.checkWDAlignment(x, y, color))
			res = true;
		return res;
	}
	
	private boolean checkHAlignment(int x, int y, CoinColor color) {
		return globalAlignment(x, y, 0, 1, color) || globalAlignment(x, y, 0, -1, color);
	}
	
	public boolean checkLDAlignment(int x, int y, CoinColor color) {
		return globalAlignment(x, y, -1, 1, color) || globalAlignment(x, y, 1, -1, color);
	}
	
	private boolean checkWDAlignment(int x, int y, CoinColor color) {
		return globalAlignment(x, y, -1, -1, color) || globalAlignment(x, y, 1, 1, color);
	}
	
	private boolean checkVAlignment(int x, int y, CoinColor color) {
		return globalAlignment(x, y, 1, 0, color) || globalAlignment(x, y, -1, 0, color);
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
