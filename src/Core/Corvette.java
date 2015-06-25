package Core;
import java.io.Serializable;

/**
 * Klasse um eine Korvette zu erzeugen, erbt von Ship
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class Corvette extends Ship implements Serializable{
	
	/** VErsionsID für speichern/laden*/
	private static final long serialVersionUID = -1070270751332851414L;
	
	/**
	 * Konstruktor für eine Korvette
	 * @param shipId zur eindeutigen Identifikation dieser Korvette
	 */
	public Corvette(int shipId) {
		super(shipId, 3, 1, 1, "C", false, true, 3, 1, "Corvette");
	}
}
