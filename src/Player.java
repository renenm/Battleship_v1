
public class Player {
	private String name;
	private int playerId = 0;
	private boolean isDead = false;
	
	public Player() {}
	
	public Player(String name, int playerId) {
		this.name = name;
		this.playerId = playerId;
		this.isDead = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	
	
}
