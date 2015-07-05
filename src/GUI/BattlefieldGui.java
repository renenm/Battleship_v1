package GUI;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BattlefieldGui extends JPanel{
	
	private static final long serialVersionUID = -1227649774584677192L;
	private int fieldsize;
	private JButton[][] buttons;
	private JPanel field;
	
	public BattlefieldGui(int fieldsize) {
		super();
		this.field = new JPanel();
		this.field.setLayout(new GridLayout(fieldsize + 1, fieldsize + 1));
		this.fieldsize = fieldsize;
		this.buttons = new JButton[fieldsize + 1][fieldsize + 1];
        for (int i = 0; i < buttons.length; i++) {
        	for (int j = 0; j < buttons[i].length; j++) {
				this.field.add(buttons[i][j] = new JButton(" "));
				this.buttons[i][j].setActionCommand(String.valueOf(i) + " " + String.valueOf(j));
				this.buttons[0][j].setText(String.valueOf(j));
				this.buttons[i][0].setText(String.valueOf(i));
				this.buttons[0][j].setEnabled(false);
				this.buttons[i][0].setEnabled(false);
        	}
		}
		this.add(field);
	}

	public int getFieldsize() {
		return fieldsize;
	}

	public void setFieldsize(int fieldsize) {
		this.fieldsize = fieldsize;
	}

	public JButton[][] getButtons() {
		return buttons;
	}

	public void setButtons(JButton[][] buttons) {
		this.buttons = buttons;
	}
}
