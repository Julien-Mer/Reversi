package view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import reversi.Square;
/**
 * GridTableFrame : frame for GridTable
 */
public class GridTableFrame extends SimpleFrame {
  private final int rowHeight = 40;  //en pixel
  
  
  /**
   * Constructor
   * It creates the GridTableModel
   * @param grid : the data table to display
   */
  public GridTableFrame(Square[][] grid) {
    // set the grid size
    this.setSize((int)(rowHeight*grid.length*1.5),(int)(rowHeight*grid[0].length*1.5));
    refresh(grid);
  }
  
  public void refresh(Square[][] grid) {
	  System.out.println("Ouais");
	 
	  this.getContentPane().removeAll();
	  
	  GridTableModel otmodel = new GridTableModel(grid);
	  JTable tab = new JTable(otmodel);
	  // to adjust some parameters
	  tab.setShowGrid(true);
	  // color for the grid lines
	  tab.setGridColor(Color.BLUE);
	  tab.setRowHeight(rowHeight);
	    
	  JScrollPane SP = new JScrollPane(tab);
	  this.getContentPane().add(SP);
	  this.getContentPane().revalidate();
	  this.getContentPane().repaint();
  }
}