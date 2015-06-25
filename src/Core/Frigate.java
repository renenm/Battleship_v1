package Core;
import java.io.Serializable;

/**
 * Klasse um eine Frigatte zu erzeugen, erbt von Ship
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class Frigate extends Ship implements Serializable{
	
	/**VersionsID für speichern/laden*/
	private static final long serialVersionUID = 2923767458181572026L;
	
	/**
	 * Konstruktor für eine Frigatte
	 * @param shipId zur eindeutigen Identifikation dieser Frigatte
	 */
	public Frigate(int shipId) {
		super(shipId, 4, 2, 2, "F", false, true, 4, 2, "Frigate");
	}
}
