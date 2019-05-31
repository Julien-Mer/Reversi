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
	
	public String getRepresentation() {
		String res = "";
		switch(this.color) {
			case BLACK:
				res = "\033[0;30m";
				break;
			case FREE:
				res = "\u001b[32m";
				break;
			case WHITE:
				res = "\033[0;37m";
				break;
			case NONE:
				res = "\033[0;33m";
				break;
			default:
				res = "\033[0;31m";
				break;
		}
		res += "■";
		res += "\033[0m";
		return res;
	}
	
}
