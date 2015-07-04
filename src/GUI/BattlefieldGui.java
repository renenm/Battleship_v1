package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Core.Destroyer;

public class BattlefieldGui {
	
	private static final long serialVersionUID = -1227649774584677192L;
	private int index = 15;
	private int fieldsize = index + 1;
	private JButton[][] battlefield;
	
	public BattlefieldGui(int fieldsize) {
		this.fieldsize = fieldsize;
		this.battlefield = new JButton[fieldsize][fieldsize];
		this.builtBattlefieldGui();
	}

	public void builtBattlefieldGui() {
		JFrame frame = new JFrame("BattlefielGui");
		frame.setSize(700,700);

		JPanel field = new JPanel();
		field.setLayout(new GridLayout(fieldsize, fieldsize));
		
        for (int i = 0; i < battlefield.length; i++) {
        	for (int j = 0; j < battlefield[i].length; j++) {
				field.add(battlefield[i][j] = new JButton("W"));
				battlefield[i][j].addActionListener(new CustomListener());
				battlefield[i][j].setActionCommand(String.valueOf(i) + " " + String.valueOf(j));
                battlefield[0][j].setText(String.valueOf(j));
                battlefield[i][0].setText(String.valueOf(i));
                battlefield[0][j].setEnabled(false);
                battlefield[i][0].setEnabled(false);
        	}
		}
        frame.add(field);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public class CustomListener implements ActionListener{
		
		Destroyer destroyer = new Destroyer(0);
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] segs = e.getActionCommand().split(" ");
			int x = Integer.parseInt(segs[0]);
			int y = Integer.parseInt(segs[1]);
			//horizontal
			for (int i = 0; i < 5; i++) {
				battlefield[x][y + i].setText("E");
				System.out.println(battlefield[x][y].isSelected());
				destroyer.setXCord(x);
				destroyer.setYCord(y);
			}
			//vertikal
			//battlefield[x + i][y].setText("E");
		}
	}
	
	public int getFieldsize() {
		return fieldsize;
	}

	public void setFieldsize(int fieldsize) {
		this.fieldsize = fieldsize;
	}
}
