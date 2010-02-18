package testCases;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.Test;

import studentClasses.StudentYearOrder;
import studentClasses.UndergraduateStudent;

public class UndergraduateStudentTest {
	
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
	
	// Fixture attributes of UndergraduateStudent
	static String year = "Freshman";
	
	@Test
	public void testSetYear() {
		UndergraduateStudent student = new UndergraduateStudent();
		student.setYear(year);
		assertEquals( year, student.getYear() );
	}

	@Test
	public void testExplicitValueConstructor() {		
		UndergraduateStudent student = 
			new UndergraduateStudent( firstName, lastName, address, city, state,
					                  studentID, hoursTaken, gpa, year );
		
		// Ensure the underlying Person is correct
		assertEquals( lastName, student.getLastName() );
		assertEquals( firstName, student.getFirstName() );
		assertEquals( address, student.getAddress() );
		assertEquals( city, student.getCity() );
		assertEquals( state, student.getState() );
		
		// Ensure the underlying AbstractStudent is correct
		assertEquals( studentID, student.getStudentID() );
		assertEquals( hoursTaken, student.getHoursTaken() );
		assertEquals( gpa, student.getGpa() );
		
		// Ensure the the UndergraduateStudent attributes were initialized
		assertEquals( year, student.getYear() );
	}
	
	@Test
	public void testConstructFromTokenizer() {
		String fixture = lastName + "\t" + firstName + "\t" + address +
			"\t" + city + "\t" + state + "\t" + studentID + "\t" +
			hoursTaken + "\t" + gpa + "\t" + year;
		StringTokenizer tokenizer = new StringTokenizer(fixture, "\t");
		
		UndergraduateStudent student = new UndergraduateStudent(tokenizer);
		
		// Ensure the underlying Person is correct
		assertEquals( lastName, student.getLastName() );
		assertEquals( firstName, student.getFirstName() );
		assertEquals( address, student.getAddress() );
		assertEquals( city, student.getCity() );
		assertEquals( state, student.getState() );
		
		// Ensure the underlying AbstractStudent is correct
		assertEquals( studentID, student.getStudentID() );
		assertEquals( hoursTaken, student.getHoursTaken() );
		assertEquals( gpa, student.getGpa() );
		
		// Ensure the properties are correct
		assertEquals( year, student.getYear() );
	}
	
	@Test
	public void testToString() {
		UndergraduateStudent student = 
			new UndergraduateStudent( firstName, lastName, address, city, state,
					                  studentID, hoursTaken, gpa, year );
		String expectedValue = "UG\t" + lastName + "\t" + firstName + "\t" + address +
		"\t" + city + "\t" + state + "\t" + studentID + "\t" +
		hoursTaken + "\t" + formattedGpa + "\t" + year;
		
		assertEquals( expectedValue, student.toString() );
	}
	
	@Test
	public void testYearNumber() {
		UndergraduateStudent student = new UndergraduateStudent();
		
		student.setYear("");
		// This student doesn't have a year set, they should be lumped with "other undergrad"
		assertEquals( StudentYearOrder.OTHER_UNDERGRADUATE.ordinal(), student.getYearNumber() );
		
		student.setYear("Freshman");
		// This student should be sorted with freshmen
		assertEquals( StudentYearOrder.FRESHMAN.ordinal(), student.getYearNumber() );
		
		student.setYear("sophomore");
		// This student should be sorted with sophomores
		assertEquals( StudentYearOrder.SOPHOMORE.ordinal(), student.getYearNumber() );
		
		student.setYear("JUNIOR");
		// This student should be sorted with juniors
		assertEquals( StudentYearOrder.JUNIOR.ordinal(), student.getYearNumber() );
		
		student.setYear("Senior");
		// This student should be sorted with juniors
		assertEquals( StudentYearOrder.SENIOR.ordinal(), student.getYearNumber() );
		
		student.setYear("totally invalid");
		// This student should be sorted with other undergrad
		assertEquals( StudentYearOrder.OTHER_UNDERGRADUATE.ordinal(), student.getYearNumber() );
	}

}
