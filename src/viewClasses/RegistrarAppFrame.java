package viewClasses;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import applicationClasses.RegistrarApp;
import studentClasses.*;

/**
 * purpose: Provides a window for working with the a database of students.
 * @author Phillip Calvin
 * @version 2/3/2010
 */
public class RegistrarAppFrame 
	extends JFrame
	implements ChangeListener {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;

	private RegistrarApp application = null;
	
	private CardLayout cardLayout = new CardLayout();
	private JPanel editors = new JPanel();
    
	private JMenuBar mainMenuBar = null;
	private JMenu fileMenu = null;  //  @jve:decl-index=0:visual-constraint="-94,58"
	private JMenu viewMenu = null;
	private JPanel statusPanel = null;
	private JLabel statusLabel = null;
	private JMenuItem menuItemExit = null;
	private JRadioButtonMenuItem menuItemSortByIDNumber = null;
	private JMenuItem menuItemOpen = null;
	
	private GraduateStudentFormPanel graduateStudentFormPanel = null;
	private UndergraduateStudentFormPanel undergraduateStudentFormPanel = null;
	
	private StudentDatabasePanel studentDatabasePanel = null;
	
	
	/**
	 * Default constructor, which provides a blank editor.
	 */
	public RegistrarAppFrame() {
		super();
		initialize();
	}
    
    /**
     * Create a new editor already configured to edit the given <tt>student</tt>.
     * @param student the student to be edited.
     */
	public RegistrarAppFrame(RegistrarApp application) {
		super();
		initialize();
		
		// Store a reference to the application we're viewing
		this.application = application;
		
		// Ask the application to notify us of changes (a new
		// database is loaded, etc.)
		this.application.addChangeListener(this);
		
		
		// Trigger an update on the student so the view will be populated.
		//this.student.update();
		
		//Note: Eclipse does not generate the code to display the frame
		//Set up the display of the frame as follows
		//Center the frame on screen and size it using Java defaults
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		frameSize.height = ((frameSize.height > screenSize.height) ? screenSize.height : frameSize.height);
		frameSize.width = ((frameSize.width > screenSize.width) ? screenSize.width : frameSize.width);
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true); //makes the frame visible
		
	}


	/**
	 * This method initializes the frame.
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(795, 764);
		this.setJMenuBar(getMainMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Registrar Application");
	}

	/**
	 * This method initializes the content pane.
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getStatusPanel(), BorderLayout.SOUTH);
			
			editors.setLayout(cardLayout);
			editors.add( getGraduateStudentFormPanel(), "graduate" );
			editors.add( getUndergraduateStudentFormPanel(), "undergraduate" );
			cardLayout.show(editors, "undergraduate");
			
			jContentPane.add(editors, BorderLayout.NORTH);
			
			jContentPane.add(getStudentDatabasePanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes mainMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMainMenuBar() {
		if (mainMenuBar == null) {
			mainMenuBar = new JMenuBar();
			mainMenuBar.add(getFileMenu());
			mainMenuBar.add(getViewMenu());
		}
		return mainMenuBar;
	}

	/**
	 * This method initializes fileMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.setMnemonic(KeyEvent.VK_F);
			fileMenu.add(getMenuItemOpen());
			fileMenu.add(getMenuItemExit());
		}
		return fileMenu;
	}

	/**
	 * This method initializes viewMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getViewMenu() {
		if (viewMenu == null) {
			viewMenu = new JMenu();
			viewMenu.setMnemonic(KeyEvent.VK_V);
			viewMenu.setText("View");
			viewMenu.add(getMenuItemSortByIDNumber());
		}
		return viewMenu;
	}

	/**
	 * This method initializes statusPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getStatusPanel() {
		if (statusPanel == null) {
			statusLabel = new JLabel();
			statusLabel.setText("No students loaded.");
			statusLabel.setName("statusLabel");
			statusPanel = new JPanel();
			statusPanel.setLayout(new BorderLayout());
			statusPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			statusPanel.add(statusLabel, BorderLayout.WEST);
		}
		return statusPanel;
	}

	/**
	 * This method initializes menuItemExit	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemExit() {
		if (menuItemExit == null) {
			menuItemExit = new JMenuItem();
			menuItemExit.setText("Exit");
			menuItemExit.setMnemonic(KeyEvent.VK_X);
			menuItemExit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// We're outta here
					System.exit(0);
				}
			});
		}
		return menuItemExit;
	}

	/**
	 * This method initializes menuItemSortByIDNumber	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getMenuItemSortByIDNumber() {
		if (menuItemSortByIDNumber == null) {
			menuItemSortByIDNumber = new JRadioButtonMenuItem();
			menuItemSortByIDNumber.setText("Sort by ID Number");
			menuItemSortByIDNumber.setSelected(false);
			menuItemSortByIDNumber.setMnemonic(KeyEvent.VK_I);
			menuItemSortByIDNumber.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					application.getDatabase().sort();
				}
			});
		}
		return menuItemSortByIDNumber;
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		this.statusLabel.setText( "Application changed: " + event.toString() );
		
		// Assume that the database has changed: let the StudentDatabasePanel know
		this.studentDatabasePanel.setStudentDatabase( this.application.getDatabase() );
	}

	/**
	 * This method initializes menuItemOpen	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemOpen() {
		if (menuItemOpen == null) {
			menuItemOpen = new JMenuItem();
			menuItemOpen.setText("Open...");
			menuItemOpen.setMnemonic(KeyEvent.VK_O);
			menuItemOpen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO: Actually open a browse window
					application.openDatabase("test/fixtures/students.txt");
				}
			});
		}
		return menuItemOpen;
	}

	/**
	 * This method initializes graduateStudentFormPanel	
	 * 	
	 * @return viewClasses.StudentFormPanel	
	 */
	private StudentFormPanel getGraduateStudentFormPanel() {
		if (graduateStudentFormPanel == null) {
			graduateStudentFormPanel = new GraduateStudentFormPanel();
			graduateStudentFormPanel.setVisible(false);
		}
		return graduateStudentFormPanel;
	}
	
	/**
	 * This method initializes undergraduateStudentFormPanel	
	 * 	
	 * @return viewClasses.StudentFormPanel	
	 */
	private StudentFormPanel getUndergraduateStudentFormPanel() {
		if (undergraduateStudentFormPanel == null) {
			undergraduateStudentFormPanel = new UndergraduateStudentFormPanel();
			undergraduateStudentFormPanel.setVisible(true);
		}
		return undergraduateStudentFormPanel;
	}

	/**
	 * This method initializes studentDatabasePanel	
	 * 	
	 * @return viewClasses.StudentDatabasePanel	
	 */
	private StudentDatabasePanel getStudentDatabasePanel() {
		if (studentDatabasePanel == null) {
			studentDatabasePanel = new StudentDatabasePanel( );
			// Subscribe to the panel's table's selection events
			studentDatabasePanel.getTableStudents().getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						int viewRow = studentDatabasePanel.getTableStudents().getSelectedRow();
						if (viewRow < 0) {
							//Selection got filtered away. Ignore.
						} else {
							int modelRow = 
									studentDatabasePanel.getTableStudents().convertRowIndexToModel(viewRow);
							
							// Fetch the selected student out of the database
							AbstractStudent selectedStudent = application.getDatabase().getStudents().get(modelRow);
							
							if (selectedStudent != null) {
								System.out.println( "Selected student: " + selectedStudent.toString() );
								
								showEditorFor(selectedStudent);
								// TODO: Don't change if dirty
							}
							
						}
					}
				}
			);
		}
		
		return studentDatabasePanel;
	}
	
	/**
	 * Display the appropriate editor for a given student and link
	 * that editor to the student.
	 * @param student The student to edit.
	 */
	private void showEditorFor(AbstractStudent student) {
		graduateStudentFormPanel.setVisible(false);
		undergraduateStudentFormPanel.setVisible(false);
		// Tell our StudentFormPanel to edit this one.
		if (student instanceof GraduateStudent) {
			graduateStudentFormPanel.setStudent(student);
			cardLayout.show(editors, "graduate");
		} else if (student instanceof UndergraduateStudent) {
			undergraduateStudentFormPanel.setStudent(student);
			cardLayout.show(editors, "undergraduate");
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
