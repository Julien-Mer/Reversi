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
				CoinColor color;
				if(j > ((width/2) + i) || (i > (height/2) && j < i - (width/2)))
					color = CoinColor.NONE;
				else if(j == width/2 && i == height/2 || j == width/2+1 && i == height/2+1)
					color = CoinColor.WHITE;
				else if(j == width/2 && i == height/2+1 || j == width/2+1 && i == height/2)
					color = CoinColor.BLACK;
				else
					color = CoinColor.FREE;
				this.grid[i][j] = new Square(i, j, color);
			}
		}
	}
	
	public void displayMap() {
		System.out.println(this.toString());
	}
	
	public boolean setCoin(int x, int y, CoinColor color) {
		boolean res = false;
		if(this.grid[x][y].isFree()) {
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
