import java.io.Serializable;
import java.util.Scanner;

import CustomExceptions.LowerSideException;
import CustomExceptions.RightLowerCornerException;
import CustomExceptions.RightSideException;
import CustomExceptions.VertLowerSideException;
import CustomExceptions.VertRightLowerCornerException;
import CustomExceptions.VertRightSideException;


public class Battlefield implements Serializable{
	
	private int fieldsize;
	private int belongsToPlayer;
	private Field[][] battlefield;
	
	public Battlefield() {}
	
	public Battlefield(int fieldsize, int belongsToPlayer) {
		this.fieldsize = fieldsize;
		this.belongsToPlayer = belongsToPlayer;
		this.battlefield = new Field[fieldsize+1][fieldsize+1];
		this.builtBattlefield();
	}
	
	public void builtBattlefield() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {
				battlefield[x][y] = new Field(false, false, true, false, false);
				battlefield[0][y].setSign("[" + y + "]");
				battlefield[x][0].setSign("[" + x + "]");
				battlefield[0][0].setSign("[ ]");
			}
		}
	}
	
	//Ausgabe Battlefield
	public void printBattlefield() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {			
				System.out.print(battlefield[x][y].getSign() + " ");
			}
			System.out.println();
		}
	}

	//Ausgabe Battlefield Gegnersicht
		public void printEnemyBattlefield() {
			for (int x = 0; x < battlefield.length; x++) {
				for (int y = 0; y < battlefield[x].length; y++) {	
					if(battlefield[x][y].isShip() == true) {
						System.out.print("[ ]");
					} else {
						System.out.print(battlefield[x][y].getSign());
					}
				}
				System.out.println();
			}
		}

	public static boolean isHorizontal() {
		Scanner scan = new Scanner(System.in);
		System.out.print("\t\tvertical / horizontal? [vert / hori]: ");
		String orientation = scan.next();
		if ("vert".equals(orientation)) {
			return false;
		} else if("hori".equals(orientation)) {
			return true;
		} else {
			isHorizontal();
		}
		return true;
	}
	
	public void shootShip(Ship myShip, int xCord, int yCord, Destroyer[][] destroyer, Frigate[][] frigate, Corvette[][] corvette, Submarine[][] submarine, int whichPlayerToAttack) throws Exception {
		myShip.setReady(false);
		myShip.setShipRespawn(0);
		int targetRadius = myShip.getShipTargetRadius();
		
		try {
			if(xCord + targetRadius > battlefield.length) {
				throw new RightSideException();
			} else {
				for (int i = 0; i < targetRadius; i++) {
					if(battlefield[yCord][xCord + i].isShip() == true) {
						battlefield[yCord][xCord + i].setHitShip(true);
						battlefield[yCord][xCord + i].setSign("[H]");
						battlefield[yCord][xCord + i].setShip(false);
						System.out.println("Hit!");
						for (int j = 0; j < destroyer[whichPlayerToAttack].length; j++) {
							if(battlefield[yCord][xCord + i].getShipId() == destroyer[whichPlayerToAttack][j].getShipId()) {
								destroyer[whichPlayerToAttack][j].livePoints --;
							}
						}
						for (int j = 0; j < frigate[whichPlayerToAttack].length; j++) {
							if(battlefield[yCord][xCord + i].getShipId() == frigate[whichPlayerToAttack][j].getShipId()) {
								frigate[whichPlayerToAttack][j].livePoints --;
							}
						}
						for (int j = 0; j < corvette[whichPlayerToAttack].length; j++) {
							if(battlefield[yCord][xCord + i].getShipId() == corvette[whichPlayerToAttack][j].getShipId()) {
								corvette[whichPlayerToAttack][j].livePoints --;
							}
						}
						for (int j = 0; j < submarine[whichPlayerToAttack].length; j++) {
							if(battlefield[yCord][xCord + i].getShipId() == submarine[whichPlayerToAttack][j].getShipId()) {
								submarine[whichPlayerToAttack][j].livePoints --;
							}
						}
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
				if(battlefield[yCord][xCord + i].isShip() == true) {
					battlefield[yCord][xCord + i].setHitShip(true);
					battlefield[yCord][xCord + i].setSign("[H]");
					battlefield[yCord][xCord + i].setShip(false);
					System.out.println("Hit!");
					for (int j = 0; j < destroyer[whichPlayerToAttack].length; j++) {
						if(battlefield[yCord][xCord + i].getShipId() == destroyer[whichPlayerToAttack][j].getShipId()) {
							destroyer[whichPlayerToAttack][j].livePoints --;
						}
					}
					for (int j = 0; j < frigate[whichPlayerToAttack].length; j++) {
						if(battlefield[yCord][xCord + i].getShipId() == frigate[whichPlayerToAttack][j].getShipId()) {
							frigate[whichPlayerToAttack][j].livePoints --;
						}
					}
					for (int j = 0; j < corvette[whichPlayerToAttack].length; j++) {
						if(battlefield[yCord][xCord + i].getShipId() == corvette[whichPlayerToAttack][j].getShipId()) {
							corvette[whichPlayerToAttack][j].livePoints --;
						}
					}
					for (int j = 0; j < submarine[whichPlayerToAttack].length; j++) {
						if(battlefield[yCord][xCord + i].getShipId() == submarine[whichPlayerToAttack][j].getShipId()) {
							submarine[whichPlayerToAttack][j].livePoints --;
						}
					}
				} else if(battlefield[yCord][xCord + i].isWater() == true){
					battlefield[yCord][xCord + i].setHit(true);
					battlefield[yCord][xCord + i].setSign("[X]");
				}
			}
		}
	}

	public void check(Ship[][] ship) {
		for (int i = 0; i < ship.length; i++) {
			for (int j = 0; j < ship[i].length; j++) {
				if(ship[i][j].livePoints == 0) {
					ship[i][j].setDead(true);
				}
			}
		}
	}
	
	public void placeShip(Ship ship) throws Exception {
		// Einlesen der Werte für das Setzen
		boolean isHorizontal = ship.isHorizontal;
		Scanner scan = new Scanner(System.in);
		int x = ship.getxCord();
		int y = ship.getyCord();

		if (isHorizontal == true) {
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
	
	// Abfrage ob das Schiff innerhalb des Spielfeldes gesetzt werden soll, für setShip
	public boolean hasPlace(Ship ship) {
		int x = ship.getxCord();
		int y = ship.getyCord();
		boolean isHorizontal = ship.isHorizontal;
		boolean hasPlace = true;
		if(ship.getxCord() > 0 & ship.getyCord() > 0) {
			if(isHorizontal == true) {
				if (x + ship.getShipSize() > battlefield.length | y > battlefield.length -1) {
					hasPlace = false;
					return hasPlace;
				}
			} else if(y + ship.getShipSize() > battlefield.length | x > battlefield.length -1) {
				hasPlace = false;
				return hasPlace;
			}
		} else {
			hasPlace = false;
			return hasPlace;
		}
		return hasPlace;
	}
		
	//Abfrage ob ein Feld platz zwischen den Schiffen ist
	public boolean hasNeighbours(Ship ship) {
		int x = ship.getxCord();
		int y = ship.getyCord();
		boolean isHorizontal = ship.isHorizontal;
		boolean hasNeighbours = false;
		if(isHorizontal == true) {
			for(int i = 0; i < ship.getShipSize(); i++) {
				if(battlefield[y][x + i].isActive() == true) {
					hasNeighbours = true;
					return hasNeighbours;
				}
			}
		} else {
			for(int i = 0; i < ship.getShipSize(); i++) {
				if(battlefield[y + i][x].isActive() == true) {
					hasNeighbours = true;
					return hasNeighbours;
				}
			}
		}	
		return hasNeighbours;
	}
	
	///Wichtig fuer shoot
	public boolean isShipThere(int x, int y) {
		String sign = battlefield[y][x].getSign();
		if(sign.equals("[C]") | sign.equals("[D]") | sign.equals("[F]") | sign.equals("[S]")) {
			return true;
		}
		return false;
	}
	
	public boolean isShipDead(Ship ship) {
		if(ship.isDead()) {
			return true;
		}
		return false;
	}

	//Getter und Setter
	public int getFieldsize() {
		return fieldsize;
	}

	public void setFieldsize(int fieldsize) {
		this.fieldsize = fieldsize;
	}

	public Field[][] getBattlefield() {
		return battlefield;
	}

	public void setBattlefield(Field[][] battlefield) {
		this.battlefield = battlefield;
	}

	public int getBelongsToPlayer() {
		return belongsToPlayer;
	}

	public void setBelongsToPlayer(int belongsToPlayer) {
		this.belongsToPlayer = belongsToPlayer;
	}
}
