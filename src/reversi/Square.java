package reversi;

public class Square {

	private int x;
	private int y;
	private CoinColor color;
	
	public Square(int x, int y, CoinColor color) {
		this.x = x; 
		this.y = y;
		this.color = color;
	}
	
	public String toString() {
		String res = "[" + this.x + "," + this.y + "] " + color;
		return res;
	}
	
	public void setColor(CoinColor color) {
		this.color = color;
	}
	
	public String getRepresentation() {
		String res = "";
		switch(this.color) {
			case BLACK:
				res = "N";
				break;
			case FREE:
				res = "F";
				break;
			case WHITE:
				res = "B";
				break;
			case NONE:
				res = "0";
				break;
			default:
				res = "f";
				break;
		}
		return res;
	}
	
	public boolean isFree() {
		 return color==CoinColor.FREE;
	}
		
	public boolean isForbidden() {
		 return color==CoinColor.NONE;
	}
	
}
