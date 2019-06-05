package reversi;

import java.util.*;

public class HumanPlayer extends Player {
	/**
	 * The scanner to receive user input
	 */
	private Scanner scan;
	
	/**
	 * Create a human player
	 * @param name name of the player
	 * @param board the board used
	 */
	public HumanPlayer(String name, Board board) {
		super(name, board);
	}
	
	/**
	 * Play method of the human player
	 */
	public void play() {
		this.board.setColorView(this.getCoinColor());
		if(this.board.getTerminal()) {
			System.out.println(this.name + ", à vous de jouer: -> x,y");
			scan = new Scanner(System.in);
			String input = scan.nextLine();
			try {
				String[] data = input.split(",");
				int x = Integer.valueOf(data[0]);
				int y = Integer.valueOf(data[1]);
				if(x >= this.board.getWidth())
					throw new Exception("Coordonnées hors grille");
				if(y >= this.board.getHeight())
					throw new Exception("Coordonnées hors grille");
				if(!this.board.setCoin(x, y, this.getCoinColor()))
					throw new Exception("Mouvement impossible");
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
				System.out.println("Saisie invalide");
				this.play();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				this.play();
			}
			this.board.getGame().play();
		}
	}
}
