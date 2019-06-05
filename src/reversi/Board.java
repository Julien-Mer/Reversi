package reversi;

import java.util.ArrayList;

import controllers.clickListener;
import view.GridTableFrame;

public class Board {

	/**
	 * Dimensions of the board
	 */
	private int width;
	private int height;
	
	/**
	 * The grid used
	 */
	private Square[][] grid;
	
	/**
	 * The table viewed by the players
	 */
	private GridTableFrame view;
	
	/**
	 * The color of the player which can play his turn
	 */
	private CoinColor playingColor;
	
	/**
	 * Boolean if the player sees a terminal or the GUI
	 */
	private boolean terminal;
	
	/**
	 * The game where the board is used
	 */
	private Game game;
	
	/**
	 * Constructor of the Board
	 * @param width the width of the board
	 * @param height the height of the board
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new Square[width][height];
	}
	
	/**
	 * Set the terminal boolean value
	 * @param terminal the boolean value
	 */
	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}
	
	/**
	 *Get the terminal boolean value
	 * @return the terminal boolean value
	 */
	public boolean getTerminal() {
		return this.terminal;
	}
	
	/**
	 * Get the game where the board is used
	 * @return the game where the board is used
	 */
	public Game getGame() {
		return this.game;
	}
	
	/**
	 * Set the game object to the game where the board is used
	 * @param game the game where the board is used
	 */
	public void setGame(Game game) {
		this.game = game;
	}
	
	/**
	 * Set the color of the current player which has to play
	 * @param color the color of the player
	 */
	public void setPlayingColor(CoinColor color) {
		this.playingColor = color;
	}
	
	/**
	 * Get the color of the current player which has to play
	 * @return the color of the player
	 */
	public CoinColor getPlayingColor() {
		return this.playingColor;
	}
	
	/**
	 * Get the grid used in the board
	 * @return the grid used
	 */
	public Square[][] getGrid() {
		return this.grid;
	}
	
	/**
	 * Set the GridTable view used to display the grid
	 * @param view the gridtable used
	 */
	public void setView(GridTableFrame view) {
		this.view = view;
	}
	
	/**
	 * Change the color of the current playing player in the view
	 * @param color the color of the player
	 */
	public void setColorView(CoinColor color) {
		this.view.setColor(color);
	}
	
	/**
	 * Get the GridTable view used to display the grid
	 * @return the gridtable
	 */
	public GridTableFrame getView() {
		return this.view;
	}
	
	/**
	 * Display the view of the grid
	 * @param color the color of the current playing player
	 */
	public void displayMap(CoinColor color) {
		if(this.terminal)
			System.out.println(this.toString());
		else {
			view.refresh(grid, color);
			view.getTable().addMouseListener(new clickListener(this));
		}
	}
	
	/**
	 * Get the number of coins of a player
	 * @param color the color of the player
	 * @return the number of coins
	 */
	public int getNbrCoins(CoinColor color) {
		int count = 0;
		for(Square[] lines : grid)
			for(Square square : lines)
				if(square.getColor() == color)
					count++;
		return count;
	}
	
	/**
	 * Get all the possibilites for a player with his color
	 * @param color the color of the player
	 * @return an arrayList of the possibilites
	 */
	public ArrayList<Square> getPossibilities(CoinColor color) {
		ArrayList<Square> possibilites = new ArrayList<Square>();
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(grid[i][j].isFree() && checkAlignment(i, j, color))
					possibilites.add(grid[i][j]);
			}
		}
		return possibilites;
	}
	
	/**
	 * Change a square object in the grid by his x and y position
	 * @param x the x position of the square
	 * @param y the y position of the square
	 * @param square the new square
	 */
	public void setSquare(int x, int y, Square square) {
		this.grid[x][y] = square;
	}
	
	/**
	 * Try to set a coin on the board, checks if the square is free and checks the alignments
	 * @param x the x position of the coin
	 * @param y the y position of the coin
	 * @param color the color of the coin
	 * @return a boolean if the coins is placed
	 */
	public boolean setCoin(int x, int y, CoinColor color) {
		boolean res = false;
		if(this.grid[x][y].isFree() && this.checkAlignment(x, y, color)) {
			this.changeCoin(x,y,color);
			res = true;
		}
		return res;
	}
	
	/**
	 * Place a coin on the board and apply all the changes to other coins in its alignments
	 * @param x the x position of the coin
	 * @param y the y position of the coin
	 * @param color the color of the coin
	 */
	public void changeCoin(int x, int y, CoinColor color) {
		 this.displayMap(color);
		 grid[x][y].setColor(color);
		 for(Square square : this.getChanges(x, y, color)) // For every coins impacted by this new coin
			 square.setColor(color);
	 }
	 
	/**
	 * Get every coins impacted by a new coin in a given alignment, you only have to provide the position and color of the coin and the incrementation of x and y to identify the alignment
	 * @param x the x position of the new coin
 	 * @param y the y position of the new coin
	 * @param incX the incrementation of x to check the alignment
	 * @param incY the incrementation of y to check the alignment
	 * @param color the color of the new coin
	 * @return an ArrayList of all the coins which have to be changed
	 */
	public ArrayList<Square> getLocalChanges(int x, int y, int incX, int incY, CoinColor color) { 
		ArrayList<Square> res = new ArrayList<Square>(); // Notre arrayList qui contient tous les changements
		ArrayList<Square> localChanges = new ArrayList<Square>(); // Notre arrayList qui contient les changements supposés, non définitifs
		boolean emp = false; // Condition de sortie s'il est impossible que d'autres cases soient ajoutées
		int i = x + incX, j = y + incY; // Position de la prochaine case selon notre alignement
		boolean condX, condY; // Les conditions d'arrêt des incrémentations 
		if(incX < 0) condX = i > 0; // En fonction de l'incrémentation, il ne faut pas sortir du tableau en X
		else condX = i < width-1;
		if(incY < 0) condY = j > 0;// En fonction de l'incrémentation, il ne faut pas sortir du tableau en Y
		else condY = j < height-1;
		
		while(condX && condY && !emp) { // Si on ne dépasse pas les limites du tableau et qu'aucune case n'est vide 
			if(grid[i][j].getColor() == color) { // Si il y a une case de notre couleur
				res.addAll(localChanges); // On ajoute a la liste définitive toutes les cases de changements possibles
				localChanges = new ArrayList<Square>(); // On réinitialise notre liste de changements possibles
			} else if(grid[i][j].isUsed()) // Si la case n'est pas de notre couleur
				localChanges.add(grid[i][j]); // On ajoute la case à notre liste de changements possibles
			else // C'est une case vide
				emp = true; // On arrête tout, il ne peut plus y avoir d'autres cases
			i = i + incX; // Incrémentation des coordonnées de notre case
			j = j + incY;
			if(incX < 0) condX = i > 0; // On redéfinit les conditions de sortie en fin de boucle c'est obligatoire vu qu'on les définit nous meme en boolean
			else condX = i < width-1;
			if(incY < 0) condY = j > 0;
			else condY = j < height-1;
		}
		return res;
	}
	
	/**
	 * Gets the changes to apply for all the alignments
	 * @param x the x position of the new coin
	 * @param y the y position of the new coin
	 * @param color the color of the coin
	 * @return an arrayList of all the coins to change
	 */
	public ArrayList<Square> getChanges(int x, int y, CoinColor color) {
		ArrayList<Square> changes = new ArrayList<Square>();
		changes.addAll(this.getLocalChanges(x, y, 1, 0, color)); // Bottom of the case
		changes.addAll(this.getLocalChanges(x, y, -1, 0, color)); // Top of the case
		changes.addAll(this.getLocalChanges(x, y, 0, -1, color)); // Left of the case
		changes.addAll(this.getLocalChanges(x, y, 0, 1, color)); // Rigth of the case
		changes.addAll(this.getLocalChanges(x, y, -1, -1, color)); // Top left diag of the case
		changes.addAll(this.getLocalChanges(x, y, -1, 1, color)); // Top right diag of the case
		changes.addAll(this.getLocalChanges(x, y, 1, -1, color)); // Bottom left diag of the case
		changes.addAll(this.getLocalChanges(x, y, 1, 1, color)); // Bottom right diag of the case
		return changes;
	}
	 
	/**
	 * Get the width of the board
	 * @return the width of the board
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Get the height of the board
	 * @return the height of the board
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Check if a coin can be placed in a given alignment by giving the incrementation of x,y and the position and color of the coin
	 * @param x the x position of the coin
	 * @param y the y position of the coin
	 * @param incX the incrementation of x to have the alignment
	 * @param incY the incrementation of y to have the alignment
	 * @param color the color of the coin
	 * @return a boolean if the coin can be placed or not
	 */
	public boolean globalAlignment(int x, int y, int incX, int incY, CoinColor color) {
		boolean res = false, emp = false; // La boolean emp représente signifie qu'un case vide a été rencontrée, res siginifie qu'il y a un case de couleur donc les cases rencontrées doivent etre changées
		int i = x + incX, j = y + incY; // On récupère les coordonnées de la prochaine case de l'alignement
		boolean condX, condY; // Les conditions d'arrêt des incrémentations 
		if(incX < 0) condX = i > 0; // En fonction de l'incrémentation, il ne faut pas sortir du tableau en X
		else condX = i < width-1;
		if(incY < 0) condY = j > 0;// En fonction de l'incrémentation, il ne faut pas sortir du tableau en Y
		else condY = j < height-1;
		
		if(condX && condY && this.grid[i][j].getColor() != color && this.grid[i][j].isUsed()) { // Si on est dans les limites du tableau, que la prochaine case n'est pas de même couleur et qu'elle est bien utilisée on peut boucler
			while(condX && condY && !res && !emp) { // On boucle pour tout l'alignement
				if(grid[i][j].getColor() == color) { // Si la couleur de la case correspond à une de nos cases
					res = true; // On arrête tout, l'alignement est forcément respecté
				} else if(!grid[i][j].isUsed()) // Une case vide a été rencontrée, or il n'y a pas une de nos case avant
					emp = true; // On arrête tout, l'alignement ne peut pas être respecté
				i = i + incX; // Incrémentation des coordonnées de notre case
				j = j + incY;
				if(incX < 0) condX = i > 0; // On redéfinit les conditions de sortie en fin de boucle c'est obligatoire vu qu'on les définit nous meme en boolean
				else condX = i < width-1;
				if(incY < 0) condY = j > 0;
				else condY = j < height-1;
			}
		}
		return res;
	}
	
	/**
	 * Checks all the alignment to verify if the coin can be placed
	 * @param x the x position of the coin
	 * @param y the y position of the coin
	 * @param color the color of the coin
	 * @return a boolean if the coin can be placed
	 */
	public boolean checkAlignment(int x, int y, CoinColor color) {
		Boolean res = false;
		if(this.checkHAlignment(x, y, color) || this.checkLDAlignment(x, y, color) || this.checkVAlignment(x, y, color) || this.checkWDAlignment(x, y, color))
			res = true;
		return res;
	}
	
	/**
	 * Checks the horizontal alignment
	 * @param x the x position of the coin
	 * @param y the y position of the coin
	 * @param color the color of the coin
	 * @return a boolean if the aligment is respected
	 */
	private boolean checkHAlignment(int x, int y, CoinColor color) {
		return globalAlignment(x, y, 0, 1, color) || globalAlignment(x, y, 0, -1, color);
	}
	
	/**
	 * Checks the left diagonal alignment
	 * @param x the x position of the coin
	 * @param y the y position of the coin
	 * @param color the color of the coin
	 * @return a boolean if the aligment is respected
	 */
	public boolean checkLDAlignment(int x, int y, CoinColor color) {
		return globalAlignment(x, y, -1, 1, color) || globalAlignment(x, y, 1, -1, color);
	}
	
	/**
	 * Checks the right diagonal alignment
	 * @param x the x position of the coin
	 * @param y the y position of the coin
	 * @param color the color of the coin
	 * @return a boolean if the aligment is respected
	 */
	private boolean checkWDAlignment(int x, int y, CoinColor color) {
		return globalAlignment(x, y, -1, -1, color) || globalAlignment(x, y, 1, 1, color);
	}
	
	/**
	 * Checks the vertical alignment
	 * @param x the x position of the coin
	 * @param y the y position of the coin
	 * @param color the color of the coin
	 * @return a boolean if the aligment is respected
	 */
	private boolean checkVAlignment(int x, int y, CoinColor color) {
		return globalAlignment(x, y, 1, 0, color) || globalAlignment(x, y, -1, 0, color);
	}
	
	/**
	 * Returns the representation of the board in a string
	 * @return a string representating the board
	 */
	public String toString() {
		String res = "";
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				res += grid[i][j].getRepresentation();
			}
			res += "\n";
		}
		return res;
	}
}
