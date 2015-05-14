
public class Frigate extends Ship {

	static private int shipId;
	
	public Frigate(int shipBelongsToPlayer, int xCord, int yCord, boolean isHorizontal) {
		super(shipId, shipBelongsToPlayer, 3, 2, 2, "F", xCord, yCord, isHorizontal, false);
		shipId++;
	}
}
