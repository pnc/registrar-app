package viewClasses;

import guiObservers.UndergraduateStudentEvent;
import guiObservers.UndergraduateStudentListener;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.ComponentOrientation;

import javax.swing.SwingConstants;

import studentClasses.*;
import javax.swing.JButton;

import studentClasses.*;

/**
 * purpose: Provides an interface for editing the 
 * properties of an <tt>UndergraduateStudent</tt>.
 * @author Phillip Calvin
 * @version 2/3/2010
 */
public class StudentFormPanel
	extends JPanel 
	implements UndergraduateStudentListener {

	private static final long serialVersionUID = 1L;
	private JLabel labelStudentID = null;
	private JTextField fieldStudentID = null;
	private JLabel labelLastName = null;
	private JTextField fieldLastName = null;
	private JTextField fieldFirstName = null;
	private JTextField fieldYear = null;
	private JTextField fieldGpa = null;
	private JTextField fieldHoursTaken = null;
	private JLabel labelHoursTaken = null;
	private JLabel labelYear = null;
	private JLabel labelGpa = null;
	private JLabel labelFirstName = null;
	private JLabel labelAddress = null;
	private JLabel labelCity = null;
	private JLabel labelState = null;
	private JTextField fieldAddress = null;
	private JTextField fieldCity = null;
	private JTextField fieldState = null;
	
	private UndergraduateStudent student = null;
	private JButton buttonSave = null;
	private JLabel fillerLabel = null;
	
	/**
	 * This is the default constructor.
	 */
	public StudentFormPanel() {
		super();
		initialize();
	}
	
	/**
	 * This constructor allows a StudentFormPanel to be instantiated
	 * with a reference to the student being edited.
	 * @param student The student being edited.
	 */
	public StudentFormPanel(UndergraduateStudent student) {
		super();
		initialize();
		this.student = student;
	}


	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		fillerLabel = new JLabel();
		fillerLabel.setText("");
		labelState = new JLabel();
		labelState.setText("State:");
		labelState.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCity = new JLabel();
		labelCity.setText("City:");
		labelCity.setHorizontalAlignment(SwingConstants.RIGHT);
		labelAddress = new JLabel();
		labelAddress.setText("Address:");
		labelAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFirstName = new JLabel();
		labelFirstName.setText("First name:");
		labelFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGpa = new JLabel();
		labelGpa.setText("GPA:");
		labelGpa.setHorizontalAlignment(SwingConstants.RIGHT);
		labelYear = new JLabel();
		labelYear.setText("Year:");
		labelYear.setHorizontalAlignment(SwingConstants.RIGHT);
		labelHoursTaken = new JLabel();
		labelHoursTaken.setText("Credit hours:");
		labelHoursTaken.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLastName = new JLabel();
		labelLastName.setText("Last name:");
		labelLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		labelStudentID = new JLabel();
		labelStudentID.setText("Student ID:");
		labelStudentID.setHorizontalAlignment(SwingConstants.RIGHT);
		labelStudentID.setPreferredSize(new Dimension(80, 16));
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(10);
		gridLayout.setHgap(5);
		gridLayout.setVgap(2);
		gridLayout.setColumns(2);
		this.setLayout(gridLayout);
		this.setSize(300, 251);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.add(labelStudentID, null);
		this.add(getFieldStudentID(), null);
		this.add(labelLastName, null);
		this.add(getFieldLastName(), null);
		this.add(labelFirstName, null);
		this.add(getFieldFirstName(), null);
		this.add(labelAddress, null);
		this.add(getFieldAddress(), null);
		this.add(labelCity, null);
		this.add(getFieldCity(), null);
		this.add(labelState, null);
		this.add(getFieldState(), null);
		this.add(labelGpa, null);
		this.add(getFieldGpa(), null);
		this.add(labelYear, null);
		this.add(getFieldYear(), null);
		this.add(labelHoursTaken, null);
		this.add(getFieldHoursTaken(), null);
		this.add(fillerLabel, null);
		this.add(getButtonSave(), null);
	}

	/**
	 * This method initializes fieldStudentID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFieldStudentID() {
		if (fieldStudentID == null) {
			fieldStudentID = new JTextField();
		}
		return fieldStudentID;
	}

	/**
	 * This method initializes fieldLastName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFieldLastName() {
		if (fieldLastName == null) {
			fieldLastName = new JTextField();
		}
		return fieldLastName;
	}

	/**
	 * This method initializes fieldFirstName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFieldFirstName() {
		if (fieldFirstName == null) {
			fieldFirstName = new JTextField();
		}
		return fieldFirstName;
	}

	/**
	 * This method initializes fieldYear	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFieldYear() {
		if (fieldYear == null) {
			fieldYear = new JTextField();
		}
		return fieldYear;
	}

	/**
	 * This method initializes fieldGpa	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFieldGpa() {
		if (fieldGpa == null) {
			fieldGpa = new JTextField();
		}
		return fieldGpa;
	}

	/**
	 * This method initializes fieldHoursTaken	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFieldHoursTaken() {
		if (fieldHoursTaken == null) {
			fieldHoursTaken = new JTextField();
		}
		return fieldHoursTaken;
	}

	/**
	 * This method initializes fieldAddress	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFieldAddress() {
		if (fieldAddress == null) {
			fieldAddress = new JTextField();
		}
		return fieldAddress;
	}

	/**
	 * This method initializes fieldCity	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFieldCity() {
		if (fieldCity == null) {
			fieldCity = new JTextField();
		}
		return fieldCity;
	}

	/**
	 * This method initializes fieldState	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFieldState() {
		if (fieldState == null) {
			fieldState = new JTextField();
		}
		return fieldState;
	}

	@Override
	/**
	 * Responds to changes to the underlying student object and updates the view
	 * accordingly.
	 */
	public void undergraduateStudentStateChanged(UndergraduateStudentEvent pe) {
		// Hydrate the view with the values from the underlying student
		this.fieldStudentID.setText( Long.toString(student.getStudentID() ) );
		this.fieldLastName.setText( student.getLastName() );
		this.fieldFirstName.setText( student.getFirstName() );
		this.fieldAddress.setText( student.getAddress() );
		this.fieldCity.setText( student.getCity() );
		this.fieldState.setText( student.getState() );
		this.fieldGpa.setText( Double.toString( student.getGpa() ) );
		this.fieldYear.setText( student.getYear() );
		this.fieldHoursTaken.setText( Integer.toString( student.getHoursTaken() ) );
	}

	/**
	 * This method initializes buttonSave and defines its event handler.
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonSave() {
		if (buttonSave == null) {
			buttonSave = new JButton();
			buttonSave.setName("");
			buttonSave.setText("Save");
			buttonSave.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// Push the values chosen in the view back to the student instance
					
					// Only attempt to change the ID if a valid one is provided
					try {
						long studentID = Long.parseLong( fieldStudentID.getText() );
						student.setStudentID( studentID );
					} catch (NumberFormatException exception) {
						// Discard the value
					}
					
					student.setLastName( fieldLastName.getText() );
					student.setFirstName( fieldFirstName.getText() );
					student.setAddress( fieldAddress.getText() );
					student.setCity( fieldCity.getText() );
					student.setState( fieldState.getText() );
					
					// Only attempt to change the GPA if a valid one is provided
					try {
						double gpa = Double.parseDouble( fieldGpa.getText() );
						student.setGpa( gpa );
					} catch (NumberFormatException exception) {
						// Discard the value
					}
					
					student.setYear( fieldYear.getText() );
					
					// Only attempt to change the number of hours taken 
					// if a number is provided
					try {
						int hoursTaken = Integer.parseInt( fieldHoursTaken.getText() );
						student.setHoursTaken( hoursTaken );
					} catch (NumberFormatException exception) {
						// Discard the value
					}
					
					student.update();
				}
			});
		}
		return buttonSave;
	}
	
	/**
	 * Sets the student that this panel is editing.
	 * @param student the new student to edit
	 */
	public void setStudent(AbstractStudent student) {
		if (student != null) {
			// Stop listening to the old student
			if (this.student != null) {
				this.student.removeChangeListener(this);
			}
			
			// Change our student reference
			this.student = student;
			
			// Begin listening to the new database
			this.student.addChangeListener(this);
		} else {
			// TODO: Disable all fields
			System.err.println("StudentFormPanel was assigned a null student and should blank.");
		}
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
