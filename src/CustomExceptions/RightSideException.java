package CustomExceptions;

/**
 * Die Exception wird für das setzen und schießen der Schiffe benötigt und erscheint, wenn außerhabl des Spielfeldes gesetzt werden soll
 * @author Max Kück, Renen Neumann, Justus Cöster
 *
 */
public class RightSideException extends Exception {
	
	private static final long serialVersionUID = 1383201113627295795L;

	public RightSideException() {
		super("ArrayIndexOutOfBounds at RightSide");
	}
}
