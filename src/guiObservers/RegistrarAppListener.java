package guiObservers;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public interface RegistrarAppListener 
	extends ChangeListener {
	
    public void databaseLoaded(ChangeEvent event);
}
