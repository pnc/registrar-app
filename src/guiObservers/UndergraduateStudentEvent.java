package guiObservers;

import java.awt.AWTEvent;
import studentClasses.*;
/**
 * purpose: UndergraduateStudentEvent defines the structure of information 
 * that will be passed to observers as a result of updating the UndergraduateStudent state.
 *  
 * The naming convention used is consistent with Java AWT conventions.
 *  
 * @author Hayden Porter
 * @version 1/16/2006
*/
public class UndergraduateStudentEvent extends AWTEvent {
	private static final long serialVersionUID = 1L;
	
	public String undergraduateStudentState = null; //stuff we decide to add to AWTEvent
	/**
	 * purpose: constructs an event from the specified UGStudent instance: source
	 *          p is the state information that we wish to pass
	 *          Receivers of this event instance will be able to access the soure
	 *          and also the string p that it contains.
	 */
    public UndergraduateStudentEvent(UndergraduateStudent source, String p) {
        //give AWTEvent parent class the source of the event and an ID
        //RESERVED_ID_MAX <= are known Java events
        super(source,RESERVED_ID_MAX+1);
        undergraduateStudentState = p;
    }
	/**
	 * purpose: returns a string holding the UGStudent's state
	 */
    public String getUndergraduateStudentState() {
        return undergraduateStudentState;
    }
	/**
	 * purpose: sets the state string for this event
	 */
    public void setUndergraduateStudentState(String p) {
        undergraduateStudentState = p;
    }
}