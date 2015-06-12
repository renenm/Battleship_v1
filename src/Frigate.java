import java.io.Serializable;


public class Frigate extends Ship implements Serializable{

	public Frigate(int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal, int fieldsize) {
		super(2, shipBelongsToPlayer, 4, 2, 2, "F", xCord, yCord, isHorizontal, false, true, fieldsize, 4, 2);
		shipId++;
	}
}
