package reversi;

public class Player {
	
	/**
	 * The name of the player
	 */
	protected String name;
	
	/**
	 * The board used by the player
	 */
	protected Board board;
	
	/**
	 * Constructor of Player
	 * @param name the name of the player
	 * @param board the board used by the player
	 */
	public Player(String name, Board board) {
		this.board = board;
		this.name = name;
	}
	
	/**
	 * The play method of the player
	 */
	public void play() { }
	
	/**
	 * The color of the player
	 */
	private CoinColor color;
	
	/**
	 * Set the color of the player
	 * @param color the color of the player
	 */
	public void setCoinColor(CoinColor color) {
		this.color = color;
	}
	
	/**
	 * Get the color of the player
	 * @return the color of the player
	 */
	public CoinColor getCoinColor() {
		return this.color;
	}
	
}
