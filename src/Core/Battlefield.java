package Core;
import java.io.Serializable;
import java.util.Scanner;

import CustomExceptions.LowerSideException;
import CustomExceptions.RightLowerCornerException;
import CustomExceptions.RightSideException;
import CustomExceptions.VertLowerSideException;
import CustomExceptions.VertRightLowerCornerException;
import CustomExceptions.VertRightSideException;

/**
 * Klasse um ein Spielfeld zu erzeugen
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class Battlefield implements Serializable{
	
	/** VersionsID für speichern/laden*/
	private static final long serialVersionUID = 6047056531801853002L;
	/** Größe des Spielfeldes*/
	private int fieldsize;
	/** Zahl des Spielers zu dem das Spielfeld gehört*/
	private int belongsToPlayer;
	/** Array der einzelnen Felder des Spielfeldes*/
	private Field[][] battlefield;
	
	/** Konstruktor füe ein Spielfeld*/
	public Battlefield(int fieldsize, int belongsToPlayer) {
		this.fieldsize = fieldsize;
		this.belongsToPlayer = belongsToPlayer;
		this.battlefield = new Field[fieldsize+1][fieldsize+1];
		this.builtBattlefield();
	}
	
	/**
	 * Methode um auf jedes Feld des Spielfeldes ein Objekt vom Typ "Field" zu legen, 
	 * gleichzeitig wird hier schon der Index am Rand des Spielfeldes festgelegt
	 */
	public void builtBattlefield() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {
				battlefield[x][y] = new Field(false, false, true, false, false);
				battlefield[0][y].setSign("[" + y + "]");
				battlefield[x][0].setSign("[" + x + "]");
				battlefield[0][0].setSign("[ ]");
				battlefield[0][y].setActive(true);
				battlefield[x][0].setActive(true);
			}
		}
	}
	
	/**
	 * Methode um ein Spielfeld mit allen Schiffen und Treffern auszugeben
	 */
	public void printBattlefield() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {			
				System.out.print(battlefield[x][y].getSign() + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Methode um ein Spielfeld, ohne die Schiffe sichtbar zu machen, auszugeben
	 */
	public void printEnemyBattlefield() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {	
				if(battlefield[x][y].isShip()) {
					System.out.print("[ ]");
				} else {
					System.out.print(battlefield[x][y].getSign());
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Methode die auf dem entsprechenden Feld vom Spielfeld die Attribute ändert.
	 * Gleichzeitg wird überprüft, wenn ein Schiff getroffen wurde, ob dieses tot ist.
	 * @param myShip Schiff mit dem geschossen werden soll
	 * @param xCord	x Koordinate des Schußes
	 * @param yCord y Koordinate des Schußes
	 * @param Ship[][][] Liste aller Schiffe
	 * @param whichPlayerToAttack welcher Spieler angegriffen werden soll
	 * @see Player.round
	 */
	public boolean shootShip(Ship myShip, int xCord, int yCord, Ship[][][] ship, int whichPlayerToAttack) {
		myShip.setReady(false);
		myShip.setShipRespawn(0);
		int targetRadius = myShip.getShipTargetRadius();
		
		try {
			if(xCord + targetRadius > battlefield.length) {
				throw new RightSideException();
			} else {
				for (int i = 0; i < targetRadius; i++) {
					if(battlefield[yCord][xCord + i].isShip()) {
						battlefield[yCord][xCord + i].setHitShip(true);
						battlefield[yCord][xCord + i].setSign("[H]");
						battlefield[yCord][xCord + i].setShip(false);
						System.out.println("Hit!");
						for (int j = 0; j < ship.length; j++) {
							for (int h = 0; h < ship[j][whichPlayerToAttack].length; h++) {
								if(battlefield[yCord][xCord + i].getShipId() == ship[j][whichPlayerToAttack][h].getShipId()) {
									ship[j][whichPlayerToAttack][h].livePoints --;
								}
							}
						}
						return true;
					} else if(battlefield[yCord][xCord + i].isWater() == true){
						battlefield[yCord][xCord + i].setHit(true);
						battlefield[yCord][xCord + i].setSign("[X]");
					}
				}
			}
		} catch(RightSideException e) {
			while(xCord + targetRadius > battlefield.length) {
				targetRadius = targetRadius - 1;
			}
			for (int i = 0; i < targetRadius; i++) {
				if(battlefield[yCord][xCord + i].isShip()) {
					battlefield[yCord][xCord + i].setHitShip(true);
					battlefield[yCord][xCord + i].setSign("[H]");
					battlefield[yCord][xCord + i].setShip(false);
					System.out.println("Hit!");
					for (int j = 0; j < ship.length; j++) {
						for (int h = 0; h < ship[j][whichPlayerToAttack].length; h++) {
							if(battlefield[yCord][xCord + i].getShipId() == ship[j][whichPlayerToAttack][h].getShipId()) {
								ship[j][whichPlayerToAttack][h].livePoints --;
							}
						}
					}
					return true;
				} else if(battlefield[yCord][xCord + i].isWater()){
					battlefield[yCord][xCord + i].setHit(true);
					battlefield[yCord][xCord + i].setSign("[X]");
				}
			}
		}
		return false;
	}
	
	/**
	 * Methode die überprüft ob ein Schiff tot ist
	 * @param Ship[][][] Liste aller Schiffe
	 */
	public void check(Ship[][][] ship, int whichPlayerToAttack) {
		for (int i = 0; i < ship.length; i++) {
			for (int j = 0; j < ship[i][whichPlayerToAttack].length; j++) {
				if(ship[i][whichPlayerToAttack][j].getLivePoints() == 0) {
					ship[i][whichPlayerToAttack][j].setShipDead(true);
				}
			}
		}
	}
	
	/**
	 * Methode um Schiffe auf dem Spielfeld zu platzieren
	 * @param ship Schiff welches platziert werden soll
	 * @throws Exceptions wenn das Schiff am äußeren rechten und unteren Rand platziert werden soll
	 * @see Player.playerPlaceShips
	 */
	public void placeShip(Ship ship) {
		int x = ship.getxCord();
		int y = ship.getyCord();

		if (ship.isHorizontal()) {
			try {
				 if(x == battlefield.length - ship.getShipSize() & y == battlefield.length - 1) {
					throw new RightLowerCornerException();
				} else if(x == battlefield.length - ship.getShipSize()) {
					throw new RightSideException();
				} else if(y == battlefield.length - 1) {
					throw new LowerSideException();
				} else {
					for (int i = 0; i < ship.getShipSize(); i++) {
						battlefield[y - 1][x + i].setActive(true);
						battlefield[y + 1][x + i].setActive(true);
						battlefield[y - 1][x - 1].setActive(true);
						battlefield[y + 1][x - 1].setActive(true);
						battlefield[y - 1][x + ship.getShipSize()].setActive(true);
						battlefield[y + 1][x + ship.getShipSize()].setActive(true);
						battlefield[y][x - 1].setActive(true);
						battlefield[y][x + ship.getShipSize()].setActive(true);
						battlefield[y][x + i].setSign("[" + ship.getShipSymbol() + "]");
						battlefield[y][x + i].setActive(true);
						battlefield[y][x + i].setWater(false);
						battlefield[y][x + i].setShip(true);
						battlefield[y][x + i].setShipId(ship.shipId);
					}
				}
			} catch (RightLowerCornerException e) {
				for (int i = 0; i < ship.getShipSize(); i++) {
					battlefield[y - 1][x + i].setActive(true);
					battlefield[y - 1][x - 1].setActive(true);
					battlefield[y][x - 1].setActive(true);
					battlefield[y][x + i].setSign("[" + ship.getShipSymbol() + "]");
					battlefield[y][x + i].setActive(true);
					battlefield[y][x + i].setWater(false);
					battlefield[y][x + i].setShip(true);
					battlefield[y][x + i].setShipId(ship.shipId);
				}
			} catch(LowerSideException e) {
				for (int i = 0; i < ship.getShipSize(); i++) {
					battlefield[y - 1][x + i].setActive(true);
					battlefield[y - 1][x - 1].setActive(true);
					battlefield[y - 1][x + ship.getShipSize()].setActive(true);
					battlefield[y][x - 1].setActive(true);
					battlefield[y][x + ship.getShipSize()].setActive(true);
					battlefield[y][x + i].setSign("[" + ship.getShipSymbol() + "]");
					battlefield[y][x + i].setActive(true);
					battlefield[y][x + i].setWater(false);
					battlefield[y][x + i].setShip(true);
					battlefield[y][x + i].setShipId(ship.shipId);
				}
			} catch (RightSideException e) {
				for (int i = 0; i < ship.getShipSize(); i++) {
					battlefield[y - 1][x + i].setActive(true);
					battlefield[y + 1][x + i].setActive(true);
					battlefield[y - 1][x - 1].setActive(true);
					battlefield[y + 1][x - 1].setActive(true);
					battlefield[y][x - 1].setActive(true);
					battlefield[y][x + i].setSign("[" + ship.getShipSymbol() + "]");
					battlefield[y][x + i].setActive(true);
					battlefield[y][x + i].setWater(false);
					battlefield[y][x + i].setShip(true);
					battlefield[y][x + i].setShipId(ship.shipId);
				}
			}
		} else {
				try {
					 if(y == battlefield.length - ship.getShipSize() & x == battlefield.length - 1) {
						throw new VertRightLowerCornerException();
					} else if(x == battlefield.length - 1) {
						throw new VertRightSideException();
					} else if(y == battlefield.length - ship.getShipSize()) {
						throw new VertLowerSideException();				
					} else {
						for (int i = 0; i < ship.getShipSize(); i++) {
							battlefield[y + i][x - 1].setActive(true);
							battlefield[y + i][x + 1].setActive(true);
							battlefield[y - 1][x].setActive(true);
							battlefield[y - 1][x - 1].setActive(true);
							battlefield[y - 1][x + 1].setActive(true);
							battlefield[y + ship.getShipSize()][x].setActive(true);
							battlefield[y + ship.getShipSize()][x - 1].setActive(true);
							battlefield[y + ship.getShipSize()][x + 1].setActive(true);
							battlefield[y + i][x].setSign("[" + ship.getShipSymbol() + "]");
							battlefield[y + i][x].setActive(true);
							battlefield[y + i][x].setWater(false);
							battlefield[y + i][x].setShip(true);
							battlefield[y + i][x].setShipId(ship.shipId);
						}
					}
				} catch (VertRightLowerCornerException e) {
					for (int i = 0; i < ship.getShipSize(); i++) {
						battlefield[y + i][x - 1].setActive(true);
						battlefield[y - 1][x].setActive(true);
						battlefield[y - 1][x - 1].setActive(true);
						battlefield[y + i][x].setSign("[" + ship.getShipSymbol() + "]");
						battlefield[y + i][x].setActive(true);
						battlefield[y + i][x].setWater(false);
						battlefield[y + i][x].setShip(true);
						battlefield[y + i][x].setShipId(ship.shipId);
					}
				} catch (VertRightSideException e) {
					for (int i = 0; i < ship.getShipSize(); i++) {
						battlefield[y + i][x - 1].setActive(true);
						battlefield[y - 1][x].setActive(true);
						battlefield[y - 1][x - 1].setActive(true);
						battlefield[y + ship.getShipSize()][x].setActive(true);
						battlefield[y + ship.getShipSize()][x - 1].setActive(true);	
						battlefield[y + i][x].setSign("[" + ship.getShipSymbol() + "]");
						battlefield[y + i][x].setActive(true);
						battlefield[y + i][x].setWater(false);
						battlefield[y + i][x].setShip(true);
						battlefield[y + i][x].setShipId(ship.shipId);
					}
				} catch (VertLowerSideException e) {
					for (int i = 0; i < ship.getShipSize(); i++) {
						battlefield[y + i][x - 1].setActive(true);
						battlefield[y + i][x + 1].setActive(true);
						battlefield[y - 1][x].setActive(true);
						battlefield[y - 1][x - 1].setActive(true);
						battlefield[y - 1][x + 1].setActive(true);
						battlefield[y + i][x].setSign("[" + ship.getShipSymbol() + "]");
						battlefield[y + i][x].setActive(true);
						battlefield[y + i][x].setWater(false);
						battlefield[y + i][x].setShip(true);
						battlefield[y + i][x].setShipId(ship.shipId);
					}
			} 
		}
	}
	
	/**
	 * Hilfsmethode für placeShip
	 * Abfrage, ob das zu platzierende Schiff innerhlab des Spielfeldes gesetzt werden soll
	 * @param ship Schiff, dass gesetzt werden soll
	 * @return false wenn das Schiff außerhalb des Spielfeldes platziert werden soll, 
	 * 		true wenn das Schiff innerhalb des Spielfeldes platziert werden soll.
	 */
	public boolean hasPlace(Ship ship) {
		int x = ship.getxCord();
		int y = ship.getyCord();
		if(ship.getxCord() > 0 & ship.getyCord() > 0) {
			if(ship.isHorizontal()) {
				if (x + ship.getShipSize() > battlefield.length | y > battlefield.length -1) {
					return false;
				}
			} else if(y + ship.getShipSize() > battlefield.length | x > battlefield.length -1) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * Hilfsmethode für placeShip
	 * Abfrage, ob ein Schiff horizontal oder vertikal platziert werden soll.
	 * @return false wenn vertikal, true wenn horizontal
	 */
	@SuppressWarnings("resource")
	public static boolean isHorizontal() {
		Scanner scan = new Scanner(System.in);
		System.out.print("\t\tvertical / horizontal? [v / h]: ");
		String orientation = scan.next();
		if ("v".equals(orientation)) {
			return false;
		} else if("h".equals(orientation)) {
			return true;
		} else {
			isHorizontal();
		}
		return true;
	}
	
	/**
	 * Abfrage, ob mindestens ein Feld zwischen den Schiffen und dem Schiff, welches platziert werden soll, frei ist.
	 * @param ship zu platzierendes Schiff
	 * @return false, wenn kein Feld dazwischen frei ist, true wenn mindestens ein Feld dazwischen frei ist
	 */
	public boolean hasNeighbours(Ship ship) {
		int x = ship.getxCord();
		int y = ship.getyCord();
		if(ship.isHorizontal()) {
			for(int i = 0; i < ship.getShipSize(); i++) {
				if(battlefield[y][x + i].isActive()) {
					return true;
				}
			}
		} else {
			for(int i = 0; i < ship.getShipSize(); i++) {
				if(battlefield[y + i][x].isActive()) {
					return true;
				}
			}
		}	
		return false;
	}
	
	/**
	 * Hilfsmethode für Player.round
	 * Abfrage für die AI, ob das Feld, welches ausgewählt wurde schon beschossen wurde.
	 * @param x X-Koordinate des Schußes
	 * @param y y-Koordinate des Schußes
	 * @see Player.round
	 * @return true, wenn das Feld schon beschoßen wurde, false wenn nicht
	 */
	public boolean checkForHit(int x, int y) {
		if(battlefield[y][x].isHit() == true || battlefield[y][x].isHitShip() == true) {
			return true;
		}
		return false;
	}
	
}
