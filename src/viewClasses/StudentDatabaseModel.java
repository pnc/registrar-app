/**
 * 
 */
package viewClasses;

import java.lang.reflect.Method;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

import studentClasses.AbstractStudent;

import applicationClasses.StudentDatabase;

/**
 * A model class that acts as a data delegate, responding to requests
 * from the JTable for data with data from a StudentDatabase.
 * @author pcalvin
 * @version 2.17.10
 */
public class StudentDatabaseModel 
	extends AbstractTableModel
	implements ChangeListener {
	
	/**
	 * An ID for serialization purposes.
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames  = {"ID Number", "Type", "First Name",	"Last Name", 
		"Address", "City", "State", "Credit Hours", "GPA", "Degree", "Years Enrolled"};
		
	private String[] fieldNames = {	 "StudentID", "Type", "FirstName",	"LastName", 
		"Address", "City", "State", "HoursTaken", "Gpa", "Degree", "YearsEnrolled"};
	
	private StudentDatabase studentDatabase = null;
	
	/**
	 * Constructs a new StudentDatabaseModel with no data.
	 */
	public StudentDatabaseModel() {
		super();
	}
	
	/**
	 * Constructs a new StudentDatabaseModel with a reference
	 * to a database.
	 * @param studentDatabase the underlying database
	 */
	public StudentDatabaseModel(StudentDatabase studentDatabase) {
		super();
		this.studentDatabase = studentDatabase;
	}
	
	/**
	 * Sets the underlying student database.
	 * @param database The new database.
	 */
	public void setStudentDatabase(StudentDatabase database) {
		System.out.println("Got a new database!");
		if (database != null) {
			// Stop listening to the old database
			if (this.studentDatabase != null) {
				this.studentDatabase.removeChangeListener(this);
				
				// Stop listening to the students in the database
				Iterator<AbstractStudent> iterator = this.studentDatabase.getStudents().iterator();
				while ( iterator.hasNext() ) {
					AbstractStudent student = iterator.next();
					student.removeChangeListener(this);
				}
			}
			
			// Change our database reference
			this.studentDatabase = database;
			
			// Begin listening to the new database
			this.studentDatabase.addChangeListener(this);
			
			// Begin listening to the students in the database
			Iterator<AbstractStudent> iterator = this.studentDatabase.getStudents().iterator();
			while ( iterator.hasNext() ) {
				AbstractStudent student = iterator.next();
				student.addChangeListener(this);
			}
			
			// Trigger a change event so anyone using us updates
			this.fireTableDataChanged();
		} else {
			System.err.println("StudentDatabasePanel was assigned a null database.");
		}
	}

	
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Returns the number of rows for the table.
	 * @return the number of rows.
	 */
	public int getRowCount() {
		if ( this.studentDatabase != null ) {
			return this.studentDatabase.getStudents().size();
		} else {
			return 0;
		}
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	/**
	 * Returns the data for a given row and column.
	 * @param row The row number of the data (which student)
	 * @param col The col number (the property of the student)
	 */
	public Object getValueAt(int row, int col) {
		Object value = null;
		// Sanity check for null database
		if ( this.studentDatabase != null ) {
			// Use the column number to build a method name
			String fieldName = fieldNames[col];
			String methodName = "get" + fieldName;
			
			// Retrieve the student in question (using the row)
			AbstractStudent student = this.studentDatabase.getStudents().get(row);
			
			// Attempt to retrieve the field name off the student object
			try {
				Method m = student.getClass().getMethod(methodName, new Class[0]);
				value = m.invoke(student, new Object[0]);
			} catch (Exception e) {
				System.err.println("Couldn't retrieve property " + fieldName + " from " + student.toString());
			}
		}
		return value;
	}

	public Class getColumnClass(int c) {
		return String.class;
		//return getValueAt(0, c).getClass();
	}

	/**
	 * Returns whether a given cell is editable.
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/**
	 * Notify anyone using us as a model that a change
	 * has occurred.
	 * @param event The change event that occurred.
	 */
	@Override
	public void stateChanged(ChangeEvent event) {
		this.fireTableDataChanged();
	}

	/*
	 * Don't need to implement this method unless your table's
	 * data can change.
	 */
	/*
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}*/

}
