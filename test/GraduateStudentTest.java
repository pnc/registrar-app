package testCases;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.Test;

import studentClasses.GraduateStudent;
import studentClasses.StudentYearOrder;

public class GraduateStudentTest {
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
	
	// Fixture attributes of GraduateStudent
	static String degree = "MS";
	static int yearsEnrolled = 2;
	
	@Test
	public void testSetYearsEnrolled() {
		GraduateStudent student = new GraduateStudent();
		student.setYearsEnrolled(yearsEnrolled);
		assertEquals( student.getYearsEnrolled(), yearsEnrolled );
	}
	
	@Test
	public void testSetDegree() {
		GraduateStudent student = new GraduateStudent();
		student.setDegree(degree);
		assertEquals( student.getDegree(), degree );
	}

	@Test
	public void testExplicitValueConstructor() {
		// Attributes of GraduateStudent
		int yearsEnrolled = 2;
		String degree = "MS"; 
		
		GraduateStudent student = 
			new GraduateStudent( firstName, lastName, address, city, state,
					             studentID, hoursTaken, gpa, yearsEnrolled, degree );
		
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
		
		// Ensure the the GraduateStudent attributes were initialized
		assertEquals( student.getYearsEnrolled(), yearsEnrolled );
		assertEquals( student.getDegree(), degree );
	}
	
	@Test
	public void testConstructFromTokenizer() {
		String fixture = lastName + "\t" + firstName + "\t" + address +
			"\t" + city + "\t" + state + "\t" + studentID + "\t" +
			hoursTaken + "\t" + gpa + "\t" + yearsEnrolled + "\t" + degree;
		StringTokenizer tokenizer = new StringTokenizer(fixture, "\t");
		
		GraduateStudent student = new GraduateStudent(tokenizer);
		
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
		assertEquals( yearsEnrolled, student.getYearsEnrolled() );
		assertEquals( degree, student.getDegree() );
	}
	
	@Test
	public void testToString() {
		GraduateStudent student = 
			new GraduateStudent( firstName, lastName, address, city, state,
					             studentID, hoursTaken, gpa, yearsEnrolled, degree );
		
		String expectedValue = "GR\t" + lastName + "\t" + firstName + "\t" + address +
		"\t" + city + "\t" + state + "\t" + studentID + "\t" +
		hoursTaken + "\t" + formattedGpa + "\t" + yearsEnrolled + "\t" + degree;
		
		assertEquals( expectedValue, student.toString() );
	}

	@Test
	public void testYearNumber() {
		GraduateStudent student = new GraduateStudent();
		
		assertEquals( StudentYearOrder.GRADUATE.ordinal(), student.getYearNumber() );
	}
}
