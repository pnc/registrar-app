package testCases;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

import com.phillipcalvin.utilities.Testing;

import comparatorClasses.NameComparator;
import comparatorClasses.StudentIDComparator;
import comparatorClasses.YearComparator;

import studentClasses.AbstractStudent;
import studentClasses.UndergraduateStudent;

import applicationClasses.StudentDatabase;

public class StudentDatabaseTest {
	// A basic student database for testing
	static String fixtureFilename = "src/testCases/fixtures/students.txt";
	// A pre-sorted (by ID) database of students
	static String sortedFixtureFilename = "src/testCases/fixtures/sorted.txt";
	// A student database, but with invalid entries
	static String invalidFixtureFilename = "src/testCases/fixtures/invalid.txt";
	// A student database that is just an empty file
	static String emptyFixtureFilename = "src/testCases/fixtures/empty.txt";
	// A bad pathname that does not exist
	static String nonExistantFixtureFilename = "src/testCases/fixtures/nope.txt";
	// This file will be deleted before each test, if it exists
	static String outputFilename = "src/testCases/fixtures/output.txt";
	
	void setUp() {
		this.setUp();
		
		// Delete our output file before each test (to ensure a level playing field.)
		File output = new File(outputFilename);
		if ( output.exists() ) {
			output.delete();
		}
	}
	
	@Test
	public void testConstructor() {
		// Ensure a blank student database is created
		StudentDatabase database = new StudentDatabase();
		
		assertEquals( 0, database.getStudents().size() );
	}
	
	@Test
	public void testSetDatabase() {
		StudentDatabase database = new StudentDatabase();
		// Ensure we can't nullify the list of students
		try {
			database.setStudents(null);
			fail("Expected setting the students list to null to throw an exception.");
		} catch (Exception e) {
			assertEquals( IllegalArgumentException.class, e.getClass() );
		}
	}
	
	@Test
	public void testReadFromFile() {
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		
		// Load the expected data from the fixture file
		try {
			// Caveat progammor!
			// The fixture file (supplied by Dr. Porter)
			// uses Windows-style line endings. 
			Scanner scanner = new Scanner( new File(fixtureFilename) );
			
			scanner.useDelimiter("\n");
			String expectedData = "";
			
			// Essentially read the entire file into memory, but re-concatenate
			// with newlines to ensure OS independence.
			while (scanner.hasNext()) {
				// Trim each line, thus removing any additional newline characters
				expectedData += scanner.next().trim();
				if (scanner.hasNext()) {
					expectedData += "\n";
				}
			}
			
			// Helpful to examine the data in the console output.
			// System.out.println( "[" + expectedData + "]");
			// System.out.println(  "[" + database.toString() + "]");
			
			assertEquals( expectedData, database.toString() );
		} catch (IOException e) {
			fail("Unable to load fixture data:");
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testReadFromEmptyFile() {
		try {
			StudentDatabase database = StudentDatabase.readFromFile(emptyFixtureFilename);
			
			// Ensure empty in, empty out
			assertEquals( "", database.toString() );
		} catch (Exception e) {
			e.printStackTrace();
			fail("Did not expect loading from a blank file to cause an Exception.");
		}
	}
	
	@Test
	public void testReadFromInvalidFile() {
		try {
			StudentDatabase database = StudentDatabase.readFromFile(invalidFixtureFilename);
			// There's one invalid record and one valid one -- make sure we get one!
			assertEquals( database.getStudents().size(), 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Did not expect loading an invalid file to cause an Exception.");
		}
	}
	
	@Test
	public void testSaveToFile() {
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		database.saveToFile(outputFilename);
		
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
	public void testWritePersistentObject() {
		// Load the fixture database
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		
		// Use its toString as the expected value for later in the test
		String expectedData = database.toString();
		
		// Serialize the data to a file
		database.writePersistentObject(outputFilename);
		
		// Read that data back in
		StudentDatabase resultDatabase = StudentDatabase.readPersistentObject(outputFilename);
		
		// Ensure it's the same
		assertEquals( expectedData, resultDatabase.toString() );
	}
	
	@Test
	public void testShuffle() {
		// Load a student database
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		
		// Store the original order
		String originalOrder = database.toString();
		
		// Ensure that the student list can be shuffled
		database.shuffle();
		
		// Ensure the order has changed
		assertFalse( "Expected the database order to have changed",
					 originalOrder.equals( database.toString() ) ); 
	}
	
	@Test
	public void testSort() {
		// Load a student database that is pre-sorted (for comparison)
		StudentDatabase database = StudentDatabase.readFromFile(sortedFixtureFilename);
		
		// Save its ordered state
		String orderedState = database.toString();
		
		// Shuffle it to make it unsorted
		database.shuffle();
		
		// Make sure that the shuffling did not, by some horrible state of the Universe,
		// result in a still-sorted list
		assertFalse( "Expected the shuffled database to be different from sorted",
					 orderedState.equals( database.toString() ) );
		
		// Sort it back, by the default attribute (ID number)
		database.sort();
		
		// Make sure it has returned to its sorted state
		assertEquals( "Expected the database to be back in sorted order",
					  orderedState, database.toString() );
	}
	
	@Test
	public void testIterator() {
		// Load the fixture database
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		
		// Produce a string by walking across the students
		String data = "";
		Iterator<AbstractStudent> iterator = database.iterator();
		
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
				      database.toString(), data );
		
		// Ensure horrible things don't happen if we have an empty database
		database = new StudentDatabase();
		assertFalse( database.iterator().hasNext() );
	}
	
	@Test
	public void testSortWithComparator() {
		// Load a student database
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		
		// We know the students' last names should end up in this order
		String last[] = {"Board","Canusee","Dover","Dupp","Jordan","Kat","Ringing"};
		
		ArrayList<String> correctLastNames = new ArrayList<String> (Arrays.asList(last));
		
		// Sort the database by name
		database.sort( new NameComparator() );
		
		// Collect the now-sorted last names
		ArrayList<String> sortedLastNames = new ArrayList<String>();
		
		for ( AbstractStudent student : database.getStudents() ) {
			sortedLastNames.add( student.getLastName() );
		}
		
		// Ensure the last name lists match
		assertEquals( "Expected the students to now be sorted by name", 
				      correctLastNames, sortedLastNames );
	}
	
	@Test
	public void testFind() {
		// Load a student database
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		
		// Attempt to find Isabell Ringing (oh, ha, Dr. Porter, your names are so funny!),
		// a student in our test fixtures with ID 999887777.
		AbstractStudent example = new AbstractStudent();
		
		// Set up our example object
		example.setFirstName("Isabelle");
		example.setLastName("Ringing");
		
		// Attempt to find one like the example
		Iterator<AbstractStudent> iterator = database.find( example, new NameComparator() );
		
		// Ensure we found her
		assertNotNull("Expected find to return an iterator", iterator);
		assertTrue( "Expected the iterator to have a value", iterator.hasNext() );
		assertEquals( "Expected Isabell to have the correct ID",
				      (999887777L), iterator.next().getStudentID() );
		
		// Try to find somebody who isn't in there
		// Set up our example object
		example.setFirstName("Notgonna");
		example.setLastName("Findme");
		
		// Attempt to find one like the example
		iterator = database.find( example, new NameComparator() );
		
		// Ensure we found her
		assertNull("Expected to get null", iterator);
		
		// Try to find several students, all seniors -- this time, our example
		// will be an undergraduate
		UndergraduateStudent senior = new UndergraduateStudent();
		senior.setYear("Senior");
		
		// Attempt to find ones like the example
		iterator = database.find( senior, new YearComparator() );
		
		// Figure out who got returned
		HashSet<String> lastNames = new HashSet<String>();
		
		// Collect the last names
		while ( iterator.hasNext() ) {
			lastNames.add( iterator.next().getLastName() );
		}
		
		// We expect two seniors
		String[] names = {"Dupp", "Kat"};
		HashSet<String> expectedNames = new HashSet<String>(Arrays.asList(names));
		
		// Ensure the right people were returned
		assertEquals( "Expected two seniors from fixture.",
				       expectedNames, lastNames );
	}
	
	@Test
	public void testFindBinary() {
		// Load a student database
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		
		// Attempt to find Isabell Ringing, a student from the fixture
		AbstractStudent example = new AbstractStudent();
		
		// Set up our example object
		example.setFirstName("Isabelle");
		example.setLastName("Ringing");
		
		// Attempt to find one like the example
		AbstractStudent student = database.findBinary( example, new NameComparator() );
		
		// Ensure we found her
		assertNotNull("Expected find to return an iterator", student);
		assertEquals( "Expected Isabell to have the correct ID",
				      (999887777L), student.getStudentID() );
		
		// Set up an example we won't find
		example.setFirstName("Notgonna");
		example.setLastName("Findme");
		
		// Attempt to find one like the example
		student = database.findBinary( example, new NameComparator() );
		
		// Ensure we did not find anyone
		assertNull("Did not expect a student in return", student);
	}
	
	@Test
	public void testAdd() {
		// Load a student database
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		
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
		database.add(student);
		
		// Make sure they show up in the output
		assertTrue( "Expected the student to show up in the output",
				    database.toString().contains( student.toString() ) );
	}
	
	@Test
	public void testRemove() {
		// Load a student database
		StudentDatabase database = StudentDatabase.readFromFile(fixtureFilename);
		
		// Attempt to find Isabelle Ringing, a student from the fixture whose ID we know
		AbstractStudent example = new AbstractStudent(999887777L);
		
		// Make sure she's in the list
		assertNotNull( "Expected Isabelle to be in the database", 
				       database.findBinary( example, new StudentIDComparator() ) );
		
		// Attempt to remove her
		AbstractStudent isabelle = database.remove( example );
		
		// Ensure we got her back
		assertNotNull("Expected to receive the removed student", isabelle);
		assertEquals( "Ringing", isabelle.getLastName() );
		
		// Ensure she is no longer in the list
		assertNull( "Expected Isabelle to not be in the database", 
				     database.findBinary( example, new StudentIDComparator() ) );
	}
	
	@Test
	public void testDefinedMethods() {
		// Ensure that the API defined by Dr. Porter (with some method names changed
		// to match those specified in earlier labs) is reflected in 
		// the StudentDatabase class. 
		String methodNames[] = {"toString", "find", "findBinary", "add", "sort", 
				  "remove", "iterator", "shuffle", "readFromFile", "saveToFile",
				  "writePersistentObject"};
		
		Testing.assertMethodsDefined( StudentDatabase.class, methodNames);
	}
}
