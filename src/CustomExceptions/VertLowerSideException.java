package CustomExceptions;

/**
 * Die Exception tritt auf, wennd ein Schiff vertikal außerhalb des Spielfeldes platziert werden soll
 * @author Max Kück, Rene Neumann, Justus Cöster
 */
public class VertLowerSideException extends Exception {
	
	private static final long serialVersionUID = -6507846466252465881L;

	public VertLowerSideException() {
		super("ArrayIndexOutOfBounds at LowerSide");
	}
}
