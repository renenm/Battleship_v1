package CustomExceptions;

/**
 * Die Exception wird f�r das setzen und schie�en der Schiffe ben�tigt und erscheint, wenn au�erhabl des Spielfeldes gesetzt werden soll
 * @author Max K�ck, Renen Neumann, Justus C�ster
 *
 */
public class RightLowerCornerException extends Exception {
	
	private static final long serialVersionUID = -4022273316173095393L;

	public RightLowerCornerException() {
		super("ArrayIndexOutOfBounds at RightLowerCorner");
	}
}
