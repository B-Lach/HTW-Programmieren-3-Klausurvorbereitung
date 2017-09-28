import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observer;
import java.util.TreeMap;
import java.util.UUID;

import bankprojekt.verarbeitung.observe.SecureObserverManager;
import bankprojekt.verarbeitung.observe.UniqueObservable;

/**
 * Mock Klasse zum Testen von verschiedenen Implementationen 
 * @author Benny Lach
 *
 * @param <T>
 */
public class SecureObserverManagerMock<T extends UniqueObservable & Serializable> extends SecureObserverManager<T> {
	
	/**
	 * Erhalte alle registrierten Observer
	 * @return observers
	 */
	public TreeMap<UUID, ArrayList<Observer>> getObservers() {
		return observers;
	}
}
