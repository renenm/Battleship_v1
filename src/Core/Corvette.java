package Core;
import java.io.Serializable;

/**
 * Klasse um eine Korvette zu erzeugen, erbt von Ship
 * @author Max K�ck, Rene Neumann, Justus C�ster
 */

public class Corvette extends Ship implements Serializable{
	
	/** VErsionsID f�r speichern/laden*/
	private static final long serialVersionUID = -1070270751332851414L;
	
	/**
	 * Konstruktor f�r eine Korvette
	 * @param shipId zur eindeutigen Identifikation dieser Korvette
	 */
	public Corvette(int shipId) {
		super(shipId, 3, 1, 1, "C", false, true, 3, 1, "Corvette");
	}
}
