package reversi;

public class Player {
	
	protected String name;
	protected Board board;
	
	public Player(String name, Board board) {
		this.board = board;
		this.name = name;
	}
	
	public void play() {
		
	}
	
	private CoinColor color;
	
	public void setCoinColor(CoinColor color) {
		this.color = color;
	}
	
	public CoinColor getCoinColor() {
		return this.color;
	}
	
}
