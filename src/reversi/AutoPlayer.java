package reversi;

import java.util.ArrayList;

public class AutoPlayer extends Player {
	
	/**
	 * AutoPlayer constructor
	 * @param name the name of the autoplayer
	 * @param board the board where the autoplayer will play
	 */
	public AutoPlayer(String name, Board board) {
		super(name, board);
	}
	
	/**
	 * Play method of the AutoPlayer, choosing the square where it wins more coins
	 */
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
