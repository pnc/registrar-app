package comparatorTestCases;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import comparatorClasses.HoursTakenComparator;

import studentClasses.AbstractStudent;

public class HoursTakenComparatorTest {
	@Test
	public void testCompare() {
		// Create two AbstractStudents, one who is one of those "fifth-year seniors"...
		AbstractStudent senior = new AbstractStudent();
		senior.setHoursTaken(450);
		
		// And one who is just starting out
		AbstractStudent freshman = new AbstractStudent();
		freshman.setHoursTaken(4);		
		
		Comparator<AbstractStudent> comparator = new HoursTakenComparator();
		
		assertTrue( "Expected freshman to be less than senior",
				    comparator.compare(freshman, senior) < 0 );
		
		assertTrue( "Expected senior to be greater than freshman",
			        comparator.compare(senior, freshman) > 0 );
		
		// Several years pass...
		freshman.setHoursTaken(450);
		
		assertTrue( "Expected the two students to be equivalent",
			        comparator.compare(freshman, senior) == 0 );
	}
	
	@Test
	public void testToString() {
		// Ensure the comparator returns a human-readable description of what it sorts by
		assertEquals( "Credit hours taken", new HoursTakenComparator().toString() );
	}
}
