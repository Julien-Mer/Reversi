package controllers;

import java.awt.event.MouseAdapter;
import javax.swing.JTable;

import reversi.Board;

import java.awt.event.MouseEvent;

public class clickListener extends MouseAdapter {
	/**
	 * The board linked to the view
	 */
	Board board;
	
	/**
	 * ClickListener of the view
	 * @param board
	 */
	public clickListener(Board board) {
		this.board = board;
	}
	
	/**
	 * Mouse clicked event to play when the player click on a square
	 * @param e The mouse event
	 */
	public void mouseClicked(MouseEvent e) {
        int x = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
        int y = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
        if (x >= 0 && y >= 0 && this.board.getPlayingColor() == this.board.getView().getColor()) { // Si c'est au joueur de jouer
        	if(this.board.setCoin(x, y, this.board.getView().getColor())) // On essaye de poser la pièce
        		this.board.getGame().play(); // Si la pièce a été posée, on dit au jeu de continuer
        }
    }
}