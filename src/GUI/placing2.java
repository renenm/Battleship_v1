package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Core.Player;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.JScrollPane;

import java.awt.Color;

public class placing2 extends JPanel {

	
	/**
	 * Create the panel.
	 */
	public placing2() {
		setLayout(null);
		
		Player[] player;
		player = new Player[3];
		int i = 0;
		
		player[0] = new Player("Max", 0, false);
		player[1] = new Player("Rene", 1, false);
		player[2] = new Player("Justus", 2, false);
	
		
			
			//player[i].getName();
			//player[i].playerPlaceShips(i, fieldsize, ship, playersBattlefield);
			
		
		
		JLabel lblNewLabel = new JLabel(player[i].getName() + "'s Turn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 11, 200, 50);
		add(lblNewLabel);
		
		
		JTextArea textArea = new JTextArea(
                "Aktuelles zu setzendes Schiff: Destroyer \n\n" + 
                "\n" +
                "Noch zu setzende Schiffe: \n\n" +
                "Destroyer 1/3 \n" + 
                "Frigates 2/2 \n" +
                "Corvettes 2/2 \n" +
                "Submarines 3/3 \n"   
        );
        textArea.setLineWrap(true);
        textArea.setVisible(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(623, 174, 154, 256);
        add(textArea);
        
        
		JLabel lblNewLabel_1 = new JLabel("Ships to be placed");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(612, 112, 154, 23);
		add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(20, 75, 582, 504);
		add(panel);
		
	
	}
}
