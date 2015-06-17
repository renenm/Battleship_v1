import java.io.Serializable;
import java.util.Scanner;

import CustomExceptions.ScannerExceptions;


public class Player implements Serializable{
	
	/**
	 * Die Klasse Player wird genutzt um einen Player zu erzeugen. Ebenfalls werden hier die Schiffe gesetzt, überprüft ob der Player tot ist und eine Runde durchgegangen.
	 * @author Max Kück, Rene Neumann, Justus Cöster
	 */
	
	private static final long serialVersionUID = 8801887131747152046L;
	private String name;
	private int playerId = 0;
	private boolean isKi = false;
	private boolean isDeadPlayer = false;
	int deadShips = 0;
	boolean allDestroyerDead = false;
	boolean allFrigatesDead = false;
	boolean allCorvettesDead = false;
	boolean allSubmarinesDead = false;
	
	public Player() {}
	
	public Player(String name, int playerId, boolean isKi) {
		this.name = name;
		this.playerId = playerId;
		this.isDeadPlayer = false;
		this.isKi = isKi;
	}
	
	/**
	 * Die Methode überprüft ob ein Spieler tot ist
	 * @param i Parameter um den Player im Array zu identifizieren
	 * @param destroyer ist das Objektrray, in dem die Zerstörer gespeichert wurden
	 * @param frigate ist das Objektarray, in dem die Frigaten gespeichert wurden
	 * @param corvette ist das Objektarray, in dem die Korvetten gespeichert wurden
	 * @param submarine ist das Objektarray, in dem die U-Boote gespeichert wurden
	 */
	public void playerDead(int i, Destroyer[][] destroyer, Frigate[][] frigate, Corvette[][] corvette, Submarine[][] submarine) {
		
		if(destroyer[i].length == 0) {
			allDestroyerDead = true;
		}						
		for (int j = 0; j < destroyer[i].length; j++) {
			if(destroyer[i][j].isDead() == true) {
				deadShips ++;
			}
			if(deadShips == destroyer[i].length) {
				allDestroyerDead = true;
			}
		}
		
		deadShips = 0;
		if(frigate[i].length == 0) {
			allFrigatesDead = true;
		}
		for (int k = 0; k < frigate[i].length; k++) {
			if(frigate[i][k].isDead() == true) {
				deadShips++;
			}
			if(deadShips == frigate[i].length) {
				allFrigatesDead = true;
			}
		}
		
		deadShips = 0;
		if(corvette[i].length == 0) {
			allCorvettesDead = true;
		}
		for (int k = 0; k < corvette[i].length; k++) {
			if(corvette[i][k].isDead() == true) {
				deadShips++;
			}
			if(deadShips == corvette[i].length) {
				allCorvettesDead = true;
			}
		}
		
		deadShips = 0;
		if(submarine[i].length == 0) {
			allSubmarinesDead = true;
		}
		for (int k = 0; k < submarine[i].length; k++) {
			if(submarine[i][k].isDead() == true) {
				deadShips++;
			}
			if(deadShips == submarine[i].length) {
				allSubmarinesDead = true;
			}
		}
		
		if(allDestroyerDead == true & allFrigatesDead == true & allCorvettesDead == true & allSubmarinesDead == true) {
			setDeadPlayer(true);
		}
	}
	
	/**
	 * Methode um die Schiffe pro Player im Array zu setzen, im unteren Teil befindet sich der Code für die AI
	 * @param i Parameter um den Player im Array zu identifizieren
	 * @param fieldsize is die Größe des Spielfeldes
	 * @param howManyDestroyer Anzahl der Zerstörer
	 * @param howManyCorvettes Anzahl der korvetten
	 * @param howManyFrigates Anzahl der Frigatten
	 * @param howManySubmarines Anzahl der U-Boote
	 * @param destroyer mehrdimensionales Array, in dem die Zerstörer gespeichert sind
	 * @param frigate mehrdimensionales Array, in dem die Frigatten gespeichert sind
	 * @param corvette mehrdimensionales Array, in dem die Korvetten gespeichert sind
	 * @param submarine mehrdimensionales Array, in dem die U-Boote gespeichert sind
	 * @param playersBattlefield Array, in dem die Spielfelder der Player gespeichert sind
	 */
	public void playerPlaceShips(int i, int fieldsize, int howManyDestroyer, int howManyCorvettes, int howManyFrigates, int howManySubmarines, Destroyer[][] destroyer, Frigate[][] frigate, Corvette[][] corvette, Submarine[][] submarine, Battlefield[] playersBattlefield) {
		int orientation;
		int xCord;
		int yCord; 
		int shipId = 1;
		boolean isHorizontal;
		
		///DESTROYER
		for(int j = 0 ; j < howManyDestroyer ; j++) {
			if(isKi() == false) {
				System.out.println("\n\tDestroyer #" + (j+1) + "/" + howManyDestroyer);
				System.out.print("\t\tX: ");
				xCord = ScannerExceptions.readInt();
				System.out.print("\t\tY: ");
				yCord = ScannerExceptions.readInt();
				isHorizontal = Battlefield.isHorizontal();
				System.out.print("\n");
			} else {
				orientation = (int) (Math.random() * 2);
				if(orientation == 0) {
					isHorizontal = true;
				} else {
					isHorizontal = false;
				}
				xCord = (int) (Math.random() * fieldsize) + 1;
				yCord = (int) (Math.random() * fieldsize) + 1;
			}
			destroyer[i][j] = new Destroyer(shipId, i, xCord, yCord, isHorizontal, fieldsize);
			shipId ++;
			if(playersBattlefield[i].hasPlace(destroyer[i][j]) == true ) {
				if(playersBattlefield[i].hasNeighbours(destroyer[i][j]) == false) {
					playersBattlefield[i].placeShip(destroyer[i][j]);
				} else {
					j--;
					System.out.println("\t\tThe ships must have at least one field between them.");
				}
			} else {
				j--;
				System.out.println("\t\tChoose coordinates inside the battlefield.");
			}
			playersBattlefield[i].printBattlefield();
		}

		
		///FRIGATE
		for(int j = 0 ; j < howManyFrigates ; j++) {
			if(isKi() == false) {
				System.out.println("\n\tFrigate #" + (j+1) + "/" + howManyFrigates);
				System.out.print("\t\tX: ");
				xCord = ScannerExceptions.readInt();
				System.out.print("\t\tY: ");
				yCord = ScannerExceptions.readInt();
				isHorizontal = Battlefield.isHorizontal();
				System.out.print("\n");
			} else {
				orientation = (int) (Math.random() * 2);
				if(orientation == 0) {
					isHorizontal = true;
				} else {
					isHorizontal = false;
				}
				xCord = (int) (Math.random() * fieldsize) + 1;
				yCord = (int) (Math.random() * fieldsize) + 1;
			}
			frigate[i][j] = new Frigate(shipId, i, xCord, yCord, isHorizontal, fieldsize);
			shipId ++;
			if(playersBattlefield[i].hasPlace(frigate[i][j]) == true ) {
				if(playersBattlefield[i].hasNeighbours(frigate[i][j]) == false) {
					playersBattlefield[i].placeShip(frigate[i][j]);
				} else {
					j--;
					System.out.println("\t\tThe ships must have at least one field between them.");
				}
			} else {
				j--;
				System.out.println("\t\tChoose coordinates inside the battlefield.");
			}
			playersBattlefield[i].printBattlefield();
		}
		
		///CORVETTE
		for(int j = 0 ; j < howManyCorvettes ; j++) {
			if(isKi() == false) {
				System.out.println("\n\tCorvette #" + (j+1) + "/" + howManyCorvettes);
				System.out.print("\t\tX: ");
				xCord = ScannerExceptions.readInt();
				System.out.print("\t\tY: ");
				yCord = ScannerExceptions.readInt();
				isHorizontal = Battlefield.isHorizontal();
				System.out.print("\n");
			} else {
				orientation = (int) (Math.random() * 2);
				if(orientation == 0) {
					isHorizontal = true;
				} else {
					isHorizontal = false;
				}
				xCord = (int) (Math.random() * fieldsize) + 1;
				yCord = (int) (Math.random() * fieldsize) + 1;
			}
			corvette[i][j] = new Corvette(shipId, i, xCord, yCord, isHorizontal, fieldsize);
			shipId ++;
			if(playersBattlefield[i].hasPlace(corvette[i][j]) == true ) {
				if(playersBattlefield[i].hasNeighbours(corvette[i][j]) == false) {
					playersBattlefield[i].placeShip(corvette[i][j]);
				} else {
					j--;
					System.out.println("\t\tThe ships must have at least one field between them.");
				}
			} else {
				j--;
				System.out.println("\t\tChoose coordinates inside the battlefield.");
			}
			playersBattlefield[i].printBattlefield();
		}
		
		///SUBMARINE
		for(int j = 0 ; j < howManySubmarines ; j++) {
			if(isKi() == false) {
				System.out.println("\n\tSubmarine #" + (j+1) + "/" + howManySubmarines);
				System.out.print("\t\tX: ");
				xCord = ScannerExceptions.readInt();
				System.out.print("\t\tY: ");
				yCord = ScannerExceptions.readInt();
				isHorizontal = Battlefield.isHorizontal();
				System.out.print("\n");
			} else {
				orientation = (int) (Math.random() * 2);
				if(orientation == 0) {
					isHorizontal = true;
				} else {
					isHorizontal = false;
				}
				xCord = (int) (Math.random() * fieldsize) + 1;
				yCord = (int) (Math.random() * fieldsize) + 1;
			}
			submarine[i][j] = new Submarine(shipId, i, xCord, yCord, isHorizontal, fieldsize);
			shipId ++; 
			if(playersBattlefield[i].hasPlace(submarine[i][j]) == true ) {
				if(playersBattlefield[i].hasNeighbours(submarine[i][j]) == false) {
					playersBattlefield[i].placeShip(submarine[i][j]);
				} else {
					j--;
					System.out.println("\t\tThe ships must have at least one field between them.");
				}
			} else {
				j--;
				System.out.println("Choose coordinates inside the battlefield.");
			}
			playersBattlefield[i].printBattlefield();
		}
	}
	
	/**
	 * Methoden um den aktuellen Player im Array eine Runde spielen zu lassen. Im unteren Teil befindet sich der Code für die AI
	 * @param fieldsize Spielfeldgröße der Spielfelder
	 * @param i Parameter um den aktuellen Player zu identifizieren
	 * @param howManyPlayers wieviele Spieler es ingesamt gibt
	 * @param player Array, in dem alle Spieler gespeichert sind
	 * @param playersBattlefield Array, in dem die jeweiligen Spielfelder gespeichert sind
	 * @param destroyer Array, in dem die Zerstörer gespeichert sind
	 * @param frigate Array, in dem die Frigatten gespeichert sind
	 * @param corvette Array, in dem die Korvetten gespeichert sind
	 * @param submarine Array, in dem die U-Boote gespeichert sind
	 */
	@SuppressWarnings("resource")
	public void round(int fieldsize, int i, int howManyPlayers, Player[] player, Battlefield[] playersBattlefield, Destroyer[][] destroyer, Frigate[][] frigate, Corvette[][] corvette, Submarine[][] submarine) {
		final int howManyPlayersFinal = howManyPlayers;
		int selection;
		int j;
		int whichTypeToAttack = 0;
		int whichPlayerToAttack = 0;
		int whichShipToAttack = 0;
		int xCord;
		int yCord;
		boolean playerDead = true;
		boolean shipIsChoosen = false;
		boolean readyForNextRound = false;
		Scanner scan = new Scanner(System.in);
		String skip;
		
		if(isKi() == false) {
			while(!readyForNextRound) {
				System.out.println("\n\n---" + getName() + "---");
				System.out.println("Please choose one of the following options:");
				System.out.println("[1] - View battlefields from the enemys (or the own)");
				System.out.println("[2] - View own ships");
				System.out.println("[3] - Attack!");
				System.out.println("[4] - Skip the current round");
				System.out.print("Please choose: ");
				selection = ScannerExceptions.readInt(1, 4) -1;
					
				switch(selection) {
					case 0:
						System.out.println("Which battlefield do you want to see?");
			        	for(j = 0 ; j < player.length ; j++) {
			        		System.out.println("[" + (j+1) + "] - Player " + player[j].getName());
			        	}
			        	System.out.print("Please choose: ");
			        	selection = ScannerExceptions.readInt(1, player.length)-1;
			        	System.out.println("Battlefield of " + player[selection].getName());
			        	
			        	playersBattlefield[selection].printEnemyBattlefield(); 	
			        break;
					case 1:
						System.out.println("Destroyer:");
			        	for(j = 0 ; j < destroyer[i].length ; j++) {
				        	if(!destroyer[i][j].isDead()) {
				        		System.out.println("\tDestroyer " + (j+1) + "/" + destroyer[i].length);
				        		System.out.println("\t\tRespawn in: " + destroyer[i][j].getShipRespawn() + "/3");
				        		System.out.println("\t\tShip ready?: " + destroyer[i][j].isReady());
				        		System.out.println("\t\tSize: " + destroyer[i][j].getShipSize());
				        		System.out.println("\t\tTarget radius: " + destroyer[i][j].getShipTargetRadius());
				        		System.out.println("\t\tShipId: " + destroyer[i][j].getShipId());
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
				        		System.out.println("\t\tShipId: " + frigate[i][j].getShipId());
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
				        		System.out.println("\t\tShipId: " + corvette[i][j].getShipId());
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
				        		System.out.println("\t\tShipId: " + submarine[i][j].getShipId());
				        		System.out.println("");
			        		} else {
			        			System.out.println("The ship is dead!");
			        		}
			        	}
			        break;
					case 2:
						while(playerDead) {
							System.out.println("Which player do you want to attack?");
							for (j = 0; j < player.length; j++) {
								System.out.println("\t[" + (j+1) + "] - Player " + player[j].getName()+ " | dead: " + player[j].isDeadPlayer());
							}
							System.out.print("Please choose: ");
							whichPlayerToAttack = ScannerExceptions.readInt(1, player.length) - 1;
							if(whichPlayerToAttack == getPlayerId()) {
								System.out.println("\nYou cannot attack yourself.\n");
							} else {
								if(player[whichPlayerToAttack].isDeadPlayer == true) {
									System.out.println("\tThe player has lost the game. Choose another player.");
									playerDead = true;
								} else {
								playerDead = false;
								}
							}
						}
						
						while(!shipIsChoosen) {
							System.out.print("With which ship do you want to attack?\n");
				        	System.out.println("\t[1] - Destroyer");
				        	System.out.println("\t[2] - Frigate");
				        	System.out.println("\t[3] - Corvette");
				        	System.out.println("\t[4] - Submarine");
				        	System.out.print("Please choose: ");
				        	selection = ScannerExceptions.readInt(1,4)-1;
				        	
				        	switch(selection) {
				        	case 0: 
				        		if(destroyer[i].length == 0) {
				        			System.out.println("There are no ships of this type selected for this game.");
				        			break;
				        		}
				        		for(j = 0 ; j < destroyer[i].length ; j++) {
					            	System.out.println("[" + (j+1) + "] - Destroyer " + (j+1) + "/" + destroyer[i].length);
					            }
					            System.out.print("Please choose: ");
					            whichShipToAttack = ScannerExceptions.readInt(1, destroyer[i].length)-1;
					            if(destroyer[i][whichShipToAttack].isDead() == false && destroyer[i][whichShipToAttack].isReady() == true) {
					            	System.out.print("X: ");
					            	xCord = ScannerExceptions.readInt();
					            	System.out.print("Y: ");
					            	yCord = ScannerExceptions.readInt();
					            	playersBattlefield[whichPlayerToAttack].shootShip(destroyer[i][whichShipToAttack], xCord, yCord, destroyer, frigate, corvette, submarine, whichPlayerToAttack);
					            	shipIsChoosen = true;
					            	readyForNextRound = true;
					            } else {
					            	System.out.println("Your ship is dead or has to respawn. Choose another ship");
					            	System.out.print("You want to skip your session? [yes/no]");
					            	skip = scan.next();
					            		if(skip.equals("yes")) {
				            				System.out.println("You skipped the current session.");
				            				shipIsChoosen = true;
											readyForNextRound = true;
				            			}
					            }
					        break;
				        	case 1:
				        		if(frigate [i].length == 0) {
				        			System.out.println("There are no ships of this type selected for this game.");
				        			break;
				        		}
				        		for(j = 0 ; j < frigate[i].length ; j++) {
					        		System.out.println("[" + (j+1) + "] - Frigate " + (j+1) + "/" + frigate[i].length);
					            } 
					        	System.out.print("Please choose: ");
					            whichShipToAttack = ScannerExceptions.readInt(1, frigate[i].length)-1;
					            if(frigate[i][whichShipToAttack].isDead() == false && frigate[i][whichShipToAttack].isReady() == true) {
					            	System.out.print("X: ");
					            	xCord = ScannerExceptions.readInt(1,fieldsize);
					            	System.out.print("Y: ");
					            	yCord = ScannerExceptions.readInt(1,fieldsize);
					            	playersBattlefield[whichPlayerToAttack].shootShip(frigate[i][whichShipToAttack], xCord, yCord, destroyer, frigate, corvette, submarine, whichPlayerToAttack);
					            	shipIsChoosen = true;
					            	readyForNextRound = true;
					            } else {
					            	System.out.println("Your ship is dead or has to respawn. Choose another ship");
					            	System.out.print("You want to skip your session? [yes/no]");
					            	skip = scan.next();
					            		if(skip.equals("yes")) {
				            				System.out.println("You skipped the current session.");
				            				shipIsChoosen = true;
											readyForNextRound = true;
				            			}
					            }
					        break;
				        	case 2:
				        		if(corvette[i].length == 0) {
				        			System.out.println("There are no ships of this type selected for this game.");
				        			break;
				        		}
					        	for(j = 0 ; j < corvette[i].length ; j++) {
					        		System.out.println("[" + (j+1) + "] - Corvette " + (j+1) + "/" + corvette[i].length);
					            }
					        	System.out.print("Please choose: ");
					            whichShipToAttack = ScannerExceptions.readInt(1, corvette[i].length)-1;
					            if(corvette[i][whichShipToAttack].isDead() == false && corvette[i][whichShipToAttack].isReady() == true) {
					            	System.out.print("X: ");
					            	xCord = ScannerExceptions.readInt(1, fieldsize);
					            	System.out.print("Y: ");
					            	yCord = ScannerExceptions.readInt(1, fieldsize);
					            	playersBattlefield[whichPlayerToAttack].shootShip(corvette[i][whichShipToAttack], xCord, yCord, destroyer, frigate, corvette, submarine, whichPlayerToAttack);
					            	shipIsChoosen = true;
					            	readyForNextRound = true;
					            } else {
					            	System.out.println("Your ship is dead or has to respawn. Choose another ship");
					            	System.out.print("You want to skip your session? [yes/no]");
					            	skip = scan.next();
					            		if(skip.equals("yes")) {
				            				System.out.println("You skipped the current session.");
				            				shipIsChoosen = true;
											readyForNextRound = true;
				            			}
					            }
					        break;
				        	case 3:
				        		if(submarine[i].length == 0) {
				        			System.out.println("There are no ships of this type selected for this game.");
				        			break;
				        		}
				        		for(j = 0 ; j < submarine[i].length ; j++) {
					        		System.out.println("[" + (j+1) + "] - Submarine " + (j+1) + "/" + submarine[i].length);
					            } 
					        	System.out.print("Please choose: ");
					            whichShipToAttack = ScannerExceptions.readInt(1,submarine[i].length)-1;
					            if(submarine[i][whichShipToAttack].isDead() == false && submarine[i][whichShipToAttack].isReady() == true) {
					            	System.out.print("X: ");
					            	xCord = ScannerExceptions.readInt(1, fieldsize);
					            	System.out.print("Y: ");
					            	yCord = ScannerExceptions.readInt(1, fieldsize);
					            	playersBattlefield[whichPlayerToAttack].shootShip(submarine[i][whichShipToAttack], xCord, yCord, destroyer, frigate, corvette, submarine, whichPlayerToAttack);
					            	shipIsChoosen = true;
					            	readyForNextRound = true;
					            } else {
					            	System.out.println("Your ship is dead or has to respawn. Choose another ship");
					            	System.out.print("You want to skip your session? [yes/no]");
					            	skip = scan.next();
					            		if(skip.equals("yes")) {
				            				System.out.println("You skipped the current session.");
				            				shipIsChoosen = true;
											readyForNextRound = true;
				            			}
					            }
					        break;
				        	}
						}
					break;
					case 3: 
						System.out.println("You skipped the current session.");
						readyForNextRound = true;
				}
			}
		} else {
			System.out.println("\n" + getName() + " is now playing.");
			while(playerDead) {
				whichPlayerToAttack = (int) (Math.random() * howManyPlayersFinal);
				if(!(whichPlayerToAttack == getPlayerId())) {
					if(player[whichPlayerToAttack].isDeadPlayer() == true) {
						playerDead = true;
					} else {
						playerDead = false;
					}
				}
			}
			while(!shipIsChoosen) {
				whichTypeToAttack = (int) (Math.random() * 4);
				switch(whichTypeToAttack) {
					case 0: 
						if(destroyer[i].length == 0) {
							break;
						}
						whichShipToAttack = (int) (Math.random() * destroyer[i].length);
						xCord = (int) (Math.random() * fieldsize) + 1;
						yCord = (int) (Math.random() * fieldsize) + 1;
						if(destroyer[i][whichShipToAttack].isDead == false && destroyer[i][whichShipToAttack].isReady == true) {
							playersBattlefield[whichPlayerToAttack].shootShip(destroyer[i][whichShipToAttack], xCord, yCord, destroyer, frigate, corvette, submarine, whichPlayerToAttack);
							System.out.println(getName() + " attacks " + player[whichPlayerToAttack].getName());
							playersBattlefield[whichPlayerToAttack].printEnemyBattlefield();
		/*					try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
		*/					shipIsChoosen = true;
							readyForNextRound = true;
						} else {
							shipIsChoosen = true;
							readyForNextRound = true;
						}
					break;
					case 1:
						if(frigate[i].length == 0) {
							break;
						}
						whichShipToAttack = (int) (Math.random() * frigate[i].length);
						xCord = (int) (Math.random() * fieldsize) + 1;
						yCord = (int) (Math.random() * fieldsize) + 1;
						if(frigate[i][whichShipToAttack].isDead == false && frigate[i][whichShipToAttack].isReady == true) {
							playersBattlefield[whichPlayerToAttack].shootShip(frigate[i][whichShipToAttack], xCord, yCord, destroyer, frigate, corvette, submarine, whichPlayerToAttack);
							System.out.println(getName() + " attacks " + player[whichPlayerToAttack].getName());
							playersBattlefield[whichPlayerToAttack].printEnemyBattlefield();
	/*						try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
		*/					shipIsChoosen = true;
							readyForNextRound = true;
						} else {
							shipIsChoosen = true;
							readyForNextRound = true;
						}
					break;
					case 2:
						if(corvette[i].length == 0) {
							break;
						}
						whichShipToAttack = (int) (Math.random() * corvette[i].length);
						xCord = (int) (Math.random() * fieldsize) + 1;
						yCord = (int) (Math.random() * fieldsize) + 1;
						if(corvette[i][whichShipToAttack].isDead == false && corvette[i][whichShipToAttack].isReady == true) {
							playersBattlefield[whichPlayerToAttack].shootShip(corvette[i][whichShipToAttack], xCord, yCord, destroyer, frigate, corvette, submarine, whichPlayerToAttack);
							System.out.println(getName() + " attacks " + player[whichPlayerToAttack].getName());
							playersBattlefield[whichPlayerToAttack].printEnemyBattlefield();
	/*						try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
		*/					shipIsChoosen = true;
							readyForNextRound = true;
						} else {
							shipIsChoosen = true;
							readyForNextRound = true;
						}
					break;
					case 3:
						if(submarine[i].length == 0) {
							break;
						}
						whichShipToAttack = (int) (Math.random() * submarine[i].length);
						xCord = (int) (Math.random() * fieldsize) + 1;
						yCord = (int) (Math.random() * fieldsize) + 1;
						if(submarine[i][whichShipToAttack].isDead == false && submarine[i][whichShipToAttack].isReady == true) {
							playersBattlefield[whichPlayerToAttack].shootShip(submarine[i][whichShipToAttack], xCord, yCord, destroyer, frigate, corvette, submarine, whichPlayerToAttack);
							System.out.println(getName() + " attacks " + player[whichPlayerToAttack].getName());
							playersBattlefield[whichPlayerToAttack].printEnemyBattlefield();
/*							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
				*/			shipIsChoosen = true;
							readyForNextRound = true;
						} else {
							shipIsChoosen = true;
							readyForNextRound = true;
						}
					break;
				}
			}
			System.out.println(getName() + " has finished its round.\n");
		}
		for (int k = 0; k < playersBattlefield.length; k++) {
			playersBattlefield[k].check(destroyer);
			playersBattlefield[k].check(frigate);
			playersBattlefield[k].check(corvette);
			playersBattlefield[k].check(submarine);
		}
	}
	
	public String getName() {
		return name;
	}

	public int getPlayerId() {
		return playerId;
	}
	
	public boolean isDeadPlayer() {
		return isDeadPlayer;
	}

	public void setDeadPlayer(boolean isDeadPlayer) {
		this.isDeadPlayer = isDeadPlayer;
	}
	
	public boolean isKi() {
		return isKi;
	}
}
