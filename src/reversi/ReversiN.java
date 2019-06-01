package reversi;

import java.io.*;
import java.util.Scanner;

import view.*;

public class ReversiN {
	private int size;
	private int nbCoin;
	private int MINSIZE = 4;
	private Mode mode;
	private Game gameplay;
	String config;
	
	public ReversiN(String fileName, String playerName1, String playerName2) {
		try {
			this.configure(fileName);
			this.gameplay = new Game(playerName1, playerName2, mode);
			GridTableFrame view = new GridTableFrame(this.gameplay.getBoard().getGrid());
			this.gameplay.getBoard().setView(view);
			System.out.println("Jouer sur terminal ? Oui ou non");
			Scanner scan = new Scanner(System.in);
			String response = "";
			while(!response.equalsIgnoreCase("OUI") && !response.equalsIgnoreCase("NON"))
				 response = scan.nextLine();
			if(response.equalsIgnoreCase("NON")) {
				this.gameplay.getBoard().setTerminal(false);
				view.showIt();
			} else
				this.gameplay.getBoard().setTerminal(true);
			this.gameplay.start();
		} catch (Exception ex) {
			System.out.println("Erreur de lecture de configuration " + ex.toString());
		}
	}
	
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
		int mode = Integer.valueOf(elements[3]);
		if(mode == 0)
			this.mode = Mode.AA;
		else if(mode == 1)
			this.mode = Mode.HA;
		else if(mode == 2)
			this.mode = Mode.HH;
		this.config = config;
	}
	
	public void printConfiguration() {
		System.out.println(this.config);
	}
	
}
