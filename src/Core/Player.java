package Core;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Scanner;

import CustomExceptions.ScannerExceptions;

/**
 * Klasse um einen Spieler zu erzeugen und um eine Runde des Sielers zu koordinieren
 */

public class Player implements Serializable{
	
	/**VersionsID für speichern/laden*/
	private static final long serialVersionUID = 8801887131747152046L;
	/**Name des Spielers*/
	private String name;
	/**ID des Spielers*/
	private int playerId = 0;
	/**tote Schiffe des Spielers*/
	private int deadShips = 0;
	/**X-Koordinate, die sich die AI merkt*/
	private int x = 1000;
	/**Y-Koordinate, die sich die AI merkt*/
	private int y = 1000;
	/**Zähler für die Runden, damit die AI um ein getroffenes Feld herum schießt*/
	private int roundCounter = -1;
	/**Speicher der AI, für die ID des Spielers der getroffen wurde*/
	private int playerToAttack = 7;
	private boolean isKi = false;
	private boolean isPlayerDead = false;
	private boolean allDestroyerDead = false;
	private boolean allFrigatesDead = false;
	private boolean allCorvettesDead = false;
	private boolean allSubmarinesDead = false;
	
	/**
	 * Konstruktor für einen Spieler
	 * @param name Name des Spielers
	 * @param playerId ID des Spielers
	 * @param isKi Boolean ob der Spieler eine KI ist
	 */
	public Player(String name, int playerId, boolean isKi) {
		this.name = name;
		this.playerId = playerId;
		this.isPlayerDead = false;
		this.isKi = isKi;
	}

	/**
	 * Hier werden die Schiffe in das jeweilige Sielfeld gesetzt
	 * @param i Parameer um den Spieler zu identifizieren
	 * @param fieldsize Größe des Spielfeldes
	 * @param ship Array mit allen Schiffen des Spieles
	 * @param playersBattlefield Array mit den Spielfeldern
	 */
	public void playerPlaceShips(int i, int fieldsize, Ship[][][] ship, Battlefield[] playersBattlefield) {
		
		/**Wert für die AI, um zu entscheiden ob das Schiff horizontal oder vertikal platziert werden soll*/
		int orientation;
		/**X-Koordinate wo das Schiff platziert werden soll*/
		int xCord;
		/**Y-Koordinate wo das Schiff platziert werden soll*/
		int yCord; 
		/**ShiffID die bei jedem erzeugen eines neuen Shiffes hochgezählt wird*/
		int shipId = 1;
		boolean isHorizontal;
		
		//Das Schiffarray wird durchgegangen, um den zu platzierenden Schiffstyp festzulegen (Bsp: 0 = Zerstörer)
		for (int k = 0; k < ship.length; k++) {
			//Wenn es keine Schiffe von dem Typ gibt, wird dieser übersprungen
			if(ship[k][i].length == 0) {
			} else {
				//Hier werden die Schiffe erstellt 
				for (int h = 0; h < ship[k][i].length; h++) {					
					switch(k) {
					case 0: 
						ship[k][i][h] = new Destroyer(shipId);
						shipId ++;
						break;
					case 1:
						ship[k][i][h] = new Frigate(shipId);
						shipId ++;
						break;
					case 2: 
						ship[k][i][h] = new Corvette(shipId);
						shipId ++;
						break;
					case 3:
						ship[k][i][h] = new Submarine(shipId);
						shipId ++;
						break;
					}
					
					//Wenn der Spieler keine AI ist werden hier die benötigten Werte eingelesen
					if(!(isKi())) {
						System.out.println("\n\t" + ship[k][i][h].getName() + (h+1) + "/" + ship[k][i].length);
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
					
					//Hier werden die restlichen Attribute auf Basis der eingegeben Werte gesetzt
					ship[k][i][h].setHorizontal(isHorizontal);
					ship[k][i][h].setXCord(xCord);
					ship[k][i][h].setYCord(yCord);
					ship[k][i][h].setShipBelongsToPlayer(i);
					
					//Wenn das Schiff entsprechend platziert werden kann, wird es platziert
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
	
	public boolean placeShipsForGui(Ship ship, int orientation, int fieldsize, int a, Battlefield[] playersBattlefield, String[] segs, ActionEvent e) {
		
		segs = e.getActionCommand().split(" ");
		int xCord;
		int yCord;
		
		boolean isHorizontal;
		
		if(orientation == 0) {
			isHorizontal = true;
		} else {
			isHorizontal = false;
		}
		
		if(!(isKi())) {
			xCord = Integer.parseInt(segs[1]);
			yCord = Integer.parseInt(segs[1]);
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
		
		ship.setHorizontal(isHorizontal);
		ship.setXCord(xCord);
		ship.setYCord(yCord);
		ship.setShipBelongsToPlayer(a);
		
		if(playersBattlefield[a].hasPlace(ship) == true ) {
			if(playersBattlefield[a].hasNeighbours(ship) == false) {
				playersBattlefield[a].placeShip(ship);
				playersBattlefield[a].printBattlefield();
				return true;
			} else {
				System.out.println("\t\tThe ships must have at least one field between them.");
				return false;
			}
		} else {
			System.out.println("\t\tChoose coordinates inside the battlefield.");
			return false;
		}
	}
	
	/**
	 * Mathode um eine Runde des Spielers durchzugehen
	 * @param fieldsize Größe des Spielfeldes
	 * @param i Parameter um den aktuellen Spieler zu identifizieren
	 * @param howManyPlayers Anzahl der Spieler
	 * @param player Array mit den Spielern
	 * @param playersBattlefield Array mit den Spielfeldern
	 * @param ship Array aller Schiffe das Spiels
	 * @return wenn ein Spieler stirbt, wird die aktuelle Anzahl von Spielern zurückgegeben
	 */
	@SuppressWarnings("resource")
	public int round(int fieldsize, int i, int howManyPlayers, Player[] player, Battlefield[] playersBattlefield, Ship[][][] ship) {
		
		/**Parameter für die Switch-Anweisung, was der Spieler tun will*/
		int selection;
		/**Parameter um den Schiffstyp zu wählen*/
		int whichTypeToAttack = 0;
		/**Parameter um den zu angreifenden Spieler zu wählen*/
		int whichPlayerToAttack = 0;
		/**Parameter um das Schiff eines Schiffstyp zu wählen, mit dem angegriffen werden soll*/
		int whichShipToAttack = 0;
		/**X-Koordinate auf die geschoßen werden soll*/
		int xCord;
		/**Y-Koordinate auf die geschoßen werden soll*/
		int yCord;
		boolean playerDead = true;
		boolean shipIsChoosen = false;
		boolean readyForNextRound = false;
		boolean cordsChoosen = true;
		boolean hitShip = false;
		String skip;
		Scanner scan = new Scanner(System.in);
		
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
					//Der Spieler lässt sich die Spielfelder der anderen oder sein eigenes anzeigen
					System.out.println("Which battlefield do you want to see?");
		        	for(int j = 0 ; j < player.length ; j++) {
		        		System.out.println("[" + (j+1) + "] - Player " + player[j].getName());
		        	}
		        	System.out.print("Please choose: ");
		        	selection = ScannerExceptions.readInt(1, player.length)-1;
		        	System.out.println("Battlefield of " + player[selection].getName());
		        	
		        	playersBattlefield[selection].printEnemyBattlefield(); 	
		        break;
				case 1:
					//Der Spieler lässt sich seine Schiffe anzeigen
					for (int k = 0; k < ship.length; k++) {
			        	for(int j = 0 ; j < ship[k][i].length ; j++) {
							System.out.println(ship[k][i][j].getName() +  " :");
				        	if(!ship[k][i][j].isShipDead()) {
				        		System.out.println("\t" + ship[k][i][j].getName() + (j+1) + "/" + ship[k][i].length);
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
					}
		        break;
				case 2:
					//Der Spieler greift einen Sanderen Spieler an
					//Auswahl des Spielers der angegriffen werden soll
					while(playerDead) {
						System.out.println("Which player do you want to attack?");
						for (int j = 0; j < player.length; j++) {
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
					//Auswahl des Schiffes mit dem angegriffen werden soll
					while(!shipIsChoosen) {
						System.out.print("With which ship do you want to attack?\n");
			        	System.out.println("\t[1] - Destroyer");
			        	System.out.println("\t[2] - Frigate");
			        	System.out.println("\t[3] - Corvette");
			        	System.out.println("\t[4] - Submarine");
			        	System.out.print("Please choose: ");
			        	selection = ScannerExceptions.readInt(1,4)-1;
			        	
		        		if(ship[selection][i].length == 0) {
		        			System.out.println("There are no ships of this type selected for this game.");
		        			shipIsChoosen = false;
		        			
		        		} else {
			        		for(int j = 0 ; j < ship[selection][i].length ; j++) {
				            	System.out.println("[" + (j+1) + "] - " + ship[selection][i][j].getName()  + (j+1) + "/" + ship[selection][i].length);
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
		        		}
					}
					break;
				case 3:
					//Der Spieler übersrpingt seine Runde
					System.out.println("You skipped the current session.");
					readyForNextRound = true;
					break;
				}
			}
		} else {
			//Wenn der Spieler eine AI ist wird dieser Block ausgeführt
			System.out.println("\n" + getName() + " is now playing.");
			//Auswahl des Spielers der angegriffen werden soll
			while(playerDead) {
				if(getPlayerToAttack() < 7) {
					whichPlayerToAttack = getPlayerToAttack();
					if(player[whichPlayerToAttack].isPlayerDead()) {
						setPlayerToAttack(7);
						playerDead = true;
					} else {
						playerDead = false;
					}
				} else {
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
			}
			//Auswahl des Schiffes, mit dem angegriffen werden soll
			while(!shipIsChoosen) {
				whichTypeToAttack = (int) (Math.random() * 4);
				if(ship[whichTypeToAttack][i].length == 0) {
					shipIsChoosen = false;
				} else {
					whichShipToAttack = (int) (Math.random() * ship[whichTypeToAttack][i].length);
					if(!(ship[whichTypeToAttack][i][whichShipToAttack].isReady())) {
						shipIsChoosen = true;
						readyForNextRound = true;
					} else if(!(ship[whichTypeToAttack][i][whichShipToAttack].isShipDead()) & ship[whichTypeToAttack][i][whichShipToAttack].isReady()) {
							while(cordsChoosen) {
								//Wenn die AI schon einmal getroffen hat, wird diese Switch abgearbeitet
								switch(getRoundCounter()) {
								case 0:
									xCord = getX() - 1;
									yCord = getY();
									setRoundCounter(getRoundCounter() - 1);
									break;
								case 1: 
									xCord = getX() + 1;
									yCord = getY();
									setRoundCounter(0);
									setPlayerToAttack(7);
									break;
								case 2: 
									xCord = getX();
									yCord = getY() - 1;
									setRoundCounter(1);
									break;
								case 3: 
									xCord = getX();
									yCord = getY() + 1;
									setRoundCounter(2);
									break;
								default :
									xCord = (int) (Math.random()* fieldsize) + 1;
									yCord = (int) (Math.random()* fieldsize) + 1;
									break;
								}
								try {
									if(playersBattlefield[whichPlayerToAttack].checkForHit(xCord, yCord) == true) {
										cordsChoosen = true;
									} else {
									System.out.println(getName() + " attacks " + player[whichPlayerToAttack].getName());
									hitShip = playersBattlefield[whichPlayerToAttack].shootShip(ship[whichTypeToAttack][i][whichShipToAttack], xCord, yCord, ship, whichPlayerToAttack);
										if(hitShip == true) {
											setX(xCord);
											setY(yCord);
											setRoundCounter(3);
											setPlayerToAttack(whichPlayerToAttack);
										}
									playersBattlefield[whichPlayerToAttack].printEnemyBattlefield();
									howManyPlayers = player[whichPlayerToAttack].playerDie(whichPlayerToAttack, howManyPlayers, playersBattlefield, ship, player);
									cordsChoosen = false;
									shipIsChoosen = true;
									}
								} catch(ArrayIndexOutOfBoundsException e) {
								}
							}
					} else {
						shipIsChoosen = false;
					}
				}
			}
			System.out.println(getName() + " has finished its round.\n");
			/*
			try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		*/
		}
		return howManyPlayers;
	}
	
	/**
	 * Hier wird überprüft, ob der Spieler, der angegriffen wurde, nun tot ist
	 * @param whichPlayerToAttack Spieler der angegriffen wurde
	 * @param howManyPlayers Anzahl der Spieler
	 * @param playersBattlefield Array mit den Spielfeldern
	 * @param ship Array mit allen Schiffen des Spiels
	 * @param player Array mit den Spielern
	 * @return gibt die korrigierte Anzahl der Spieler zurück
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
		}	
		if(getAllDestroyerDead() & getAllFrigatesDead() & getAllCorvettesDead() & getAllSubmarinesDead()) {
			setPlayerDead(true);
		}
		if(player[whichPlayerToAttack].isPlayerDead()) {
			System.out.println(player[whichPlayerToAttack].getName() + " is dead.");
			howManyPlayers --;
		}
		return howManyPlayers;
	}
	
	//Getter und Setter für die Attribute eines Spielers
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRoundCounter() {
		return roundCounter;
	}

	public void setRoundCounter(int roundCounter) {
		this.roundCounter = roundCounter;
	}

	public int getPlayerToAttack() {
		return playerToAttack;
	}

	public void setPlayerToAttack(int playerToAttack) {
		this.playerToAttack = playerToAttack;
	}
}
