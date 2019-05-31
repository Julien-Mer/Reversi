package reversi;

public class Game implements IGame {
	
	private Player player1;
	private Player player2;
	
	public String description() {
		String res = "";
		return res;
	}
	
	public void start() {
		
	}
	
	public void endOfGame() {
		
	}
	
	public Game(String playerName1, String playerName2, Mode mode) {
		Board board = new Board(15, 15);
		this.initalizeBoard();
		if(mode == Mode.AA)
			this.player1 = new AutoPlayer(playerName1, board);
		else
			this.player1 = new HumanPlayer(playerName1, board);
		if(mode == Mode.HH)
			this.player2 = new HumanPlayer(playerName2, board);
		else
			this.player2 = new AutoPlayer(playerName2, board);
		this.start();
		System.out.println(board.toString());
	}
	
	private void initalizeBoard() {
		
	}
}
