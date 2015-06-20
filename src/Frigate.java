import java.io.Serializable;

/**
 * Klasse um eine Frigatte zu erzeugen, erbt von Ship
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class Frigate extends Ship implements Serializable{

	private static final long serialVersionUID = 2923767458181572026L;

	public Frigate(int shipId, int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal) {
		super(shipId, shipBelongsToPlayer, 4, 2, 2, "F", xCord, yCord, isHorizontal, false, true, 4, 2);
	}
}
