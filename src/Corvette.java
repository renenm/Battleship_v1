import java.io.Serializable;

/**
 * Klasse um eine Korvette zu erzeugen, erbt von Ship
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class Corvette extends Ship implements Serializable{

	private static final long serialVersionUID = -1070270751332851414L;

	public Corvette(int shipId, int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal, int fieldsize) {
		super(shipId, shipBelongsToPlayer, 3, 1, 1, "C", xCord, yCord, isHorizontal, false, true, fieldsize, 3, 1);
	}
}
