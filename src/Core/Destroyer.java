package Core;
import java.io.Serializable;

/**
 * Klasse um einen Zerstörer zu erzeugen, erbt von Ship
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class Destroyer extends Ship implements Serializable{
	
	/** VersionsID für speichern/laden*/
	private static final long serialVersionUID = -7251467127327278602L;
	
	/**
	 * Konstruktor für einen Zerstörer
	 * @param shipId zur eindeutigen Identifikation dieses Zerstörers
	 */
	public Destroyer(int shipId) {
		super(shipId, 5, 3, 3, "D", false, true, 5, 3, "Destroyer");
	}
}
