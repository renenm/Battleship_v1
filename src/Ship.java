import java.io.Serializable;

/**
 * Die Klasse wird zum erzeugen von Schiffen benötigt und ist die Superklasse der Schifftypen. Zu dem wird hier die relaodzeit überprüft
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class Ship implements Serializable{

	private static final long serialVersionUID = -1625395140503894621L;
	protected int shipId;
	protected int shipBelongsToPlayer;
	protected int shipSize;
	protected int shipRespawn;
	protected final int finalRespwan;
	protected int shipTargetRadius;
	protected String shipSymbol;
	protected int xCord;
	protected int yCord;
	protected boolean isHorizontal;
	protected boolean isDead;
	protected boolean isReady;
	protected int fieldsize;
	protected int livePoints;
	
	public Ship(int shipId, int shipBelongsToPlayer, int shipSize, int shipRespawn, int shipTargetRadius, String shipSymbol, int xCord, int yCord, boolean isHorizontal, boolean isDead, boolean isReady, int fieldsize, int livePoints, int finalRespawn) {
		this.shipId = shipId;
		this.shipBelongsToPlayer = shipBelongsToPlayer;
		this.shipSize = shipSize;
		this.shipRespawn = shipRespawn;
		this.shipTargetRadius = shipTargetRadius;
		this.shipSymbol = shipSymbol;
		this.xCord = xCord;
		this.yCord = yCord;
		this.isHorizontal = isHorizontal;
		this.isDead = isDead;
		this.isReady = isReady;
		this.fieldsize = fieldsize;
		this.livePoints = livePoints;
		this.finalRespwan = finalRespawn;
	}
	
	/**
	 * Die Methode überprüft in jeder Runde die Reloadzeit der Schiffe und setzt diesen gegebenenfalls wieder auf null
	 * @param destroyer Objektarray, in dem die Zerstörer gespeichert sind
	 * @param frigate Objektarray, in dem die Frigatten gespeichert sind
	 * @param corvette Objektarray, in dem die Korvetten gespeichert sind
	 * @param submarine Objektarray, in dem die U-Boote gespeichert sind
	 * @param i int-Wert, um den Spieler, der an der rihe ist zu identifizieren
	 */
	public static void shipReload(Destroyer[][] destroyer, Frigate[][] frigate, Corvette[][] corvette, Submarine[][] submarine, int i) {
		
		for (int j = 0; j < destroyer[i].length; j++) {
			destroyer[i][j].shipRespawn ++;
			if(destroyer[i][j].getShipRespawn() >= destroyer[i][j].finalRespwan) {
				destroyer[i][j].setReady(true);
			}
		}
		for (int j = 0; j < frigate[i].length; j++) {
			frigate[i][j].shipRespawn ++;
			if(frigate[i][j].getShipRespawn() >= frigate[i][j].finalRespwan) {
				frigate[i][j].setReady(true);
			}
		}
		for (int j = 0; j < corvette[i].length; j++) {
			corvette[i][j].shipRespawn ++;
			if(corvette[i][j].getShipRespawn() >= corvette[i][j].finalRespwan) {
				corvette[i][j].setReady(true);
			}
		}
		for (int j = 0; j < submarine[i].length; j++) {
			submarine[i][j].shipRespawn ++;
			if(submarine[i][j].getShipRespawn() >= submarine[i][j].finalRespwan) {
				submarine[i][j].setReady(true);
			}
		}
		
	}

	public int getShipId() {
		return shipId;
	}

	public int getShipBelongsToPlayer() {
		return shipBelongsToPlayer;
	}

	public int getShipSize() {
		return shipSize;
	}

	public int getShipRespawn() {
		return shipRespawn;
	}

	public void setShipRespawn(int shipRespawn) {
		this.shipRespawn = shipRespawn;
	}

	public int getShipTargetRadius() {
		return shipTargetRadius;
	}

	public String getShipSymbol() {
		return shipSymbol;
	}

	public int getxCord() {
		return xCord;
	}

	public int getyCord() {
		return yCord;
	}

	public boolean isHorizontal() {
		return isHorizontal;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public int getFieldsize() {
		return fieldsize;
	}

	public int getLivePoints() {
		return livePoints;
	}	
}
