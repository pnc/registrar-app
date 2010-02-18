package testCases;

import static org.junit.Assert.*;
import org.junit.Test;
import studentClasses.StudentYearOrder;

public class StudentYearOrderTest {
	@Test
	public void testSortValues() {
		// Ensure that the sort values define a graduate as being later than a freshman
		assertTrue( StudentYearOrder.GRADUATE.ordinal() > 
		            StudentYearOrder.FRESHMAN.ordinal() );
		
		// Ensure that the sort values define a senior as being later than a freshman
		assertTrue( StudentYearOrder.SENIOR.ordinal() > 
		            StudentYearOrder.FRESHMAN.ordinal() );
		
		// Ensure that other types sort last
		assertTrue( StudentYearOrder.OTHER.ordinal() > 
		            StudentYearOrder.GRADUATE.ordinal() );
		
		// Ensure that we can look up an enum by a String
		assertEquals( StudentYearOrder.SOPHOMORE,
				      StudentYearOrder.get("sophomore") );
		
		// Ensure that unknown types are given as null
		assertEquals( null,
				      StudentYearOrder.get("sophomore and then some") );
	}
}
