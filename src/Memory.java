import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

public class Memory {
	
	//Information during Game
	static int howManyPlayers;
	static int fieldsize;
	static int howManyCorvettes;
	static int howManyDestroyer;
	static int howManyFrigates;
	static int howManySubmarines;
		
	static int fsLoc = 0;
	static int pLoc = 1;
	static int cLoc = 2;
	static int dLoc = 3;
	static int fLoc = 4;
	static int sLoc = 5;
	
	//Information from save File
	static int[] save = {fsLoc, pLoc, cLoc, dLoc, fLoc, sLoc};
		
	
	
	public static void control() {
		System.out.println("");
		System.out.print("Loading Savegame");
		System.out.print("\n-----------------------\n\n");
		
		loadGame("savegame.txt");
	}
	
	public static void loadGame(String filePath) {
		File inputFile;
		BufferedReader inputReader;
		
		try {
			inputFile = new File(filePath);
			inputReader = new BufferedReader(new FileReader(inputFile));
			
			for(int i = 0; i < save.length; i++) {
				save[i] = Integer.parseInt(inputReader.readLine());
			}
			//Lesen aus Datei
			fieldsize = save[fsLoc];
			howManyPlayers = save[pLoc];
			howManyCorvettes = save[cLoc];
			howManyDestroyer = save[dLoc];
			howManyFrigates = save[fLoc];
			howManySubmarines = save[sLoc];
			
			//Datum vom Savegame
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(inputFile.lastModified() );
			System.out.print("Letztes Savegame vom: \n\t" + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH)+1) + "." + cal.get(Calendar.YEAR));
			System.out.println(" um " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + " Uhr");
			
			System.out.println("fieldsize: " + fieldsize + "NumPlayers: " + howManyPlayers + "Corvettes: " + howManyCorvettes + "Destroyer: " + howManyDestroyer + "Frigates: " +howManyFrigates + "Submarines: " 
					+ howManySubmarines);
			inputReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveGame(String filePath, int fieldsize, int howManyPlayers, int howManyCorvettes, int howManyDestroyer, int howManyFrigates, int howManySubmarines, 
	Battlefield[] playersBattlefield, Player[] player, Corvette[][] corvette, Destroyer[][] destroyer, 	Frigate[][] frigate, Submarine[][] submarine) {
		
		File outputFile;
		BufferedWriter outputWriter;
		
		try {
			outputFile = new File(filePath);
			outputWriter = new BufferedWriter(new FileWriter(outputFile));
			
			System.out.println("class memory: saving in savegame.txt");
			
			save[fsLoc] = fieldsize;
			save[pLoc] = howManyPlayers;
			save[cLoc] = howManyCorvettes;
			save[dLoc] = howManyDestroyer;
			save[fLoc] = howManyFrigates;
			save[sLoc] = howManySubmarines;
			
			
			for(int i = 0; i < save.length; i++) {
				outputWriter.write(Integer.toString(save[i]) + "\n");
			}
		
			outputWriter.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}