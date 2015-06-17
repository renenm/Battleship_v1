package CustomExceptions;

/**
 * Die Exception tritt auf, wennd ein Schiff vertikal au�erhalb des Spielfeldes platziert werden soll
 * @author Max K�ck, Rene Neumann, Justus C�ster
 */
public class VertRightSideException extends Exception {

	private static final long serialVersionUID = -6972807363548307436L;

	public VertRightSideException() {
		super("ArrayIndexOutOfBounds at RightSide");
	}
}
