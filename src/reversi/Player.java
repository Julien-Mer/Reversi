package reversi;

public class Player {
	
	protected String name;
	protected Board board;
	protected int coinsRemaining;
	
	public Player(String name, Board board) {
		this.board = board;
		this.name = name;
	}
	
	public void play() { }
	
	private CoinColor color;
	
	public void setCoinColor(CoinColor color) {
		this.color = color;
	}
	
	public CoinColor getCoinColor() {
		return this.color;
	}
	
	public int getCoinsRemaining() {
		return this.coinsRemaining;
	}
	
	public void setCoinsRemaining(int coinsRemaining) {
		this.coinsRemaining = coinsRemaining;
	}
	
}
