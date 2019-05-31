import java.util.Scanner;

import reversi.*;

public class GameLauncher {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Nom du premier joueur: ");
		String name1 = scan.nextLine();
		System.out.println("Nom du deuxième joueur: ");
		String name2 = scan.nextLine();
		ReversiN reversiN = new ReversiN( "config.conf", name1, name2);
	}
}
