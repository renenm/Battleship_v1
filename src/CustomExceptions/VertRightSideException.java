package CustomExceptions;

/**
 * Die Exception tritt auf, wennd ein Schiff vertikal außerhalb des Spielfeldes platziert werden soll
 * @author Max Kück, Rene Neumann, Justus Cöster
 */
public class VertRightSideException extends Exception {

	private static final long serialVersionUID = -6972807363548307436L;

	public VertRightSideException() {
		super("ArrayIndexOutOfBounds at RightSide");
	}
}
