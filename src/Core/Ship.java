package Core;
import java.io.Serializable;

/**
 * Die Klasse wird zum erzeugen von Schiffen benötigt und ist die Superklasse der Schifftypen. Zu dem wird hier die relaodzeit überprüft
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class Ship implements Serializable{
	
	/**VersionsID für speichern/laden*/
	private static final long serialVersionUID = -1625395140503894621L;
	/**SchiffID*/
	protected int shipId;
	/**Zu welchem Spieler dieses Schiff gehört*/
	protected int shipBelongsToPlayer;
	/**Größe des Schiffes*/
	protected int shipSize;
	/**dynamische NachladeZeit*/
	protected int shipRespawn;
	/**Nachladezeit des Schiffes*/
	protected final int finalRespwan;
	/**Trefferradius des Schiffes*/
	protected int shipTargetRadius;
	/**Symbol des Schiffes zur Anzeige auf dem Spielfeld Bsp: Zerstörer = "D"*/
	protected String shipSymbol;
	/**Name des Schiffes*/
	protected String name;
	/**X-Koordinate des Schiffes*/
	protected int xCord;
	/**Y-Koordinate des Schiffes*/
	protected int yCord;
	protected boolean isHorizontal;
	protected boolean isShipDead;
	protected boolean isReady;
	/**Lebenspunkt des Schiffes*/
	protected int livePoints;
	
	/**
	 * Konstruktor für ein Schiff
	 * @param shipId ShiffID
	 * @param shipSize Größe des Schiffes
	 * @param shipRespawn dynamische Nachladezeit des Schiffes
	 * @param shipTargetRadius Trefferradius des Schiffes
	 * @param shipSymbol Symbol des Schiffes zur Anzege auf dem SPielfeld
	 * @param isShipDead
	 * @param isReady
	 * @param livePoints
	 * @param finalRespawn festgelegte Nachladezeit
	 * @param name Name des Schiffes
	 */
	public Ship(int shipId, int shipSize, int shipRespawn, int shipTargetRadius, String shipSymbol, boolean isShipDead, boolean isReady, int livePoints, int finalRespawn, String name) {
		this.shipId = shipId;
		this.shipSize = shipSize;
		this.shipRespawn = shipRespawn;
		this.shipTargetRadius = shipTargetRadius;
		this.shipSymbol = shipSymbol;
		this.isShipDead = isShipDead;
		this.isReady = isReady;
		this.livePoints = livePoints;
		this.finalRespwan = finalRespawn;
		this.name = name;
	}
	
	/**
	 * Die Methode überprüft in jeder Runde die Reloadzeit der Schiffe und setzt diesen gegebenenfalls wieder auf null
	 * @param ship Array mit allen Schiffen des Spiels
	 */
	public static void shipReload(Ship[][][] ship) {
		for (int i = 0; i < ship.length; i++) {
			for (int j = 0; j < ship[i].length; j++) {
				for (int h = 0; h < ship[i][j].length; h++) {
					ship[i][j][h].shipRespawn ++;
					if(ship[i][j][h].getShipRespawn() >= ship[i][j][h].finalRespwan) {
						ship[i][j][h].setReady(true);
					}
				}
			}
		}	
	}
	
	//Getter und Setter für die Attribute eines Schiffes
	public int getShipId() {
		return shipId;
	}
	
	public void setShipBelongsToPlayer(int shipBelongsToPLayer) {
		this.shipBelongsToPlayer = shipBelongsToPLayer;
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
	
	public void setXCord(int xCord) {
		this.xCord = xCord;
	}

	public int getxCord() {
		return xCord;
	}
	
	public void setYCord(int yCord) {
		this.yCord = yCord;
	}
	
	public int getyCord() {
		return yCord;
	}
	
	public void setHorizontal(boolean isHorizontal) {
		this.isHorizontal = isHorizontal;
	}

	public boolean isHorizontal() {
		return isHorizontal;
	}

	public boolean isShipDead() {
		return isShipDead;
	}

	public void setShipDead(boolean isShipDead) {
		this.isShipDead = isShipDead;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public int getLivePoints() {
		return livePoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
