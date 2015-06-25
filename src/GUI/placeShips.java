package GUI;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.*;

class placeShips {
    public static void main(String[] args) {
        battlefield_gui myFrame = new battlefield_gui();
        myFrame.getContentPane();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}

class battlefield_gui extends JFrame implements ActionListener {
	int index = 14;
	int fieldsize = index+1;
	
    JButton[][] buttons = new JButton[fieldsize][fieldsize];
    JPanel mPanel = new JPanel();
    JPanel bPanel = new JPanel();
    JPanel cPanel = new JPanel();
    //JTextArea scoreKeeper = new JTextArea();
    Container c = getContentPane();
    int[][] intArray = new int[fieldsize][fieldsize];

    public battlefield_gui() {
        butGen();
        score2();
        //cPanel.add(scoreKeeper);
        bPanel.setLayout(new GridLayout(fieldsize,fieldsize));
        mPanel.setLayout(new BorderLayout());
        mPanel.add(bPanel, BorderLayout.CENTER);
        mPanel.add(cPanel, BorderLayout.SOUTH);
        c.add(mPanel);

        setTitle("Battlefield GUI");
        setSize(700,700);
        setResizable(false);
        setVisible(true);
    }

    private void butGen() {
        for(int i=0;i<fieldsize;i++)
            for(int j=0;j<fieldsize;j++) {
                buttons[i][j] = new JButton(String.valueOf(i)+"x"+String.valueOf(j));
                buttons[i][j].setActionCommand("button" +i +"_" +j);
                buttons[i][j].addActionListener(this);
                bPanel.add(buttons[i][j]);
                
                
                //Index
                buttons[0][j].setEnabled(false);
                buttons[i][0].setEnabled(false);        
                
                buttons[0][j].setText(String.valueOf(j));
                buttons[i][0].setText(String.valueOf(i));             
            } 
    }
    
   
    
    private void score2() {
        for(int i=1;i<fieldsize;i++)
            for(int j=1;j<fieldsize;j++)
                buttons[i][j].setText(String.valueOf(intArray[i][j]));
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().contains("button")) {
            //int i = Integer.parseInt(Character.toString(e.getActionCommand().replaceAll("button","").replaceAll("_", "").charAt(0)));
            //int j = Integer.parseInt(Character.toString(e.getActionCommand().replaceAll("button","").replaceAll("_", "").charAt(1)));
            //intArray[i][j]++;
            //System.out.println(e.getActionCommand() +"  " +i +"  " +j);
            
            
            
        }
        score2();
    }
}
