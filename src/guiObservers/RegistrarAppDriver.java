/**
 * 
 */
package guiObservers;

import applicationClasses.RegistrarApp;
import studentClasses.UndergraduateStudent;
import viewClasses.RegistrarAppFrame;
/**
 * Instantiates listeners (debug and GUI) for manually testing the functionality
 * of the application.
 * @author Phillip Calvin
 * @version 2/3/2010
 */
public class RegistrarAppDriver {

	/**
	 * Runs the main registrar application.
	 * @param args the command-line arguments passed to the program
	 */
	public static void main(String[] args) {
		RegistrarAppDriver driver = new RegistrarAppDriver();
		driver.run();
	}

	/**
	 * Runs the application by instantiating the main application window.
	 */
	public void run() {
		RegistrarApp app = new RegistrarApp();
		
		// We might use it, though!
		@SuppressWarnings("unused")
		RegistrarAppFrame frame = new RegistrarAppFrame(app);
	}

}
