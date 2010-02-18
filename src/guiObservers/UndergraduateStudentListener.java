package guiObservers;

/**
 * purpose: Interface defining the methods that an observer of UndergraduateStudent must implement
 *
 *  @author Hayden Porter
 *  @version 1/16/2006
 */
public interface UndergraduateStudentListener extends java.util.EventListener {
    //methods are named according to the variable names changed
    public void undergraduateStudentStateChanged(UndergraduateStudentEvent pe);
}
