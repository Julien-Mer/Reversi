package view;

import javax.swing.table.AbstractTableModel;
import javax.swing.ImageIcon;
import reversi.Square;
import reversi.CoinColor;

/**
 * GridTableModel : the graphical table
 */
public class GridTableModel extends AbstractTableModel {

  private int noOfRows, noOfCols;
  private Square[][] grid;
  private static final String PATH = "./images/";    // the folder
  // the image are directly created from the files
  private String imageFree= "blue.png";
  private String imageWhite= "pion-blanc.png";
  private String imageBlack= "pion-noir.png";
  private String imageNone="gris.png" ;
  
 /*
  * Constructor
  * @param grid : the table to display
  */
  public GridTableModel(Square[][] grid) {
    this.grid = grid;
    noOfRows = this.grid.length;
    noOfCols = this.grid[0].length;
  }

// Implementing the tree inherited abstract methods:
  
   public int getRowCount() {
    return(noOfRows);
  }
  public int getColumnCount() {
    return(noOfCols);
  }

  public Object getValueAt(int r,int c) {
    Object result = new Object();
    Square sq = grid[r][c];
    if (sq.isFree()) result= new ImageIcon(PATH + imageFree);
    else if (sq.isForbidden()) result= new ImageIcon(PATH + imageNone);
    else if (sq.getColor() == CoinColor.WHITE) result= new ImageIcon(PATH + imageWhite); // couleur
    else result= new ImageIcon(PATH + imageBlack);
    return result;
  }
 
 
  /**
   * get the name of the column
   * @param c : the number of the column
   * @return a String for the name of the column
   */
  public String getColumnName(int c){
    return (new Integer(c).toString());
  }
  
   /**
   * get the class of the object at column c
   * @param c : the number of the column
   * @return the class of the object at column c
   */
   public Class getColumnClass(int c) {
      return this.getValueAt(0, c).getClass();
   }
}