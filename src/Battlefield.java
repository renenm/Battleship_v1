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
					if(battlefield[x][y].getSign().equals("[D]") || battlefield[x][y].getSign().equals("[F]") || battlefield[x][y].getSign().equals("[C]") || battlefield[x][y].getSign().equals("[S]")) {
						System.out.print("[ ]");
					} else {
						System.out.print(battlefield[x][y].getSign());
					}
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
	
	public void shootShip(Ship myShip, int xCord, int yCord) throws Exception {
		myShip.setReady(false);
		myShip.setShipRespawn(0);
		int targetRadius = myShip.getShipTargetRadius();
		
		//AB HIER WIRD AUCH DER RADIUS BESCHOSSEN
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
					} else if(battlefield[yCord][xCord + i].isWater() == true) {
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
				} else if(battlefield[yCord][xCord + i].isWater() == true) {
					battlefield[yCord][xCord + i].setHit(true);
					battlefield[yCord][xCord + i].setSign("[X]");
				}
			}
		}
			
			
			//SCHIFFE STERBEN HIER - RIP
			
			if(myShip.isHorizontal) {
				///DESTROYER + BEREICH
				try {
					if(!battlefield[xCord - 0][yCord].equals("[D]")) {
						if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit() && battlefield[xCord + 4][yCord].isHit() && battlefield[xCord + 5][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord - 1][yCord].equals("[D]")) {
						if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit() && battlefield[xCord + 4][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord - 2][yCord].equals("[D]")) {
						if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord - 3][yCord].equals("[D]")) {
						if(battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord - 4][yCord].equals("[D]")) {
						if(battlefield[xCord - 3][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					///DESTROYER - BEREICH
					if(!battlefield[xCord + 0][yCord].equals("[D]")) {
						if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit() && battlefield[xCord - 4][yCord].isHit() && battlefield[xCord - 5][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord + 1][yCord].equals("[D]")) {
						if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit() && battlefield[xCord - 4][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord + 2][yCord].equals("[D]")) {
						if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord + 3][yCord].equals("[D]")) {
						if(battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord + 4][yCord].equals("[D]")) {
						if(battlefield[xCord + 3][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					///Frigate + BEREICH
					if(!battlefield[xCord - 0][yCord].equals("[F]")) {
						if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit() && battlefield[xCord + 4][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord - 1][yCord].equals("[F]")) {
						if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit()){
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord - 2][yCord].equals("[F]")) {
						if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord - 3][yCord].equals("[F]")) {
						if(battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					///Frigate - BEREICH
					if(!battlefield[xCord + 0][yCord].equals("[F]")) {
						if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit() && battlefield[xCord - 4][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord + 1][yCord].equals("[F]")) {
						if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord + 2][yCord].equals("[F]")) {
						if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord + 3][yCord].equals("[F]")) {
						if(battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					///Corvette + BEREICH
					if(!battlefield[xCord - 0][yCord].equals("[C]")) {
						if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit() && battlefield[xCord + 3][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord - 1][yCord].equals("[C]")) {
						if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit()){
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord - 2][yCord].equals("[C]")) {
						if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					///Corvette - BEREICH
					if(!battlefield[xCord + 0][yCord].equals("[C]")) {
						if(battlefield[xCord -1 ][yCord].isHit() && battlefield[xCord - 2][yCord].isHit() && battlefield[xCord - 3][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord + 1][yCord].equals("[C]")) {
						if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord + 2][yCord].equals("[C]")) {
						if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					///Submarine + BEREICH
					if(!battlefield[xCord - 0][yCord].equals("[S]")) {
						if(battlefield[xCord + 1][yCord].isHit() && battlefield[xCord + 2][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord - 1][yCord].equals("[S]")) {
						if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord + 1][yCord].isHit()){
							myShip.setDead(true);
						}
					}
					///Submarine - BEREICH
					if(!battlefield[xCord + 0][yCord].equals("[S]")) {
						if(battlefield[xCord - 1][yCord].isHit() && battlefield[xCord - 2][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord + 1][yCord].equals("[S]")) {
						if(battlefield[xCord - 0][yCord].isHit() && battlefield[xCord - 1][yCord].isHit()) {
							myShip.setDead(true);
						}
					}
				} catch(Exception a1) {  }
			} else { //VERTIKAL!!!
				///DESTROYER + BEREICH
				try {
					if(!battlefield[xCord][yCord - 0].equals("[D]")) {
						if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit() && battlefield[xCord][yCord + 4].isHit() && battlefield[xCord][yCord + 5].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord - 1].equals("[D]")) {
						if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit() && battlefield[xCord][yCord + 4].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord - 2].equals("[D]")) {
						if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord - 3].equals("[D]")) {
						if(battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord - 4].equals("[D]")) {
						if(battlefield[xCord][yCord - 3].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit()) {
							myShip.setDead(true);
						}
					}
					///DESTROYER - BEREICH
					if(!battlefield[xCord][yCord + 0].equals("[D]")) {
						if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit() && battlefield[xCord][yCord - 4].isHit() && battlefield[xCord][yCord - 5].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord + 1].equals("[D]")) {
						if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit() && battlefield[xCord][yCord - 4].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord + 2].equals("[D]")) {
						if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord + 3].equals("[D]")) {
						if(battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord + 4].equals("[D]")) {
						if(battlefield[xCord][yCord + 3].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit()) {
							myShip.setDead(true);
						}
					}
					///Frigate + BEREICH
					if(!battlefield[xCord][yCord - 0].equals("[F]")) {
						if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit() && battlefield[xCord][yCord + 4].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord - 1].equals("[F]")) {
						if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord - 2].equals("[F]")) {
						if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord - 3].equals("[F]")) {
						if(battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit()) {
							myShip.setDead(true);
						}
					}
					///Frigate - BEREICH
					if(!battlefield[xCord][yCord + 0].equals("[F]")) {
						if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit() && battlefield[xCord][yCord - 4].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord + 1].equals("[F]")) {
						if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord + 2].equals("[F]")) {
						if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord + 3].equals("[F]")) {
						if(battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 0].isHit() && battlefield[xCord][yCord - 1].isHit()) {
							myShip.setDead(true);
						}
					}
					///Corvette + BEREICH
					if(!battlefield[xCord][yCord - 0].equals("[C]")) {
						if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit() && battlefield[xCord][yCord + 3].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord - 1].equals("[C]")) {
						if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit()){
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord - 2].equals("[C]")) {
						if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit()) {
							myShip.setDead(true);
						}
					}
					///Corvette - BEREICH
					if(!battlefield[xCord][yCord + 0].equals("[C]")) {
						if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit() && battlefield[xCord][yCord - 3].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord + 1].equals("[C]")) {
						if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord + 2].equals("[C]")) {
						if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit()) {
							myShip.setDead(true);
						}
					}
					///Submarine + BEREICH
					if(!battlefield[xCord][yCord - 0].equals("[S]")) {
						if(battlefield[xCord][yCord + 1].isHit() && battlefield[xCord][yCord + 2].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord - 1].equals("[S]")) {
						if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord + 1].isHit()){
							myShip.setDead(true);
						}
					}
					///Submarine - BEREICH
					if(!battlefield[xCord][yCord + 0].equals("[S]")) {
						if(battlefield[xCord][yCord - 1].isHit() && battlefield[xCord][yCord - 2].isHit()) {
							myShip.setDead(true);
						}
					}
					if(!battlefield[xCord][yCord + 1].equals("[S]")) {
						if(battlefield[xCord][yCord - 0].isHit() && battlefield[xCord][yCord - 1].isHit()) {
							myShip.setDead(true);
						}
					}
				} catch(Exception a1) {  }
			}
			//myShip.setDead(true);
			
		//}
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
						battlefield[y][x + i].setShip(true);
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
