package Lecture5;
/**
 * File: DrawAMac.java
 * 
 * This program draws a Mac, demonstrating Graphics routines and applets.
 * @author DLK, modified by THC, converted to Java and JApplet by Scot Drysdale
 */
import java.awt.*;
import javax.swing.*;

public class DrawAMac extends JApplet {
	private static final long serialVersionUID = 0;  // gets rid of a warning

	/**
	 * The Applet equivalent of a constructor.  Initialize variables, set graphics.
	 * Called when the applet starts running.
	 */
	public void init() {
		setSize(400, 400);
		Container cp = getContentPane();  // content pane holds components
		cp.add(new DrawAMacCanvas());     // the canvas is the only component

		setVisible(true);   // makes the applet (and its components) visible
	}

	/**
	 * This class is a JPanel with the paintComponent needed to draw the Mac.
	 */
	private class DrawAMacCanvas extends JPanel {
		private static final long serialVersionUID = 0;  // gets rid of a warning

		/**
		 * Paints what goes on the canvas - an old-style Mac
		 * @param page the graphic object of the panel for drawing on
		 */
		public void paintComponent(Graphics page) {  
			super.paintComponent(page);
			page.setColor(Color.blue);
			page.drawRoundRect(70, 15, 160, 210, 10, 10);  // draw the Mac outline

			page.drawLine(70, 190, 230, 190);

			// Draw floppy drive
			page.setColor(Color.red);
			page.drawLine(140, 162, 140, 158);
			page.drawLine(140, 158, 190, 158);
			page.drawLine(190, 158, 190, 153);
			page.drawLine(190, 153, 215, 153);
			page.drawLine(215, 153, 215, 167);
			page.drawLine(215, 167, 190, 167);
			page.drawLine(190, 167, 190, 163);
			page.drawLine(190, 163, 140, 163);

			page.fillOval(85, 160, 20, 20);                // Apple Logo circle                    

			page.setColor(Color.green);
			page.drawRoundRect(90, 30, 120, 100, 20, 20);  // screen outline

			page.setColor(Color.gray);
			page.fillRoundRect(100, 40, 100, 80, 20, 20);
		}  
	}
}