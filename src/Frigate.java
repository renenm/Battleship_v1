
public class Frigate extends Ship {

	static private int shipId;
	
	public Frigate(int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal, int fieldsize) {
		super(shipId, shipBelongsToPlayer, 4, 2, 2, "F", xCord, yCord, isHorizontal, false, true, fieldsize);
		shipId++;
	}
}
