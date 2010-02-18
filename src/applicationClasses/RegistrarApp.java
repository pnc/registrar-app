/**
 * 
 */
package applicationClasses;

import java.util.Comparator;
import java.util.Iterator;

import com.phillipcalvin.utilities.EventSource;

import comparatorClasses.StudentIDComparator;

import studentClasses.AbstractStudent;


/** 
 * purpose: Handles basic functionality for the registrar application, including
 * loading and saving a database of students.
 * @author Phillip Calvin
 * @version: 1.20.2010
 */
public class RegistrarApp extends EventSource {

	/**
	 * A reference to the currently-loaded <tt>StudentDatabase</tt>.
	 * @uml.property  name="database"
	 */
	private StudentDatabase database;
	
	/**
	 * Initializes a new registrar application by creating a blank database.
	 * You may overwrite this default database by using the load methods.
	 */
	public RegistrarApp() {
		super();
		this.database = new StudentDatabase();
	}
	
	/**
	 * Returns the current student database.
	 * @return  The current StudentDatabase
	 * @uml.property  name="database"
	 */
	public StudentDatabase getDatabase() {
		return database;
	}

	/**
	 * Replaces the current student database with the provided one.
	 * @param database  The database of students to set.
	 * @uml.property  name="database"
	 */
	public void setDatabase(StudentDatabase database) {
		if (database != null) {
			this.database = database;
			// Let everyone else know that this changed
			this.notifyListeners();
		} else {
			throw new IllegalArgumentException("Student database must be an instance of ArrayList.");
		}
	}


	/**
	 * Loads a student database from the given file. If it's a .ser file,
	 * the file will be saved as a serialized object. If it ends in .txt, it is
	 * assumed to be a tab-separated file.
	 * @param fileName the filename of the file to read
	 */
	public boolean openDatabase(String fileName) {
		StudentDatabase loadedDatabase = null;
		
		// Determine what type of file it is
		String fileType = extensionFor(fileName);
		
		if ( fileType.equals("ser") ) {
			loadedDatabase = StudentDatabase.readPersistentObject(fileName);
		} else if ( fileType.equals("txt") ) {
			loadedDatabase = StudentDatabase.readFromFile(fileName);
		} else {
			// Unrecognized type, complain and return false
			System.err.println("Unrecognized file type: " + fileType);
			return false;
		}		
		
		if (loadedDatabase != null) {
			setDatabase(loadedDatabase);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Determines the extension for a given filename, if any.
	 * @param fileName the filename whose extension should be found
	 * @return The extension, if any was found, or a blank string if no extension
	 */
	public static String extensionFor(String fileName) {
		int periodLocation = fileName.lastIndexOf(".");
		return ( periodLocation == -1 ) ? "" : fileName.substring( periodLocation+1, fileName.length() );
	} 

	/**
	 * Saves a student database to the given file.
	 * @param fileName the filename of the file to write. If it's a .ser file,
	 * the file will be saved as a serialized object. If it ends in .txt, it will be saved
	 * as a tab-separated file.
	 */
	public boolean saveDatabase(String fileName) {		
		// Determine what type of file it is
		String fileType = extensionFor(fileName);
		
		if ( fileType.equals("ser") ) {
			getDatabase().writePersistentObject(fileName);
			return true;
		} else if ( fileType.equals("txt") ) {
			getDatabase().saveToFile(fileName);
			return true;
		} else {
			// Unrecognized type, complain and return false
			System.err.println("Unrecognized file type: " + fileType);
			return false;
		}
	}
	
	/**
	 * Sorts the database using the given <tt>Comparator&lt;AbstractStudent&lt;</tt>.
	 * @return The student database as a tab-separated string.
	 * @param comparator The comparator to use to compare the students.
	 */
	public String displayStudents(Comparator<AbstractStudent> comparator) {
		// Sort the underlying database
		getDatabase().sort(comparator);
		
		// Return the tab-separated list of students
		return getDatabase().toString();
	}
	
	/**
	 * Finds a student matching the given <tt>example</tt>. Matching
	 * is defined as having the same ID number.
	 * @return The matching student, if any.
	 * @param example A student with the ID for which you're searching.
	 */
	public AbstractStudent findStudent(AbstractStudent example) {
		return getDatabase().findBinary( example, new StudentIDComparator() );
	}
	
	/**
	 * Finds a student matching the given <tt>example</tt>. Matching
	 * is defined as having the same ID number.
	 * @return The matching student, if any.
	 * @param example A student with the ID for which you're searching.
	 */
	public AbstractStudent editStudent(AbstractStudent example) {
		return getDatabase().findBinary( example, new StudentIDComparator() );
	}
	
	/**
	 * Adds the given student to the lists of students.
	 * @param student The student to add to the list.
	 */
	public void addStudent(AbstractStudent student) {
		getDatabase().add(student);
	}
	
	/**
	 * Removes the first student who has the same 
	 * ID number as the provided <tt>example</tt> student.
	 * @param example A student whose ID should match the removed student.
	 * @return The removed student, or <tt>null</tt> if no student matched
	 */
	public AbstractStudent removeStudent(AbstractStudent example) {
		return getDatabase().remove(example);
	}
	
	/**
	 * @return an iterator for visiting each student in currently-loaded
	 * student database.
	 */
	public Iterator<AbstractStudent> iterator() {
		return getDatabase().getStudents().iterator();
	}
}
