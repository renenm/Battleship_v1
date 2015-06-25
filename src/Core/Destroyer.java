package Core;
import java.io.Serializable;

/**
 * Klasse um einen Zerst�rer zu erzeugen, erbt von Ship
 * @author Max K�ck, Rene Neumann, Justus C�ster
 */

public class Destroyer extends Ship implements Serializable{
	
	/** VersionsID f�r speichern/laden*/
	private static final long serialVersionUID = -7251467127327278602L;
	
	/**
	 * Konstruktor f�r einen Zerst�rer
	 * @param shipId zur eindeutigen Identifikation dieses Zerst�rers
	 */
	public Destroyer(int shipId) {
		super(shipId, 5, 3, 3, "D", false, true, 5, 3, "Destroyer");
	}
}
