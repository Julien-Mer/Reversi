package view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import java.awt.Color;

import reversi.CoinColor;
import reversi.Square;
/**
 * GridTableFrame : frame for GridTable
 */
public class GridTableFrame extends SimpleFrame {
  private final int rowHeight = 40;  //en pixel
  private JTable tab;
  private CoinColor color; // La couleur du joueur qui perçoit la fenêtre
  
  /**
   * Constructor
   * It creates the GridTableModel
   * @param grid : the data table to display
   */
  public GridTableFrame(Square[][] grid) {
    // set the grid size
    this.setSize((int)(rowHeight*grid.length*1.5),(int)(rowHeight*grid[0].length*1.5));
  }
  
  public void setColor(CoinColor color) {
	  this.color = color;
  }
  
  public CoinColor getColor() {
	  return this.color;
  }
  
  public JTable getTable() {
	  return this.tab;
  }
  
  public void refresh(Square[][] grid, CoinColor color) {
	  this.getContentPane().removeAll();
	  
	  GridTableModel otmodel = new GridTableModel(grid);
	  this.tab = new JTable(otmodel);
	  // to adjust some parameters
	  tab.setShowGrid(true);
	  // color for the grid lines
	  tab.setGridColor(Color.BLUE);
	  tab.setRowHeight(rowHeight);
	    
	  JScrollPane SP = new JScrollPane(tab);
	  if(color == CoinColor.BLACK)
		  this.setTitle("Tour: blanc");
	  else this.setTitle("Tour: noir");
	  this.getContentPane().add(SP);
	  this.getContentPane().revalidate();
	  this.getContentPane().repaint();
  }
}