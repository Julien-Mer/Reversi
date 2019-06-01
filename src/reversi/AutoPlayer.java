package reversi;

public class AutoPlayer extends Player {
	
	public AutoPlayer(String name, Board board) {
		super(name, board);
	}
	
	public void play() {
		System.out.println("Autoplay");
	}
	
}
