package comparatorTestCases;

import static org.junit.Assert.*;

import org.junit.Test;

import comparatorClasses.StudentIDComparator;

import studentClasses.GraduateStudent;
import studentClasses.UndergraduateStudent;

public class StudentIDComparatorTest {
	@Test
	public void testCompare() {
		// This student should be first
		GraduateStudent first = new GraduateStudent();
		first.setStudentID(1000);
		
		// This student should be second
		UndergraduateStudent second = new UndergraduateStudent();
		second.setStudentID(1001);
		
		StudentIDComparator comparator = new StudentIDComparator();
		
		assertTrue("Expected first object to be less than second",
				   comparator.compare(first, second) < 0);
		
		// Now they should be equivalent
		second.setStudentID(1000);
		
		assertTrue("Expected first object to be equal to the second",
				   comparator.compare(first, second) == 0);
		
		// Now first should be greater
		second.setStudentID(999);
		
		assertTrue("Expected first object to be greater than the second",
				   comparator.compare(first, second) > 0);
	}
	
	@Test
	public void testToString() {
		// Ensure the comparator returns a human-readable description of what it sorts by
		assertEquals( "ID number", new StudentIDComparator().toString() );
	}
}
