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
 * properties of an <tt>GraduateStudent</tt>.
 * @author Phillip Calvin
 * @version 2/3/2010
 */
public class GraduateStudentFormPanel
	extends StudentFormPanel {
		
	/**
	 * Updates the editor with the associated student's values.
	 */
	public void updateFields() {
		super.updateFields();
		System.out.println("Asked to update fields.");
	}

	/**
	 * Saves the values in the editor into the associated student.
	 */
	public void persistFields() {
		super.persistFields();
		System.out.println("Asked to persist.");
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
