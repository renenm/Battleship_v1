package CustomExceptions;

/**
 * Die Exception wird für das setzen und schießen der Schiffe benötigt und erscheint, wenn außerhabl des Spielfeldes gesetzt werden soll
 * @author Max Kück, Renen Neumann, Justus Cöster
 *
 */
public class LowerSideException extends Exception {
	
	private static final long serialVersionUID = -61771888028423913L;

	public LowerSideException() {
		super("ArrayIndexOutOfBoundsException at LowerSide");
	}
}
