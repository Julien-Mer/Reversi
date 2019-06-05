package reversi;

import java.io.*;
import java.util.Scanner;

import view.*;

public class ReversiN {
	/**
	 * The size of the board
	 */
	private int size;
	private int MINSIZE;
	
	/**
	 * The number of coins in the game
	 */
	private int nbCoin;
	
	/**
	 * The mode of the game
	 */
	private Mode mode;
	
	/**
	 * The Game used in reversi
	 */
	private Game gameplay;
	
	/**
	 * The config used by the game
	 */
	String config;
	
	/**
	 * Creates the game with a configuration
	 * @param fileName the fileName of the configuration
	 * @param playerName1 the name of the first player
	 * @param playerName2 the name of the second player
	 */
	public ReversiN(String fileName, String playerName1, String playerName2) {
		try {
			this.configure(fileName);
		} catch (Exception ex) {
			System.out.println("Impossible de lire la configuration: " + ex.toString());
			this.newGame(playerName1, playerName2);
		}
		this.initGame(playerName1, playerName2);
	}
	
	/**
	 * Creates a new game without configuration
	 * @param playerName1 the name of the first player
	 * @param playerName2 the name of the second player
	 */
	public ReversiN(String playerName1, String playerName2) {
		this.newGame(playerName1, playerName2);
		this.initGame(playerName1, playerName2);
	}
	
	/**
	 * Initializes the game
	 * @param playerName1 the name of the first player
	 * @param playerName2 the name of the second player
	 */
	public void initGame(String playerName1, String playerName2) {
		this.gameplay = new Game(playerName1, playerName2, this.mode, this.size);
		GridTableFrame view = new GridTableFrame(this.gameplay.getBoard().getGrid());
		this.gameplay.getBoard().setView(view);
		
		System.out.println("Voulez vous voir la description du jeu ? Oui ou non");
		Scanner scan = new Scanner(System.in);
		String response = "";
		while(!response.equalsIgnoreCase("OUI") && !response.equalsIgnoreCase("NON"))
			 response = scan.nextLine();
		if(response.equalsIgnoreCase("OUI")) 
			System.out.println(this.gameplay.description());
		
		System.out.println("Jouer sur terminal ? Oui ou non");
		response = "";
		while(!response.equalsIgnoreCase("OUI") && !response.equalsIgnoreCase("NON"))
			 response = scan.nextLine();
		if(response.equalsIgnoreCase("NON")) {
			this.gameplay.getBoard().setTerminal(false);
			view.showIt();
		} else
			this.gameplay.getBoard().setTerminal(true);
		this.gameplay.setCoins(this.nbCoin);
		this.gameplay.start();
	}
	
	/**
	 * Gets the mode of the game by an int
	 * @param mode the mode of the game represented by an int
	 * @return the mode of the game
	 */
	public Mode getMode(int mode) {
		Mode res = null;
		switch(mode) {
			case 0:
				res = Mode.AA;
				break;
			case 1:
				res = Mode.HA;
				break;
			case 2:
				res = Mode.HH;
				break;
			default:
				res = Mode.HA;
		}
		return res;
	}
	
	/**
	 * Creates a new game
	 * @param playerName1 the name of the first player
	 * @param playerName2 the name of the second player
	 */
	public void newGame(String playerName1, String playerName2) {
		Scanner scan = new Scanner(System.in);
		System.out.println("--- Nouvelle configuration ---");
		int size = -1;
		while(size <= 0) {
			try {
				System.out.println("Taille du tableau:");
				size = scan.nextInt();
			} catch(Exception ex) { }
		}
		int nbCoin = -1;
		while(nbCoin <= 0) {
			try {
				System.out.println("Nombre de pièces:");
				nbCoin = scan.nextInt();
			} catch(Exception ex) { }
		}
		int mode = -1;
		while(mode < 0 || mode > 2) { // Si le mode de jeu est bien valide
			try {
				System.out.println("Mode de jeu:");
				System.out.println("0 - Ordinateur vs Ordinateur");
				System.out.println("1 - Joueur vs Ordinateur");
				System.out.println("2 - Joueur vs Joueur");
				mode = scan.nextInt();
			} catch(Exception ex) { }
		}
		this.configure(size, size, nbCoin, mode);
		System.out.println("Voulez-vous sauvegarder cette configuration ? Oui ou non");
		String response = "";
		while(!response.equalsIgnoreCase("OUI") && !response.equalsIgnoreCase("NON"))
			 response = scan.nextLine();
		if(response.equalsIgnoreCase("OUI")) {
			System.out.println("Nom de cette configuration: ");
			response = scan.nextLine();
			try {
				this.saveConfiguration(response);
			} catch(Exception ex) {
				System.out.println("Erreur lors de l'écriture de la configuration: " + ex.toString());
			}
		}
	}
	
	/**
	 * Create the configuration string 
	 * @param minSize the minimum size
	 * @param size the size of the board
	 * @param nbCoin the number of coins
	 * @param mode the mode of the game representated by an int
	 */
	public void configure(int minSize, int size, int nbCoin, int mode) {
		this.MINSIZE = minSize;
		this.size = size;
		this.nbCoin = nbCoin;
		this.mode = this.getMode(mode);
		this.config = minSize + ";" + size + ";" + nbCoin + ";" + mode;
	}
	
	/**
	 * Configures the game with a configuration file
	 * @param fileName the name of the configuration
	 * @throws Exception if the configuration is not working
	 */
	public void configure(String fileName) throws Exception {
		InputStream  in = new FileInputStream(fileName);
		String config = "";
		BufferedReader buffer = new BufferedReader(new InputStreamReader(in)); 
		String line = buffer.readLine(); 
		while(line != null){ 
			config += line + "\n";
			line = buffer.readLine(); 
		}
		config = config.trim();
		String[] elements = config.split(";");
		this.MINSIZE = Integer.valueOf(elements[0]); 
		this.size = Integer.valueOf(elements[1]);
		this.nbCoin = Integer.valueOf(elements[2]);
		this.mode = this.getMode(Integer.valueOf(elements[3]));
		this.config = config;
		in.close();
		if(this.MINSIZE <= 0 || this.size <= 0 || this.nbCoin <= 0)
			throw new Exception ("Configuration corrompue");
	}
	
	/**
	 * Saves the configuration in a file
	 * @param fileName the name of the file
	 * @throws Exception if the file wasn't created
	 */
	public void saveConfiguration(String fileName) throws Exception {
		OutputStream out = new FileOutputStream(fileName);
		BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out));
		buffer.write(this.config);
		buffer.flush();
		out.close();
	}
	
	/**
	 * Print the configuration String of the game
	 */
	public void printConfiguration() {
		System.out.println(this.config);
	}
	
}
