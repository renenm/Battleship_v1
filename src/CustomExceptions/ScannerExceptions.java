package CustomExceptions;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Die Klasse fängt falsch eingegeben int-Werte ab 
 * @author Max Kück, Rene Neumann, Justus Cöster
 *
 */
public class ScannerExceptions {
	
	/**
	 * Die Methode fängt alle negativen int-Werte ab
	 * @return 
	 */
	public static int readInt() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner( System.in);
		int num = -1;
		try {    
			num = scan.nextInt();
			if(!(num >= 0)) { 
				System.out.println("\t\tPlease enter a positive number:" );
				num = readInt();
			}	
		} catch ( InputMismatchException ex) { 
				System.out.println("\t\tPlease enter a number:" );
				num = readInt();
			} 
		return num;
	}
	
	/**
	 * Die Methode fängt alle int_Werte ab, die ncht in dem angegeben Bereich liegen
	 * @param min untere grenzen der Wert
	 * @param max obere Grenzen der Werte
	 * @return s
	 */
	public static int readInt(int min, int max) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner( System.in);
		int num = -1;
		try {    
			num = scan.nextInt();
			if(!(num >= min && num <= max)) { 
				System.out.print("Please enter a number between " + min + " and " + max + ": ");
				num = readInt(min, max);
			}	
		} catch ( InputMismatchException ex) { 
				System.out.println("\t\tPlease enter a number between X AND Y:" );
				num = readInt(min, max);
			} 
		return num;
	}
}
