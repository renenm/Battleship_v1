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
		System.out.print("\n");
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
		System.out.println("saving in savegame.txt");
		Memory.saveGameNew("savegame.txt", playersBattlefield, player, corvette, destroyer, frigate, submarine);
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
		final int HOWMANYPLAYERS_FINAL = howManyPlayers;
		int round = 0;
		int selection = 0;
		int whichPlayerToAttack = 0;
		int whichShipToAttack = 0;
		int i,j;
		int deadShips = 0; //Counter, um Spieler totzusetzen
		String possibleWinner = "Nobody";
		boolean readyForNextRound = false;
		boolean shipIsChoosen = false;
		boolean playerIsDead = true; //Unglückliche Wortwahl...
		boolean allDestroyerDead = false;
		boolean allFrigatesDead = false;
		boolean allCorvettesDead = false;
		boolean allSubmarinesDead = false;
		while(howManyPlayers > 1) { //Der Counter wird beim Sterben eines Players um einen runtergezählt
			System.out.println("___ROUND " + (round+1) + "___");
			round++;
			for(i = 0 ; i < HOWMANYPLAYERS_FINAL ; i++) {
				
				allDestroyerDead = false;
				allFrigatesDead = false;
				allCorvettesDead = false;
				allSubmarinesDead = false;
				//SHIPSRELOAD
				deadShips = 0;
	        	for(j = 0 ; j < destroyer[i].length ; j++) {
        			if(destroyer[i][j].getShipRespawn() >= 3) {
        				destroyer[i][j].setShipRespawn(3);
        				destroyer[i][j].setReady(true);
        			} else {
        				destroyer[i][j].setShipRespawn( (destroyer[i][j].getShipRespawn()+1) );
        				if(destroyer[i][j].getShipRespawn() >= 3) {
        					destroyer[i][j].setReady(true);
        				}
        			}
        			if(destroyer[i][j].isDead()) {
        				deadShips++;
        			}
        			if(deadShips == destroyer[i].length) {
        				allDestroyerDead = true;
        			}
	        	}
	        	///
	        	deadShips = 0;
	        	for(j = 0 ; j < frigate[i].length ; j++) {
        			if(frigate[i][j].getShipRespawn() >= 2) {
        				frigate[i][j].setShipRespawn(2);
        				frigate[i][j].setReady(true);
        			} else {
        				frigate[i][j].setShipRespawn( (frigate[i][j].getShipRespawn()+1) );
        				if(frigate[i][j].getShipRespawn() >= 2) {
        					frigate[i][j].setReady(true);
        				}
        			}
        			if(frigate[i][j].isDead()) {
        				deadShips++;
        			}
        			if(deadShips == frigate[i].length) {
        				allFrigatesDead = true;
        			}
	        	}
	        	///
	        	deadShips = 0;
	        	for(j = 0 ; j < corvette[i].length ; j++) {
        			if(corvette[i][j].getShipRespawn() >= 1) {
        				corvette[i][j].setShipRespawn(1);
        				corvette[i][j].setReady(true);
        			} else {
        				corvette[i][j].setShipRespawn( (corvette[i][j].getShipRespawn()+1) );
        				if(corvette[i][j].getShipRespawn() >= 1) {
        					corvette[i][j].setReady(true);
        				}
        			}
        			if(corvette[i][j].isDead()) {
        				deadShips++;
        			}
        			if(deadShips == corvette[i].length) {
        				allCorvettesDead = true;
        			}
	        	}
	        	///
	        	deadShips = 0;
	        	for(j = 0 ; j < submarine[i].length ; j++) {
        			if(submarine[i][j].getShipRespawn() >= 1) {
        				submarine[i][j].setShipRespawn(1);
        				submarine[i][j].setReady(true);
        			} else {
        				submarine[i][j].setShipRespawn( ((submarine[i][j].getShipRespawn())+1) );
        				if(submarine[i][j].getShipRespawn() >= 1) {
        					submarine[i][j].setReady(true);
        				}
        			}
        			if(submarine[i][j].isDead()) {
        				deadShips++;
        			}
        			if(deadShips == submarine[i].length) {
        				allSubmarinesDead = true;
        			}
        			
	        	}
	        	
	        	//SHIPSRELOAD ENDE
	        	
	        	if(allSubmarinesDead && allFrigatesDead && allDestroyerDead && allCorvettesDead) {
	        		player[i].setDead(true);
	        		howManyPlayers--;
	        	}
				
				if(player[i].isDead() == false) {
					possibleWinner = player[i].getName();
					readyForNextRound = false;
					playerIsDead = true;
					shipIsChoosen = false;
					while(!readyForNextRound) {
						
						System.out.println("---" + player[i].getName() + "---");

		        		
						System.out.println("Please choose one of the following options:");
						System.out.println("[1] - View battlefields from the enemys (or the own)");
						System.out.println("[2] - View own ships");
						System.out.println("[3] - Attack!");
						System.out.print("Please choose: ");
						selection = readInt()-1;
						switch(selection) { 
					        case 0: //View Battlefields
					        	System.out.println("Which battlefield do you want to see?");
					        	for(j = 0 ; j < HOWMANYPLAYERS_FINAL ; j++) {
					        		System.out.println("[" + (j+1) + "] - Player " + player[j].getName());
					        	}
					        	System.out.print("Please choose: ");
					        	selection = readInt()-1;
					        	System.out.println("Battlefield of " + player[selection].getName());
					        	if (player[i].getPlayerId() == (selection)) {
					        		playersBattlefield[selection].printBattlefield();
					        	} else {
						        	playersBattlefield[selection].printEnemyBattlefield();
						        	//Zeigt das eigene Spielfeld an
					        	}
					            break; 
					        case 1: //View own ships
					        	System.out.println("Destroyer:");
					        	for(j = 0 ; j < destroyer[i].length ; j++) {
						        	if(!destroyer[i][j].isDead()) {
						        		System.out.println("\tDestroyer " + (j+1) + "/" + destroyer[i].length);
						        		System.out.println("\t\tRespawn in: " + destroyer[i][j].getShipRespawn() + "/3");
						        		System.out.println("\t\tShip ready?: " + destroyer[i][j].isReady());
						        		System.out.println("\t\tSize: " + destroyer[i][j].getShipSize());
						        		System.out.println("\t\tTarget radius: " + destroyer[i][j].getShipTargetRadius());
						        		System.out.println("");
						        	} else {
						        		System.out.println("The ship is dead!");
						        	}
					        	}
					        	System.out.println("Frigate:");
					        	for(j = 0 ; j < frigate[i].length ; j++) {
					        		if(!frigate[i][j].isDead()) {
						        		System.out.println("\tFrigate " + (j+1) + "/" + frigate[i].length);
						        		System.out.println("\t\tRespawn in: " + frigate[i][j].getShipRespawn() + "/2");
						        		System.out.println("\t\tShip ready?: " + frigate[i][j].isReady());
						        		System.out.println("\t\tSize: " + frigate[i][j].getShipSize());
						        		System.out.println("\t\tTarget radius: " + frigate[i][j].getShipTargetRadius());
						        		System.out.println("");
					        		} else {
					        			System.out.println("The ship is dead!");
					        		}
					        	}
					        	System.out.println("Corvette:");
					        	for(j = 0 ; j < corvette[i].length ; j++) {
					        		if(!corvette[i][j].isDead()) {
						        		System.out.println("\tCorvette " + (j+1) + "/" + corvette[i].length);
						        		System.out.println("\t\tRespawn in: " + corvette[i][j].getShipRespawn() + "/1");
						        		System.out.println("\t\tShip ready?: " + corvette[i][j].isReady());
						        		System.out.println("\t\tSize: " + corvette[i][j].getShipSize());
						        		System.out.println("\t\tTarget radius: " + corvette[i][j].getShipTargetRadius());
						        		System.out.println("");
					        		} else {
					        			System.out.println("The ship is dead!");
					        		}
					        	}
					        	System.out.println("Submarine:");
					        	for(j = 0 ; j < submarine[i].length ; j++) {
					        		if(!submarine[i][j].isDead()) {
						        		System.out.println("\tSubmarine " + (j+1) + "/" + submarine[i].length);
						        		System.out.println("\t\tRespawn in: " + submarine[i][j].getShipRespawn() + "/1");
						        		System.out.println("\t\tShip ready?: " + submarine[i][j].isReady());
						        		System.out.println("\t\tSize: " + submarine[i][j].getShipSize());
						        		System.out.println("\t\tTarget radius: " + submarine[i][j].getShipTargetRadius());
						        		System.out.println("");
					        		} else {
					        			System.out.println("The ship is dead!");
					        		}
					        	}
					            break; 
					        case 2: //Attack
					        	while(playerIsDead) {
					        		System.out.println("Which player do you want to attack?");
						        	for(j = 0 ; j < HOWMANYPLAYERS_FINAL ; j++) {
						        		System.out.println("\t[" + (j+1) + "] - Player " + player[j].getName() + " | dead: " + player[j].isDead());
						        	}
						        	System.out.print("Please choose: ");
						        	whichPlayerToAttack = readInt()-1;
						        	if(player[whichPlayerToAttack].isDead() == true) {
						        		System.out.println("The player has lost the game. Choose another player.");
						        		playerIsDead = true;
						        	} else {
						        		playerIsDead = false;
						        	}
					        	}
					        	
					        	while(!shipIsChoosen) {
						        	System.out.print("With which ship do you want to attack?");
						        	System.out.println("\t[1] - Destroyer");
						        	System.out.println("\t[2] - Frigate");
						        	System.out.println("\t[3] - Corvette");
						        	System.out.println("\t[4] - Submarine");
						        	System.out.print("Please choose: ");
						        	selection = readInt()-1;
						        
						        	switch(selection) { 
								        case 0: 
								            for(j = 0 ; j < destroyer[i].length ; j++) {
								            	System.out.println("[" + (j+1) + "] - Destroyer " + (j+1) + "/" + destroyer[i].length);
								            }
								            System.out.print("Please choose: ");
								            whichShipToAttack = readInt()-1;
								            if(destroyer[i][whichShipToAttack].isDead() == false && destroyer[i][whichShipToAttack].isReady() == true) {
								            	System.out.print("X: ");
								            	xCord = readInt();
								            	System.out.print("Y: ");
								            	yCord = readInt();
								            	playersBattlefield[whichPlayerToAttack].shootShip(destroyer[i][whichShipToAttack], xCord, yCord);
								            	readyForNextRound = true;
								            	shipIsChoosen = true;
								            } else {
								            	System.out.println("Your ship is dead or has to respawn. Choose another ship");
								            }
								            break; 
								        case 1: 
								        	for(j = 0 ; j < frigate[i].length ; j++) {
								        		System.out.println("[" + (j+1) + "] - Frigate " + (j+1) + "/" + frigate[i].length);
								            } 
								        	System.out.print("Please choose: ");
								            whichShipToAttack = readInt()-1;
								            if(frigate[i][whichShipToAttack].isDead() == false && frigate[i][whichShipToAttack].isReady() == true) {
								            	System.out.print("X: ");
								            	xCord = readInt();
								            	System.out.print("Y: ");
								            	yCord = readInt();
								            	playersBattlefield[whichPlayerToAttack].shootShip(frigate[i][whichShipToAttack], xCord, yCord);
								            	readyForNextRound = true;
								            	shipIsChoosen = true;
								            } else {
								            	System.out.println("Your ship is dead or has to respawn. Choose another ship");
								            }
								            break; 
								        case 2: 
								        	for(j = 0 ; j < corvette[i].length ; j++) {
								        		System.out.println("[" + (j+1) + "] - Corvette " + (j+1) + "/" + corvette[i].length);
								            }
								        	System.out.print("Please choose: ");
								            whichShipToAttack = readInt()-1;
								            if(corvette[i][whichShipToAttack].isDead() == false && corvette[i][whichShipToAttack].isReady() == true) {
								            	System.out.print("X: ");
								            	xCord = readInt();
								            	System.out.print("Y: ");
								            	yCord = readInt();
								            	playersBattlefield[whichPlayerToAttack].shootShip(corvette[i][whichShipToAttack], xCord, yCord);
								            	readyForNextRound = true;
								            	shipIsChoosen = true;
								            } else {
								            	System.out.println("Your ship is dead or has to respawn. Choose another ship");
								            }
								            break; 
								        case 3: 
								        	for(j = 0 ; j < submarine[i].length ; j++) {
								        		System.out.println("[" + (j+1) + "] - Submarine " + (j+1) + "/" + submarine[i].length);
								            } 
								        	System.out.print("Please choose: ");
								            whichShipToAttack = readInt()-1;
								            if(submarine[i][whichShipToAttack].isDead() == false && submarine[i][whichShipToAttack].isReady() == true) {
								            	System.out.print("X: ");
								            	xCord = readInt();
								            	System.out.print("Y: ");
								            	yCord = readInt();
								            	playersBattlefield[whichPlayerToAttack].shootShip(submarine[i][whichShipToAttack], xCord, yCord);
								            	readyForNextRound = true;
								            	shipIsChoosen = true;
								            } else {
								            	System.out.println("Your ship is dead or has to respawn. Choose another ship");
								            }
								            break; 
								        default: 
								            System.out.println("Glitcharea - Make yourself scarce!"); 
								    }	   
					        	}
					        	
					        	break;
					        default: 
					            System.out.println("Glitcharea - Make yourself scarce!"); 
						} 
					}
				} else { //Aktueller Player ist tot, wird also übersprungen
					//readyForNextRound = true;
				}
				System.out.println("saving in savegame.txt");
				Memory.saveGameNew("savegame.txt", playersBattlefield, player, corvette, destroyer, frigate, submarine);
				System.out.println("saving complete");
			} //Eine Runde
		}
		System.out.println("Game is over");
		System.out.println("\tWinner: " + possibleWinner);
		
	}
}