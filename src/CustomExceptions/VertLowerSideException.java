package CustomExceptions;

/**
 * Die Exception tritt auf, wennd ein Schiff vertikal au�erhalb des Spielfeldes platziert werden soll
 * @author Max K�ck, Rene Neumann, Justus C�ster
 */
public class VertLowerSideException extends Exception {
	
	private static final long serialVersionUID = -6507846466252465881L;

	public VertLowerSideException() {
		super("ArrayIndexOutOfBounds at LowerSide");
	}
}
