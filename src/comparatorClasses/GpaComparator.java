package comparatorClasses;

import java.util.Comparator;

import studentClasses.AbstractStudent;

/**
 * purpose: Allows comparison of two <tt>AbstractStudent</tt>s based on
 * their GPA.
 * @author Phillip Calvin
 * @version 1.27.2010
 */
public class GpaComparator implements Comparator<AbstractStudent> {
	
	/**
	 * Compare two <tt>AbstractStudent</tt>s and return a number that
	 * represents whether <tt>left</tt> is greater, equal, or less 
	 * than <tt>right</tt> based on GPA.
	 * @param left The student on the left side of the comparison
	 * @param right The student on the right side of the comparison
	 * @return negative if left < right, 0 if left == right, positive if left > right 
	 */
	@Override
	public int compare(AbstractStudent left, AbstractStudent right) {
		return Double.compare( left.getGpa(), right.getGpa() );
	}
	
	/**
	 * @return a human-readable description of the property by which
	 * the comparator sorts.
	 */
	public String toString() {
		return "GPA";
	}
}
