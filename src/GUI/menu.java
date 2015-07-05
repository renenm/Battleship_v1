package GUI;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Core.*;

public class menu extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	//GUI Main
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
					frame.display();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setTitle("Battleship");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Frame
	public menu() {
	}
	
	public void display() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel main_menu = new JPanel();
		contentPane.add(main_menu, "name_13411935568151");
		main_menu.setLayout(null);
		
		JPanel instructions = new JPanel();
		contentPane.add(instructions, "name_13414986804130");
		instructions.setLayout(null);
		
		JPanel preparation = new JPanel();
		contentPane.add(preparation, "name_17012035418956");
		preparation.setLayout(null);
		
		JPanel placing = new JPanel();
		contentPane.add(placing, "name_17012035418956");
		placing.setLayout(null);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_menu.setVisible(false);
				preparation(preparation, placing);
				preparation.setVisible(true);
			}
		});
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblMainMenu.setBounds(0, 46, 1254, 50);
		main_menu.add(lblMainMenu);
		btnNewGame.setBounds(479, 145, 300, 100);
		main_menu.add(btnNewGame);
		
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Load Game");
				Core.Game.loadGame();
			}
		});
		btnLoadGame.setBounds(479, 305, 300, 100);
		main_menu.add(btnLoadGame);
		
		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_menu.setVisible(false);
				instructions.setVisible(true);
			}
		});
		btnInstructions.setBounds(479, 464, 300, 100);
		main_menu.add(btnInstructions);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_menu.setVisible(true);
				instructions.setVisible(false);
			}
		});
		
		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setBounds(0, 30, 1254, 50);
		lblInstructions.setFont(new Font("Times New Roman", Font.BOLD, 26));
		instructions.add(lblInstructions);
		btnBack.setBounds(20, 561, 300, 100);
		instructions.add(btnBack);
		
		JLabel lblPreparation = new JLabel("Preparation");
		lblPreparation.setBounds(0, 0, 1254, 31);
		preparation.add(lblPreparation);
		lblPreparation.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreparation.setFont(new Font("Times New Roman", Font.BOLD, 26));
		
		JButton btnBack2 = new JButton("back");
		btnBack2.setBounds(10, 561, 300, 100);
		preparation.add(btnBack2);
		btnBack2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main_menu.setVisible(true);
				preparation.setVisible(false);
			}
		});
	}
	public void preparation(JPanel preparation, JPanel placing) {
		JPanel player = new JPanel();
		player.setBounds(81, 206, 468, 201);
		preparation.add(player);
		player.setLayout(null);
		
		JLabel lblNumberOfPlayers = new JLabel("Number of Players");
		lblNumberOfPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfPlayers.setBounds(60, 0, 143, 14);
		player.add(lblNumberOfPlayers);
		
		JPanel ki = new JPanel();
		ki.setBounds(359, 18, 99, 173);
		player.add(ki);
		ki.setLayout(null);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox.setBounds(0, 0, 97, 23);
		ki.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox_1.setBounds(0, 30, 97, 23);
		ki.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("");
		checkBox_2.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox_2.setBounds(0, 60, 97, 23);
		ki.add(checkBox_2);
		checkBox_2.setEnabled(false);
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox_3.setBounds(0, 90, 97, 23);
		ki.add(checkBox_3);
		checkBox_3.setEnabled(false);
		
		JCheckBox checkBox_4 = new JCheckBox("");
		checkBox_4.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox_4.setBounds(0, 120, 97, 23);
		ki.add(checkBox_4);
		checkBox_4.setEnabled(false);
		
		JCheckBox checkBox_5 = new JCheckBox("");
		checkBox_5.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox_5.setBounds(0, 150, 97, 23);
		ki.add(checkBox_5);
		checkBox_5.setEnabled(false);
		
		SpinnerNumberModel playerSelect = new SpinnerNumberModel(2, 2, 6, 1);  
		SpinnerNumberModel valueSelect = new SpinnerNumberModel(0, 0, 30, 1);  
		SpinnerNumberModel valueSelect2 = new SpinnerNumberModel(0, 0, 50, 1);  
		SpinnerNumberModel valueSelect3 = new SpinnerNumberModel(0, 0, 50, 1);  
		SpinnerNumberModel valueSelect4 = new SpinnerNumberModel(0, 0, 50, 1);  
		SpinnerNumberModel valueSelect5 = new SpinnerNumberModel(0, 0, 50, 1);  
		
		JSpinner numberOfPlayers = new JSpinner(playerSelect);
		numberOfPlayers.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int num = (int) numberOfPlayers.getValue();
				
				switch(num) {
					case 2: 
						textField_2.setEnabled(false);
						checkBox_2.setEnabled(false);
						textField_3.setEnabled(false);
						checkBox_3.setEnabled(false);
						textField_4.setEnabled(false);
						checkBox_4.setEnabled(false);
						textField_5.setEnabled(false);
						checkBox_5.setEnabled(false);
						break;
					case 3: 
						textField_2.setEnabled(true);
						checkBox_2.setEnabled(true);
						textField_3.setEnabled(false);
						checkBox_3.setEnabled(false);
						textField_4.setEnabled(false);
						checkBox_4.setEnabled(false);
						textField_5.setEnabled(false);
						checkBox_5.setEnabled(false);
						break;
					case 4: 
						textField_2.setEnabled(true);
						checkBox_2.setEnabled(true);
						textField_3.setEnabled(true);
						checkBox_3.setEnabled(true);
						textField_4.setEnabled(false);
						checkBox_4.setEnabled(false);
						textField_5.setEnabled(false);
						checkBox_5.setEnabled(false);
						break;
					case 5: 
						textField_2.setEnabled(true);
						checkBox_2.setEnabled(true);
						textField_3.setEnabled(true);
						checkBox_3.setEnabled(true);
						textField_4.setEnabled(true);
						checkBox_4.setEnabled(true);
						textField_5.setEnabled(false);
						checkBox_5.setEnabled(false);
						break;
					case 6: {
						textField_2.setEnabled(true);
						checkBox_2.setEnabled(true);
						textField_3.setEnabled(true);
						checkBox_3.setEnabled(true);
						textField_4.setEnabled(true);
						checkBox_4.setEnabled(true);
						textField_5.setEnabled(true);
						checkBox_5.setEnabled(true);
						break;
					}
				}	
			}
		});
		numberOfPlayers.setBounds(83, 20, 92, 20);
		player.add(numberOfPlayers);
		
		JLabel lblNameOfPlayer = new JLabel("Name of Players");
		lblNameOfPlayer.setBounds(221, 0, 128, 14);
		player.add(lblNameOfPlayer);
		lblNameOfPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel playerNames = new JPanel();
		playerNames.setBounds(221, 20, 128, 183);
		player.add(playerNames);
		playerNames.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 0, 128, 18);
		playerNames.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(0, 30, 128, 18);
		playerNames.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(0, 60, 128, 18);
		playerNames.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEnabled(false);
		
		textField_3 = new JTextField();
		textField_3.setBounds(0, 90, 128, 18);
		playerNames.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEnabled(false);
		
		textField_4 = new JTextField();
		textField_4.setBounds(0, 120, 128, 18);
		playerNames.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setEnabled(false);
		
		textField_5 = new JTextField();
		textField_5.setBounds(0, 150, 128, 18);
		playerNames.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setEnabled(false);
		
		JLabel lblKi = new JLabel("Is KI");
		lblKi.setBounds(359, 0, 99, 14);
		player.add(lblKi);
		lblKi.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel size = new JPanel();
		size.setBounds(667, 207, 510, 201);
		preparation.add(size);
		size.setLayout(null);
		
		JLabel lblBattlesize = new JLabel("Size of Battlefield");
		lblBattlesize.setBounds(0, 0, 98, 14);
		size.add(lblBattlesize);
		lblBattlesize.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDestroyer = new JLabel("Destroyer");
		lblDestroyer.setBounds(245, 20, 86, 14);
		size.add(lblDestroyer);
		
		JLabel lblFrigate = new JLabel("Frigate");
		lblFrigate.setBounds(245, 70, 86, 14);
		size.add(lblFrigate);
		
		JLabel lblCorvette = new JLabel("Corvette");
		lblCorvette.setBounds(245, 120, 86, 14);
		size.add(lblCorvette);
		
		JLabel lblSubmarine = new JLabel("Submarine");
		lblSubmarine.setBounds(245, 170, 86, 14);
		size.add(lblSubmarine);
		
		JSpinner numBattlesize = new JSpinner(valueSelect);
		numBattlesize.setBounds(0, 20, 98, 20);
		size.add(numBattlesize);
		
		JSpinner numDestroyer = new JSpinner(valueSelect2);
		numDestroyer.setBounds(138, 20, 86, 20);
		size.add(numDestroyer);
		
		JSpinner numFrigates = new JSpinner(valueSelect3);
		numFrigates.setBounds(138, 70, 86, 20);
		size.add(numFrigates);
		
		JSpinner numCorvette = new JSpinner(valueSelect4);
		numCorvette.setBounds(138, 120, 86, 20);
		size.add(numCorvette);
		
		JSpinner numSubmarine = new JSpinner(valueSelect5);
		numSubmarine.setBounds(138, 170, 86, 20);
		size.add(numSubmarine);
		
		JLabel lblNumberOfShips = new JLabel("Number of Ships");
		lblNumberOfShips.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfShips.setBounds(124, 0, 112, 14);
		size.add(lblNumberOfShips);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(944, 561, 300, 100);
		preparation.add(btnStartGame);
		
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int round = 0;
				int fieldsize = (int) numBattlesize.getValue();
				int howManyPlayers = (int) numberOfPlayers.getValue();
				int howManyDestroyer = (int) numDestroyer.getValue();
				int howManyFrigates = (int) numFrigates.getValue();
				int howManyCorvettes = (int) numCorvette.getValue();
				int howManySubmarines = (int) numSubmarine.getValue();
				
				String player1 = (String) textField.getText();
				String player2 = (String) textField_1.getText();
				String player3 = (String) textField_2.getText();
				String player4 = (String) textField_3.getText();
				String player5 = (String) textField_4.getText();
				String player6 = (String) textField_5.getText();
				
				boolean ki1 = checkBox.isSelected();
				boolean ki2 = checkBox_1.isSelected();
				boolean ki3 = checkBox_2.isSelected();
				boolean ki4 = checkBox_3.isSelected();
				boolean ki5 = checkBox_4.isSelected();
				boolean ki6 = checkBox_5.isSelected();
				
				Battlefield[] playersBattlefield;
				Player[] player;
				Ship[][][] ship;
				
				player = new Player[howManyPlayers];
				playersBattlefield = new Battlefield[howManyPlayers];
				ship = new Ship [4][][];
				
				ship[0] = new Destroyer[howManyPlayers][howManyDestroyer];
				ship[1] = new Frigate[howManyPlayers][howManyFrigates];
				ship[2] = new Corvette[howManyPlayers][howManyCorvettes];
				ship[3] = new Submarine[howManyPlayers][howManySubmarines];
				
				int shipId = 1;
				for (int k = 0; k < ship.length; k++) {	
					for (int i = 0; i < ship[k].length; i++) {
						for (int h = 0; h < ship[k][i].length; h++) {					
							switch(k) {
							case 0: 
								ship[k][i][h] = new Destroyer(shipId);
								shipId ++;
								break;
							case 1:
								ship[k][i][h] = new Frigate(shipId);
								shipId ++;
								break;
							case 2: 
								ship[k][i][h] = new Corvette(shipId);
								shipId ++;
								break;
							case 3:
								ship[k][i][h] = new Submarine(shipId);
								shipId ++;
								break;
							}
						}
					}
				}
				
				switch (howManyPlayers) {
					case 2: 
						player[0] = new Player(player1, 0, ki1);
						player[1] = new Player(player2, 1, ki2);
						break;
					case 3: 
						player[0] = new Player(player1, 0, ki1);
						player[1] = new Player(player2, 1, ki2);
						player[2] = new Player(player3, 2, ki3);
						break;
					case 4: 
						player[0] = new Player(player1, 0, ki1);
						player[1] = new Player(player2, 1, ki2);
						player[2] = new Player(player3, 2, ki3);
						player[3] = new Player(player4, 3, ki4);
						break;
					case 5: 
						player[0] = new Player(player1, 0, ki1);
						player[1] = new Player(player2, 1, ki2);
						player[2] = new Player(player3, 2, ki3);
						player[3] = new Player(player4, 3, ki4);
						player[4] = new Player(player5, 4, ki5);
						break;						
					case 6: 
						player[0] = new Player(player1, 0, ki1);
						player[1] = new Player(player2, 1, ki2);
						player[2] = new Player(player3, 2, ki3);
						player[3] = new Player(player4, 3, ki4);
						player[4] = new Player(player5, 4, ki5);
						player[5] = new Player(player6, 5, ki6);
						break;
				}
				for(int i = 0; i < howManyPlayers; i++) {
					playersBattlefield[i] = new Battlefield(fieldsize, i);
				}
				
				preparation.setVisible(false);
				placeShips(placing, player, ship, fieldsize, playersBattlefield);
				placing.setVisible(true);
			}
		});
	}
	
	public void placeShips(JPanel placing, Player[] player, Ship[][][] ship, int fieldsize, Battlefield[] playersBattlefield) {
		
		for (int a = 0; a < player.length; a++) {
			JLabel lblNewLabel = new JLabel(player[0].getName());
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			placing.add(lblNewLabel);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(940, 50, 323, 33);
			
			JTextArea textArea = new JTextArea(
	                "Aktuelles zu setzendes Schiff: Destroyer \n\n" + 
	                "\n" +
	                "Noch zu setzende Schiffe: \n\n" +
	                "Destroyer " + ship[0][a].length + "\n" +
	                "Frigates " + ship[1][a].length + "\n" +
	                "Corvettes " + ship[2][a].length + "\n" +
	                "Submarines " +  + ship[3][a].length);
			
	        textArea.setLineWrap(true);
	        textArea.setVisible(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setBounds(978, 160, 248, 286);
	        placing.add(textArea);
	        
			JLabel lblNewLabel_1 = new JLabel("Ships to be placed");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(978, 116, 245, 33);
			placing.add(lblNewLabel_1);
			
			//Scrollpane initialized
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 10, 900, 660);
			placing.add(scrollPane);
			JPanel field = new JPanel();
			field.setBounds(10, 10, 900, 660);
			JButton[][] buttons = new JButton[fieldsize +1][fieldsize +1];
			scrollPane.setViewportView(field);
			field.setLayout(new GridLayout(fieldsize+1, fieldsize+1));
			
			for (int i = 0; i < buttons.length; i++) {
		        for (int j = 0; j < buttons[i].length; j++) {
					field.add(buttons[i][j] = new JButton(" "));
					buttons[i][j].addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String orientation = JOptionPane.showInputDialog(placing,
			                        "Do you want to place your ship horizontal or vertical?", null);
							
							String[] segs = e.getActionCommand().split(" ");
							int x = Integer.parseInt(segs[0]);
							int y = Integer.parseInt(segs[1]);
							
							if("horizontal".equals(orientation)) {
								if(player[0].placeShipsForGui(ship[1][0][0], orientation, fieldsize, 0, playersBattlefield, segs, e)); {
									for (int i = 0; i < ship[1][0][0].getShipSize(); i++) {
										buttons[x][y + i].setText(ship[1][0][0].getShipSymbol());
									}
								}
							} else {
								if(player[0].placeShipsForGui(ship[1][0][0], orientation, fieldsize, 0, playersBattlefield, segs, e)); {
									for (int i = 0; i < ship[1][0][0].getShipSize(); i++) {
										buttons[x + i][y].setText(ship[1][0][0].getShipSymbol());
									}
								} 
							}
						}
					});
					buttons[i][j].setActionCommand(String.valueOf(i) + " " + String.valueOf(j));
					buttons[0][j].setText(String.valueOf(j));
					buttons[i][0].setText(String.valueOf(i));
					buttons[0][j].setEnabled(false);
					buttons[i][0].setEnabled(false);
		        }
			}
			field.setVisible(true);
		}
	}	
				
					
				
//				//Muss Noch GUI-fiziert werden
//				for(int i = 0 ; i < howManyPlayers ; i++) { //i = SpielderID, J = SchiffID
//					playersBattlefield[i].printBattlefield();
//					System.out.println("");
//					System.out.print(player[i].getName() + ", please enter the coordinates of your ships:");
//					player[i].playerPlaceShips(i, fieldsize, ship, playersBattlefield);
//					System.out.println("\n----------------------- NEXT PLAYER\n");
//				}						
//				int[] values = new int[7];
//				values[0] = fieldsize;
//				values[1] = howManyPlayers;
//				values[2] = howManyDestroyer;
//				values[3] = howManyFrigates;
//				values[4] = howManyCorvettes;
//				values[5] = howManySubmarines;
//				values[6] = round;
//				SaveLoad.saveBattlefield(playersBattlefield);
//				SaveLoad.savePlayer(player);
//				SaveLoad.saveShips(ship);
//				SaveLoad.saveValues(values);
//				Core.Game.game(round, fieldsize, howManyPlayers, howManyDestroyer, howManyFrigates, howManyCorvettes, howManySubmarines, playersBattlefield, player, ship, values);
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}