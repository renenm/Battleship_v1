package CustomExceptions;

/**
 * Die Exception wird f�r das setzen und schie�en der Schiffe ben�tigt und erscheint, wenn au�erhabl des Spielfeldes gesetzt werden soll
 * @author Max K�ck, Renen Neumann, Justus C�ster
 *
 */
public class LowerSideException extends Exception {
	
	private static final long serialVersionUID = -61771888028423913L;

	public LowerSideException() {
		super("ArrayIndexOutOfBoundsException at LowerSide");
	}
}
