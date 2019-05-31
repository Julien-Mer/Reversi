package reversi;

import java.util.*;

public class HumanPlayer extends Player {
	private Scanner scan;
	
	public HumanPlayer(String name, Board board) {
		super(name, board);
	}
	
	public void play() {
		System.out.println(this.name + ", à vous de jouer: -> x,y");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		try {
			String[] data = input.split(",");
			int x = Integer.valueOf(data[0]);
			int y = Integer.valueOf(data[1]);
		} catch (Exception ex) {
			System.out.println("Coordonnées invalides");
			this.play();
		}
	}
}
