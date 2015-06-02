
public class Field {
	
	private String sign = "[ ]"; 
	private boolean isActive = false; 
	private boolean isHit = false;
	private boolean isWater = true; 
	private boolean isHitShip = false;
	private boolean isShip = false;

	public Field() {}
	
	public Field(boolean isActive, boolean isHit, boolean isWater, boolean isHitShip, boolean isShip) {
		this.sign = sign;
		this.isActive = isActive;
		this.isHit = isHit;
		this.isWater = isWater;
		this.isHitShip = isHitShip;
		this.isShip = isShip;
	}
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	public boolean isWater() {
		return isWater;
	}

	public void setWater(boolean isWater) {
		this.isWater = isWater;
	}

	public boolean isHitShip() {
		return isHitShip;
	}

	public void setHitShip(boolean isHitShip) {
		this.isHitShip = isHitShip;
	}

	public boolean isShip() {
		return isShip;
	}

	public void setShip(boolean isShip) {
		this.isShip = isShip;
	}	
}
