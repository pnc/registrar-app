package guiObservers;

/**
 * purpose: Illustrates a simple debug text observer that prints out
 * the UndergraduateStudent state any time a UndergraduateStudentStateChanged 
 * notification is received.
 * @author Hayden Porter
 * @version 1/16/2006
 */
public class UndergraduateStudentDebugListener implements UndergraduateStudentListener {
    public UndergraduateStudentDebugListener() {
    }
    
	/**
	 * purpose: get the string from the event instance being sent and show the string's
	 *          value in the console.
	 */
    public void undergraduateStudentStateChanged(UndergraduateStudentEvent de) {
        System.out.println("UndergraduateStudentDebugListener: "+de.getUndergraduateStudentState());
    }
}