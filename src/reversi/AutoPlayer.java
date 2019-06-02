package reversi;

import java.util.ArrayList;

public class AutoPlayer extends Player {
	
	public AutoPlayer(String name, Board board) {
		super(name, board);
	}
	
	public void play() {
		ArrayList<Square> possibilities = this.board.getPossibilities(this.getCoinColor());
		int newCoins = 0;
		Square choice = null;
		for(Square possibility : possibilities) {
			int coins = this.board.getChanges(possibility.getX(), possibility.getY(), possibility.getColor()).size();
			if(coins >= newCoins) {
				choice = possibility;
				newCoins = coins;
			}
		}
		this.board.setCoin(choice.getX(), choice.getY(), this.getCoinColor());
		this.board.getGame().play();
	}
	
}
