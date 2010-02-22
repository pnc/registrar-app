package studentClasses;

import java.io.Serializable;
import java.util.StringTokenizer;

import com.phillipcalvin.utilities.EventSource;

/**
 * purpose: Models a person, having a first and last name, a street address
 * and its components, city and state.
 * @author: Phillip Calvin
 * @version: 1.13.2010
 */
public class Person
	extends EventSource
	implements Serializable {

	/**
	 * A unique serial that can be used to version schema.
	 */
	private static final long serialVersionUID = -2127096088939759972L;
	
	/**
	 * @uml.property  name="firstName"
	 */
	private String firstName = "";

	/**
	 * Getter of the property <tt>firstName</tt>
	 * @return  Returns the firstName.
	 * @uml.property  name="firstName"
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter of the property <tt>firstName</tt>
	 * @param firstName  The firstName to set.
	 * @uml.property  name="firstName"
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @uml.property  name="lastName"
	 */
	private String lastName = "";

	/**
	 * Getter of the property <tt>lastName</tt>
	 * @return  Returns the lastName.
	 * @uml.property  name="lastName"
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter of the property <tt>lastName</tt>
	 * @param lastName  The lastName to set.
	 * @uml.property  name="lastName"
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @uml.property  name="address"
	 */
	private String address = "";

	/**
	 * Getter of the property <tt>address</tt>
	 * @return  Returns the address.
	 * @uml.property  name="address"
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter of the property <tt>address</tt>
	 * @param address  The address to set.
	 * @uml.property  name="address"
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @uml.property  name="city"
	 */
	private String city = "";

	/**
	 * Getter of the property <tt>city</tt>
	 * @return  Returns the city.
	 * @uml.property  name="city"
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Setter of the property <tt>city</tt>
	 * @param city  The city to set.
	 * @uml.property  name="city"
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @uml.property  name="state"
	 */
	private String state = "";

	/**
	 * Getter of the property <tt>state</tt>
	 * @return  Returns the state.
	 * @uml.property  name="state"
	 */
	public String getState() {
		return state;
	}

	/**
	 * Setter of the property <tt>state</tt>
	 * @param state  The state to set.
	 * @uml.property  name="state"
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Returns the person's attributes as tab-separated data
	 * in the following order:
	 *   last name, first name, address, city, state
	 * @return A String of properties, separated by tabs.
	 */
	public String toString() {
		return 	this.getLastName() + "\t" + 
			   	this.getFirstName() + "\t" + 
			   	this.getAddress() + "\t" + 
			   	this.getCity() + "\t" +
			   	this.getState();
	}

	/**
	 * purpose: instantiate a new person with a default set of properties.
	 */
	public Person(){
	}


	/**
	 * purpose: instantiate a new person with the specified properties.
	 * @param firstName the first name of the person
	 * @param lastName the last name of the person
	 * @param address the street address of the person
	 * @param city the name of the city portion of the address
	 * @param state the two-letter code representing the state of the address
	 */
	public Person(String firstName, String lastName, String address, String city, String state){
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setCity(city);
		setState(state);
	}
	
	/**
	 * Instantiates a Person from the values given in the tokenizer.
	 * @param tokenizer A StringTokenizer that contains tab-delimited data in this order:
	 * lastName, firstName, address, city, state;
	 */
	public Person(StringTokenizer tokenizer) {
		// TODO: Detect null values, if needed
		setLastName( tokenizer.nextToken() );
		setFirstName( tokenizer.nextToken() );
		setAddress( tokenizer.nextToken() );
		setCity( tokenizer.nextToken() );
		setState( tokenizer.nextToken() );
	}

}
