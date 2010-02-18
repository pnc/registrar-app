package studentClasses;

/**
 * purpose: An enum for defining the order of student years.
 * This abstracts the idea of how years should be ordered to a single
 * place. This way, when new classes are added or the requirements change,
 * it is trivial to change the order definition. Simply re-order the constants
 * below.
 * @author Phillip Calvin
 * @version 1.27.2009
 */
public enum StudentYearOrder {
	FRESHMAN,
	SOPHOMORE, 
	JUNIOR, 
	SENIOR,
	OTHER_UNDERGRADUATE,
	GRADUATE,
	OTHER;
	
	/**
	 * Attempts to return the student year order representing the year
	 * that is passed in. For instance, the <tt>FRESHMAN</tt> type will be
	 * returned if <tt>"Freshman"</tt> is passed.
	 * @param name A string representation of the enum name
	 * @return The StudentYearOrder that matches the name, or null if
	 *         there is no match.
	 */
	public static StudentYearOrder get(String name) {
		StudentYearOrder year = null;
		try {
			year = StudentYearOrder.valueOf( name.toUpperCase() );
		} catch (IllegalArgumentException e) {
			// We couldn't find one for this name, so allow the caller
			// to choose another type.
		}
		return year;
	}
}
