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
	 * A test suite that creates a debug and text editor.
	 */
	public void testObservers() {
		UndergraduateStudent student = new UndergraduateStudent();
		
		// Create a debug listener and make it listen
		UndergraduateStudentDebugListener debugListener = new UndergraduateStudentDebugListener();
		student.addUndergraduateStudentListener(debugListener);
		student.showListeners(); //see if a debug listener shows up
		
		// Create a text editor listener and make it listen
		UndergraduateStudentTextEditorListener textListener = new UndergraduateStudentTextEditorListener(student);
		student.addUndergraduateStudentListener(textListener);
		
		student.showListeners(); //see if both a debug and texteditor listener show up
		student.update(); //show current state
		textListener.edit(); //begin the editing process on this UndergraduateStudent record
		
		System.out.println("After editing completed, here is Listener list");
		student.showListeners(); //see if the texteditor listener has detached
		System.out.println("Adding new text editor");
		textListener = new UndergraduateStudentTextEditorListener(student);
		student.addUndergraduateStudentListener(textListener);
		student.showListeners(); //does the new editor show up?
		textListener.edit();
		System.out.println("After editing, here is Listener list");
		student.showListeners();
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
	
	/**
	 * A test suite that creates a debug listener and GUI editor for a new student.
	 */
	public void editStudent() {
		UndergraduateStudent student = new UndergraduateStudent();
		
		UndergraduateStudentDebugListener debugListener = new UndergraduateStudentDebugListener();
		student.addUndergraduateStudentListener(debugListener);
		student.showListeners(); //see if a debug listener shows up
		
		//StudentFormFrame frame = new StudentFormFrame(student);
	}

}
