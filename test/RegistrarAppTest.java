package testCases;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

import com.phillipcalvin.utilities.Testing;

import comparatorClasses.GpaComparator;
import comparatorClasses.StudentIDComparator;
import comparatorClasses.HoursTakenComparator;
import comparatorClasses.NameComparator;
import comparatorClasses.YearComparator;

import studentClasses.AbstractStudent;
import studentClasses.UndergraduateStudent;

import applicationClasses.RegistrarApp;
import applicationClasses.StudentDatabase;

public class RegistrarAppTest {
	
	static String fixtureFilename = "src/testCases/fixtures/students.txt";
	static String serializedFixtureFilename = "src/testCases/fixtures/students.ser";
	
	static String invalidFixtureFilename = "src/testCases/fixtures/invalid.txt";
	static String emptyFixtureFilename = "src/testCases/fixtures/empty.txt";
	static String nonExistantFixtureFilename = "src/testCases/fixtures/nope.txt";
	
	// This file will be deleted before each test, if it exists
	static String outputFilename = "src/testCases/fixtures/output.txt";
	static String serializedOutputFilename = "src/testCases/fixtures/output.ser";
	static String unknownOutputFilename = "src/testCases/fixtures/output";
	
	void setUp() {
		this.setUp();
		
		// Delete our output files before each test (to ensure a level playing field.)
		String[] testFiles = {outputFilename, serializedOutputFilename, unknownOutputFilename};
		for (String filename : testFiles) {
			File output = new File(filename);
			if ( output.exists() ) {
				output.delete();
			}
		}
	}
	
	/**
	 * Validate certain properties about a student -- Allie Kat --
	 * who we expect to exist in most of our fixture files.
	 * @param application A RegistrarApp instance that has been populated with data
	 */
	public static void ensureContainsStudent(RegistrarApp application) {
		// Validate certain properties about the student "Allie Kat"
		UndergraduateStudent student = null;
		
		Iterator<AbstractStudent> iterator =
			application.getDatabase().getStudents().iterator();
		
		// Locate Allie Kat
		while ( iterator.hasNext() ) {
			AbstractStudent possibleStudent = iterator.next();
			if ( possibleStudent.getLastName().equals("Kat") ) {
				student = (UndergraduateStudent) possibleStudent;
			}
		}
		
		// Verify her properties
		assertNotNull(student);
		assertEquals(3.125, student.getGpa() );
		assertEquals("Senior", student.getYear() );
	}
	
	@Test
	public void testSetDatabase() {
		RegistrarApp application = new RegistrarApp();
		// Ensure we can't nullify the student database
		try {
			application.setDatabase(null);
			fail("Expected setting the database to null to throw an exception.");
		} catch (Exception e) {
			assertEquals( IllegalArgumentException.class, e.getClass() );
		}
	}
	
	@Test
	public void testOpenTextDatabase() {
		RegistrarApp application = new RegistrarApp();
		boolean result = application.openDatabase(fixtureFilename);
		
		assertTrue( "Expected openDatabase to return true.", result );
		
		// Ensure the correct number of students were loaded
		assertEquals( 7, application.getDatabase().getStudents().size() );
		
		ensureContainsStudent(application);
	}
	
	@Test
	public void testOpenSerializedDatabase() {
		RegistrarApp application = new RegistrarApp();
		boolean result = application.openDatabase(serializedFixtureFilename);
		
		assertTrue( "Expected openDatabase to return true.", result );
		
		// Ensure the correct number of students were loaded
		assertEquals( 7, application.getDatabase().getStudents().size() );
		
		ensureContainsStudent(application);
	}
	
	@Test
	public void testOpenNonExistantDatabase() {
		RegistrarApp application = new RegistrarApp();
		boolean result = application.openDatabase(nonExistantFixtureFilename);
		
		assertFalse( "Expected openDatabase to return false.", result );
	}
	
	@Test
	public void testOpenUnknownDatabaseType() {
		RegistrarApp application = new RegistrarApp();
		boolean result = application.openDatabase(unknownOutputFilename);
		
		assertFalse( "Expected openDatabase to return false.", result );
	}
	
	@Test
	public void testOpenInvalidDatabase() {
		RegistrarApp application = new RegistrarApp();
		boolean result = application.openDatabase(invalidFixtureFilename);
		
		assertTrue( "Expected openDatabase to return true.", result );
		
		ensureContainsStudent(application);
	}
	
	@Test
	public void testSaveTextDatabase() {
		RegistrarApp application = new RegistrarApp();
		
		// Ensure writing a blank database doesn't throw an exception
		try {
			application.saveDatabase(outputFilename);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Did not expect saving a blank database to fail.");
		}
		
		// Load the database
		application.openDatabase(fixtureFilename);
		
		// Write the database back to a file
		application.saveDatabase(outputFilename);
		
		String expectedData = null;
		String actualData = null;
		
		try {
			// Read the entire file
			Scanner scan = new Scanner( new File(fixtureFilename) );  
			scan.useDelimiter("\\Z");  
			expectedData = scan.next(); 
		} catch (IOException e) {
			fail("Unable to read input fixture file.");
			e.printStackTrace();
		}
		
		try {
			// Read the entire file
			Scanner scan = new Scanner( new File(outputFilename) );  
			scan.useDelimiter("\\Z");  
			actualData = scan.next(); 
		} catch (IOException e) {
			fail("Unable to read output file.");
			e.printStackTrace();
		}
		
		// Ensure the files' content is the same
		assertEquals( expectedData, actualData );
	}
	
	@Test
	public void testSaveSerializedDatabase() {
		RegistrarApp application = new RegistrarApp();
		
		// Load the database
		application.openDatabase(fixtureFilename);
		
		// Write the database back to a serialized file
		application.saveDatabase(serializedOutputFilename);
		
		assertTrue( "Expected the serialized file to exist",
					 new File(serializedOutputFilename).exists() );
		
		// Try to save an unknown type
		assertFalse( application.saveDatabase(unknownOutputFilename) );
		
		assertFalse( "Expected the unknown file to not exist",
					 new File(unknownOutputFilename).exists() );
	}
	
	@Test
	@SuppressWarnings("unchecked") // Suppress warnings, since we'll be working with generic types
	public void testDisplayStudents() {
		RegistrarApp application = new RegistrarApp();
		
		// Ensure that the method returns blank is there's no database
		assertEquals( "", application.displayStudents( new GpaComparator() ));
		
		// Load an application instance from the fixture file
		
		application.openDatabase(fixtureFilename);
		
		// Load a raw database from the same fixture file -- we'll use this
		// to ensure the students are being sorted by the underlying database object.
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		
		// Set up all the classes we'll use as comparators
		Class comparatorArray[] = {
			GpaComparator.class,
			HoursTakenComparator.class,
			NameComparator.class,
			StudentIDComparator.class,
			YearComparator.class
		};
		
		
		ArrayList<Class> comparators = new ArrayList<Class> (Arrays.asList(comparatorArray));
		
		// Use each comparator to sort the list and then ensure it was sorted 
		// correctly. This is done as a loop since we have quite a few comparators to test.
		for (Class comparator : comparators) {
			// Instantiate a comparator
			Comparator<AbstractStudent> comparatorInstance;
			try {
				comparatorInstance = (Comparator<AbstractStudent>) comparator.newInstance();
				
				// Use it to sort our reference database
				database.sort(comparatorInstance);
				
				// Now, compare that to what the application returns
				assertEquals( "Expected the students to be sorted by " + comparatorInstance.toString(),
					          database.toString(), application.displayStudents(comparatorInstance) );
			} catch (Exception e) {
				e.printStackTrace();
				fail( "Unable to instantiate " + comparator.getName() );
			}
		}
	}
	
	@Test
	public void testIterator() {
		// Load an application instance from the fixture file
		RegistrarApp application = new RegistrarApp();
		application.openDatabase(fixtureFilename);
		
		// Produce a string by walking across the students
		String data = "";
		Iterator<AbstractStudent> iterator = application.iterator();
		
		while (iterator.hasNext()) {
			AbstractStudent student = iterator.next();
			
			// Add this student's representation to the string
			data += student.toString();
			
			// Add a newline if there are more records
			if (iterator.hasNext()) {
				data += "\n";
			}
		}
		
		assertEquals( "Expected the iterator to allow visiting every student",
				      application.getDatabase().toString(), data );
	}
	
	@Test
	public void testFindStudent() {
		// Load an application instance from the fixture file
		RegistrarApp application = new RegistrarApp();
		application.openDatabase(fixtureFilename);
		
		// We're looking for someone we know is there:
		// Allie Kat with ID 444556666
		AbstractStudent example = new AbstractStudent(444556666L);
		
		// Perform the search
		AbstractStudent match = application.findStudent(example);
		
		assertNotNull("Expected a result", match);
		assertEquals( "Allie", match.getFirstName() );
		assertEquals( "Kat", match.getLastName() );
		
		// Look for someone who isn't there
		example = new AbstractStudent(333L);
		
		// Perform the search
		match = application.findStudent(example);
		
		assertNull("Expected no result", match);
	}
	
	@Test
	public void testEditStudent() {
		// Load an application instance from the fixture file
		RegistrarApp application = new RegistrarApp();
		application.openDatabase(fixtureFilename);
		
		// We're looking for someone we know is there:
		// Allie Kat with ID 444556666
		AbstractStudent example = new AbstractStudent(444556666L);
		
		// Perform the edit
		AbstractStudent match = application.editStudent(example);
		
		assertNotNull("Expected a result", match);
		assertEquals( "Allie", match.getFirstName() );
		assertEquals( "Kat", match.getLastName() );
		
		// Look for someone who isn't there
		example = new AbstractStudent(333L);
		
		// Perform the edit
		match = application.editStudent(example);
		
		assertNull("Expected no result", match);
	}
	
	@Test
	public void testAddStudent() {
		// Load an application instance from the fixture file
		RegistrarApp application = new RegistrarApp();
		application.openDatabase(fixtureFilename);
		
		// Prepare a new student
		UndergraduateStudent student = 
			new UndergraduateStudent( 
				UndergraduateStudentTest.firstName, 
				UndergraduateStudentTest.lastName, 
				UndergraduateStudentTest.address, 
				UndergraduateStudentTest.city, 
				UndergraduateStudentTest.state,
				UndergraduateStudentTest.studentID, 
				UndergraduateStudentTest.hoursTaken, 
				UndergraduateStudentTest.gpa, 
				UndergraduateStudentTest.year );
		
		// Add the student
		application.addStudent(student);
		
		// Make sure they show up in the output
		assertTrue( "Expected the student to show up in the output",
				    application.getDatabase().toString().contains( student.toString() ) );
	}
	
	@Test
	public void testRemoveStudent() {
		// Load an application instance from the fixture file
		RegistrarApp application = new RegistrarApp();
		application.openDatabase(fixtureFilename);
		
		// Attempt to find Isabelle Ringing, a student from the fixture whose ID we know
		AbstractStudent example = new AbstractStudent(999887777L);
		
		// Make sure she's in the list
		assertNotNull( "Expected Isabelle to be in the database", 
				       application.findStudent(example) );
		
		// Attempt to remove her
		AbstractStudent isabelle = application.removeStudent( example );
		
		// Ensure we got her back
		assertNotNull("Expected to receive the removed student", isabelle);
		assertEquals( "Ringing", isabelle.getLastName() );
		
		// Ensure she is no longer in the list
		assertNull( "Expected Isabelle to not be in the database", 
				     application.getDatabase().findBinary( example, new StudentIDComparator() ) );
	}
	
	@Test
	public void testDefinedMethods() {
		// Ensure that the API defined by Dr. Porter (with some method names changed
		// to match those specified in earlier labs) is reflected in 
		// the StudentDatabase class. 
		String methodNames[] = {"openDatabase", "saveDatabase", "displayStudents",
				"findStudent", "addStudent", "editStudent", "removeStudent", "iterator"};
		
		Testing.assertMethodsDefined( RegistrarApp.class, methodNames);
	}
}
