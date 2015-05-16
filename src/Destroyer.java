
public class Destroyer extends Ship {

	static private int shipId;
	
	public Destroyer(int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal) {
		super(shipId, shipBelongsToPlayer, 5, 3, 3, "D", xCord, yCord, isHorizontal, false, true);
		shipId++;
	}
}
