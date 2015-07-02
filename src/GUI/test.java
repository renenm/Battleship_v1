package GUI;

import java.awt.*;
import java.awt.event.*;

class MyCanvas extends Canvas  {
// Notiere alle bisherigen Klicks in einem Feld

private boolean klicks[][];
// Der Punkt, auf den zuletzt geklickt wurde

private Point klick = null;

public MyCanvas () {


	int fieldsize = 14;
	
	klicks = new boolean [fieldsize+1][fieldsize+1];
	for (int i = 0; i < klicks.length; i++)
		for (int j = 0; j < klicks[i].length; j++)
			klicks[i][j] = false;
		

		addMouseListener (new MyMouseListener ());
}



//der letzte Klick wird angezeigt

public void update (Graphics g) {
 // Berechne die Breite und Höhe der Felder

 Rectangle r = getBounds ();
 int b = r.width - 1;
 int h = r.height - 1;

 double y = (double)h / klicks.length;
 double x = (double)b / klicks[0].length;
 
 
 // Ermittle das Feld, auf das geklickt wurde

 if (klick != null) {
    int zeile = (int)(klick.y / y);
    int spalte =(int)(klick.x / x);
    g.fillRect ((int)(x*spalte), (int)(y*zeile), (int)x+1, (int)y+1);      
    klick = null;
    klicks[zeile][spalte] = true;
    System.out.println("x:" + zeile + "y: "+ spalte);
 }
 
 
}




// Danach werden alle bisher geklickten Rechtecke gefärbt

public void paint (Graphics g) {
 // Berechne die Breite und Höhe der Felder

 Rectangle r = getBounds ();
 int b = r.width - 1;
 int h = r.height - 1;
 double y = (double)h / klicks.length;
 double x = (double)b / klicks[0].length;
 
 // Waagrechte Linien

 for (int i = 0; i < klicks.length; i++) {
   g.drawLine (0, (int)(y*i), b, (int)(y*i));
 }
 
 // Senkrechte Linien

 for (int i = 0; i < klicks[0].length; i++) {
    g.drawLine ((int)(x*i), 0, (int)(x*i), h);
 }
 
 // Ermittle das Feld, auf das geklickt wurde

 if (klick != null) {
    int zeile = (int)(klick.y / y);
    int spalte =(int)(klick.x / x);
    klick = null;
    klicks[zeile][spalte] = true;
 }
 
 for (int i = 0; i < klicks.length; i++)
   for (int j = 0; j < klicks[i].length; j++)
     if (klicks[i][j])
       g.fillRect ((int)(x*j), (int)(y*i), (int)x+1, (int)y+1);      


}

class MyMouseListener extends java.awt.event.MouseAdapter {
 public void mouseClicked (MouseEvent event) {
   klick = event.getPoint ();
   repaint();
 }
}

}

public class test extends Frame {
public test (String Title) {
 super (Title);
 setSize (300, 300);
 add (new MyCanvas (), BorderLayout.CENTER);
 addWindowListener(new WindowAdapter() {
     public void windowClosing(WindowEvent event) {
     System.exit(0);
   }
 });    
 setVisible(true);
}

public static void main (String [] args) {
 new test ("Battlefield");
}
}
