package reversi;

public interface IGame {
	/**
	 * The description and rules of the game
	 * @return a string descripting the game
	 */
	public String description();
	
	/**
	 * Starts a new game
	 */
	public void start();
	
	/**
	 * Ends the game
	 */
	public void endOfGame();
}
