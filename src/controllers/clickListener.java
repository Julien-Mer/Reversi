package controllers;

import java.awt.event.MouseAdapter;
import javax.swing.JTable;

import reversi.Board;

import java.awt.event.MouseEvent;

public class clickListener extends MouseAdapter {
	Board board;
	
	public clickListener(Board board) {
		this.board = board;
	}
	
	public void mouseClicked(MouseEvent e) {
        int x = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
        int y = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
        if (x >= 0 && y >= 0 && this.board.getPlayingColor() == this.board.getView().getColor()) {
        	if(this.board.setCoin(x, y, this.board.getView().getColor()))
        		this.board.getGame().play();
        }
    }
}