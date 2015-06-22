package Core;
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
	private boolean isPlayerDead = false;
	private int deadShips = 0;
	private boolean allDestroyerDead = false;
	private boolean allFrigatesDead = false;
	private boolean allCorvettesDead = false;
	private boolean allSubmarinesDead = false;
	
	public Player() {}
	
	public Player(String name, int playerId, boolean isKi) {
		this.name = name;
		this.playerId = playerId;
		this.isPlayerDead = false;
		this.isKi = isKi;
	}

	/**
	 * Methode um die Schiffe pro Player in das Spielfeld zu setzen, ruft Battlefield - placeShips auf um das setzen zu realisieren
	 * @param i Parameter um den Player im Array zu identifizieren
	 * @param fieldsize is die Größe des Spielfeldes
	 * @param howManyDestroyer Anzahl der Zerstörer
	 * @param howManyCorvettes Anzahl der korvetten
	 * @param howManyFrigates Anzahl der Frigatten
	 * @param howManySubmarines Anzahl der U-Boote
	 * @param ship dreidimensionales Array in dem die schiffe gespeichert werden
	 * @param playersBattlefield Array, in dem die Spielfelder der Player gespeichert sind
	 */
	public void playerPlaceShips(int i, int fieldsize, int howManyDestroyer, int howManyCorvettes, int howManyFrigates, int howManySubmarines, Ship[][][] ship, Battlefield[] playersBattlefield) {
		int orientation;
		int xCord;
		int yCord; 
		int shipId = 1;
		boolean isHorizontal;
		
		for (int k = 0; k < 4; k++) {
			if(k == 0) {
				for (int h = 0; h < ship[k][i].length; h++) {
					if(!(isKi())) {
						System.out.println("\n\tDestroyer #" + (h+1) + "/" + howManyDestroyer);
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
					ship[k][i][h] = new Destroyer(shipId, i, xCord, yCord, isHorizontal);
					shipId ++;
					if(playersBattlefield[i].hasPlace(ship[k][i][h]) == true ) {
						if(playersBattlefield[i].hasNeighbours(ship[k][i][h]) == false) {
							playersBattlefield[i].placeShip(ship[k][i][h]);
						} else {
							h--;
							System.out.println("\t\tThe ships must have at least one field between them.");
						}
					} else {
						h--;
						System.out.println("\t\tChoose coordinates inside the battlefield.");
					}
					playersBattlefield[i].printBattlefield();
				}
			} else if(k == 1) {
				for (int h = 0; h < ship[k][i].length; h++) {
					if(!(isKi())) {
						System.out.println("\n\tFrigate #" + (h+1) + "/" + howManyFrigates);
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
					ship[k][i][h] = new Frigate(shipId, i, xCord, yCord, isHorizontal);
					shipId ++;
					if(playersBattlefield[i].hasPlace(ship[k][i][h]) == true ) {
						if(playersBattlefield[i].hasNeighbours(ship[k][i][h]) == false) {
							playersBattlefield[i].placeShip(ship[k][i][h]);
						} else {
							h--;
							System.out.println("\t\tThe ships must have at least one field between them.");
						}
					} else {
						h--;
						System.out.println("\t\tChoose coordinates inside the battlefield.");
					}
					playersBattlefield[i].printBattlefield();
				}
			} else if(k == 2) {
				for (int h = 0; h < ship[k][i].length; h++) {
					if(!(isKi())) {
						System.out.println("\n\tCorvette #" + (h+1) + "/" + howManyCorvettes);
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
					ship[k][i][h] = new Corvette(shipId, i, xCord, yCord, isHorizontal);
					shipId ++;
					if(playersBattlefield[i].hasPlace(ship[k][i][h]) == true ) {
						if(playersBattlefield[i].hasNeighbours(ship[k][i][h]) == false) {
							playersBattlefield[i].placeShip(ship[k][i][h]);
						} else {
							h--;
							System.out.println("\t\tThe ships must have at least one field between them.");
						}
					} else {
						h--;
						System.out.println("\t\tChoose coordinates inside the battlefield.");
					}
					playersBattlefield[i].printBattlefield();
				}
			} else if(k == 3) {
				for (int h = 0; h < ship[k][i].length; h++) {
					if(!(isKi())) {
						System.out.println("\n\tSubmarine #" + (h+1) + "/" + howManySubmarines);
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
					ship[k][i][h] = new Submarine(shipId, i, xCord, yCord, isHorizontal);
					shipId ++;
					if(playersBattlefield[i].hasPlace(ship[k][i][h]) == true ) {
						if(playersBattlefield[i].hasNeighbours(ship[k][i][h]) == false) {
							playersBattlefield[i].placeShip(ship[k][i][h]);
						} else {
							h--;
							System.out.println("\t\tThe ships must have at least one field between them.");
						}
					} else {
						h--;
						System.out.println("\t\tChoose coordinates inside the battlefield.");
					}
					playersBattlefield[i].printBattlefield();
				}
			}
		}
	}
	
	/**
	 * Methoden um den aktuellen Player im Array eine Runde spielen zu lassen. Im unteren Teil befindet sich der Code für die AI
	 * Nach dem Schuß wird überprüft ob der Spieler tot ist
	 * @param fieldsize Spielfeldgröße der Spielfelder
	 * @param i Parameter um den aktuellen Player zu identifizieren
	 * @param howManyPlayers wieviele Spieler es ingesamt gibt
	 * @param player Array, in dem alle Spieler gespeichert sind
	 * @param playersBattlefield Array, in dem die jeweiligen Spielfelder gespeichert sind
	 * @param ship dreideimensionales Array, in dem die Schiffe gespeichert sind
	 * @return 
	 */
	@SuppressWarnings("resource")
	public int round(int fieldsize, int i, int howManyPlayers, Player[] player, Battlefield[] playersBattlefield, Ship[][][] ship) {
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
		
		if(!(isKi())) {
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
					for (int k = 0; k < ship.length; k++) {
						if(k == 0) {
							System.out.println("Destroyer:");
				        	for(j = 0 ; j < ship[k][i].length ; j++) {
					        	if(!ship[k][i][j].isShipDead()) {
					        		System.out.println("\tDestroyer " + (j+1) + "/" + ship[k][i].length);
					        		System.out.println("\t\tRespawn in: " + ship[k][i][j].getShipRespawn() + "/3");
					        		System.out.println("\t\tShip ready?: " + ship[k][i][j].isReady());
					        		System.out.println("\t\tSize: " + ship[k][i][j].getShipSize());
					        		System.out.println("\t\tTarget radius: " + ship[k][i][j].getShipTargetRadius());
					        		System.out.println("\t\tShipId: " + ship[k][i][j].getShipId());
					        		System.out.println("");
					        	} else {
					        		System.out.println("The ship is dead!");
					        	}
				        	}
						} else if(k == 1) {
				        	System.out.println("Frigate:");
				        	for(j = 0 ; j < ship[k][i].length ; j++) {
				        		if(!ship[k][i][j].isShipDead()) {
					        		System.out.println("\tFrigate " + (j+1) + "/" + ship[k][i].length);
					        		System.out.println("\t\tRespawn in: " + ship[k][i][j].getShipRespawn() + "/2");
					        		System.out.println("\t\tShip ready?: " + ship[k][i][j].isReady());
					        		System.out.println("\t\tSize: " + ship[k][i][j].getShipSize());
					        		System.out.println("\t\tTarget radius: " + ship[k][i][j].getShipTargetRadius());
					        		System.out.println("\t\tShipId: " + ship[k][i][j].getShipId());
					        		System.out.println("");
				        		} else {
				        			System.out.println("The ship is dead!");
				        		}
				        	}
						} else if(k == 2) {
				        	System.out.println("Corvette:");
				        	for(j = 0 ; j < ship[k][i].length ; j++) {
				        		if(!ship[k][i][j].isShipDead()) {
					        		System.out.println("\tCorvette " + (j+1) + "/" + ship[k][i].length);
					        		System.out.println("\t\tRespawn in: " + ship[k][i][j].getShipRespawn() + "/1");
					        		System.out.println("\t\tShip ready?: " + ship[k][i][j].isReady());
					        		System.out.println("\t\tSize: " + ship[k][i][j].getShipSize());
					        		System.out.println("\t\tTarget radius: " + ship[k][i][j].getShipTargetRadius());
					        		System.out.println("\t\tShipId: " + ship[k][i][j].getShipId());
					        		System.out.println("");
				        		} else {
				        			System.out.println("The ship is dead!");
				        		}
				        	}
						} else if(k == 3) {
				        	System.out.println("Submarine:");
				        	for(j = 0 ; j < ship[k][i].length ; j++) {
				        		if(!ship[k][i][j].isShipDead()) {
					        		System.out.println("\tSubmarine " + (j+1) + "/" + ship[k][i].length);
					        		System.out.println("\t\tRespawn in: " + ship[k][i][j].getShipRespawn() + "/1");
					        		System.out.println("\t\tShip ready?: " + ship[k][i][j].isReady());
					        		System.out.println("\t\tSize: " + ship[k][i][j].getShipSize());
					        		System.out.println("\t\tTarget radius: " + ship[k][i][j].getShipTargetRadius());
					        		System.out.println("\t\tShipId: " + ship[k][i][j].getShipId());
					        		System.out.println("");
				        		} else {
				        			System.out.println("The ship is dead!");
				        		}
				        	}
						}
					}
		        break;
				case 2:
					while(playerDead) {
						System.out.println("Which player do you want to attack?");
						for (j = 0; j < player.length; j++) {
							System.out.println("\t[" + (j+1) + "] - Player " + player[j].getName()+ " | dead: " + player[j].isPlayerDead());
						}
						System.out.print("Please choose: ");
						whichPlayerToAttack = ScannerExceptions.readInt(1, player.length) - 1;
						if(whichPlayerToAttack == getPlayerId()) {
							System.out.println("\nYou cannot attack yourself.\n");
						} else {
							if(player[whichPlayerToAttack].isPlayerDead()) {
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
			        		if(ship[selection][i].length == 0) {
			        			System.out.println("There are no ships of this type selected for this game.");
			        			break;
			        		}
			        		for(j = 0 ; j < ship[selection][i].length ; j++) {
				            	System.out.println("[" + (j+1) + "] - Destroyer " + (j+1) + "/" + ship[selection][i].length);
				            }
				            System.out.print("Please choose: ");
				            whichShipToAttack = ScannerExceptions.readInt(1, ship[selection][i].length)-1;
				            if(!(ship[selection][i][whichShipToAttack].isShipDead()) && ship[selection][i][whichShipToAttack].isReady()) {
				            	System.out.print("X: ");
				            	xCord = ScannerExceptions.readInt();
				            	System.out.print("Y: ");
				            	yCord = ScannerExceptions.readInt();
				            	playersBattlefield[whichPlayerToAttack].shootShip(ship[selection][i][whichShipToAttack], xCord, yCord, ship, whichPlayerToAttack);					            	
				            	howManyPlayers = player[whichPlayerToAttack].playerDie(whichPlayerToAttack, howManyPlayers, playersBattlefield, ship, player);
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
			        		if(ship[selection][i].length == 0) {
			        			System.out.println("There are no ships of this type selected for this game.");
			        			break;
			        		}
			        		for(j = 0 ; j < ship[selection][i].length ; j++) {
				        		System.out.println("[" + (j+1) + "] - Frigate " + (j+1) + "/" + ship[selection][i].length);
				            } 
				        	System.out.print("Please choose: ");
				            whichShipToAttack = ScannerExceptions.readInt(1, ship[selection][i].length)-1;
				            if(!(ship[selection][i][whichShipToAttack].isShipDead()) && ship[selection][i][whichShipToAttack].isReady()) {
				            	System.out.print("X: ");
				            	xCord = ScannerExceptions.readInt(1,fieldsize);
				            	System.out.print("Y: ");
				            	yCord = ScannerExceptions.readInt(1,fieldsize);
				            	playersBattlefield[whichPlayerToAttack].shootShip(ship[selection][i][whichShipToAttack], xCord, yCord, ship, whichPlayerToAttack);
				            	howManyPlayers = player[whichPlayerToAttack].playerDie(whichPlayerToAttack, howManyPlayers, playersBattlefield, ship, player);
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
			        		if(ship[selection][i].length == 0) {
			        			System.out.println("There are no ships of this type selected for this game.");
			        			break;
			        		}
				        	for(j = 0 ; j < ship[selection][i].length ; j++) {
				        		System.out.println("[" + (j+1) + "] - Corvette " + (j+1) + "/" + ship[selection][i].length);
				            }
				        	System.out.print("Please choose: ");
				            whichShipToAttack = ScannerExceptions.readInt(1, ship[selection][i].length)-1;
				            if(!(ship[selection][i][whichShipToAttack].isShipDead()) && ship[selection][i][whichShipToAttack].isReady()) {
				            	System.out.print("X: ");
				            	xCord = ScannerExceptions.readInt(1, fieldsize);
				            	System.out.print("Y: ");
				            	yCord = ScannerExceptions.readInt(1, fieldsize);
				            	playersBattlefield[whichPlayerToAttack].shootShip(ship[selection][i][whichShipToAttack], xCord, yCord, ship, whichPlayerToAttack);
				            	howManyPlayers = player[whichPlayerToAttack].playerDie(whichPlayerToAttack, howManyPlayers, playersBattlefield, ship, player);
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
			        		if(ship[selection][i].length == 0) {
			        			System.out.println("There are no ships of this type selected for this game.");
			        			break;
			        		}
			        		for(j = 0 ; j < ship[selection][i].length ; j++) {
				        		System.out.println("[" + (j+1) + "] - Submarine " + (j+1) + "/" + ship[selection][i].length);
				            } 
				        	System.out.print("Please choose: ");
				            whichShipToAttack = ScannerExceptions.readInt(1,ship[selection][i].length)-1;
				            if(!(ship[selection][i][whichShipToAttack].isShipDead()) && ship[selection][i][whichShipToAttack].isReady()) {
				            	System.out.print("X: ");
				            	xCord = ScannerExceptions.readInt(1, fieldsize);
				            	System.out.print("Y: ");
				            	yCord = ScannerExceptions.readInt(1, fieldsize);
				            	playersBattlefield[whichPlayerToAttack].shootShip(ship[selection][i][whichShipToAttack], xCord, yCord, ship, whichPlayerToAttack);
				            	howManyPlayers = player[whichPlayerToAttack].playerDie(whichPlayerToAttack, howManyPlayers, playersBattlefield, ship, player);
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
					break;
				}
			}
		} else {
			System.out.println("\n" + getName() + " is now playing.");
			while(playerDead) {
				whichPlayerToAttack = (int) (Math.random() * player.length);
				if(whichPlayerToAttack == getPlayerId()) {
					playerDead = true;
				} else {
					if(player[whichPlayerToAttack].isPlayerDead()) {
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
						if(ship[whichTypeToAttack][i].length == 0) {
							break;
						} else {
							whichShipToAttack = (int) (Math.random() * ship[whichTypeToAttack][i].length);
							xCord = (int) (Math.random() * fieldsize) + 1;
							yCord = (int) (Math.random() * fieldsize) + 1;
							if(!(ship[whichTypeToAttack][i][whichShipToAttack].isReady())) {
								shipIsChoosen = true;
								readyForNextRound = true;
							} else if(!(ship[whichTypeToAttack][i][whichShipToAttack].isShipDead()) && ship[whichTypeToAttack][i][whichShipToAttack].isReady()) {
								playersBattlefield[whichPlayerToAttack].shootShip(ship[whichTypeToAttack][i][whichShipToAttack], xCord, yCord, ship, whichPlayerToAttack);
								System.out.println(getName() + " attacks " + player[whichPlayerToAttack].getName());
								playersBattlefield[whichPlayerToAttack].printEnemyBattlefield();
								howManyPlayers = player[whichPlayerToAttack].playerDie(whichPlayerToAttack, howManyPlayers, playersBattlefield, ship, player);
	/*							try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
								e.printStackTrace();
								}
		*/						shipIsChoosen = true;
								readyForNextRound = true;
							} else {
								shipIsChoosen = false;
								readyForNextRound = false;
							}
						}
					break;
					case 1:
						if(ship[whichTypeToAttack][i].length == 0) {
							break;
						} else {
							whichShipToAttack = (int) (Math.random() * ship[whichTypeToAttack][i].length);
							xCord = (int) (Math.random() * fieldsize) + 1;
							yCord = (int) (Math.random() * fieldsize) + 1;
							if(!(ship[whichTypeToAttack][i][whichShipToAttack].isReady())) {
								shipIsChoosen = true;
								readyForNextRound = true;
							} else if(!(ship[whichTypeToAttack][i][whichShipToAttack].isShipDead()) && ship[whichTypeToAttack][i][whichShipToAttack].isReady()) {
								playersBattlefield[whichPlayerToAttack].shootShip(ship[whichTypeToAttack][i][whichShipToAttack], xCord, yCord, ship, whichPlayerToAttack);
								System.out.println(getName() + " attacks " + player[whichPlayerToAttack].getName());
								playersBattlefield[whichPlayerToAttack].printEnemyBattlefield();
								howManyPlayers = player[whichPlayerToAttack].playerDie(whichPlayerToAttack, howManyPlayers, playersBattlefield, ship, player);
		/*						try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
			*/					shipIsChoosen = true;
								readyForNextRound = true;
							} else {
								shipIsChoosen = false;
								readyForNextRound = false;
							}
						}
					break;
					case 2:
						if(ship[whichTypeToAttack][i].length == 0) {
							break;
						} else {
							whichShipToAttack = (int) (Math.random() * ship[whichTypeToAttack][i].length);
							xCord = (int) (Math.random() * fieldsize) + 1;
							yCord = (int) (Math.random() * fieldsize) + 1;
							if(!(ship[whichTypeToAttack][i][whichShipToAttack].isShipDead()) && ship[whichTypeToAttack][i][whichShipToAttack].isReady()) {
								playersBattlefield[whichPlayerToAttack].shootShip(ship[whichTypeToAttack][i][whichShipToAttack], xCord, yCord, ship, whichPlayerToAttack);
								System.out.println(getName() + " attacks " + player[whichPlayerToAttack].getName());
								playersBattlefield[whichPlayerToAttack].printEnemyBattlefield();
								howManyPlayers = player[whichPlayerToAttack].playerDie(whichPlayerToAttack, howManyPlayers, playersBattlefield, ship, player); 
		/*						try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
			*/					shipIsChoosen = true;
								readyForNextRound = true;
							} else {
								shipIsChoosen = false;
								readyForNextRound = false;
							}
						}
					break;
					case 3:
						if(ship[whichTypeToAttack][i].length == 0) {
							break;
						} else {
							whichShipToAttack = (int) (Math.random() *ship[whichTypeToAttack][i].length);
							xCord = (int) (Math.random() * fieldsize) + 1;
							yCord = (int) (Math.random() * fieldsize) + 1;
							if(!(ship[whichTypeToAttack][i][whichShipToAttack].isShipDead()) && ship[whichTypeToAttack][i][whichShipToAttack].isReady()) {
								playersBattlefield[whichPlayerToAttack].shootShip(ship[whichTypeToAttack][i][whichShipToAttack], xCord, yCord, ship, whichPlayerToAttack);
								System.out.println(getName() + " attacks " + player[whichPlayerToAttack].getName());
								playersBattlefield[whichPlayerToAttack].printEnemyBattlefield();
								howManyPlayers = player[whichPlayerToAttack].playerDie(whichPlayerToAttack, howManyPlayers, playersBattlefield, ship, player);
	/*							try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
					*/			shipIsChoosen = true;
								readyForNextRound = true;
							} else {
								shipIsChoosen = false;
								readyForNextRound = false;
							}
						}
					break;
				}
			}
			System.out.println(getName() + " has finished its round.\n");
		}
		return howManyPlayers;
	}
	
	/**
	 * Methoe um nach dem schießen zu überprüfen ob der angegriffene Spieler tot ist und ob Schiffe tot sind
	 * @param whichPlayerToAttack Spieler der angegriffen wurde
	 * @param howManyPlayers dynamische Anzahl der Spieler, wird verrinert, sobald ein Spieler tot ist
	 * @param playersBattlefield Array mit den Spielfeldern
	 * @param ship dreidimensionales Array  in dem die Schiffe gespeichert sind
	 * @param player Array der Player
	 * @return gibt die korrigierte Anzahl der Spieler wieder, sollte eine gestroben sein
	 */
	public int playerDie(int whichPlayerToAttack, int howManyPlayers, Battlefield[] playersBattlefield, Ship[][][] ship, Player[] player) {
		
		playersBattlefield[whichPlayerToAttack].check(ship, whichPlayerToAttack);
		
		for (int i = 0; i < 4; i++) {
			if(i == 0) {
				if(ship[i][whichPlayerToAttack].length == 0) {
					setAllDestroyerDead(true);
				}						
				for (int j = 0; j < ship[i][whichPlayerToAttack].length; j++) {
					if(ship[i][whichPlayerToAttack][j].isShipDead()) {
						deadShips ++;
					}
					if(deadShips == ship[i][whichPlayerToAttack].length) {
						setAllDestroyerDead(true);
					}
				}
			} else if(i == 1) {
				deadShips = 0;
				if(ship[i][whichPlayerToAttack].length == 0) {
					setAllFrigatesDead(true);
				}
				for (int k = 0; k < ship[i][whichPlayerToAttack].length; k++) {
					if(ship[i][whichPlayerToAttack][k].isShipDead()) {
						deadShips++;
					}
					if(deadShips == ship[i][whichPlayerToAttack].length) {
						setAllFrigatesDead(true);
					}
				}
			} else if( i == 2) { 
				deadShips = 0;
				if(ship[i][whichPlayerToAttack].length == 0) {
					setAllCorvettesDead(true);
				}
				for (int k = 0; k < ship[i][whichPlayerToAttack].length; k++) {
					if(ship[i][whichPlayerToAttack][k].isShipDead()) {
						deadShips++;
					}
					if(deadShips == ship[i][whichPlayerToAttack].length) {
						setAllCorvettesDead(true);
					}
				}
			} else if( i == 3) {
				deadShips = 0;
				if(ship[i][whichPlayerToAttack].length == 0) {
					setAllSubmarinesDead(true);
				}
				for (int k = 0; k < ship[i][whichPlayerToAttack].length; k++) {
					if(ship[i][whichPlayerToAttack][k].isShipDead()) {
						deadShips++;
					}
					if(deadShips == ship[i][whichPlayerToAttack].length) {
						setAllSubmarinesDead(true);
					}
				}
			}
			
			if(getAllDestroyerDead() & getAllFrigatesDead() & getAllCorvettesDead() & getAllSubmarinesDead()) {
				setPlayerDead(true);
			}
			if(player[whichPlayerToAttack].isPlayerDead()) {
				System.out.println(player[whichPlayerToAttack].getName() + " is dead.");
				howManyPlayers --;
			}
		}
		return howManyPlayers;
	}
	
	public void setAllDestroyerDead(boolean allDestroyerDead) {
		this.allDestroyerDead = allDestroyerDead;	
	}
	
	public boolean getAllDestroyerDead() {
		return allDestroyerDead;
	}
	
	public void setAllFrigatesDead(boolean allFrigatesDead) {
		this.allFrigatesDead = allFrigatesDead;	
	}
	
	public boolean getAllFrigatesDead() {
		return allFrigatesDead;
	}
	
	public void setAllCorvettesDead(boolean allCorvettesDead) {
		this.allCorvettesDead = allCorvettesDead;		
	}
	
	public boolean getAllCorvettesDead() {
		return allCorvettesDead;
	}
	
	public void setAllSubmarinesDead(boolean allSubmarinesDead) {
		this.allSubmarinesDead = allSubmarinesDead;		
	}
	
	public boolean getAllSubmarinesDead() {
		return allSubmarinesDead;
	}

	public String getName() {
		return name;
	}

	public int getPlayerId() {
		return playerId;
	}
	
	public boolean isPlayerDead() {
		return isPlayerDead;
	}

	public void setPlayerDead(boolean isPlayerDead) {
		this.isPlayerDead = isPlayerDead;
	}
	
	public boolean isKi() {
		return isKi;
	}
}
