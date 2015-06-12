import java.io.Serializable;


public class Corvette extends Ship implements Serializable{

	public Corvette(int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal, int fieldsize) {
		super(3, shipBelongsToPlayer, 3, 1, 1, "C", xCord, yCord, isHorizontal, false, true, fieldsize, 3, 1);
		shipId++;
	}
}
