package bankprojekt.verarbeitung.observe;

import java.io.Serializable;
import java.util.Observable;
import java.util.UUID;

/**
 * Base class to implement observable with some sort of uniquness 
 * Using {@code object.hashCode()} or {@code System.identityHashCode(object)} is not save use:
 * {@code object.hashCode()} can be overwritten by the developer
 * result of {@code System.identityHashCode(object)} can change over time due to JVM moving the objects
 * in memory  
 * @author Benny Lach
 *
 */
public class UniqueObservable extends Observable implements Serializable {
	
	private static final long serialVersionUID = 6685698226461963558L;
	private UUID id;
	/**
	 * Default intializer 
	 */
	public UniqueObservable() {
		this.id = UUID.randomUUID();
	}
	
	/**
	 * Get the unique id of the object
	 * @return The id
	 */
	final public UUID getId() {
		return this.id;
	}
}
