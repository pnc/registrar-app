package studentClasses;

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * purpose: Models a graduate student, a specific type of student
 * that has been enrolled for a number of years and has a certain
 * degree.
 * @author Phillip Calvin
 * @version 1.13.2010
 */
public class GraduateStudent 
	extends AbstractStudent
	implements Serializable {
	
	/**
	 * A unique serial that can be used to version schema.
	 */
	private static final long serialVersionUID = -8516146318324910368L;
	
	/**
	 * @uml.property  name="yearsEnrolled"
	 */
	private int yearsEnrolled = 0;
	
	/**
	 * Getter of the property <tt>yearsEnrolled</tt>
	 * @return  Returns the number of years enrolled.
	 * @uml.property  name="yearsEnrolled"
	 */
	public int getYearsEnrolled() {
		return yearsEnrolled;
	}

	/**
	 * Setter of the property <tt>yearsEnrolled</tt>
	 * @param year  The number of years the student has been enrolled/
	 * @uml.property  name="yearsEnrolled"
	 */
	public void setYearsEnrolled(int yearsEnrolled) {
		this.yearsEnrolled = yearsEnrolled;
	}
	
	/**
	 * @uml.property  name="degree"
	 */
	private String degree = "";
	
	/**
	 * Getter of the property <tt>degree</tt>
	 * @return  Returns the degree the student aims to receive.
	 * @uml.property  name="degree"
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @return an user-displayable form of this type of student.
	 */
	public String getType() {
		return "Graduate";
	}
	
	/**
	 * Setter of the property <tt>degree</tt>
	 * @param year  The abbreviation of the degree the student aims to receive.
	 * @uml.property  name="degree"
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	/**
	 * Returns the student's attributes as tab-separated data
	 * in the following order:
	 *   years enrolled, intended degree
	 * plus the attributes of the underlying student.
	 * @return A String of properties, separated by tabs.
	 */
	public String toString() {	
		return 	"GR\t" +
		 		super.toString() + "\t" +
				this.getYearsEnrolled() + "\t" +
				this.getDegree();
	}
	
	/**
	 * Returns a numerical value that represents what year the student is.
	 * This can be used to sort the student in a list.
	 * See <tt>StudentYearOrder</tt>.
	 * @return  A value representing the year of the student
	 */
	public int getYearNumber() {
		return StudentYearOrder.GRADUATE.ordinal();
	}
	
	/**
	 * purpose: Default value constructor for graduate student.
	 */
	public GraduateStudent() {
	}
	
	/**
	 * purpose: Explicit value constructor, which takes values for the
	 * attributes of the underlying graduate student, student, and person.
	 * @param firstName the first name of the student
	 * @param lastName the last name of the student
	 * @param address the street address of the student
	 * @param city the city in which the student lives
	 * @param state the state in which the student lives
	 * @param studentID a long ID number uniquely identifying the student
	 * @param hoursTaken the int total number of class hours taken
	 * @param gpa the double value of the student's GPA
	 * @param yearsEnrolled the number of years the student has been enrolled
	 * @param degree the degree type the student aims to receive
	 */
	public GraduateStudent(String firstName, String lastName,
			String address, String city, String state, long studentID,
			int hoursTaken, double gpa, int yearsEnrolled, String degree) {
		super(firstName, lastName, address, city, state, studentID, hoursTaken,
				gpa);
		this.yearsEnrolled = yearsEnrolled;
		this.degree = degree;
	}
	
	/**
	 * Instantiates a GraduateStudent from the values given in the tokenizer.
	 * @param tokenizer A StringTokenizer that contains tab-delimited data in this order:
	 * hours taken, intended degree
	 */
	public GraduateStudent(StringTokenizer tokenizer) {
		super(tokenizer);
		
		// TODO: Detect null values, if needed
		setYearsEnrolled( Integer.parseInt( tokenizer.nextToken() ) );
		setDegree( tokenizer.nextToken() );
	}
}
