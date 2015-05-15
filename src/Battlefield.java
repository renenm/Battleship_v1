import java.util.Scanner;


public class Battlefield {
	
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
				battlefield[x][y] = new Field(false, false, true);
			}
		}
	}
	
	//Ausgabe Battlefield
	public void printBattlefield() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {
				
				battlefield[0][y].setSign("[" + y + "]");
				battlefield[x][0].setSign("[" + x + "]");
				battlefield[0][0].setSign("[ ]");
				
				System.out.print(battlefield[x][y].getSign() + " ");
			}
			System.out.println();
		}
	}
	
	//Ausgabe Active-Fields
	public void printActive() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {
				System.out.print(battlefield[x][y].isActive() + " ");
			}
			System.out.println();
		}
	}
	
	//Ausgabe Wasser
	public void printWater() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {
				System.out.print(battlefield[x][y].isWater() + " ");
			}
			System.out.println();
		}
	}
	
	//Ausgabe Hit
	public void printHit() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {
				System.out.print(battlefield[x][y].isHit() + " ");
			}
			System.out.println();
		}
	}
	
	//Ausgabe Raw
	public void printRaw() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {
				System.out.print(battlefield[x][y] + " ");
			}
			System.out.println();
		}
	}

	
	public static boolean isHorizontal() {
		Scanner scan = new Scanner(System.in);
		boolean isHorizontal;
		System.out.print("\t\tvertical / horizontal? [vert / hori]: ");
		String orientation = scan.next();
		if ("vert".equals(orientation)) {
			return false;
		} else if("hori".equals(orientation)) {
			return true;
		} else {
			System.out.print("\t\tPlease type vert or hori");
			isHorizontal();
		}
		return true;
	}
	
	public void shootShip(Ship myShip, int xCord, int yCord) {
		int respawn = myShip.getShipRespawn();
		
		if(respawn >= 1) {
			battlefield[xCord][yCord].setHit(true);
			myShip.setShipRespawn(respawn-1);
			
		} else {
			System.out.println("You have to wait.");
		}
	}
	
	public void placeShip(Ship ship) throws Exception {

		// Einlesen der Werte für das Setzen
		boolean isHorizontal = ship.isHorizontal;
		Scanner scan = new Scanner(System.in);
		int x = ship.getxCord();
		int y = ship.getyCord();
		//boolean hasPlace = hasPlace(ship);

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
	
	//Ausgabe Battlefield Gegnersicht
	public void printEnemyBattlefield() {
		for (int x = 0; x < battlefield.length; x++) {
			for (int y = 0; y < battlefield[x].length; y++) {
				
				battlefield[0][y].setSign("[" + y + "]");
				battlefield[x][0].setSign("[" + x + "]");
				battlefield[0][0].setSign("[ ]");
				
				if(battlefield[x][y].isHit()) {
					System.out.print("[H]" + " ");
				} else {
					System.out.print("[ ]" + " ");
				}
			}
			System.out.println();
		}
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
