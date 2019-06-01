package reversi;

import java.util.*;

public class Game implements IGame {
	
	private Player player1;
	private Player player2;
	private List<Player> timeLine = new LinkedList<Player>();
	private Board board;
	
	public String description() {
		String res = "";
		return res;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public void start() {
		System.out.println("--- D�but de la partie ---");
		this.board.displayMap();
		
		boolean end = false, passed = false;
		while(!end) {
			Player p = timeLine.remove(0);
			if(p.board.getPossibilities(p.getCoinColor()).size() > 0) {
				p.play();
				passed = false;
			} else {
				if(passed) {
					System.out.println("Plus personne ne peut jouer...");
					end = true;
				} else {
					System.out.println("Le joueur " + p.name + " doit passer son tour...");
					passed = true;
				}
			}
			timeLine.add(p);
		}
		endOfGame();
	}
	
	public void endOfGame() {
		System.out.println("--- Fin de la partie ---");
		Player winner = null;
		int scoreMax = 0;
		for(Player p : timeLine) {
			int score = p.board.getNbrCoins(p.getCoinColor());
			System.out.println(p.name + ": " + score);
			if(score >= scoreMax) {
				scoreMax = score;
				winner = p;
			}
		}
		System.out.println("Victoire de " + winner.name + " avec un score de " + scoreMax);
	}
	
	public Game(String playerName1, String playerName2, Mode mode) {
		this.board = new Board(15, 15);
		System.out.println("--- Initialisation du tableau ---");
		this.initalizeBoard();
		if(mode == Mode.AA)
			this.player1 = new AutoPlayer(playerName1, this.board);
		else
			this.player1 = new HumanPlayer(playerName1, this.board);
		if(mode == Mode.HH)
			this.player2 = new HumanPlayer(playerName2, this.board);
		else
			this.player2 = new AutoPlayer(playerName2, this.board);
		this.player1.setCoinColor(CoinColor.WHITE);
		this.player2.setCoinColor(CoinColor.BLACK);
		this.timeLine.add(this.player1);
		this.timeLine.add(this.player2);
	}
	
	private void initalizeBoard() {
		for(int i = 0; i < this.board.getWidth(); i++) {
			for(int j = 0; j < this.board.getHeight(); j++) {
				CoinColor color;
				if(j > ((this.board.getWidth()/2) + i) || (i > (this.board.getHeight()/2) && j < i - (this.board.getWidth()/2)))
					color = CoinColor.NONE;
				else if(j == this.board.getWidth()/2 && i == this.board.getHeight()/2 || j == this.board.getWidth()/2+1 && i == this.board.getWidth()/2+1)
					color = CoinColor.WHITE;
				else if(j == this.board.getWidth()/2 && i == this.board.getHeight()/2+1 || j == this.board.getWidth()/2+1 && i == this.board.getWidth()/2)
					color = CoinColor.BLACK;
				else
					color = CoinColor.FREE;
				this.board.setSquare(i, j, new Square(i, j, color));
				
			}
		}
	}
}
