import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class Memory {

	public static void control() throws Exception {
		System.out.println("");
		System.out.print("Loading Savegame");
		System.out.print("\n-----------------------\n\n");
		
		loadGame("savegame.txt");
		Main.vorbereitung();
	}
	
	public static void saveGameNew(String filePath, Battlefield[] playersBattlefield, Player[] player, Corvette[][] corvette, 
			Destroyer[][] destroyer, Frigate[][] frigate, Submarine[][] submarine) {
		
		FileWriter fw;
	    try {
	    	//Savedatei bestimmen    	
	    	fw = new FileWriter("savegame.dat");
	    	//Zu schreibender Inhalt
	    	fw.write("File\n");
	    	fw.write("arrPlayer\n");
	    	for (int i = 0; i < player.length; i++) {
	    		fw.write(player[i].getName() + "\n");
	    		fw.write(player[i].getPlayerId() + "\n");
	    		fw.write(player[i].isDead() + "\n");
	    	}
	    	fw.write("arrBattlefield\n");
	    	for (int i = 0; i < playersBattlefield.length; i++) {
		    	  fw.write(playersBattlefield[i].getFieldsize() + "\n");
		          fw.write(playersBattlefield[i].getBelongsToPlayer() + "\n");
		       // fw.write(playersBattlefield[i].Field[][] getBattlefield() + "\n");
	    	}
		      /*
		      fw.write("arrDestroyer\n");
		      for (int i = 0; i < destroyer.length; i++) {
		    	  fw.write(destroyer[i].getShipId() + "\n");
		          fw.write(destroyer[i].getBattlefield() + "\n");
		          fw.write(destroyer[i].getShipRespawn() + "\n");
		        }
		        */
	    	fw.close();
	    } catch (IOException e) {
	    	System.out.println("Error");
	    	e.printStackTrace();
	    }
	}
	
	public static void loadGame(String filePath) {
		File inputFile;
		BufferedReader inputReader;
		
		int[] save = {1, 2, 3, 4, 5, 6};

		int howManyPlayers;
		int fieldsize;
		int howManyCorvettes;
		int howManyDestroyer;
		int howManyFrigates;
		int howManySubmarines;
		
		try {
			inputFile = new File(filePath);
			inputReader = new BufferedReader(new FileReader(inputFile));
			
			for(int i = 0; i < save.length; i++) {
				save[i] = Integer.parseInt(inputReader.readLine());
			}
			//Lesen aus Datei
			fieldsize = save[0];
			howManyPlayers = save[1];
			howManyCorvettes = save[2];
			howManyDestroyer = save[3];
			howManyFrigates = save[4];
			howManySubmarines = save[5];
			
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
		
		int[] save = {1, 2, 3, 4, 5, 6};
		
		File outputFile;
		BufferedWriter outputWriter;
		
		try {
			outputFile = new File(filePath);
			outputWriter = new BufferedWriter(new FileWriter(outputFile));
			
			save[0] = fieldsize;
			save[1] = howManyPlayers;
			save[2] = howManyCorvettes;
			save[3] = howManyDestroyer;
			save[4] = howManyFrigates;
			save[5] = howManySubmarines;
			
			for(int i = 0; i < save.length; i++) {
				outputWriter.write(Integer.toString(save[i]) + "\n");
			}
			outputWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}