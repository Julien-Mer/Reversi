package reversi;

import java.util.*;

public class HumanPlayer extends Player {
	private Scanner scan;
	
	public HumanPlayer(String name, Board board) {
		super(name, board);
	}
	
	public void play() {
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
		} catch (NullPointerException | NumberFormatException ex) {
			System.out.println("Saisie invalide");
			this.play();
		} catch (Exception ex) {
		System.out.println(ex.getMessage());
		this.play();
	}
	}
}
