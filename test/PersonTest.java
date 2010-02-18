package testCases;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.Test;

import studentClasses.Person;

public class PersonTest {
	
	static String firstName = "John";
	static String lastName = "Smith";
	static String address = "15 Oak St";
	static String city = "Portsmouth";
	static String state = "RI";
	
	@Test
	public void testSetFirstName() {
		Person person = new Person();
		person.setFirstName(firstName);
		assertEquals( firstName, person.getFirstName() );
	}

	@Test
	public void testSetLastName() {
		Person person = new Person();
		person.setLastName(lastName);
		assertEquals( lastName, person.getLastName() );
	}

	@Test
	public void testSetAddress() {
		Person person = new Person();
		person.setAddress(address);
		assertEquals( address, person.getAddress() );
	}

	@Test
	public void testSetCity() {
		Person person = new Person();
		person.setCity(city);
		assertEquals( city, person.getCity() );
	}

	@Test
	public void testSetState() {
		Person person = new Person();
		person.setState(state);
		assertEquals( state, person.getState() );
	}

	@Test
	public void testPersonExplicitValueConstructor() {
		Person person = new Person(firstName, lastName, address, city, state);
		
		assertEquals( firstName, person.getFirstName() );
		assertEquals( lastName, person.getLastName() );
		assertEquals( address, person.getAddress() );
		assertEquals( city, person.getCity() );
		assertEquals( state, person.getState() );
	}
	
	@Test
	public void testConstructFromTokenizer() {
		String fixture = lastName + "\t" + firstName + "\t" + address +
			"\t" + city + "\t" + state;
		StringTokenizer tokenizer = new StringTokenizer(fixture, "\t");
		
		Person person = new Person(tokenizer);
		
		assertEquals( lastName, person.getLastName() );
		assertEquals( firstName, person.getFirstName() );
		assertEquals( address, person.getAddress() );
		assertEquals( city, person.getCity() );
		assertEquals( state, person.getState() );
	}
	
	@Test
	public void testToString() {
		Person person = new Person(firstName, lastName, address, city, state);
		String expectedValue = lastName + "\t" + firstName + "\t" + address +
		"\t" + city + "\t" + state;
		
		assertEquals( expectedValue, person.toString() );
	}
}
