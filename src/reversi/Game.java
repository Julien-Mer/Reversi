package reversi;

import java.util.*;

public class Game implements IGame {
	
	/**
	 * The players of the game
	 */
	private Player player1;
	private Player player2;
	
	/**
	 * The limit of coins in the game
	 */
	private int coins;
	
	/**
	 * The timeLine of the players
	 */
	private List<Player> timeLine = new LinkedList<Player>(); // Inutile dans ce cadre là mais si le projet doit évoluer, on pourra avoir plusieurs joueurs et par exemple ajouter un joueur en cours de partie etc
	
	/**
	 * The board used in the game
	 */
	private Board board;
	
	/**
	 * The description with the rules of the game
	 */
	public String description() {
		String res = "/***** Déroulement du jeu *****/\n" +
				"Les blancs commencent toujours la partie. Puis l'autre joueur joue à tour de rôle, chacun étant tenu de capturer des pions adverses lors de son mouvement.\n" +
				"Si un joueur ne peut pas capturer de pion(s) adverse(s), il est forcé de passer son tour. Si aucun des deux joueurs ne peut jouer, ou si la grille ne comporte plus de case vide, la partie s'arrête.\n" +
				"Le gagnant en fin de partie est celui qui possède le plus de pions.\n\n" +
				"/***** Capture *****/\n" +
				"La capture de pions survient lorsqu'un joueur place un de ses pions à l'extrémité d'un alignement de pions adverses contigus et dont l'autre extrémité est déjà occupée par un de ses propres pions.\n" +
				"Les alignements considérés peuvent être une colonne, une ligne, ou une diagonale. Si le pion nouvellement placé vient fermer plusieurs alignements, il capture tous les pions adverses des lignes ainsi fermées.\n" + 
				"La capture se traduit par le retournement des pions capturés. Ces retournements n'entraînent pas d'effet de capture en cascade : seul le pion nouvellement posé est pris en compte.\n\n" + 
				"/***** Modes possibles *****/\n" +
				"Ordinateur vs Ordinateur\n" + 
				"Joueur vs Ordinateur\n" + 
				"Joueur vs Joueur\n\n" +
				"-----> Bon jeu sur Reversi ! (`v`) <-----\n";
		return res;
	}
	
	/**
	 * Get the board used in the game
	 * @return the board used in the game
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Boolean if the previous player was forced to pass
	 */
	Boolean passed = false;
	
	/**
	 * Play method of the game, changes the timeLine
	 */
	public void play() {
		Player p = timeLine.remove(0); // On prend le premier joueur qui doit jouer
		timeLine.add(p); // On le remet à la fin
		board.setPlayingColor(p.getCoinColor()); // On change la couleur du joueur qui doit jouer
		if(p.board.getPossibilities(p.getCoinColor()).size() > 0 && this.coins > 0) { // S'il a des possibilités et qu'il reste des coins
			this.coins--;
			p.play(); // On appelle le joueur pour jouer
			passed = false;
		} else {
			if(passed) { // Le joueur précédent ne peut pas jouer non plus
				System.out.println("Plus personne ne peut jouer...");
				endOfGame(); // Fin du jeu
			} else {
				System.out.println("Le joueur " + p.name + " doit passer son tour...");
				this.board.displayMap(p.getCoinColor()); // On change la couleur du projet joueur
				passed = true;
				this.play();
			}
		}
	}
	
	/**
	 * Starts a new game
	 */
	public void start() {
		System.out.println("--- Début de la partie ---");
		this.board.displayMap(CoinColor.BLACK);
		this.board.setGame(this);
		this.play();
	}
	
	/**
	 * Ends the game
	 */
	public void endOfGame() {
		System.out.println("--- Fin de la partie ---");
		Player winner = null;
		int scoreMax = 0;
		for(Player p : timeLine) {
			int score = p.board.getNbrCoins(p.getCoinColor());
			System.out.println(p.name + ": " + score);
			if(score >= scoreMax) {
				scoreMax = score;
				winner = p;
			}
		}
		System.out.println("Victoire de " + winner.name + " avec un score de " + scoreMax);
	}

	/**
	 * Change the value of coins
	 * @param coins the number of coins
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	/**
	 * Constructor of the game
	 * @param playerName1 the name of the first player
	 * @param playerName2 the name of the second player
	 * @param mode the mode of the game
	 * @param size the size of the board
	 */
	public Game(String playerName1, String playerName2, Mode mode, int size) {
		this.board = new Board(size, size);
		System.out.println("--- Initialisation du tableau ---");
		this.initalizeBoard();
		if(mode == Mode.AA)
			this.player1 = new AutoPlayer(playerName1, this.board);
		else
			this.player1 = new HumanPlayer(playerName1, this.board);
		if(mode == Mode.HH)
			this.player2 = new HumanPlayer(playerName2, this.board);
		else
			this.player2 = new AutoPlayer(playerName2, this.board);
		this.player1.setCoinColor(CoinColor.WHITE);
		this.player2.setCoinColor(CoinColor.BLACK);
		this.timeLine.add(this.player1);
		this.timeLine.add(this.player2);
	}
	
	/**
	 * Initializes the board, places players coins, sets the free and forbidden squares
	 */
	private void initalizeBoard() {
		for(int i = 0; i < this.board.getWidth(); i++) {
			for(int j = 0; j < this.board.getHeight(); j++) {
				CoinColor color;
				if(j > (this.board.getWidth()/2 + i) || (i > this.board.getHeight()/2 && j < i - this.board.getWidth()/2))
					color = CoinColor.NONE;
				else if(j == (int)(this.board.getWidth()-1)/2 && i == (int)((this.board.getHeight()-1)/2) || j == (int)((this.board.getWidth()-1)/2)+1 && i == (int)((this.board.getWidth()-1)/2)+1)
					color = CoinColor.WHITE;
				else if(j == (int)((this.board.getWidth()-1)/2) && i == (int)((this.board.getHeight()-1)/2)+1 || j == (int)((this.board.getWidth()-1)/2)+1 && i == (int)((this.board.getWidth()-1)/2))
					color = CoinColor.BLACK;
				else
					color = CoinColor.FREE;
				this.board.setSquare(i, j, new Square(i, j, color));
				
			}
		}
	}
}
