import java.util.Scanner;

import reversi.*;

public class GameLauncher {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);	
		System.out.println("Nom du premier joueur: ");
		String name1 = scan.nextLine();
		System.out.println("Nom du deuxième joueur: ");
		String name2 = scan.nextLine();
		System.out.println("Voulez-vous utiliser une configuration ? Oui ou non");
		
		ReversiN reversiN = null;
		String response = "";
		while(!response.equalsIgnoreCase("OUI") && !response.equalsIgnoreCase("NON"))
			 response = scan.nextLine();
		if(response.equalsIgnoreCase("OUI")) {
			System.out.println("Nom de la configuration: ");
			response = scan.nextLine();
			reversiN = new ReversiN(response, name1, name2);
		} else
			reversiN = new ReversiN(name1, name2);
	}
}
