/**
 * 
 */
package com.phillipcalvin.utilities;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A class that manages a set of listeners and allows them to be
 * notified of changes to the object.
 * @author pcalvin
 * @version 2.17.10
 */
public class EventSource {
	
	public EventSource() {
		this.listeners = new Vector<ChangeListener>();
	}
	
	/**
	 * Adds a listener who is interested in receiving an update whenever
	 * this class's properties change.
	 * @param listener A class implementing ChangeListener. 
	 */
	public void addChangeListener(ChangeListener listener) {
		if(!listeners.contains(listener)) {
			listeners.addElement(listener);
		}
	}
	
	/**
	 * Removes the given listener from the list of objects who
	 * receive notifications about changes to this object.
	 * @param listener The object that no longer wishes to receive updates.
	 */
	public void removeChangeListener(ChangeListener listener) {
		listeners.removeElement(listener);
	}
	
	/**
	 * Notifies listeners that something has changed.
	 * This can be triggered by the object itself.
	 */
	@SuppressWarnings({ "unchecked" })
	protected void notifyListeners() {
		//create a copy to prevent modifications of the vector state from crashing the program
		Vector<ChangeListener> copyOfListeners = 
			(Vector<ChangeListener>)(listeners.clone());
		ChangeEvent event = new ChangeEvent(this);
		Enumeration<ChangeListener> enum1 = copyOfListeners.elements();
		while(enum1.hasMoreElements()) {
			ChangeListener listener = enum1.nextElement();
			listener.stateChanged(event);
		}
	}
	
	/**
	 * Notifies listeners, but can be triggered by anyone.
	 */
	public void update() {
		notifyListeners();
	}
	
	/**
	 * An attribute that holds the observers interested in receiving
	 * updates about changes to this object.
	 */
	transient Vector<ChangeListener> listeners = null;
}
