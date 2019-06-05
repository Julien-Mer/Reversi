package reversi;

public class Square {

	/**
	 * The position of the square
	 */
	private int x;
	private int y;
	
	/**
	 * The color of the square
	 */
	private CoinColor color;
	
	/**
	 * Constructor of Square
	 * @param x the x position of the square
	 * @param y the y position of the square
	 * @param color the color of the square
	 */
	public Square(int x, int y, CoinColor color) {
		this.x = x; 
		this.y = y;
		this.color = color;
	}
	
	/**
	 * Returns a representation of the square in a string
	 * @return the representation of the square
	 */
	public String toString() {
		String res = "[" + this.x + "," + this.y + "] " + color.name();
		return res;
	}
	
	/**
	 * Change the color of the square
	 * @param color the new color of the square
	 */
	public void setColor(CoinColor color) {
		this.color = color;
	}
	
	/**
	 * Get the representation of the Square with a letter
	 * @return a letter representating the square
	 */
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
	
	/**
	 * Gets the x position of the square
	 * @return the x position of the square
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Gets the y position of the square
	 * @return the y position of the square
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Boolean if the square is free or not 
	 * @return a boolean if the square is free or not
	 */
	public boolean isFree() {
		 return color==CoinColor.FREE;
	}
	
	/**
	 * Gets the color of the square
	 * @return the color of the square
	 */
	public CoinColor getColor() {
		return this.color;
	}
	
	/**
	 * Boolean if the square is used or not
	 * @return a boolean if the square is used
	 */
	public boolean isUsed() {
		return !isFree() && !isForbidden();
	}
	
	/**
	 * Boolean if the square is forbidden or not
	 * @return a boolean if the square if forbidden
	 */
	public boolean isForbidden() {
		 return color==CoinColor.NONE;
	}
	
}
