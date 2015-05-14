import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

public class SaveGame {
	//Information during Game
	static int a = 150;
	static int b = 250;
	static int c = 350;
	
	//Information from save File
	static int[] save = {a, b, c};
	static int aLoc = 0;
	static int bLoc = 1;
	static int cLoc = 2;

	private static void readPlayer(String filePath) {
		File inputFile;
		BufferedReader inputReader;
		
		try {
			inputFile = new File(filePath);
			inputReader = new BufferedReader(new FileReader(inputFile));
			
			for(int i = 0; i < save.length; i++) {
				save[i] = Integer.parseInt(inputReader.readLine());
			}

			a = save[aLoc];
			b = save[bLoc];
			c = save[cLoc];
			
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(inputFile.lastModified() );
			System.out.print("Letztes Savegame vom: \n\t" + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH)+1) + "." + cal.get(Calendar.YEAR));
			System.out.println(" um " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + " Uhr");
			System.out.println("Inhalt Letzter Save:\n\ta: " + a + " b: " + b + " c: " + c);
			
			inputReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void updateData(String filePath) {
		a = a-15;
		b = b-15;
		c = c-15;
		System.out.println("Update(-15 each):\n\ta: " + a + " b: " + b + " c: " + c);
	}
	
	private static void savePlayer(String filePath) {
		File outputFile;
		BufferedWriter outputWriter;
		
		try {
			outputFile = new File(filePath);
			outputWriter = new BufferedWriter(new FileWriter(outputFile));
			
			save[aLoc] = a;
			save[bLoc] = b;
			save[cLoc] = c;
			
			for(int i = 0; i < save.length; i++) {
				outputWriter.write(Integer.toString(save[i]) + "\n");
			}
			System.out.println("New Save:\n\ta: " + a + " b: " + b + " c: " + c);
			outputWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveManage() {
		System.out.println("");
		System.out.print("Loading Savegame");
		System.out.print("\n-----------------------\n\n");
		
		
		
		readPlayer("Player Save.txt");
		updateData("Player Save.txt");
		savePlayer("Player Save.txt");

		System.out.print("\n-----------------------\n\n");
		readPlayer("Player Save.txt");
		updateData("Player Save.txt");
		savePlayer("Player Save.txt");
		
		System.out.print("\n-----------------------\n\n");
		readPlayer("Player Save.txt");
		updateData("Player Save.txt");
		savePlayer("Player Save.txt");
		System.out.print("\n-----------------------\n\n");
		
		//Schuss
	}
}