package bankprojekt.verarbeitung.observe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import java.util.UUID;

import bankprojekt.verarbeitung.ObjectCloner;

/**
 * Class to delegate the observable pattern without leaking the observed object to
 * the observer realized by populating clones of the observed object only.
 * 
 * <p> The Observable has to implement Serializable
 * 
 * @author Benny Lach
 *
 * @param <T> The object TO observe 
 */
public class SecureObserverManager<T extends UniqueObservable & Serializable> implements Observer {
	protected TreeMap<UUID, ArrayList<Observer>> observers;
	
	public SecureObserverManager() {
		observers = new TreeMap<>();
	}
	
	/**
	 * Method to add an observer for a given observable
	 * 
	 * @param observer The observer
	 * @param observable The observable
	 * 
	 * @throws IllegalArgumentException if observer or observable is null 
	 */
	public void addObserverForObservable(Observer observer, T observable) {
		validate(observer, observable);
		
		ArrayList<Observer> list = observers.get(observable.getId());
		// First time an observer is set for the observable
		if(list == null) {
			// Add us as the observer for delegating observation
			// instead of populating the original object directly
			observable.addObserver(this);
			list = new ArrayList<Observer>();
			observers.put(observable.getId(), list);
		}
		// Do not add the same observer several times
		if (!list.contains(observer)) {
			list.add(observer);
		}
	}
	
	/**
	 * Method to remove an observer for a given observable
	 * 
	 * <p> Trying to remove an observer for an observable that was never 
	 * registered as an observer before has no effect.
	 *  
	 * @param observer The observer
	 * @param observable The observable
	 * 
	 * @throws illegalArgumentException if oberver or observable is null
	 */
	public void removeObserverForObservable(Observer observer, T observable) {
		validate(observer, observable);
	
		ArrayList<Observer> list = observers.get(observable.getId());
		
		if (list != null && list.contains(observer)) {
			list.remove(observer);
			// Remove us as an observer if the list of observers is empty
			// furthermore remove the key from the map
			if (list.isEmpty()) {
				observers.remove(observable.getId());
				observable.deleteObserver(this);
			}
		}
		
	}
	
	// Guarantee valid objects
	private void validate(Observer observer, T object) {
		if (observer == null || object == null) {
			throw new IllegalArgumentException("One or more properties are null");
		}
	}
	
	/**
	 * Method to clone an object
	 * @param source Object to clone
	 * @return The clone
	 */
	private T cloneObservable(T source) {
		try {
			return ObjectCloner.clone(source);
		} catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		T clone = cloneObservable((T) o);
		
		if (clone != null) {
			for(Observer ob: observers.get(clone.getId())) {
				ob.update(clone, arg);
			}
		}	
	}
}
