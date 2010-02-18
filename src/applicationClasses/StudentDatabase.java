/**
 * 
 */
package applicationClasses;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.phillipcalvin.utilities.EventSource;

import comparatorClasses.StudentIDComparator;

import studentClasses.AbstractStudent;
import studentClasses.GraduateStudent;
import studentClasses.UndergraduateStudent;

/** 
 * purpose: Stores a list of students in memory and provides methods for saving
 * and loading this list to and from disk.
 * @author Phillip Calvin
 * @version: 1.20.2010
 */
public class StudentDatabase extends EventSource {

	private ArrayList<AbstractStudent> students;
	
	/********************************************************************
	 *     Constructors
	 ********************************************************************/
	
	/**
	 * Creates a StudentDatabase with a blank list of students.
	 */
	public StudentDatabase() {
		super();
		setStudents( new ArrayList<AbstractStudent>() );
	}
	
	/**
	 * Creates a StudentDatabase instance pre-populated with the given
	 * <tt>ArrayList</tt> of <tt>AbstractStudent</tt>s.
	 */
	public StudentDatabase(ArrayList<AbstractStudent> students) {
		super();
		setStudents(students);
	}
	
	/********************************************************************
	 *     Property accessors
	 ********************************************************************/
	
	/**
	 * Gets the list of students currently stored in the database. 
	 * @return an ArrayList of AbstractStudent objects
	 */
	public ArrayList<AbstractStudent> getStudents() {
		return this.students;
	}
	
	/**
	 * Replaces the current list of students with a new list. Throws an
	 * <tt>IllegalArgumentException</tt> if <tt>null</tt> is passed.
	 * Use a blank ArrayList to reset the student list, or create a new
	 * database.
	 * @param students The new ArrrayList of AbstractStudents
	 */
	public void setStudents(ArrayList<AbstractStudent> students) {
		if (students != null) {
			this.students = students;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/********************************************************************
	 *     Serialization and persistence methods
	 ********************************************************************/
	
	/**
	 * Returns the contents of the database as a string, with
	 * properties delimited by tabs and records delimited by newlines.
	 * @return the data as a String
	 */
	public String toString() {
		String data = "";
		if (students != null) {
			// Iterate across the students
			Iterator<AbstractStudent> iterator = getStudents().iterator();
			while ( iterator.hasNext() ) {
				// Turn each student into a string of data
				String record = iterator.next().toString();
				
				// Add this data to the file
				data += record;
				
				// Add a newline between each record (but not after the last)
				if ( iterator.hasNext() ) { 
					data += "\n";
				}
			}
		}
		
		
		return data;
	}
	
	/** 
	 * Instantiates a new <tt>StudentDatabase</tt> based on the data contained
	 * in <tt>fileName</tt>, assuming it is readable.
	 */
	public static StudentDatabase readFromFile(String fileName){
		StudentDatabase database = null;
		FileReader file = null;
		try {
			file = new FileReader(fileName);
			BufferedReader buffer = new BufferedReader(file);
			String line = null;
			
			ArrayList<AbstractStudent> students = new ArrayList<AbstractStudent>();
			
			// Read each line in the file until there are no lines left
			while ( (line = buffer.readLine()) != null ) {
				StringTokenizer tokenizer = new StringTokenizer(line, "\t");
				
				AbstractStudent student = null;
				
				try {
					// Determine the type of student
					String studentType = tokenizer.nextToken().toUpperCase();
					if ( studentType.equals("UG") ) {
						student = new UndergraduateStudent(tokenizer);
					} else if ( studentType.equals("GR") ) {
						student = new GraduateStudent(tokenizer);
					} else {
						throw new Exception("Invalid student type '" + studentType + "'");
					}
					
					// If the loaded student is valid, add it to the list
					if ( student.getFirstName() != null ) {
						students.add(student);
					}
				} catch (Exception e) {
					System.err.println("Unable to load student from data:");
					System.err.println(line);
					e.printStackTrace();
				}
			}
			
			// Create a StudentDatabase instance populated with the loaded students
			database = new StudentDatabase(students);
			
			file.close();
		} catch (IOException e) {
			System.err.println("An error occurred while loading the student database:");
			e.printStackTrace();
		}
		
		return database;
	}
	
	/**
	 * Saves the current students to the given filename.
	 * @param fileName the filename to write to
	 */
	public boolean saveToFile(String fileName) {
		try {
			// Open the file for writing
			PrintWriter writer = new PrintWriter(fileName);
			
			Iterator<AbstractStudent> iterator =
				getStudents().iterator();
			
			// Write out each student
			while ( iterator.hasNext() ) {
				AbstractStudent student = iterator.next();
				writer.write( student.toString() );
				
				// Delineate each record with a Windows-style newline
				// (for compatibility, though we strip it when we read.)
				if ( iterator.hasNext() ) {
					writer.write("\r\n");
				}
			}
			
			writer.close();
			
			return true;
		} catch (IOException e) {
			System.err.println("Unable to save database.");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Serializes the state of the database to the given file.
	 * @param fileName the path of the file to save to
	 */
	public boolean writePersistentObject(String fileName) {
		try {
			// Open the file for writing
			ObjectOutputStream writer = 
				new ObjectOutputStream( new FileOutputStream(fileName) );
			
			// Write out the database
			writer.writeObject( getStudents() );
			
			writer.close();
			
			return true;
		} catch (IOException e) {
			System.err.println("Unable to save database.");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Instantiates a new <tt>StudentDatabase</tt> from the given file.
	 * @param fileName the path of the file to load from
	 */
	public static StudentDatabase readPersistentObject(String fileName) {
		try {
			// Open the file for reading
			ObjectInputStream reader = 
				new ObjectInputStream( new FileInputStream(fileName) );
			
			// Read the database and return a StudentDatabase instantiated out of it
			Object input = reader.readObject();
			
			reader.close();
			
			if (input instanceof ArrayList) {
				@SuppressWarnings("unchecked")
				ArrayList<AbstractStudent> students = 
					(ArrayList<AbstractStudent>) input;
				
				return new StudentDatabase( students );
			} else {
				throw new Exception("File did not contain a list of students.");
			}		
		} catch (Exception e) {
			System.err.println("Unable to load database:");
			e.printStackTrace();
			return null;
		}
	}
	
	/********************************************************************
	 *     Sorting and manipulation methods
	 ********************************************************************/
	
	/**
	 * Shuffles the order of the student list.
	 */
	public void shuffle() {
		Collections.shuffle(this.students);
		
		// Notify listeners that we've changed.
		this.notifyListeners();
	}
	
	/**
	 * Sorts the list of students by their student ID.
	 */
	public void sort() {
		Collections.sort(this.students);
		
		// Notify listeners that we've changed.
		this.notifyListeners();
	}
	
	
	/**
	 * Sorts the list of students, but using the given <tt>Comparator</tt>
	 * @param a <tt>Comparator&lt;AbstractStudent&gt;</tt> to use for comparison
	 */
	public void sort(Comparator<AbstractStudent> comparator) {
		Collections.sort(this.students, comparator);
		
		// Notify listeners that we've changed.
		this.notifyListeners();
	}
	
	/**
	 * Adds the given student to the database.
	 * @param student The student to add to the database.
	 */
	public void add(AbstractStudent student) {
		if ( !getStudents().contains(student) ) {
			getStudents().add(student);
			
			// Notify listeners that we've changed.
			this.notifyListeners();
		}
	}
	
	/**
	 * Removes the first student who has the same 
	 * ID number as the provided <tt>example</tt> student.
	 * @param example A student whose ID should match the removed student.
	 * @return The removed student, or <tt>null</tt> if no student matched
	 */
	public AbstractStudent remove(AbstractStudent example) {
		// Attempt to locate the student
		AbstractStudent student = this.findBinary( example, new StudentIDComparator() );
		
		// If we located a student, remove them
		if (student != null) {
			getStudents().remove(student);
			
			// Notify listeners that we've changed.
			this.notifyListeners();
		}
		
		return student;
	}
	
	/**
	 * Provides an iterator for working across all of the students in the
	 * database, in whatever order they currently are arranged.
	 * @return an iterator for all students
	 */
	public Iterator<AbstractStudent> iterator() {
		return getStudents().iterator();
	}
	
	/********************************************************************
	 *     Searching and finding
	 ********************************************************************/
	
	/**
	 * Locate any and all students that match <tt>example</tt>, based on
	 * the comparison performed by <tt>comparator</tt>.
	 * If no matching students are found, <tt>null</tt> is returned.
	 * @param example a student to whom the other should be compared
	 * @param comparator the comparator to use when comparing to the example
	 * @return a list of all matching students, <tt>null</tt> if none match
	 */
	public Iterator<AbstractStudent> find(AbstractStudent example, Comparator<AbstractStudent> comparator) {
		ArrayList<AbstractStudent> matches = new ArrayList<AbstractStudent>();
		
		// Walk across all the students and see if any match
		for ( AbstractStudent student : getStudents() ) {
			if ( comparator.compare(example, student) == 0 ) {
				// Add to the list of matches
				matches.add(student);
			}
		}
		
		// See if we found anything
		if ( matches.size() > 0 ) {
			return matches.iterator();
		} else {
			return null;
		}
	}
	
	/**
	 * Locate the first student who matches <tt>example</tt>, based on
	 * the comparison performed by <tt>comparator</tt>.
	 * If no matching student is found, <tt>null</tt> is returned.
	 * @param example a student to whom the other should be compared
	 * @param comparator the comparator to use when comparing to the example
	 * @return the first matching student found, <tt>null</tt> if none match
	 */
	public AbstractStudent findBinary(AbstractStudent example, Comparator<AbstractStudent> comparator) {
		// Sort the students using the comparator
		this.sort(comparator);
		
		// Perform a binary search to find a matching student
		int index = Collections.binarySearch( getStudents(), example, comparator );
		
		// If the result is zero or positive, we have a result
		if (index >= 0) {
			// Fetch and return the result
			return getStudents().get(index);
		} else {
			// No result: return null
			return null;
		}
	}
}
