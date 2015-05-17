
public class Submarine extends Ship {

	static private int shipId;
	
	public Submarine(int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal, int fieldsize) {
		super(shipId, shipBelongsToPlayer, 2, 1, 1, "S", xCord, yCord, isHorizontal, false, true, fieldsize);
		shipId++;
	}
}
