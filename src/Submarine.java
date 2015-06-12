import java.io.Serializable;


public class Submarine extends Ship implements Serializable{

	public Submarine(int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal, int fieldsize) {
		super(4, shipBelongsToPlayer, 2, 1, 1, "S", xCord, yCord, isHorizontal, false, true, fieldsize, 2, 1);
		shipId++;
	}
}
