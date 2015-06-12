import java.util.Scanner;

import CustomExceptions.ScannerExceptions;


public class Game {
	
	public static void helloMessage() {
		System.out.print("/// BATTLESHIP v0.1 ERSTE FINAL \\\\\\");
		System.out.print("\n-----------------------\n");
	}
	
	public static void gameMode() throws Exception {
		System.out.print("New Game? [yes / no]: ");
		Scanner scan = new Scanner(System.in);
		String mode = scan.next();
		
		if ("yes".equals(mode)) {
			//Manoever-Vorbereitung
			SaveLoad.addFolder();
			preparation();
		} else if("no".equals(mode)) {
			//SaveGame laden
			int[] values = SaveLoad.valuesLoad();
			int fieldsize = values[0];
			int howManyPlayers = values[1];
			int howManyDestroyer = values[2];
			int howManyFrigates = values[3];
			int howManyCorvettes = values[4];
			int howManySubmarines = values[5];
			
			Battlefield[] playersBattlefield = SaveLoad.playersBattlefieldLoad(howManyPlayers);
			Player[] player = SaveLoad.playerLoad(howManyPlayers);
			Destroyer[][] destroyer = SaveLoad.destroyerLoad(howManyPlayers, howManyDestroyer);
			Frigate[][] frigate = SaveLoad.frigateLoad(howManyPlayers, howManyFrigates);
			Corvette[][] corvette = SaveLoad.corvetteLoad(howManyPlayers, howManyCorvettes);
			Submarine[][] submarine = SaveLoad.submarineLoad(howManyPlayers, howManySubmarines);
			
			game(fieldsize, howManyPlayers, howManyDestroyer, howManyFrigates, howManyCorvettes, howManySubmarines, playersBattlefield, player, destroyer, frigate, corvette, submarine);
		} else {
			System.out.println("\tPlease type in 'yes' or 'no'");
			gameMode();
		}
	}
	
	public static void preparation() throws Exception {
		System.out.print("\n\n");
		System.out.print("Manoeuvre preparation");
		System.out.print("\n-----------------------\n");
		
		//Textstrings
		String err0 = "The ships must have at least one field between them.";
		String err1 = "Choose coordinates inside the battlefield.";
		
		//Variablen initialiseren
		int howManyPlayers;
		int fieldsize;
		int howManyCorvettes;
		int howManyDestroyer;
		int howManyFrigates;
		int howManySubmarines;
		int xCord;
		int yCord;
		boolean isHorizontal = false;
		
		//Objektarrays erstellen
		Battlefield[] playersBattlefield; //DIE EINZELNEN SPIELFELDER!
		Player[] player;
		Corvette[][] corvette;
		Destroyer[][] destroyer;
		Frigate[][] frigate;
		Submarine[][] submarine;
		
		//Einlesen der Variablen
		System.out.print("Battlesize [square]: ");
		fieldsize = ScannerExceptions.readInt();
			while(!(fieldsize >= 2)) {
				System.out.print("Please insert a minimum of 2: ");
				fieldsize = ScannerExceptions.readInt();
			}
		System.out.println("\nHow many...");
		System.out.print("\tDestroyer(5 fields): ");
			howManyDestroyer = ScannerExceptions.readInt(); //1,10 muss da noch als Parameter später rein
		System.out.print("\tFrigates(4 fields): ");
			howManyFrigates = ScannerExceptions.readInt();
		System.out.print("\tCorvettes(3 fields): ");
			howManyCorvettes = ScannerExceptions.readInt();
		System.out.print("\tSubmarines(2 fields): ");
			howManySubmarines = ScannerExceptions.readInt();
		System.out.print("\nHow many players(2-6)?: ");
			howManyPlayers = ScannerExceptions.readInt();
		
		//Nummer der Spieler zwischen 2 und 6
		while(!(howManyPlayers < 7 && howManyPlayers > 1)) {
			System.out.print("\tPlease insert a number from 2 to 6: ");
			howManyPlayers = ScannerExceptions.readInt();
		}
		
		//Arrays den Speicher zuweisen
		player = new Player[howManyPlayers];
		playersBattlefield = new Battlefield[howManyPlayers];
		destroyer = new Destroyer[howManyPlayers][howManyDestroyer];
		frigate = new Frigate[howManyPlayers][howManyFrigates];
		corvette = new Corvette[howManyPlayers][howManyCorvettes];
		submarine = new Submarine[howManyPlayers][howManySubmarines];
				
		//Jeder Spieler bekommt ein Spielfeld zugewiesen
		@SuppressWarnings("resource")
		Scanner scan = new Scanner( System.in  );
		
		for(int i = 0 ; i < howManyPlayers ; i++) {
			System.out.print("\tName of Player #" + (i+1) + ": ");
			player[i] = new Player(scan.next(), i);
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
			playersBattlefield[i] = new Battlefield(fieldsize, i);
			
			///DESTROYER
			for(int j = 0 ; j < howManyDestroyer ; j++) {
				System.out.println("\n\tDestroyer #" + (j+1) + "/" + howManyDestroyer);
				System.out.print("\t\tX: ");
				xCord = ScannerExceptions.readInt();
				System.out.print("\t\tY: ");
				yCord = ScannerExceptions.readInt();
				isHorizontal = Battlefield.isHorizontal();
				System.out.print("\n");
				destroyer[i][j] = new Destroyer(i, xCord, yCord, isHorizontal, fieldsize);
				
				if(playersBattlefield[i].hasPlace(destroyer[i][j]) == true ) {
					if(playersBattlefield[i].hasNeighbours(destroyer[i][j]) == false) {
						playersBattlefield[i].placeShip(destroyer[i][j]);
					} else {
						j--;
						System.out.println("\t\t" + err0);
					}
				} else {
					j--;
					System.out.println("\t\t" + err1);
				}
				playersBattlefield[i].printBattlefield();
			}

			
			///FRIGATE
			for(int j = 0 ; j < howManyFrigates ; j++) {
				System.out.println("\n\tFrigate #" + (j+1) + "/" + howManyFrigates);
				System.out.print("\t\tX: ");
				xCord = ScannerExceptions.readInt();
				System.out.print("\t\tY: ");
				yCord = ScannerExceptions.readInt();
				isHorizontal = Battlefield.isHorizontal();
				System.out.print("\n");
				frigate[i][j] = new Frigate(i, xCord, yCord, isHorizontal, fieldsize);

				if(playersBattlefield[i].hasPlace(frigate[i][j]) == true ) {
					if(playersBattlefield[i].hasNeighbours(frigate[i][j]) == false) {
						playersBattlefield[i].placeShip(frigate[i][j]);
					} else {
						j--;
						System.out.println("\t\t" + err0);
					}
				} else {
					j--;
					System.out.println("\t\t" + err1);
				}
				playersBattlefield[i].printBattlefield();
			}
			
			///CORVETTE
			for(int j = 0 ; j < howManyCorvettes ; j++) {
				System.out.println("\n\tCorvette #" + (j+1) + "/" + howManyCorvettes);
				System.out.print("\t\tX: ");
				xCord = ScannerExceptions.readInt();
				System.out.print("\t\tY: ");
				yCord = ScannerExceptions.readInt();
				isHorizontal = Battlefield.isHorizontal();
				System.out.print("\n");
				corvette[i][j] = new Corvette(i, xCord, yCord, isHorizontal, fieldsize);
				
				if(playersBattlefield[i].hasPlace(corvette[i][j]) == true ) {
					if(playersBattlefield[i].hasNeighbours(corvette[i][j]) == false) {
						playersBattlefield[i].placeShip(corvette[i][j]);
					} else {
						j--;
						System.out.println("\t\t" + err0);
					}
				} else {
					j--;
					System.out.println("\t\t" + err1);
				}
				playersBattlefield[i].printBattlefield();
			}
			
			///SUBMARINE
			for(int j = 0 ; j < howManySubmarines ; j++) {
				System.out.println("\n\tSubmarine #" + (j+1) + "/" + howManySubmarines);
				System.out.print("\t\tX: ");
				xCord = ScannerExceptions.readInt();
				System.out.print("\t\tY: ");
				yCord = ScannerExceptions.readInt();
				isHorizontal = Battlefield.isHorizontal();
				System.out.print("\n");
				submarine[i][j] = new Submarine(i, xCord, yCord, isHorizontal, fieldsize);

				if(playersBattlefield[i].hasPlace(submarine[i][j]) == true ) {
					if(playersBattlefield[i].hasNeighbours(submarine[i][j]) == false) {
						playersBattlefield[i].placeShip(submarine[i][j]);
					} else {
						j--;
						System.out.println("\t\t" + err0);
					}
				} else {
					j--;
					System.out.println(err1);
				}
				playersBattlefield[i].printBattlefield();
			}
			System.out.println("\n----------------------- NEXT PLAYER\n");
		}
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nGet ready...\n");
		
		//Hier wird der Spielablauf aufgerufen
		game(fieldsize, howManyPlayers, howManyDestroyer, howManyFrigates, howManyCorvettes, howManySubmarines, playersBattlefield, player, destroyer, frigate, corvette, submarine);
	}
	
	
	public static void game(int fieldsize, int howManyPlayers, int howManyDestroyer, int howManyFrigates, int howManyCorvettes, int howManySubmarines, Battlefield[] playersBattlefield, Player[] player, Destroyer[][] destroyer, Frigate[][] frigate, Corvette[][] corvette, Submarine[][] submarine) throws Exception {
		String possibleWinner = "Nobody"; 
		int round = 0;
		
		//Array ist wichtig für das Speichern. 
		int[] values = new int[6];
		values[0] = fieldsize;
		values[1] = howManyPlayers;
		values[2] = howManyDestroyer;
		values[3] = howManyFrigates;
		values[4] = howManyCorvettes;
		values[5] = howManySubmarines;
		
		while(howManyPlayers > 1) {
			System.out.println("___ROUND " + (round+1) + "___");
			round ++;
			for(int i = 0; i < howManyPlayers; i++) {
				Player.playerDead(destroyer, frigate, corvette, submarine, player);
				Ship.shipReload(destroyer, frigate, corvette, submarine, i);
				
				if(player[i].isDead() == false) {
					possibleWinner = player[i].getName();
					player[i].round(fieldsize, i, player, playersBattlefield, destroyer, frigate, corvette, submarine);
					
					
					
					SaveLoad.save(playersBattlefield);
					SaveLoad.savePlayer(player);
					SaveLoad.saveDestroyer(destroyer);
					SaveLoad.saveFrigate(frigate);
					SaveLoad.saveCorvette(corvette);
					SaveLoad.saveSubmarine(submarine);
					SaveLoad.saveValues(values);

				} else {
					howManyPlayers --;
					System.out.println(player[i].getName() + " is dead.");
				}
			}
		}
		System.out.println("Game is Over!");
		System.out.println("Winner: " + possibleWinner);
	}
}
