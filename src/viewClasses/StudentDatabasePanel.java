/**
 * 
 */
package viewClasses;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import applicationClasses.StudentDatabase;

import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;

/**
 * A panel for displaying a student database as a table.
 * @author pcalvin
 * @version 2.17.2010
 */
public class StudentDatabasePanel 
	extends JPanel
	implements ChangeListener {

	private static final long serialVersionUID = 1L;
	
	private StudentDatabase studentDatabase = null;  //  @jve:decl-index=0:
	private StudentDatabaseModel databaseModel = null;
	private JScrollPane jScrollPane = null;

	private JScrollPane scrollViewStudents = null;

	private JTable tableStudents = null;

	/**
	 * This is the default constructor. You should not use this constructor
	 * in a real environment, however, because the panel needs a reference
	 * to a StudentDatabase to be useful.
	 */
	public StudentDatabasePanel() {
		super();
		// Create a model, which we'll populate with data in just a moment.
		this.databaseModel = new StudentDatabaseModel();
		
		initialize();
	}
	
	/**
	 * The constructor you normally want to use, which supplies an
	 * initial StudentDatabase.
	 * @param studentDatabase A student database.
	 */
	public StudentDatabasePanel(StudentDatabase studentDatabase) {
		super();
		// Create a model, which we'll populate with data in just a moment.
		this.databaseModel = new StudentDatabaseModel();
		
		this.setStudentDatabase(studentDatabase);
	}

	/**
	 * This method initializes this panel and creates the container widget.
	 * 
	 * @return void
	 */
	private void initialize() {		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.add(getScrollViewStudents(), gridBagConstraints);
		
		// Inform our table of its model
		this.tableStudents.setModel(this.databaseModel);
	}

	
	/**
	 * Sets the underlying student database.
	 */
	public void setStudentDatabase(StudentDatabase database) {
		System.err.println("Notified of a new database.");
		if (database != null) {
			// Stop listening to the old database
			if (this.studentDatabase != null) {
				this.studentDatabase.removeChangeListener(this);
			}
			
			// Change our database reference
			this.studentDatabase = database;
			
			// Let our underlying model know about the new database
			this.databaseModel.setStudentDatabase(database);
			
			// Begin listening to the new database
			this.studentDatabase.addChangeListener(this);
		} else {
			System.err.println("StudentDatabasePanel was assigned a null database.");
		}
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		// Something about the state of the database has changed
		// TODO: Update, maybe we don't even need to
	}

	/**
	 * This method initializes scrollViewStudents	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollViewStudents() {
		if (scrollViewStudents == null) {
			scrollViewStudents = new JScrollPane();
			scrollViewStudents.setViewportView(getTableStudents());
		}
		return scrollViewStudents;
	}

	/**
	 * This method initializes tableStudents. It also offers the
	 * table as public so that others can subscribe to its selection.
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTableStudents() {
		if (tableStudents == null) {
			tableStudents = new JTable();
			tableStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		
		return tableStudents;
	}
}
