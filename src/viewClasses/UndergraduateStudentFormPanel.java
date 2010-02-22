package viewClasses;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.ComponentOrientation;

import javax.swing.SwingConstants;
import java.awt.Component;

import studentClasses.*;
import javax.swing.JButton;

import studentClasses.*;

/**
 * purpose: Provides an interface for editing the 
 * properties of an <tt>UndergraduateStudent</tt>.
 * @author Phillip Calvin
 * @version 2/3/2010
 */
public class UndergraduateStudentFormPanel
	extends StudentFormPanel {
	
	private JLabel labelYear = null;
	
	/**
	 * Initialize the panel by adding the specific fields for
	 * the undergraduate student.
	 */
	public void initialize() {
		super.initialize();
		
		labelYear = new JLabel();
		labelYear.setText("Year:");
		labelYear.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.add(labelYear, null);
		this.add(getFieldYear(), null);
	}
	
	/**
	 * This method initializes fieldYear	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getFieldYear() {
		if (fieldYear == null) {
			this.fieldYear = new JTextField();
		}
		return fieldYear;
	}
	
	/**
	 * Updates the editor with the associated student's values.
	 */
	public void updateFields() {
		super.updateFields();
		this.fieldYear.setText( ((UndergraduateStudent)student).getYear() );
	}
	
	/**
	 * Saves the values in the editor into the associated student.
	 */
	public void persistFields() {
		super.persistFields();
		
		((UndergraduateStudent)student).setYear( fieldYear.getText() );
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
