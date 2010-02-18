package studentClasses;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * purpose: Models certain common attributes of students: GPA, an ID number,
 *          and number of credit hours taken.
 * @author: Phillip Calvin
 * @version: 1.13.2010
 */
public class AbstractStudent
	extends Person
	implements Serializable, Comparable<AbstractStudent> {

	/**
	 * A unique serial that can be used to version schema.
	 */
	private static final long serialVersionUID = 3633123747860484299L;
	
	/**
	 * @uml.property  name="gpa"
	 */
	private double gpa;

	/**
	 * Getter of the property <tt>gpa</tt>
	 * @return  Returns the GPA of the student.
	 * @uml.property  name="gpa"
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Setter of the property <tt>gpa</tt>
	 * @param gpa  The new GPA of the student.
	 * @uml.property  name="gpa"
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * @uml.property  name="hoursTaken"
	 */
	private int hoursTaken;

	/**
	 * Getter of the property <tt>hoursTaken</tt>
	 * @return  Returns the total number of credit hours taken.
	 * @uml.property  name="hoursTaken"
	 */
	public int getHoursTaken() {
		return hoursTaken;
	}

	/**
	 * Setter of the property <tt>hoursTaken</tt>
	 * @param hoursTaken The number of credit hours the student has taken.
	 * @uml.property  name="hoursTaken"
	 */
	public void setHoursTaken(int hoursTaken) {
		this.hoursTaken = hoursTaken;
	}

	/**
	 * @uml.property  name="studentID"
	 */
	private long studentID;

	/**
	 * Getter of the property <tt>studentID</tt>
	 * @return  Returns the ID number of the student.
	 * @uml.property  name="studentID"
	 */
	public long getStudentID() {
		return studentID;
	}

	/**
	 * Setter of the property <tt>studentID</tt>
	 * @param studentID  The new ID of the student.
	 * @uml.property  name="studentID"
	 */
	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}
	
	/**
	 * Returns a numerical value that represents what year the student is.
	 * @return  A value representing the year of the student
	 */
	public int getYearNumber() {
		return 0;
	}
	
	/**
	 * Compares this student to another <tt>AbstractStudent</tt> based
	 * on student ID.
	 * @param student The student to compare to.
	 * @return A negative number if this < other, 0 if this == other, and a positive number if this > other 
	 */
	public int compareTo(AbstractStudent student) {
		return (int) ( this.studentID - student.studentID );
	}
	
	/**
	 * Returns the student's attributes as tab-separated data
	 * in the following order:
	 *   student id, hours, GPA
	 * plus the attributes of the underlying person.
	 * @return A String of properties, separated by tabs.
	 */
	public String toString() {
		// Define a locale for converting the GPA to a String
		Locale locale = new Locale("us","US");
		NumberFormat format = NumberFormat.getNumberInstance(locale);
		format.setMinimumFractionDigits(3); 
		format.setMaximumFractionDigits(3);
		String formattedGpa = format.format( this.getGpa() );
		
		return 	super.toString() + "\t" +
				this.getStudentID() + "\t" + 
			   	this.getHoursTaken() + "\t" + 
			   	formattedGpa;
	}
	
	/**
	 * purpose: Instantiate a new AbstractStudent with the default attribute values.
	 */
	public AbstractStudent() {
		super();
	}


	/**
	 * purpose: Instantiate a new AbstractStudent already initialized with the
	 * specified properties, corresponding to those of the Person and
	 * those of the Student.
	 */
	public AbstractStudent(	String firstName, 
							String lastName, 
							String address, 
							String city, 
							String state, 
							long studentID, 
							int hoursTaken, 
							double gpa ){
		super(firstName, lastName, address, city, state);
		
		setStudentID(studentID);
		setHoursTaken(hoursTaken);
		setGpa(gpa);
	}
	
	/**
	 * Instantiates an AbstractStudent from the values given in the tokenizer.
	 * @param tokenizer A StringTokenizer that contains tab-delimited data in this order:
	 * student ID, hours taken, gpa;
	 */
	public AbstractStudent(StringTokenizer tokenizer) {
		super(tokenizer);
		
		// TODO: Detect null values, if needed
		setStudentID( Long.parseLong( tokenizer.nextToken() ) );
		setHoursTaken( Integer.parseInt( ( tokenizer.nextToken() ) ) );
		setGpa( Double.parseDouble( tokenizer.nextToken() ) );
	}
	
	/**
	 * Instantiates an AbstractStudent with the provided student ID.
	 * @param studentID the ID to give the new student
	 */
	public AbstractStudent(long studentID) {
		this.studentID = studentID;
	}

}
