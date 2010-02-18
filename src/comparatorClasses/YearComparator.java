package comparatorClasses;

import java.util.Comparator;

import studentClasses.AbstractStudent;

/**
 * purpose: Allows comparison of two <tt>AbstractStudent</tt>s based on the
 * their year in school (freshman, sophomore, graduate, etc.).
 * @author Phillip Calvin
 * @version 1.27.2010
 */
public class YearComparator implements Comparator<AbstractStudent> {	
	
	/**
	 * Compare to <tt>AbstractStudent</tt>s and return a number that
	 * represents whether <tt>left</tt> is greater, equal, or less 
	 * than <tt>right</tt> based on their year in school.
	 * @param left The student on the left side of the comparison
	 * @param right The student on the right side of the comparison
	 * @return negative if left < right, 0 if left == right, positive if left > right 
	 */
	@Override
	public int compare(AbstractStudent left, AbstractStudent right) {
		return left.getYearNumber() - right.getYearNumber();
	}
	
	/**
	 * @return a human-readable description of the property by which
	 * the comparator sorts.
	 */
	public String toString() {
		return "Year";
	}
}