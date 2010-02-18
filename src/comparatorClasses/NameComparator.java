package comparatorClasses;

import java.util.Comparator;

import studentClasses.AbstractStudent;

/**
 * purpose: Allows comparison of two <tt>AbstractStudent</tt>s based on
 * their last name. If their last name is the same, they will be subsorted
 * by first name.
 * @author Phillip Calvin
 * @version 1.27.2010
 */
public class NameComparator implements Comparator<AbstractStudent> {

	/**
	 * Compare two <tt>AbstractStudent</tt>s and return a number that
	 * represents whether <tt>left</tt> is greater, equal, or less 
	 * than <tt>right</tt> based on last name, then first name.
	 * @param left The student on the left side of the comparison
	 * @param right The student on the right side of the comparison
	 * @return negative if left < right, 0 if left == right, positive if left > right 
	 */
	@Override
	public int compare(AbstractStudent left, AbstractStudent right) {
		int result;
		// First, compare to the last names
		result = left.getLastName().compareTo( right.getLastName() );
		
		if (result == 0) {
			// The last names were the same -- fall back to first names
			result = left.getFirstName().compareTo( right.getFirstName() );
		}
		
		return result;
	}

	/**
	 * @return a human-readable description of the property by which
	 * the comparator sorts.
	 */
	public String toString() {
		return "Name";
	}
}
