/**Illustrates a simple text-based editing observer that can
be used to modify the UndergraduateStudent state.

@author Hayden Porter
@date 1/16/2003

 */

package guiObservers; //place your package here

import java.io.*;

import studentClasses.UndergraduateStudent;

/**
 * A basic listener designed to allow editing one student using a text interface. 
 * @author Hayden Porter
 * @version 2/3/2010
 */
public class UndergraduateStudentTextEditorListener implements UndergraduateStudentListener {
	UndergraduateStudent undergraduateStudent; //Reference to the UndergraduateStudent this editor works with
	
	String mainMenu[] ={ "(1) idNum","(2) last name",
			"(3) first name","(4) gpa", 
			"(5) credit hours","(6) year",
			"(7) address(no spaces)","(8) city (no spaces)",
			"(9) state abbreviation (no spaces)",
			"(10) Show Updates","(11) Exit"};


	/**Construct this Editor on a given UndergraduateStudent Provides back navagability to
	 * the object I'm listening to.
	 */
	public UndergraduateStudentTextEditorListener (UndergraduateStudent s) {
		undergraduateStudent = s;
	}


	/**Create a menu string by concatenating menu items and
	 * separating them with new lines.
	 */
	private String buildMenu(String [] b) {
		String a = new String();
		for(int i = 0;i < b.length;i++) {
			a+=b[i]+"\n";
		}
		return a;
	}

	/**Defines the editing actions that can be performed by this editor. 
	 * Prints the main menu as a prompt, interacts with the user to 
	 * change the state of UndergraduateStudent.
	 */

	public void edit() {
		boolean editing = true;
		System.out.println("Select from the following menu");
		String output = buildMenu(mainMenu);
		while(editing) {
			int sel = Integer.parseInt(interact(output));
			switch (sel){
			case 1: String a = "Enter UndergraduateStudent id number";
				String s = interact(a);
				int idNum = Integer.parseInt(s);
				undergraduateStudent.setStudentID(idNum);
				undergraduateStudent.update(); //Inform others I've made this change.
			break;
			case 2: 
				String lastName = interact("Enter the new last name:");
				undergraduateStudent.setLastName(lastName);
				undergraduateStudent.update();
			break;
			case 3: 
				String firstName = interact("Enter the new first name:");
				undergraduateStudent.setFirstName(firstName);
				undergraduateStudent.update();
			break;
			case 4: 
				String gpaText = interact("Enter the GPA:");
				double gpa = Double.parseDouble(gpaText);
				undergraduateStudent.setGpa(gpa);
				undergraduateStudent.update(); 
			break;
			case 5: 
				String creditHoursText = interact("Enter the number of credit hours:");
				int hoursTaken = Integer.parseInt(creditHoursText);
				undergraduateStudent.setHoursTaken(hoursTaken);
				undergraduateStudent.update(); 
			break;
			case 6: 
				String year = interact("Enter the student's year:");
				undergraduateStudent.setYear(year);
				undergraduateStudent.update(); 
			break;
			case 7: 
				String address = interact("Enter the student's address:");
				undergraduateStudent.setAddress(address);
				undergraduateStudent.update(); 
			break;
			case 8: 
				String city = interact("Enter the student's city:");
				undergraduateStudent.setCity(city);
				undergraduateStudent.update(); 
			break;
			case 9: 
				String state = interact("Enter the student's state:");
				undergraduateStudent.setState(state);
				undergraduateStudent.update(); 
			break;
			case 10: 
				undergraduateStudent.update(); break;
			case 11: 
				editing = false; //Stop editing
				//When editing is completed remove this listener
				undergraduateStudent.removeUndergraduateStudentListener(this);
			break;
			default: ;  //do nothing

			}// end case
		}//endwhile
	}


	/**
	 * Display menu information and then return a user's response as a String.
	 */
	private String interact(String in) {
		System.out.println(in);
		return getUserResponseFromConsole();
	}

	/**
	 * Get user response from console and return as a string.
	 */
	private String getUserResponseFromConsole(){
		try {
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			return console.readLine();
		}
		catch (IOException exception) {
			System.out.println("IOerror in getUserResponseFromConsole");
			return "Error";
		}

	}

	/** Implement the UndergraduateStudentListener Interface. Show the new UndergraduateStudent state following
	 * any changes.
	 */
	public void undergraduateStudentStateChanged(UndergraduateStudentEvent de) {
		System.out.println("UndergraduateStudentEditorListener: "+de.getUndergraduateStudentState());
	}
}