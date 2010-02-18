package comparatorTestCases;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import comparatorClasses.GpaComparator;

import studentClasses.AbstractStudent;

public class GpaComparatorTest {
	@Test
	public void testCompare() {
		// Create two AbstractStudents, one who is not doing so well...
		AbstractStudent bozo = 
			new AbstractStudent( "", "", "", "", "",
					             1000, 0, 2.5 );
		// And one who is making this look easy
		AbstractStudent smarty = 
			new AbstractStudent( "", "", "", "", "",
					             1000, 0, 4.0 );
		
		
		Comparator<AbstractStudent> comparator = new GpaComparator();
		
		assertTrue( "Expected Bozo to be doing less well than Smarty",
				    comparator.compare(bozo, smarty) < 0 );
		
		assertTrue( "Expected Smarty to be doing better than Bozo",
			        comparator.compare(smarty, bozo) > 0 );
		
		// Tutoring occurs...
		bozo.setGpa(4.0);
		
		assertTrue( "Expected Bozo and Smarty to be doing equally well",
			        comparator.compare(bozo, smarty) == 0 );
	}
	
	@Test
	public void testToString() {
		// Ensure the comparator returns a human-readable description of what it sorts by
		assertEquals( "GPA", new GpaComparator().toString() );
	}
}
