package studentClasses;

import guiObservers.UndergraduateStudentEvent;
import guiObservers.UndergraduateStudentListener;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * purpose: Models an undergraduate student, a specific type of student
 * who is considered to be in a certain year of education.
 * @author Phillip Calvin
 * @version 1.13.2010
 */
public class UndergraduateStudent 
	extends AbstractStudent
	implements Serializable {
	
	/**
	 * A unqiue ID for this class that can be used to version the schema.
	 */
	private static final long serialVersionUID = 2629815476714035022L;
	
	/**
	 * @uml.property  name="year"
	 */
	private String year = "Freshman";
	
	/**
	 * Getter of the property <tt>year</tt>
	 * @return  Returns the year of the student (e.g., Freshman, Sophomore).
	 * @uml.property  name="year"
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Setter of the property <tt>year</tt>
	 * @param year  The year of the student (e.g., Freshman, Sophomore)
	 * @uml.property  name="year"
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * Returns the student's attributes as tab-separated data
	 * in the following order:
	 *   year (e.g., Freshman)
	 * plus the attributes of the underlying student.
	 * @return A String of properties, separated by tabs.
	 */
	public String toString() {	
		return 	"UG\t" +
		        super.toString() + "\t" +
				this.getYear();
	}
	
	/**
	 * @return an user-displayable form of this type of student.
	 */
	public String getType() {
		return "Undergraduate";
	}
	
	/**
	 * Returns a numerical value that represents what year the student is.
	 * This can be used to sort the student in a list.
	 * See <tt>StudentYearOrder</tt>.
	 * @return  A value representing the year of the student
	 */
	public int getYearNumber() {
		// Attempt to retrieve whatever order enum corresponds to this student type
		StudentYearOrder type = StudentYearOrder.get( this.getYear() );
		
		if (type == null) {
			// We didn't recognize this type, sort it at the bottom of the undergrads
			type = StudentYearOrder.OTHER_UNDERGRADUATE;
		}
		
		// Return the value of the student year order enum entry
		return type.ordinal();
	}

	/**
	 * Notifies all listening objects that a change has occurred.
	 */
	public void update() {
		notifyListeners();
	}
	
	/**
	 * An attribute that holds the observers subscribed to this student's events.
	 */
	transient Vector<UndergraduateStudentListener> listeners = null;
	
	/**
	 * purpose: Instantiate a new undergraduate student with the default
	 * attributes.
	 */
	public UndergraduateStudent() {
		listeners = new Vector<UndergraduateStudentListener>();
	}

	/**
	 * purpose: Explicit value constructor, which takes values for the
	 * attributes of the underlying undergraduate student, student, and person.
	 * @param firstName the first name of the student
	 * @param lastName the last name of the student
	 * @param address the street address of the student
	 * @param city the city in which the student lives
	 * @param state the state in which the student lives
	 * @param studentID a long ID number uniquely identifying the student
	 * @param hoursTaken the int total number of class hours taken
	 * @param gpa the double value of the student's GPA
	 * @param year the year of the student, e.g., Freshman
	 */
	public UndergraduateStudent(String firstName, String lastName,
			String address, String city, String state, long studentID,
			int hoursTaken, double gpa, String year) {
		super(firstName, lastName, address, city, state, studentID, hoursTaken,
				gpa);
		this.year = year;
		listeners = new Vector<UndergraduateStudentListener>();
	}
	
	/**
	 * Instantiates an UndergraduateStudent from the values given in the tokenizer.
	 * @param tokenizer A StringTokenizer that contains tab-delimited data in this order:
	 * hours taken, intended degree
	 */
	public UndergraduateStudent(StringTokenizer tokenizer) {
		super(tokenizer);
		
		// TODO: Detect null values, if needed
		setYear( tokenizer.nextToken() );
		
		listeners = new Vector<UndergraduateStudentListener>();
	}
}
