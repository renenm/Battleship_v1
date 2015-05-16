import java.util.* ;

public class Main {
	public static void main(String args[]) throws Exception {
		//Begrüßungstext
		helloMessage();
		
		//Spielmodusauswahl
		gameMode();
	}
	
	public static void helloMessage() {
		System.out.print("/// BATTLESHIP v0.1 \\\\\\");
		System.out.print("\n-----------------------\n\n");
	}
	
	public static void gameMode() throws Exception {
		System.out.print("New Game? [yes / no]: ");
		Scanner scan = new Scanner(System.in);
		String mode = scan.next();
		
		if ("yes".equals(mode)) {
			//Manoever-Vorbereitung
			vorbereitung();
		} else if("no".equals(mode)) {
			//SaveGame laden
			Memory.control();
		} else {
			System.out.println("\tPlease type in 'yes' or 'no'");
			gameMode();
		}
		scan.close();
	}
	
	//Abfangen aller nicht positiven Int's beim Scanner
	public static int readInt() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner( System.in);
		int num = -1;
		try {    
			num = scan.nextInt();
			if(!(num >= 0)) { 
				System.out.println("Error: Please enter a positive number:" );
				num = readInt();
			}	
		} catch ( InputMismatchException ex) { 
				System.out.println("Error: Please enter a number:" );
				num = readInt();
			} 
		return num;
	}	
		
	public static void vorbereitung() throws Exception {
		System.out.println("");
		System.out.print("Manoeuvre preparation");
		System.out.print("\n-----------------------\n\n");
		
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
		fieldsize = readInt();
			while(!(fieldsize >= 2)) {
				System.out.print("Please insert a minimum of 2: ");
				fieldsize = readInt();
			}
		System.out.println("How many...");
		System.out.print("\tDestroyer(5 fields): ");
			howManyDestroyer = readInt();
		System.out.print("\tFrigates(4 fields): ");
			howManyFrigates = readInt();
		System.out.print("\tCorvettes(3 fields): ");
			howManyCorvettes = readInt();
		System.out.print("\tSubmarines(2 fields): ");
			howManySubmarines = readInt();
		System.out.print("How many players(2-6)?: ");
			howManyPlayers = readInt();
		
		//Nummer der Spieler zwischen 2 und 6
		while(!(howManyPlayers < 7 && howManyPlayers > 1)) {
			System.out.print("Please insert a number from 2 to 6: ");
			howManyPlayers = readInt();
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
		
		Memory.saveGame("savegame.txt", fieldsize,howManyPlayers, howManyCorvettes, howManyDestroyer, howManyFrigates, howManySubmarines, 
				playersBattlefield, player, corvette, destroyer, frigate, submarine);
		System.out.println("saving complete");

		
		/* Ab hier werden die Schiffe in die jeweiligen Spielfelder plaziert.
		 * Jedes Objektarray von playersBattlefield braucht zwei Informationen als Arrayübergabe:
		 * [Feldgröße][SpielderID]
		 * 
		 * Die äußere Schleife ist für einen Spieler. Die inneren für die jeweiligen Schiffe.
		 * Wenn die Schleife zB 2x durchläuft (i = 2), dann gilt das für den dritten Spieler (0,1,2)!!!!
		 */
		for(int i = 0 ; i < howManyPlayers ; i++) { //i = SpielderID, J = SchiffID
			System.out.print(player[i].getName() + ", please enter the coordinates of your ships:");
			playersBattlefield[i] = new Battlefield(fieldsize, i);
			
			///DESTROYER
			for(int j = 0 ; j < howManyDestroyer ; j++) {
				System.out.println("\n\tDestroyer #" + (j+1) + "/" + howManyDestroyer);
				System.out.print("\t\tX: ");
				xCord = readInt();
				System.out.print("\t\tY: ");
				yCord = readInt();
				isHorizontal = Battlefield.isHorizontal();
				destroyer[i][j] = new Destroyer(i, xCord, yCord, isHorizontal);
				
				if(playersBattlefield[i].hasPlace(destroyer[i][j]) == true ) {
					if(playersBattlefield[i].hasNeighbours(destroyer[i][j]) == false) {
						playersBattlefield[i].placeShip(destroyer[i][j]);
					} else {
						j--;
						System.out.println("Error: The ships must have at least one field between them.");
					}
				} else {
					j--;
					System.out.println("Error: Choose coordinates INSIDE the battlefield.");
				}
				playersBattlefield[i].printBattlefield();
			}
			
			///FRIGATE
			for(int j = 0 ; j < howManyFrigates ; j++) {
				System.out.println("\n\tFrigate #" + (j+1) + "/" + howManyFrigates);
				System.out.print("\t\tX: ");
				xCord = readInt();
				System.out.print("\t\tY: ");
				yCord = readInt();
				isHorizontal = Battlefield.isHorizontal();
				frigate[i][j] = new Frigate(i, xCord, yCord, isHorizontal);

				if(playersBattlefield[i].hasPlace(frigate[i][j]) == true ) {
					if(playersBattlefield[i].hasNeighbours(frigate[i][j]) == false) {
						playersBattlefield[i].placeShip(frigate[i][j]);
					} else {
						j--;
						System.out.println("Error: The ships must have at least one field between them.");
					}
				} else {
					j--;
					System.out.println("Error: Choose coordinates INSIDE the battlefield.");
				}
				playersBattlefield[i].printBattlefield();
			}
			
			///CORVETTE
			for(int j = 0 ; j < howManyCorvettes ; j++) {
				System.out.println("\n\tCorvette #" + (j+1) + "/" + howManyCorvettes);
				System.out.print("\t\tX: ");
				xCord = readInt();
				System.out.print("\t\tY: ");
				yCord = readInt();
				isHorizontal = Battlefield.isHorizontal(); //new
				corvette[i][j] = new Corvette(i, xCord, yCord, isHorizontal);
				
				if(playersBattlefield[i].hasPlace(corvette[i][j]) == true ) {
					if(playersBattlefield[i].hasNeighbours(corvette[i][j]) == false) {
						playersBattlefield[i].placeShip(corvette[i][j]);
					} else {
						j--;
						System.out.println("Error: The ships must have at least one field between them.");
					}
				} else {
					j--;
					System.out.println("Error: Choose coordinates INSIDE the battlefield.");
				}
				playersBattlefield[i].printBattlefield();
			}
			
			///SUBMARINE
			for(int j = 0 ; j < howManySubmarines ; j++) {
				System.out.println("\n\tSubmarine #" + (j+1) + "/" + howManySubmarines);
				System.out.print("\t\tX: ");
				xCord = readInt();
				System.out.print("\t\tY: ");
				yCord = readInt();
				isHorizontal = Battlefield.isHorizontal();
				submarine[i][j] = new Submarine(i, xCord, yCord, isHorizontal);

				if(playersBattlefield[i].hasPlace(submarine[i][j]) == true ) {
					if(playersBattlefield[i].hasNeighbours(submarine[i][j]) == false) {
						playersBattlefield[i].placeShip(submarine[i][j]);
					} else {
						j--;
						System.out.println("Error: The ships must have at least one field between them.");
					}
				} else {
					j--;
					System.out.println("Error: Choose coordinates INSIDE the battlefield.");
				}
				playersBattlefield[i].printBattlefield();
			}
		}
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//Genereller Spielablauf
		int PlayerToAttack;
		int whichKindOfShipToAttack; //Das eigene Schiff
		int whichShipIdToAttack; //Das eigene Schiff
		int i,j;
		for(i = 0 ; i < howManyPlayers ; i++) {
			System.out.println("Player " + player[i].getName());
			
			System.out.println("Which player do you want to attack?");
			for(j = 0 ; j < howManyPlayers ; j++) {
				System.out.println(player[j].getName() + "[" + (j+1) + "]");
			}
			PlayerToAttack = readInt();
			
			System.out.println("With which ship do you want to attack?");
			
			System.out.println("Destroyer [1]");
			System.out.println("Frigate [2]");
			System.out.println("Corvette [3]");
			System.out.println("Submarine [4]");
			whichKindOfShipToAttack = readInt()-1;
			
			switch(whichKindOfShipToAttack){ 
	        case 0: 
	            for(j = 0 ; j < destroyer[i].length ; j++) {
	            	System.out.println("Destroyer " + (j+1) + "/" + destroyer[i].length + ": [" + (j+1) + "]");
	            }
	            break; 
	        case 1: 
	        	for(j = 0 ; j < frigate[i].length ; j++) {
	            	System.out.println("Frigate " + (j+1) + "/" + frigate[i].length + ": [" + (j+1) + "]");
	            } 
	            break; 
	        case 2: 
	        	for(j = 0 ; j < corvette[i].length ; j++) {
	            	System.out.println("Corvette " + (j+1) + "/" + corvette[i].length + ": [" + (j+1) + "]");
	            }
	            break; 
	        case 3: 
	        	for(j = 0 ; j < submarine[i].length ; j++) {
	            	System.out.println("Submarine " + (j+1) + "/" + submarine[i].length + ": [" + (j+1) + "]");
	            } 
	            break; 
	        default: 
	            System.out.println("ERR"); 
	        } 
			whichShipIdToAttack = readInt()-1;
			
			System.out.print("\t\tX: ");
			xCord = readInt();
			System.out.print("\t\tY: ");
			yCord = readInt();
			if(playersBattlefield[PlayerToAttack].isShipThere(xCord, yCord)) {
				switch(whichKindOfShipToAttack){ 
		        case 0: 
					playersBattlefield[PlayerToAttack].shootShip(destroyer[i][whichShipIdToAttack], xCord, yCord);
		            break; 
		        case 1: 
					playersBattlefield[PlayerToAttack].shootShip(frigate[i][whichShipIdToAttack], xCord, yCord); 
		            break; 
		        case 2: 
					playersBattlefield[PlayerToAttack].shootShip(corvette[i][whichShipIdToAttack], xCord, yCord);
		            break; 
		        case 3: 
					playersBattlefield[PlayerToAttack].shootShip(submarine[i][whichShipIdToAttack], xCord, yCord);
		            break; 
		        default: 
		            System.out.println("ERR"); 
		        } 
			} else {
				System.out.println("There is no ship.");
			}
			
		}
		System.out.println("FERTIG");
		
	}
}