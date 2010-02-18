package comparatorTestCases;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import comparatorClasses.YearComparator;

import studentClasses.AbstractStudent;
import studentClasses.GraduateStudent;
import studentClasses.UndergraduateStudent;

public class YearComparatorTest {
	@Test
	public void testCompare() {
		// Create two UndergraduateStudents, one who is a senior...
		UndergraduateStudent senior = new UndergraduateStudent();
		senior.setYear("Senior");
		
		// ...one who is just starting out...
		UndergraduateStudent freshman = new UndergraduateStudent();
		freshman.setYear("Freshman");
		
		// And one who is in graduate school.
		GraduateStudent graduate = new GraduateStudent();
		
		Comparator<AbstractStudent> comparator = new YearComparator();
		
		assertTrue( "Expected freshman to be less than senior",
				    comparator.compare(freshman, senior) < 0 );
		
		assertTrue( "Expected senior to be greater than freshman",
			        comparator.compare(senior, freshman) > 0 );
		
		assertTrue( "Expected freshman to be equivalent to freshman",
		        comparator.compare(freshman, freshman) == 0 );
		
		assertTrue( "Expected graduate to be greater than freshman",
			        comparator.compare(graduate, freshman) > 0 );
		
		assertTrue( "Expected graduate to be greater than senior",
		        comparator.compare(graduate, senior) > 0 );
		
		assertTrue( "Expected freshman to be smaller than graduate",
		        comparator.compare(freshman, graduate) < 0 );
		
		assertTrue( "Expected graduate to be equivalent to graduate",
		        comparator.compare(graduate, graduate) == 0 );
	}
	
	@Test
	public void testToString() {
		// Ensure the comparator returns a human-readable description of what it sorts by
		assertEquals( "Year", new YearComparator().toString() );
	}
}
