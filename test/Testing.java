package com.phillipcalvin.utilities;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Testing {
	/**
	 * Asserts that certain methods exist on a class.
	 */
	@SuppressWarnings("unchecked")
	public static void assertMethodsDefined(Class klass, String[] methodNames) {
		Set<String> methods = new HashSet<String> (Arrays.asList(methodNames));
		Set<String> foundMethods = new HashSet<String>();
		
		for(Method method : klass.getDeclaredMethods()) {
			// Note that we found this method
			foundMethods.add( method.getName() );
		}
		
		// Make sure all the ones we were looking for were found
		methods.removeAll(foundMethods);
		assertEquals( "Expected all methods to be implemented, found that some were not:",
				      new HashSet<String>(), methods );
	}
}
