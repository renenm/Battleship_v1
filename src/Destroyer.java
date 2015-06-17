import java.io.Serializable;

/**
 * Klasse um einen Zerst�rer zu erzeugen, erbt von Ship
 * @author Max K�ck, Rene Neumann, Justus C�ster
 */

public class Destroyer extends Ship implements Serializable{

	private static final long serialVersionUID = -7251467127327278602L;

	public Destroyer(int shipId, int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal, int fieldsize) {
		super(shipId, shipBelongsToPlayer, 5, 3, 3, "D", xCord, yCord, isHorizontal, false, true, fieldsize, 5, 3);
	}
}
