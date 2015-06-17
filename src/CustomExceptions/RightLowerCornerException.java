package CustomExceptions;

/**
 * Die Exception wird für das setzen und schießen der Schiffe benötigt und erscheint, wenn außerhabl des Spielfeldes gesetzt werden soll
 * @author Max Kück, Renen Neumann, Justus Cöster
 *
 */
public class RightLowerCornerException extends Exception {
	
	private static final long serialVersionUID = -4022273316173095393L;

	public RightLowerCornerException() {
		super("ArrayIndexOutOfBounds at RightLowerCorner");
	}
}
