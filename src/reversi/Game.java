package reversi;

import java.util.*;

public class Game implements IGame {
	
	private Player player1;
	private Player player2;
	private List<Player> timeLine = new LinkedList<Player>();
	
	
	public String description() {
		String res = "";
		return res;
	}
	
	public void start() {
		boolean end = false;
		while(!end) {
			Player p = timeLine.remove(0);
			p.play();
			timeLine.add(p);
		}
	}
	
	public void endOfGame() {
		
	}
	
	public Game(String playerName1, String playerName2, Mode mode) {
		Board board = new Board(15, 15);
		System.out.println("--- Initialisation du tableau ---");
		this.initalizeBoard(board);
		if(mode == Mode.AA)
			this.player1 = new AutoPlayer(playerName1, board);
		else
			this.player1 = new HumanPlayer(playerName1, board);
		if(mode == Mode.HH)
			this.player2 = new HumanPlayer(playerName2, board);
		else
			this.player2 = new AutoPlayer(playerName2, board);
		this.player1.setCoinColor(CoinColor.WHITE);
		this.player2.setCoinColor(CoinColor.BLACK);
		this.timeLine.add(this.player1);
		this.timeLine.add(this.player2);
		System.out.println("--- Début de la partie ---");
		System.out.println(board.toString());
		this.start();
	}
	
	private void initalizeBoard(Board board) {
		for(int i = 0; i < board.getWidth(); i++) {
			for(int j = 0; j < board.getHeight(); j++) {
				CoinColor color;
				if(j > ((board.getWidth()/2) + i) || (i > (board.getHeight()/2) && j < i - (board.getWidth()/2)))
					color = CoinColor.NONE;
				else if(j == board.getWidth()/2 && i == board.getHeight()/2 || j == board.getWidth()/2+1 && i == board.getWidth()/2+1)
					color = CoinColor.WHITE;
				else if(j == board.getWidth()/2 && i == board.getHeight()/2+1 || j == board.getWidth()/2+1 && i == board.getWidth()/2)
					color = CoinColor.BLACK;
				else
					color = CoinColor.FREE;
				board.setSquare(i, j, new Square(i, j, color));
				
			}
		}
	}
}
