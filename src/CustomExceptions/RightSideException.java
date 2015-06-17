package CustomExceptions;

/**
 * Die Exception wird f�r das setzen und schie�en der Schiffe ben�tigt und erscheint, wenn au�erhabl des Spielfeldes gesetzt werden soll
 * @author Max K�ck, Renen Neumann, Justus C�ster
 *
 */
public class RightSideException extends Exception {
	
	private static final long serialVersionUID = 1383201113627295795L;

	public RightSideException() {
		super("ArrayIndexOutOfBounds at RightSide");
	}
}
