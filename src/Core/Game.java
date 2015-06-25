package Core;
import java.util.Scanner;

import CustomExceptions.ScannerExceptions;

/**
 * Klasse die den Spielablauf regelt
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class Game {
	
	/**
	 * Abfrage, ob ein neues Spiel gespielt werden soll, oder ein altes geladen werden soll
	 */
	@SuppressWarnings("resource")
	public static void gameMode() {
		System.out.print("/// BATTLESHIP ZweiteFINAL \\\\\\");
		System.out.print("\n-----------------------\n");
		System.out.print("New Game? [yes / no]: ");
		Scanner scan = new Scanner(System.in);
		String mode = scan.next();
		
		if ("yes".equals(mode)) {
			SaveLoad.addFolder();
			preparation();
		} else if("no".equals(mode)) {
			loadGame();
		} else {
			System.out.println("\tPlease type in 'yes' or 'no'");
			gameMode();
		}
	}
	
	/**
	 * Methode um ein altes Spiel zu laden,
	 * danach wird ein Spiel mit den geladenen Werten erstellt.
	 * @see Game.game
	 */
	public static void loadGame() {
		int[] values = SaveLoad.valuesLoad();
		int fieldsize = values[0];
		int howManyPlayers = values[1];
		int howManyDestroyer = values[2];
		int howManyFrigates = values[3];
		int howManyCorvettes = values[4];
		int howManySubmarines = values[5];
		int round = values[6];
		
		Battlefield[] playersBattlefield = SaveLoad.playersBattlefieldLoad(howManyPlayers);
		Player[] player = SaveLoad.playerLoad(howManyPlayers);
		Ship[][][] ship = SaveLoad.shipsLoad(howManyPlayers, howManyDestroyer, howManyFrigates, howManyCorvettes, howManySubmarines);
				
		game(round, fieldsize, howManyPlayers, howManyDestroyer, howManyFrigates, howManyCorvettes, howManySubmarines, playersBattlefield, player, ship, values);
	}
	
	/**
	 * Methode um ein Spiel vorzubereiten und die benötigten Werte einzulesen
	 * Am Ende wird ein Spiel mit den eingelesenen Werten gestartet
	 * @see GAme.game
	 */
	@SuppressWarnings("resource")
	public static void preparation() {
		System.out.print("\n\n");
		System.out.print("Manoeuvre preparation");
		System.out.print("\n-----------------------\n");
		
		//Variablen initialiseren
		int howManyPlayers;
		int howManyAis = 0;
		int fieldsize;
		int howManyCorvettes;
		int howManyDestroyer;
		int howManyFrigates;
		int howManySubmarines;
		int round = 0;
		String isKiThere;
		Scanner scan = new Scanner(System.in);
		
		//Objektarrays erstellen
		Battlefield[] playersBattlefield;
		Player[] player;
		//Alle Schiffe werden Ship gespeichert, wobei die erste Dimension den Schiffstyp identifiziert
		//die zweite Dimension den Spieler und die dritte das Schiff
		Ship[][][] ship = new Ship[4][][];
		
		//Einlesen der Variablen
		System.out.print("Battlesize [square]: ");
		fieldsize = ScannerExceptions.readInt(5,9999);
		System.out.println("\nHow many...");
		System.out.print("\tDestroyer(5 fields): ");
			howManyDestroyer = ScannerExceptions.readInt(0, 5);
		System.out.print("\tFrigates(4 fields): ");
			howManyFrigates = ScannerExceptions.readInt(0, 5);
		System.out.print("\tCorvettes(3 fields): ");
			howManyCorvettes = ScannerExceptions.readInt(0, 5);
		System.out.print("\tSubmarines(2 fields): ");
			howManySubmarines = ScannerExceptions.readInt(0, 5);
			
		//Wenn von jedem Schiffstyp keiner ausgewählt wurde, startet preparation erneut
		if(howManyCorvettes + howManyDestroyer + howManyFrigates + howManySubmarines == 0) {
			System.out.println("\nYou cannot play without ships. So please choose at least one type of ship.");
			preparation();
		} else {
			//Einlesen der Spieler
			System.out.print("\nHow many players(2-6)?: ");
				howManyPlayers = ScannerExceptions.readInt(2, 6); 
			
			//Abfrage ob AIs hinzugefügt werden sollen
			System.out.print("\nDo you want to add an artificial inteligenze? [yes/no] ");
			isKiThere = scan.next();
			if(isKiThere.equals("yes")) {
				System.out.print("How many artifical inteligenzes do you want to add? ");
				howManyAis = ScannerExceptions.readInt(1, howManyPlayers);
			}
			
			//Arrays den Speicher zuweisen
			player = new Player[howManyPlayers];
			playersBattlefield = new Battlefield[howManyPlayers];	
			ship[0] = new Destroyer[howManyPlayers][howManyDestroyer];
			ship[1] = new Frigate[howManyPlayers][howManyFrigates];
			ship[2] = new Corvette[howManyPlayers][howManyCorvettes];
			ship[3] = new Submarine[howManyPlayers][howManySubmarines];
			
			//Jeder Spieler bekommt ein Spielfeld zugewiesen		
			for(int i = 0; i < howManyPlayers - howManyAis; i++) {
				System.out.print("\n\tName of player #" + (i+1) + ": ");
				player[i] = new Player(scan.next(), i, false);
				playersBattlefield[i] = new Battlefield(fieldsize, i);
			}
			
			//Jede AI bekommt ein Spielfeld zugewiesen
			for (int i = howManyPlayers - howManyAis; i < howManyPlayers; i++) {
				System.out.print("\n\tName of player #" + (i+1) + "(is AI): ");
				player[i] = new Player(scan.next(), i, true);
				playersBattlefield[i] = new Battlefield(fieldsize, i);
			}
			System.out.println("\n-----------------------\n");	
			
			/* Ab hier werden die Schiffe in die jeweiligen Spielfelder platziert.
			 * Das Spielfeld im Array playersBattlefield an der Stelle i gehört dem Spieler im Array player an der Stelle i.
			 * Z.B.: playersBattlefield[1] ist das Sielfled von player[1]. Somit wird die Zugehörigkeit sichergestellt
			 */
			for(int i = 0 ; i < howManyPlayers ; i++) { //i = SpielderID, J = SchiffID
				playersBattlefield[i].printBattlefield();
				System.out.println("");
				System.out.print(player[i].getName() + ", please enter the coordinates of your ships:");
				player[i].playerPlaceShips(i, fieldsize, ship, playersBattlefield);
				System.out.println("\n----------------------- NEXT PLAYER\n");
			}
			
			System.out.println("\nGet ready...\n");
			
			//Alle eingegebenen Werte und OnjektArrays werden gespeichert
			int[] values = new int[7];
			values[0] = fieldsize;
			values[1] = howManyPlayers;
			values[2] = howManyDestroyer;
			values[3] = howManyFrigates;
			values[4] = howManyCorvettes;
			values[5] = howManySubmarines;
			values[6] = round;
	
			SaveLoad.saveBattlefield(playersBattlefield);
			SaveLoad.savePlayer(player);
			SaveLoad.saveShips(ship);
			SaveLoad.saveValues(values);
			
			//Spielablauf wird aufgerufen
			game(round, fieldsize, howManyPlayers, howManyDestroyer, howManyFrigates, howManyCorvettes, howManySubmarines, playersBattlefield, player, ship, values);
		}
	}
	
	/**
	 * Hier wird der Spielablauf koordniniert
	 * @param round Rundenzahl
	 * @param fieldsize Größe des Spielfeldes
	 * @param howManyPlayers Anzahl der Spieler
	 * @param howManyDestroyer Anzahl der Zerstörer
	 * @param howManyFrigates Anzahl der Frigatten
	 * @param howManyCorvettes Anzahl der Korvetten
	 * @param howManySubmarines Anzahl der U-Boote
	 * @param playersBattlefield Array mit den Spielfelder
	 * @param player Array mit den Spielern
	 * @param ship Array aller Schiffe
	 * @param values Array mit den wichtigsten int-Werten, für speichern/laden
	 */
	public static void game(int round, int fieldsize, int howManyPlayers, int howManyDestroyer, int howManyFrigates, int howManyCorvettes, int howManySubmarines, Battlefield[] playersBattlefield, Player[] player, Ship[][][] ship , int[] values) {
		String possibleWinner = "Nobody";
		
		//Die Schleife wird so lange ausgeführt, bis nur noch ein Spieler übrig ist
		while(howManyPlayers > 1) {
			System.out.println("___ROUND " + (round+1) + "___");
			round ++;
			
			for(int i = 0; i < player.length; i++) {
				//Wenn ein Spieler bereits tot ist, wird dieser übersrpungen
				if(player[i].isPlayerDead()) {
					System.out.println(player[i].getName() + " is already dead.");
				} else {
					//Wenn nicht wird eine Runde mit ihm durchgenagen
					possibleWinner = player[i].getName();
					howManyPlayers = player[i].round(fieldsize, i, howManyPlayers, player, playersBattlefield, ship);					
				}
			}
			
			//Die Reload der Schiffe wird korrigiert
			Ship.shipReload(ship);
			
			//Alle korrigierten Werte werden nach einer Runde abgespeichert
			values[1] = howManyPlayers;
			values[6] = round;
			SaveLoad.saveBattlefield(playersBattlefield);
			SaveLoad.savePlayer(player);
			SaveLoad.saveShips(ship);
			SaveLoad.saveValues(values);
		}
		System.out.println("Game is Over!");
		System.out.println("Winner: " + possibleWinner);
	}
}
