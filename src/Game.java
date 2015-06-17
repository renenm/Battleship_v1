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
		System.out.print("/// BATTLESHIP v0.4Final \\\\\\");
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
	 * Methode um ein altes Spiel zu laden, danach wird ein Spiel mit den geladenen Werten erstellt
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
		Destroyer[][] destroyer = SaveLoad.destroyerLoad(howManyPlayers, howManyDestroyer);
		Frigate[][] frigate = SaveLoad.frigateLoad(howManyPlayers, howManyFrigates);
		Corvette[][] corvette = SaveLoad.corvetteLoad(howManyPlayers, howManyCorvettes);
		Submarine[][] submarine = SaveLoad.submarineLoad(howManyPlayers, howManySubmarines);
		
		game(round, fieldsize, howManyPlayers, howManyDestroyer, howManyFrigates, howManyCorvettes, howManySubmarines, playersBattlefield, player, destroyer, frigate, corvette, submarine, values);
	}
	
	/**
	 * Methode um ein Spiel vorzubereiten und die benötigten Werte einzulesen
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
		Corvette[][] corvette;
		Destroyer[][] destroyer;
		Frigate[][] frigate;
		Submarine[][] submarine;
		
		//Einlesen der Variablen
		System.out.print("Battlesize [square]: ");
		fieldsize = ScannerExceptions.readInt(2,9999);
		System.out.println("\nHow many...");
		System.out.print("\tDestroyer(5 fields): ");
			howManyDestroyer = ScannerExceptions.readInt(0, 5);
		System.out.print("\tFrigates(4 fields): ");
			howManyFrigates = ScannerExceptions.readInt(0, 5);
		System.out.print("\tCorvettes(3 fields): ");
			howManyCorvettes = ScannerExceptions.readInt(0, 5);
		System.out.print("\tSubmarines(2 fields): ");
			howManySubmarines = ScannerExceptions.readInt(0, 5);
		if(howManyCorvettes + howManyDestroyer + howManyFrigates + howManySubmarines == 0) {
			System.out.println("\nYou cannot play without ships. So please choose at least one type of ship.");
			preparation();
		} else {
			System.out.print("\nHow many players(2-6)?: ");
				howManyPlayers = ScannerExceptions.readInt(2, 6); 
			
			
			System.out.print("\nDo you want to add an artificial inteligenze? [yes/no] ");
			isKiThere = scan.next();
			if(isKiThere.equals("yes")) {
				System.out.print("How many artifical inteligenzes do you want to add? ");
				howManyAis = ScannerExceptions.readInt(1, howManyPlayers);
			}
			//Arrays den Speicher zuweisen
			player = new Player[howManyPlayers];
			playersBattlefield = new Battlefield[howManyPlayers];
			destroyer = new Destroyer[howManyPlayers][howManyDestroyer];
			frigate = new Frigate[howManyPlayers][howManyFrigates];
			corvette = new Corvette[howManyPlayers][howManyCorvettes];
			submarine = new Submarine[howManyPlayers][howManySubmarines];
					
			//Jeder Spieler bekommt ein Spielfeld zugewiesen		
			for(int i = 0; i < howManyPlayers - howManyAis; i++) {
				System.out.print("\n\tName of player #" + (i+1) + ": ");
				player[i] = new Player(scan.next(), i, false);
				playersBattlefield[i] = new Battlefield(fieldsize, i);
			}
			
			for (int i = howManyPlayers - howManyAis; i < howManyPlayers; i++) {
				System.out.print("\n\tName of player #" + (i+1) + "(is AI): ");
				player[i] = new Player(scan.next(), i, true);
				playersBattlefield[i] = new Battlefield(fieldsize, i);
			}
			System.out.println("\n-----------------------\n");	
			
			/* Ab hier werden die Schiffe in die jeweiligen Spielfelder plaziert.
			 * Jedes Objektarray von playersBattlefield braucht zwei Informationen als Arrayübergabe:
			 * [Feldgröße][SpielderID]
			 * 
			 * Die äußere Schleife ist für einen Spieler. Die inneren für die jeweiligen Schiffe.
			 * Wenn die Schleife zB 2x durchläuft (i = 2), dann gilt das für den dritten Spieler (0,1,2)!!!!
			 */
			for(int i = 0 ; i < howManyPlayers ; i++) { //i = SpielderID, J = SchiffID
				playersBattlefield[i].printBattlefield();
				System.out.println("");
				System.out.print(player[i].getName() + ", please enter the coordinates of your ships:");
				player[i].playerPlaceShips(i, fieldsize, howManyDestroyer, howManyCorvettes, howManyFrigates, howManySubmarines, destroyer, frigate, corvette, submarine, playersBattlefield);
				System.out.println("\n----------------------- NEXT PLAYER\n");
			}
			
			System.out.println("\nGet ready...\n");
			
			int[] values = new int[7];
			values[0] = fieldsize;
			values[1] = howManyPlayers;
			values[2] = howManyDestroyer;
			values[3] = howManyFrigates;
			values[4] = howManyCorvettes;
			values[5] = howManySubmarines;
			values[6] = round;
	
			SaveLoad.save(playersBattlefield);
			SaveLoad.savePlayer(player);
			SaveLoad.saveDestroyer(destroyer);
			SaveLoad.saveFrigate(frigate);
			SaveLoad.saveCorvette(corvette);
			SaveLoad.saveSubmarine(submarine);
			SaveLoad.saveValues(values);
			
			//Hier wird der Spielablauf aufgerufen
			game(round, fieldsize, howManyPlayers, howManyDestroyer, howManyFrigates, howManyCorvettes, howManySubmarines, playersBattlefield, player, destroyer, frigate, corvette, submarine, values);
		}
	}
	
	/**
	 * Methode die den eigentlichen Spielablauf koordiniert
	 * @param round Anzahl der gespielten Runden
	 * @param fieldsize Spielfeldgröße
	 * @param howManyPlayers Anzahl der Spieler
	 * @param howManyDestroyer Anzahl der Zerstörer
	 * @param howManyFrigates Anzahl der Frigatten
	 * @param howManyCorvettes Anzahl der Korvetten
	 * @param howManySubmarines Anzahl der U-Boote
	 * @param playersBattlefield Array, in dem die Spielfelder der Player gespeichert sind
	 * @param player Array, in dem die Player gespeichert sind
	 * @param destroyer Array, in dem die Zerstörer gespeichert sind
	 * @param frigate Array, in dem die Frigatten gespeichert sind
	 * @param corvette Array, in dem die Korvetten gespeichert sind
	 * @param submarine Array, in dem die U-Boote gespeichert sind
	 * @param values Array, in dem die wichtigen Spielwerte gespeichert werden, um diese nach dem Laden zu reproduzieren
	 */
	public static void game(int round, int fieldsize, int howManyPlayers, int howManyDestroyer, int howManyFrigates, int howManyCorvettes, int howManySubmarines, Battlefield[] playersBattlefield, Player[] player, Destroyer[][] destroyer, Frigate[][] frigate, Corvette[][] corvette, Submarine[][] submarine, int[] values) {
		String possibleWinner = "Nobody"; 
		
		while(howManyPlayers > 1) {
			System.out.println("___ROUND " + (round+1) + "___");
			round ++;
			for(int i = 0; i < howManyPlayers; i++) {
				player[i].playerDead(i, destroyer, frigate, corvette, submarine);
				Ship.shipReload(destroyer, frigate, corvette, submarine, i);
				
				if(player[i].isDeadPlayer() == false) {
					possibleWinner = player[i].getName();
					player[i].round(fieldsize, i, howManyPlayers, player, playersBattlefield, destroyer, frigate, corvette, submarine);
				} else {
					howManyPlayers --;
					System.out.println(player[i].getName() + " is dead.");
				}
			}
			values[1] = howManyPlayers;
			values[6] = round;
			SaveLoad.save(playersBattlefield);
			SaveLoad.savePlayer(player);
			SaveLoad.saveDestroyer(destroyer);
			SaveLoad.saveFrigate(frigate);
			SaveLoad.saveCorvette(corvette);
			SaveLoad.saveSubmarine(submarine);
			SaveLoad.saveValues(values);
		}
		System.out.println("Game is Over!");
		System.out.println("Winner: " + possibleWinner);
	}
}
