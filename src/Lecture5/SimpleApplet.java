package Lecture5;
/**
 * SimpleApplet.java
 * 
 * A simple applet to demonstrate the format of a Java applet.
 * 
 * A Java applet is basically a subclass of the standard JApplet class, which overrides
 * the init method and adds components to the Content Pane.  The init method is 
 * used to set up any information needed by the applet when it is first created
 *
 * Components are things that take up space on the window.  Some of them are 
 * buttons, sliders, check boxes, radio buttons, etc.  We will see a number of
 * these soon.
 * 
 * Another thing that we might want to do is to draw something on the applet.
 * To do this we create a class that extends JPanel and overrides the paintComponent
 * method.  This applet shows how to do that.
 */
import java.awt.*;            // needed for the drawing code
import javax.swing.*;         // for the definition of JApplet

public class SimpleApplet extends JApplet {
	private static final long serialVersionUID = 1L;  // avoid a warning message

	/**
	 * Initializes the applet.  Takes the place of a constructor.
	 */
	public void init() {
		setSize(500, 100);
		Container cp = getContentPane();    // content pane holds components
		cp.add(new Canvas());               // the canvas is the only component

		setVisible(true);   // makes the applet (and its components) visible
	}


	/**
	 * Serves as the drawing canvas for graphics.
	 */
	private class Canvas extends JPanel {
		private static final long serialVersionUID = 1L;

		/**
		 * Paints this component.
		 * @param page the Graphics object that we draw on.  
		 */
		public void paintComponent(Graphics page) {
			super.paintComponent(page);

			page.drawString("An applet a day keeps the doctor away.", 30, 30);
		}
	}
}
