package comparatorTestCases;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import comparatorClasses.NameComparator;

import studentClasses.AbstractStudent;

public class NameComparatorTest {
	@Test
	public void testCompare() {
		// Create two AbstractStudents, one named Abigail Adams...
		AbstractStudent abigail = 
			new AbstractStudent( "Abigail", "Adams", "", "", "",
					             1000, 0, 4.0 );
		// And one named Zed Zadder
		AbstractStudent zed = 
			new AbstractStudent( "Zed", "Zadder", "", "", "",
					             1000, 0, 4.0 );
		
		
		Comparator<AbstractStudent> comparator = new NameComparator();
		
		// Make sure Adams comes before Zadder
		assertTrue( "Expected Adams to come before Zadder",
				    comparator.compare(abigail, zed) < 0 );
		
		// Make sure Adams is equal to Adams
		assertTrue( "Expected Adams to be equivalent to Adams",
			    comparator.compare(abigail, abigail) == 0 );
		
		// Make sure Zadder comes after Adams
		assertTrue( "Expected Adams to come before Zadder",
			    comparator.compare(zed, abigail) > 0 );
		
		// Abigail Marries Zed =)
		abigail.setLastName("Zadder");
		
		// Make sure Abigail comes first
		assertTrue( "Expected Abigail to come before Zed",
			    comparator.compare(abigail, zed) < 0 );
		
		// Make sure Zed comes after
		assertTrue( "Expected ZEd to come after Abigail",
			    comparator.compare(zed, abigail) > 0 );
	}
	
	@Test
	public void testToString() {
		// Ensure the comparator returns a human-readable description of what it sorts by
		assertEquals( "Name", new NameComparator().toString().toString() );
	}
}
