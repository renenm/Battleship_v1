package CustomExceptions;

/**
 * Die Exception tritt auf, wennd ein Schiff vertikal au�erhalb des Spielfeldes platziert werden soll
 * @author Max K�ck, Rene Neumann, Justus C�ster
 */
public class VertRightLowerCornerException extends Exception {

	private static final long serialVersionUID = 8416703784332920340L;

	public VertRightLowerCornerException() {
		super("ArrayIndexOutOfBounds at RightLowerCorner");
	}
}
