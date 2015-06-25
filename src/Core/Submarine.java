package Core;
import java.io.Serializable;

/**
 * Klasse um einen U-Boot zu erzeugen, erbt von Ship
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class Submarine extends Ship implements Serializable{

	private static final long serialVersionUID = 4247451515760488489L;

	public Submarine(int shipId) {
		super(shipId, 2, 1, 1, "S", false, true, 2, 1, "Submarine");
	}
}
