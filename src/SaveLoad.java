import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Die Klasse wird benötigt um ein Spiel abzuspeichern oder zu laden, dies erfolgt durch Serialisierung der Objektarrays
 * @author Max Kück, Rene Neumann, Justus Cöster
 */

public class SaveLoad {
	
	static FileOutputStream fileout;
	static ObjectOutputStream objectout;
	static File outputFile;
	static BufferedWriter outputWriter;
	static File inputFile;
	static BufferedReader inputReader;
	static FileInputStream filein;
	static ObjectInputStream objectin;
	static String path = "One";
	
	/**
	 * Die Methode erstellt, falls nicht vorhanden, einen neuen Ordner, in dem die Saves gespeichert werden
	 */
	public static void addFolder() {

		boolean success = (new File(".//game" + path)).mkdir();
		if(success) {
			System.out.println("created");
		}
	}
	
	/**
	 * die Methode speichert das Objektarray mit den Spielfeldern ab
	 * @param playersBattlefield Objektarray, welches die Spielfelder beinhaltet
	 */
	public static void save(Battlefield[] playersBattlefield) {
		try {
			fileout = new FileOutputStream("game" + path + "/saveBattlefield.cnk");
			objectout = new ObjectOutputStream(fileout);
			
			for (int i = 0; i < playersBattlefield.length; i++) {
				objectout.writeObject(playersBattlefield[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Die Methode speichert das Objektarray mit den Spielern ab
	 * @param Objektarray, welches die Spieler beinhaltet
	 */
	public static void savePlayer(Player[] player) {
		try {
			fileout = new FileOutputStream("game" + path  + "/savePlayer.cnk");
			objectout = new ObjectOutputStream(fileout);
			
			for (int i = 0; i < player.length; i++) {
				objectout.writeObject(player[i]);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Die Methode speichert, das Objektarray mit den Zerstörern ab
	 * @param destroyer Objektarray, welches die Zerstörer beinhaltet
	 */
	public static void saveDestroyer(Destroyer[][] destroyer) {
		try {
			fileout = new FileOutputStream("game" + path  + "/saveDestroyer.cnk");
			objectout = new ObjectOutputStream(fileout);
		
				for (int i = 0; i < destroyer.length; i++) {
					for (int j = 0; j < destroyer[i].length; j++) {
						objectout.writeObject(destroyer[i][j]);
					}
				}
		} catch (IOException e) {
		e.printStackTrace();
		}
	}
	
	/**
	 * Die Methode speichert, das Objektarray mit den Frigatten ab
	 * @param frigate Objektarray, welches die Frigatten beinhaltet
	 */
	public static void saveFrigate(Frigate[][] frigate) {
		try {
			fileout = new FileOutputStream("game" + path  + "/saveFrigate.cnk");
			objectout = new ObjectOutputStream(fileout);
		
			for (int i = 0; i < frigate.length; i++) {
				for (int j = 0; j < frigate[i].length; j++) {
					objectout.writeObject(frigate[i][j]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Die Methode speichert, das Objektarray mit den Korvetten ab
	 * @param corvette Objektarray, welches die korvetten beinhaltet
	 */
	public static void saveCorvette(Corvette[][] corvette) {
		try {
			fileout = new FileOutputStream("game" + path  + "/saveCorvette.cnk");
			objectout = new ObjectOutputStream(fileout);
		
			for (int i = 0; i < corvette.length; i++) {
				for (int j = 0; j < corvette[i].length; j++) {
					objectout.writeObject(corvette[i][j]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Die Methode speichert, das Objektarray mit den U-Booten ab
	 * @param submarine Objektarray, welches die U-Boote beinhaltet
	 */
	public static void saveSubmarine(Submarine[][] submarine) {
		try {
			fileout = new FileOutputStream("game" + path  + "/saveSubmarine.cnk");
			objectout = new ObjectOutputStream(fileout);
			
			for (int i = 0; i < submarine.length; i++) {
				for (int j = 0; j < submarine[i].length; j++) {
					objectout.writeObject(submarine[i][j]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Die Methode speichert, das Array mit den wichtigsten int-Werten des Spiels ab
	 * @param values Array mit den int-Werten
	 */
	public static void saveValues(int[] values) {
	
		try {
			outputFile = new File("game" + path  + "/saveValues.cnk");
			outputWriter = new BufferedWriter(new FileWriter(outputFile));
			
			for(int i = 0; i < values.length; i++) {
				outputWriter.write(Integer.toString(values[i]) + "\n");
			}
			outputWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Die Methode lädt die wichtigen int-Werten in das Spiel
	 * @return Array mit den int-Werten
	 */
	public static int[] valuesLoad() {
		
		int [] values = new int[7];
		
		try {
			inputFile = new File("game" + path  + "/saveValues.cnk");
			inputReader = new BufferedReader(new FileReader(inputFile));
		
			for(int i = 0; i < values.length; i++) {
				values[i] = Integer.parseInt(inputReader.readLine());
			}
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values;
	}
	
	/**
	 * die Methode lädt die Spielfelder in das Spiel
	 * @param howManyPlayers Anzahl der Spieler um das Objektarray zu erzeugen
	 * @return Objektarray mit den geladenen Spielfeldern
	 */
	public static Battlefield[] playersBattlefieldLoad(int howManyPlayers) {
		
		Battlefield[] playersBattlefield = new Battlefield[howManyPlayers];
		try {
			filein = new FileInputStream("game" + path  + "/saveBattlefield.cnk");
			objectin = new ObjectInputStream(filein);

			for (int i = 0; i < playersBattlefield.length; i++) {
				playersBattlefield[i] = (Battlefield) objectin.readObject();
			}
			return playersBattlefield;
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return playersBattlefield;
	}
	
	/**
	 * Die Methode lädt die Spieler in das Spiel
	 * @param howManyPlayers Anzahl der Spieler um das Objektarray zu erzeugen
	 * @return Objektarray mit den geladenen Spielern
	 */
	public static Player[] playerLoad(int howManyPlayers) {
		
		Player[] player = new Player[howManyPlayers];
		try {
			filein = new FileInputStream("game" + path  + "/savePlayer.cnk");
			objectin = new ObjectInputStream(filein);

			for (int i = 0; i < player.length; i++) {
				player[i] = (Player) objectin.readObject();
			}
			return player;
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return player;
	}
	
	/**
	 * Die Methode lädt die Zerstörer in das Spiel
	 * @param howManyPlayers Anzahl der Spieler um das Objektarray zu erzeugen
	 * @param howManyDestroyer Anzahl der Zerstörer um das Objektarray zu erzeugen
	 * @return Objektarray mit den geladenen Zerstörern
	 */
	public static Destroyer[][] destroyerLoad(int howManyPlayers, int howManyDestroyer) {
		
		Destroyer[][] destroyer = new Destroyer[howManyPlayers][howManyDestroyer];
		try {
			filein = new FileInputStream("game" + path  + "/saveDestroyer.cnk");
			objectin = new ObjectInputStream(filein);

			for (int i = 0; i < destroyer.length; i++) {
				for (int j = 0; j < destroyer[i].length; j++) {
					destroyer[i][j] = (Destroyer) objectin.readObject();	
				}
			}
			return destroyer;
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return destroyer;
	}
	
	/**
	 * Die Methode lädt die Frigatten in das Spiel
	 * @param howManyPlayers Anzahl der Spieler um das Objektarray zu erzeugen
	 * @param howManyDestroyer Anzahl der Frigatten um das Objektarray zu erzeugen
	 * @return Objektarray mit den geladenen Frigatten
	 */
	public static Frigate[][] frigateLoad(int howManyPlayers, int howManyFrigates) {
		
		Frigate[][] frigate = new Frigate[howManyPlayers][howManyFrigates];
		try {
			filein = new FileInputStream("game" + path  + "/saveFrigate.cnk");
			objectin = new ObjectInputStream(filein);

			for (int i = 0; i < frigate.length; i++) {
				for (int j = 0; j < frigate[i].length; j++) {
					frigate[i][j] = (Frigate) objectin.readObject();	
				}
			}
			return frigate;
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return frigate;
	}
	
	/**
	 * Die Methode lädt die Korvetten in das Spiel
	 * @param howManyPlayers Anzahl der Spieler um das Objektarray zu erzeugen
	 * @param howManyDestroyer Anzahl der Korvetten um das Objektarray zu erzeugen
	 * @return Objektarray mit den geladenen Korvetten
	 */
	public static Corvette[][] corvetteLoad(int howManyPlayers, int howManyCorvettes) {
		
		Corvette[][] corvette = new Corvette[howManyPlayers][howManyCorvettes];
		try {
			filein = new FileInputStream("game" + path  + "/saveCorvette.cnk");
			objectin = new ObjectInputStream(filein);

			for (int i = 0; i < corvette.length; i++) {
				for (int j = 0; j < corvette[i].length; j++) {
					corvette[i][j] = (Corvette) objectin.readObject();
				}
			}
			return corvette;
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return corvette;
	}
	
	/**
	 * Die Methode lädt die U-Boote in das Spiel
	 * @param howManyPlayers Anzahl der Spieler um das Objektarray zu erzeugen
	 * @param howManyDestroyer Anzahl der U-Boote um das Objektarray zu erzeugen
	 * @return Objektarray mit den geladenen U-Boote
	 */
	public static Submarine[][] submarineLoad(int howManyPlayers, int howManySubmarines) {
		
		Submarine[][] submarine = new Submarine[howManyPlayers][howManySubmarines];
		try {
			filein = new FileInputStream("game" + path  + "/saveSubmarine.cnk");
			objectin = new ObjectInputStream(filein);

			for (int i = 0; i < submarine.length; i++) {
				for (int j = 0; j < submarine[i].length; j++) {
					submarine[i][j] = (Submarine) objectin.readObject();
				}
			}
			return submarine;
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return submarine;
	}
}

