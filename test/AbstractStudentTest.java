package testCases;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.Test;

import studentClasses.AbstractStudent;

public class AbstractStudentTest {
	
	// Fixture attributes of Person
	static String firstName = "John";
	static String lastName = "Smith";
	static String address = "15 Oak St";
	static String city = "Portsmouth";
	static String state = "RI";
	
	// Fixture attributes of AbstractStudent
	static long studentID = 1684837;
	static int hoursTaken = 16;
	static double gpa = 4.0;
	static String formattedGpa = "4.000";
	
	/**
	 * Produces a fixture version of an <tt>AbstractStudent</tt> for
	 * testing.
	 * @return a fixture of <tt>AbstractStudent</tt>
	 */
	public static AbstractStudent studentFixture() {
		return new AbstractStudent( firstName, lastName, address, city, state,
					             	studentID, hoursTaken, gpa );
	}
	
	@Test
	public void testSetGpa() {
		AbstractStudent student = new AbstractStudent();
		student.setGpa(gpa);
		assertEquals( student.getGpa(), gpa );	
	}

	@Test
	public void testSetHoursTaken() {
		AbstractStudent student = new AbstractStudent();
		student.setHoursTaken(hoursTaken);
		assertEquals( student.getHoursTaken(), hoursTaken );
	}

	@Test
	public void testSetStudentID() {
		AbstractStudent student = new AbstractStudent();
		student.setStudentID(studentID);
		assertEquals( student.getStudentID(), studentID );
	}
	
	public void testExplicitValueConstructor() {	
		AbstractStudent student = 
			new AbstractStudent( firstName, lastName, address, city, state,
					             studentID, hoursTaken, gpa );
		
		// Ensure the Person-related attributes were initialized correctly
		assertEquals( student.getFirstName(), firstName );
		assertEquals( student.getLastName(), lastName );
		assertEquals( student.getAddress(), address );
		assertEquals( student.getCity(), city );
		assertEquals( student.getState(), state );
		
		// Ensure that the AbstractStudent attributes were initialized, too
		assertEquals( student.getStudentID(), studentID );
		assertEquals( student.getHoursTaken(), hoursTaken );
		assertEquals( student.getGpa(), gpa );	
	}
	
	@Test
	public void testConstructFromTokenizer() {
		String fixture = lastName + "\t" + firstName + "\t" + address +
			"\t" + city + "\t" + state + "\t" + studentID + "\t" +
			hoursTaken + "\t" + gpa;
		StringTokenizer tokenizer = new StringTokenizer(fixture, "\t");
		
		AbstractStudent student = new AbstractStudent(tokenizer);
		
		assertEquals( lastName, student.getLastName() );
		assertEquals( firstName, student.getFirstName() );
		assertEquals( address, student.getAddress() );
		assertEquals( city, student.getCity() );
		assertEquals( state, student.getState() );
		assertEquals( studentID, student.getStudentID() );
		assertEquals( hoursTaken, student.getHoursTaken() );
		assertEquals( gpa, student.getGpa() );
	}
	
	@Test
	public void testConstructWithID() {
		AbstractStudent student = new AbstractStudent(studentID);
		
		assertEquals( studentID, student.getStudentID() );
	}
	
	@Test
	public void testToString() {
		AbstractStudent student = 
			new AbstractStudent( firstName, lastName, address, city, state,
					             studentID, hoursTaken, gpa );
		String expectedValue = lastName + "\t" + firstName + "\t" + address +
		"\t" + city + "\t" + state + "\t" + studentID + "\t" +
		hoursTaken + "\t" + formattedGpa;
		
		assertEquals( expectedValue, student.toString() );
	}
	
	@Test
	public void testComparison() {
		// This student should be first
		AbstractStudent first = studentFixture();
		first.setStudentID(1000);
		
		// This student should be second
		AbstractStudent second = studentFixture();
		second.setStudentID(1001);
		
		assertTrue("Expected first object to be less than second",
				   first.compareTo(second) < 0);
		
		// Now they should be equivalent
		second.setStudentID(1000);
		
		assertTrue("Expected first object to be equal to the second",
				   first.compareTo(second) == 0);
		
		// Now first should be greater
		second.setStudentID(999);
		
		assertTrue("Expected first object to be greater than the second",
				   first.compareTo(second) > 0);
	}
	
	@Test
	public void testDefaultYearNumber() {
		// Ensure the default implementation of default year number is
		// to appear at the top of the list.
		AbstractStudent student = new AbstractStudent();
		assertEquals("Expected default year number to be 0",
				     0, student.getYearNumber());
	}
}
