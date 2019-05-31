package reversi;

public class Board {

	private int width;
	private int height;
	private Square[][] grid;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new Square[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				CoinColor color = CoinColor.WHITE;
				this.grid[i][j] = new Square(i, j, color);
			}
		}
	}
	
	public void checkAlignment() {
		
	}
	
	private void checkHAlignment() {
		
	}

	public void checkLDAlignment() {
		
	}
	
	private void checkWDAlignment() {
		
	}
	
	private void checkVAlignment() {
		
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
