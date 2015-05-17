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
	
	public static void shipReload(Ship myShip) { //UNBENUTZT
		int respawn = myShip.getShipRespawn();
		myShip.setShipRespawn(respawn + 1);
		if(myShip.getShipSymbol().equals("D")) {
			if(myShip.getShipRespawn() >= 3) {
				myShip.setReady(true);
			} else {
				
			}
		}
		if(myShip.getShipSymbol().equals("F")) {
			if(myShip.getShipRespawn() >= 2) {
				myShip.setReady(true);
			} else {
				myShip.setShipRespawn(respawn + 1);
			}
		}
		if(myShip.getShipSymbol().equals("C")) {
			if(myShip.getShipRespawn() >= 1) {
				myShip.setReady(true);
			} else {
				myShip.setShipRespawn(respawn + 1);
			}
		}
		if(myShip.getShipSymbol().equals("S")) {
			if(myShip.getShipRespawn() >= 1) {
				myShip.setReady(true);
			} else {
				myShip.setShipRespawn(respawn + 1);
			}
		}
	}
	
	public void shootShip(Ship myShip, int xCord, int yCord) {
		myShip.setReady(false);
		myShip.setShipRespawn(0);
		//if(myShip.isReady()) {
			if(isShipThere(xCord, yCord)) {
				battlefield[yCord][xCord].setHit(true);
			}
			
			//AB HIER WIRD AUCH DER RADIUS BESCHOSSEN
			if(myShip.getShipSymbol().equals("D")) { //F‹R DEN RADIUS DES DESTROYER
				//1. Reihe (v.l.n.r)
				if(battlefield[yCord - 2][xCord + 2].getSign().equals("[D]") || battlefield[yCord - 2][xCord + 2].getSign().equals("[F]") || battlefield[yCord - 2][xCord + 2].getSign().equals("[C]") || battlefield[yCord - 2][xCord + 2].getSign().equals("[S]")) {
					battlefield[yCord - 2][xCord + 2].setHit(true);
				}
				if(battlefield[yCord - 1][xCord + 2].getSign().equals("[D]") || battlefield[yCord - 1][xCord + 2].getSign().equals("[F]") || battlefield[yCord - 1][xCord + 2].getSign().equals("[C]") || battlefield[yCord - 1][xCord + 2].getSign().equals("[S]")) {
					battlefield[yCord - 1][xCord + 2].setHit(true);
				}
				if(battlefield[yCord - 0][xCord + 2].getSign().equals("[D]") || battlefield[yCord - 0][xCord + 2].getSign().equals("[F]") || battlefield[yCord - 0][xCord + 2].getSign().equals("[C]") || battlefield[yCord - 0][xCord + 2].getSign().equals("[S]")) {
					battlefield[yCord - 0][xCord + 2].setHit(true);
				}
				if(battlefield[yCord + 1][xCord + 2].getSign().equals("[D]") || battlefield[yCord + 1][xCord + 2].getSign().equals("[F]") || battlefield[yCord + 1][xCord + 2].getSign().equals("[C]") || battlefield[yCord + 1][xCord + 2].getSign().equals("[S]")) {
					battlefield[yCord + 1][xCord + 2].setHit(true);
				}
				if(battlefield[yCord + 2][xCord + 2].getSign().equals("[D]") || battlefield[yCord + 2][xCord + 2].getSign().equals("[F]") || battlefield[yCord + 2][xCord + 2].getSign().equals("[C]") || battlefield[yCord + 2][xCord + 2].getSign().equals("[S]")) {
					battlefield[yCord + 2][xCord + 2].setHit(true);
				}
				//2. Reihe (v.l.n.r)
				if(battlefield[yCord - 2][xCord + 1].getSign().equals("[D]") || battlefield[yCord - 2][xCord + 1].getSign().equals("[F]") || battlefield[yCord - 2][xCord + 1].getSign().equals("[C]") || battlefield[yCord - 2][xCord + 1].getSign().equals("[S]")) {
					battlefield[yCord - 2][xCord + 1].setHit(true);
				}
				if(battlefield[yCord - 1][xCord + 1].getSign().equals("[D]") || battlefield[yCord - 1][xCord + 1].getSign().equals("[F]") || battlefield[yCord - 1][xCord + 1].getSign().equals("[C]") || battlefield[yCord - 1][xCord + 1].getSign().equals("[S]")) {
					battlefield[yCord - 1][xCord + 1].setHit(true);
				}
				if(battlefield[yCord - 0][xCord + 1].getSign().equals("[D]") || battlefield[yCord - 0][xCord + 1].getSign().equals("[F]") || battlefield[yCord - 0][xCord + 1].getSign().equals("[C]") || battlefield[yCord - 0][xCord + 1].getSign().equals("[S]")) {
					battlefield[yCord - 0][xCord + 1].setHit(true);
				}
				if(battlefield[yCord + 1][xCord + 1].getSign().equals("[D]") || battlefield[yCord + 1][xCord + 1].getSign().equals("[F]") || battlefield[yCord + 1][xCord + 1].getSign().equals("[C]") || battlefield[yCord + 1][xCord + 1].getSign().equals("[S]")) {
					battlefield[yCord + 1][xCord + 1].setHit(true);
				}
				if(battlefield[yCord + 2][xCord + 1].getSign().equals("[D]") || battlefield[yCord + 2][xCord + 1].getSign().equals("[F]") || battlefield[yCord + 2][xCord + 1].getSign().equals("[C]") || battlefield[yCord + 2][xCord + 1].getSign().equals("[S]")) {
					battlefield[yCord + 2][xCord + 1].setHit(true);
				}
				//3. Reihe (v.l.n.r)
				if(battlefield[yCord - 2][xCord + 0].getSign().equals("[D]") || battlefield[yCord - 2][xCord + 0].getSign().equals("[F]") || battlefield[yCord - 2][xCord + 0].getSign().equals("[C]") || battlefield[yCord - 2][xCord + 0].getSign().equals("[S]")) {
					battlefield[yCord - 2][xCord + 0].setHit(true);
				}
				if(battlefield[yCord - 1][xCord + 0].getSign().equals("[D]") || battlefield[yCord - 1][xCord + 0].getSign().equals("[F]") || battlefield[yCord - 1][xCord + 0].getSign().equals("[C]") || battlefield[yCord - 1][xCord + 0].getSign().equals("[S]")) {
					battlefield[yCord - 1][xCord + 0].setHit(true);
				}
				if(battlefield[yCord - 0][xCord + 0].getSign().equals("[D]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[F]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[C]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[S]")) {
					battlefield[yCord - 0][xCord + 0].setHit(true);
				}
				if(battlefield[yCord + 1][xCord + 0].getSign().equals("[D]") || battlefield[yCord + 1][xCord + 0].getSign().equals("[F]") || battlefield[yCord + 1][xCord + 0].getSign().equals("[C]") || battlefield[yCord + 1][xCord + 0].getSign().equals("[S]")) {
					battlefield[yCord + 1][xCord + 0].setHit(true);
				}
				if(battlefield[yCord + 2][xCord + 0].getSign().equals("[D]") || battlefield[yCord + 2][xCord + 0].getSign().equals("[F]") || battlefield[yCord + 2][xCord + 0].getSign().equals("[C]") || battlefield[yCord + 2][xCord + 0].getSign().equals("[S]")) {
					battlefield[yCord + 2][xCord + 0].setHit(true);
				}
				//4. Reihe (v.l.n.r)
				if(battlefield[yCord - 2][xCord - 1].getSign().equals("[D]") || battlefield[yCord - 2][xCord - 1].getSign().equals("[F]") || battlefield[yCord - 2][xCord - 1].getSign().equals("[C]") || battlefield[yCord - 2][xCord - 1].getSign().equals("[S]")) {
					battlefield[yCord - 2][xCord - 1].setHit(true);
				}
				if(battlefield[yCord - 1][xCord - 1].getSign().equals("[D]") || battlefield[yCord - 1][xCord - 1].getSign().equals("[F]") || battlefield[yCord - 1][xCord - 1].getSign().equals("[C]") || battlefield[yCord - 1][xCord - 1].getSign().equals("[S]")) {
					battlefield[yCord - 1][xCord - 1].setHit(true);
				}
				if(battlefield[yCord - 0][xCord - 1].getSign().equals("[D]") || battlefield[yCord - 0][xCord - 1].getSign().equals("[F]") || battlefield[yCord - 0][xCord - 1].getSign().equals("[C]") || battlefield[yCord - 0][xCord - 1].getSign().equals("[S]")) {
					battlefield[yCord - 0][xCord - 1].setHit(true);
				}
				if(battlefield[yCord + 1][xCord - 1].getSign().equals("[D]") || battlefield[yCord + 1][xCord - 1].getSign().equals("[F]") || battlefield[yCord + 1][xCord - 1].getSign().equals("[C]") || battlefield[yCord + 1][xCord - 1].getSign().equals("[S]")) {
					battlefield[yCord + 1][xCord - 1].setHit(true);
				}
				if(battlefield[yCord + 2][xCord - 1].getSign().equals("[D]") || battlefield[yCord + 2][xCord - 1].getSign().equals("[F]") || battlefield[yCord + 2][xCord - 1].getSign().equals("[C]") || battlefield[yCord + 2][xCord - 1].getSign().equals("[S]")) {
					battlefield[yCord + 2][xCord - 1].setHit(true);
				}
				//5. Reihe (v.l.n.r)
				if(battlefield[yCord - 2][xCord - 2].getSign().equals("[D]") || battlefield[yCord - 2][xCord - 2].getSign().equals("[F]") || battlefield[yCord - 2][xCord - 2].getSign().equals("[C]") || battlefield[yCord - 2][xCord - 2].getSign().equals("[S]")) {
					battlefield[yCord - 2][xCord - 2].setHit(true);
				}
				if(battlefield[yCord - 1][xCord - 2].getSign().equals("[D]") || battlefield[yCord - 1][xCord - 2].getSign().equals("[F]") || battlefield[yCord - 1][xCord - 2].getSign().equals("[C]") || battlefield[yCord - 1][xCord - 2].getSign().equals("[S]")) {
					battlefield[yCord - 1][xCord - 2].setHit(true);
				}
				if(battlefield[yCord - 0][xCord - 2].getSign().equals("[D]") || battlefield[yCord - 0][xCord - 2].getSign().equals("[F]") || battlefield[yCord - 0][xCord - 2].getSign().equals("[C]") || battlefield[yCord - 0][xCord - 2].getSign().equals("[S]")) {
					battlefield[yCord - 0][xCord - 2].setHit(true);
				}
				if(battlefield[yCord + 1][xCord - 2].getSign().equals("[D]") || battlefield[yCord + 1][xCord - 2].getSign().equals("[F]") || battlefield[yCord + 1][xCord - 2].getSign().equals("[C]") || battlefield[yCord + 1][xCord - 2].getSign().equals("[S]")) {
					battlefield[yCord + 1][xCord - 2].setHit(true);
				}
				if(battlefield[yCord + 2][xCord - 2].getSign().equals("[D]") || battlefield[yCord + 2][xCord - 2].getSign().equals("[F]") || battlefield[yCord + 2][xCord - 2].getSign().equals("[C]") || battlefield[yCord + 2][xCord - 2].getSign().equals("[S]")) {
					battlefield[yCord + 2][xCord - 2].setHit(true);
				}
			}
			if(myShip.getShipSymbol().equals("F")) { //F‹R DEN RADIUS DER FRIGATE
				//2. Reihe (v.l.n.r)
				if(battlefield[yCord - 1][xCord + 1].getSign().equals("[D]") || battlefield[yCord - 1][xCord + 1].getSign().equals("[F]") || battlefield[yCord - 1][xCord + 1].getSign().equals("[C]") || battlefield[yCord - 1][xCord + 1].getSign().equals("[S]")) {
					battlefield[yCord - 1][xCord + 1].setHit(true);
				}
				if(battlefield[yCord - 0][xCord + 1].getSign().equals("[D]") || battlefield[yCord - 0][xCord + 1].getSign().equals("[F]") || battlefield[yCord - 0][xCord + 1].getSign().equals("[C]") || battlefield[yCord - 0][xCord + 1].getSign().equals("[S]")) {
					battlefield[yCord - 0][xCord + 1].setHit(true);
				}
				if(battlefield[yCord + 1][xCord + 1].getSign().equals("[D]") || battlefield[yCord + 1][xCord + 1].getSign().equals("[F]") || battlefield[yCord + 1][xCord + 1].getSign().equals("[C]") || battlefield[yCord + 1][xCord + 1].getSign().equals("[S]")) {
					battlefield[yCord + 1][xCord + 1].setHit(true);
				}
				//3. Reihe (v.l.n.r)
				if(battlefield[yCord - 1][xCord + 0].getSign().equals("[D]") || battlefield[yCord - 1][xCord + 0].getSign().equals("[F]") || battlefield[yCord - 1][xCord + 0].getSign().equals("[C]") || battlefield[yCord - 1][xCord + 0].getSign().equals("[S]")) {
					battlefield[yCord - 1][xCord + 0].setHit(true);
				}
				if(battlefield[yCord - 0][xCord + 0].getSign().equals("[D]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[F]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[C]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[S]")) {
					battlefield[yCord - 0][xCord + 0].setHit(true);
				}
				if(battlefield[yCord + 1][xCord + 0].getSign().equals("[D]") || battlefield[yCord + 1][xCord + 0].getSign().equals("[F]") || battlefield[yCord + 1][xCord + 0].getSign().equals("[C]") || battlefield[yCord + 1][xCord + 0].getSign().equals("[S]")) {
					battlefield[yCord + 1][xCord + 0].setHit(true);
				}
				//4. Reihe (v.l.n.r)
				if(battlefield[yCord - 1][xCord - 1].getSign().equals("[D]") || battlefield[yCord - 1][xCord - 1].getSign().equals("[F]") || battlefield[yCord - 1][xCord - 1].getSign().equals("[C]") || battlefield[yCord - 1][xCord - 1].getSign().equals("[S]")) {
					battlefield[yCord - 1][xCord - 1].setHit(true);
				}
				if(battlefield[yCord - 0][xCord - 1].getSign().equals("[D]") || battlefield[yCord - 0][xCord - 1].getSign().equals("[F]") || battlefield[yCord - 0][xCord - 1].getSign().equals("[C]") || battlefield[yCord - 0][xCord - 1].getSign().equals("[S]")) {
					battlefield[yCord - 0][xCord - 1].setHit(true);
				}
				if(battlefield[yCord + 1][xCord - 1].getSign().equals("[D]") || battlefield[yCord + 1][xCord - 1].getSign().equals("[F]") || battlefield[yCord + 1][xCord - 1].getSign().equals("[C]") || battlefield[yCord + 1][xCord - 1].getSign().equals("[S]")) {
					battlefield[yCord + 1][xCord - 1].setHit(true);
				}
			}
			if(myShip.getShipSymbol().equals("C")) { //F‹R DEN RADIUS DER CORVETTE	
				//3. Reihe (v.l.n.r)
				if(battlefield[yCord - 0][xCord + 0].getSign().equals("[D]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[F]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[C]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[S]")) {
					battlefield[yCord - 0][xCord + 0].setHit(true);
				}
			}
			if(myShip.getShipSymbol().equals("S")) { //F‹R DEN RADIUS DER SUBMARINE
				//3. Reihe (v.l.n.r)
				if(battlefield[yCord - 0][xCord + 0].getSign().equals("[D]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[F]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[C]") || battlefield[yCord - 0][xCord + 0].getSign().equals("[S]")) {
					battlefield[yCord - 0][xCord + 0].setHit(true);
				}
			}
			
			//SCHIFFE STERBEN HIER - RIP
			if(!myShip.isHorizontal) {
				///DESTROYER + BEREICH
				if(!battlefield[xCord - 0][yCord].equals("[D]")) {
					if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit() && battlefield[xCord + 4][yCord].isHit() && battlefield[xCord + 5][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord - 1][yCord].equals("[D]")) {
					if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit() && battlefield[xCord + 4][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord - 2][yCord].equals("[D]")) {
					if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord - 3][yCord].equals("[D]")) {
					if(battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord - 4][yCord].equals("[D]")) {
					if(battlefield[xCord - 3][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///DESTROYER - BEREICH
				else if(!battlefield[xCord + 0][yCord].equals("[D]")) {
					if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit() && battlefield[xCord - 4][yCord].isHit() && battlefield[xCord - 5][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord + 1][yCord].equals("[D]")) {
					if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit() && battlefield[xCord - 4][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord + 2][yCord].equals("[D]")) {
					if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord + 3][yCord].equals("[D]")) {
					if(battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord + 4][yCord].equals("[D]")) {
					if(battlefield[xCord + 3][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Frigate + BEREICH
				else if(!battlefield[xCord - 0][yCord].equals("[F]")) {
					if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit() && battlefield[xCord + 4][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord - 1][yCord].equals("[F]")) {
					if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit()){
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord - 2][yCord].equals("[F]")) {
					if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord - 3][yCord].equals("[F]")) {
					if(battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Frigate - BEREICH
				else if(!battlefield[xCord + 0][yCord].equals("[F]")) {
					if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit() && battlefield[xCord - 4][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord + 1][yCord].equals("[F]")) {
					if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord + 2][yCord].equals("[F]")) {
					if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord + 3][yCord].equals("[F]")) {
					if(battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Corvette + BEREICH
				else if(!battlefield[xCord - 0][yCord].equals("[C]")) {
					if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord - 1][yCord].equals("[C]")) {
					if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit()){
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord - 2][yCord].equals("[C]")) {
					if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Corvette - BEREICH
				else if(!battlefield[xCord + 0][yCord].equals("[C]")) {
					if(battlefield[xCord -1 ][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord + 1][yCord].equals("[C]")) {
					if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord + 2][yCord].equals("[C]")) {
					if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Submarine + BEREICH
				else if(!battlefield[xCord - 0][yCord].equals("[S]")) {
					if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord - 1][yCord].equals("[S]")) {
					if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit()){
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Submarine - BEREICH
				else if(!battlefield[xCord + 0][yCord].equals("[S]")) {
					if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord + 1][yCord].equals("[S]")) {
					if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
			} else { //VERTIKAL!!!
				///DESTROYER + BEREICH
				if(!battlefield[xCord][yCord - 0].equals("[D]")) {
					if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit() && battlefield[xCord][yCord + 4].isHit() && battlefield[xCord][yCord + 5].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord - 1].equals("[D]")) {
					if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit() && battlefield[xCord][yCord + 4].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord - 2].equals("[D]")) {
					if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord - 3].equals("[D]")) {
					if(battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord - 4].equals("[D]")) {
					if(battlefield[xCord][yCord - 3].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///DESTROYER - BEREICH
				else if(!battlefield[xCord][yCord + 0].equals("[D]")) {
					if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit() && battlefield[xCord][yCord - 4].isHit() && battlefield[xCord][yCord - 5].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord + 1].equals("[D]")) {
					if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit() && battlefield[xCord][yCord - 4].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord + 2].equals("[D]")) {
					if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord + 3].equals("[D]")) {
					if(battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord + 4].equals("[D]")) {
					if(battlefield[xCord][yCord + 3].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Frigate + BEREICH
				else if(!battlefield[xCord][yCord - 0].equals("[F]")) {
					if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit() && battlefield[xCord][yCord + 4].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord - 1].equals("[F]")) {
					if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord - 2].equals("[F]")) {
					if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord - 3].equals("[F]")) {
					if(battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Frigate - BEREICH
				else if(!battlefield[xCord][yCord + 0].equals("[F]")) {
					if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit() && battlefield[xCord][yCord - 4].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord + 1].equals("[F]")) {
					if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord + 2].equals("[F]")) {
					if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord + 3].equals("[F]")) {
					if(battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 0].isHit() && battlefield[xCord][yCord - 1].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Corvette + BEREICH
				else if(!battlefield[xCord][yCord - 0].equals("[C]")) {
					if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord - 1].equals("[C]")) {
					if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit()){
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord - 2].equals("[C]")) {
					if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Corvette - BEREICH
				else if(!battlefield[xCord][yCord + 0].equals("[C]")) {
					if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord + 1].equals("[C]")) {
					if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord + 2].equals("[C]")) {
					if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Submarine + BEREICH
				else if(!battlefield[xCord][yCord - 0].equals("[S]")) {
					if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord - 1].equals("[S]")) {
					if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit()){
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				///Submarine - BEREICH
				else if(!battlefield[xCord][yCord + 0].equals("[S]")) {
					if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
				else if(!battlefield[xCord][yCord + 1].equals("[S]")) {
					if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit()) {
						myShip.setDead(true); System.out.println("TEST NUDEL ARSCH");
					}
				}
			}
		//}
	}
	
	public void placeShip(Ship ship) throws Exception {

		// Einlesen der Werte f√ºr das Setzen
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
	
	// Abfrage ob das Schiff innerhalb des Spielfeldes gesetzt werden soll, f√ºr setShip
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
