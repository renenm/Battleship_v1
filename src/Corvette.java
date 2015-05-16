
public class Corvette extends Ship {

	static private int shipId;
	
	public Corvette(int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal) {
		super(shipId, shipBelongsToPlayer, 3, 1, 1, "C", xCord, yCord, isHorizontal, false, true);
		shipId++;
	}
}
