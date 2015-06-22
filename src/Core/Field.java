package Core;
import java.io.Serializable;

/**
 * Klasse die ein Feld auf dem Spielfeld verwaltet
 * @author Max K�ck, Rene Neumann, Justus C�ster
 */

public class Field implements Serializable{
	
	private static final long serialVersionUID = 2601581867209360826L;
	private String sign = "[ ]"; 
	private boolean isActive = false; 
	private boolean isHit = false;
	private boolean isWater = true; 
	private boolean isHitShip = false;
	private boolean isShip = false;
	private int shipId;

	public Field() {}
	
	public Field(boolean isActive, boolean isHit, boolean isWater, boolean isHitShip, boolean isShip) {
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

	public int getShipId() {
		return shipId;
	}

	public void setShipId(int shipId) {
		this.shipId = shipId;
	}	
}
