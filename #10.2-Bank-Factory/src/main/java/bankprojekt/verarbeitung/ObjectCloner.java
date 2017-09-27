package bankprojekt.verarbeitung;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Klasse zum Klonen von Objekten, die das Serializable Interface implementieren
 * @author Benny Lach
 *
 */
public class ObjectCloner {
	
	/**
	 * Funktion zum Klonen eines Objektes
	 * @param object zu Klonendes Objekt
	 * @return Klon
	 * @throws IllegalArgumentException Uebergebens Obkekt ist null
	 * @throws IOException Objekt kann nicht geklont werden (Eigenschaften des Objektes implementieren nicht Serializable)
	 * @throws ClassNotFoundException Klasse des serialisierten Objektes kann nicht gefunden werden
	 */
	@SuppressWarnings("unchecked")
	public final static <T extends Serializable> T clone(T object) throws IOException, ClassNotFoundException {
		if (object == null) {
			throw new IllegalArgumentException("Uebergebenes Objekt darf nicht null sein");
		}
		
		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
		
		ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream);
		objectOutStream.writeObject(object);
		objectOutStream.flush();
		objectOutStream.close();

		byte[] bytes = byteOutStream.toByteArray();

		ByteArrayInputStream byteInStream = new ByteArrayInputStream(bytes);
		ObjectInputStream objectInStream = new ObjectInputStream(byteInStream);

		T clone = (T) objectInStream.readObject();
		
		objectInStream.close();
		
		return clone;
	}
}
