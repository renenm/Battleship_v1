import java.io.Serializable;


public class Destroyer extends Ship implements Serializable{

	public Destroyer(int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal, int fieldsize) {
		super(1, shipBelongsToPlayer, 5, 3, 3, "D", xCord, yCord, isHorizontal, false, true, fieldsize, 5, 3);
		shipId++;
	}
}
