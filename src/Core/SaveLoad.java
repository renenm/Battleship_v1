package Core;
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
	public static void saveShips(Ship[][][] ship) {
		try {
			fileout = new FileOutputStream("game" + path  + "/saveShips.cnk");
			objectout = new ObjectOutputStream(fileout);
			for (int i = 0; i < ship.length; i++) {
				for (int j = 0; j < ship[i].length; j++) {
					for (int h = 0; h < ship[i][j].length; h++) {
						objectout.writeObject(ship[i][j][h]);
					}
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
	public static Ship[][][] shipsLoad(int howManyPlayers, int howManyDestroyer, int howManyFrigates, int howManyCorvettes, int howManySubmarines ) {
		
		Ship[][][] ship = new Ship[4][howManyPlayers][];
		try {
			filein = new FileInputStream("game" + path  + "/saveShips.cnk");
			objectin = new ObjectInputStream(filein);

			for (int i = 0; i < howManyPlayers; i++) {
				for (int j = 0; j < howManyDestroyer; j++) {
					ship[0][i][j] = (Destroyer) objectin.readObject();	
				}
			}
			
			for (int i = 0; i < howManyPlayers; i++) {
				for (int j = 0; j < howManyFrigates; j++) {
					ship[1][i][j] = (Frigate) objectin.readObject();	
				}
			}
			
			for (int i = 0; i < howManyPlayers; i++) {
				for (int j = 0; j < howManyCorvettes; j++) {
					ship[2][i][j] = (Corvette) objectin.readObject();	
				}
			}
			
			for (int i = 0; i < howManyPlayers; i++) {
				for (int j = 0; j < howManySubmarines; j++) {
					ship[3][i][j] = (Submarine) objectin.readObject();	
				}
			}
			return ship;
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return ship;
	}
}

