package CustomExceptions;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ScannerExceptions {
	
	//Abfangen aller nicht positiven Int's beim Scanner
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
