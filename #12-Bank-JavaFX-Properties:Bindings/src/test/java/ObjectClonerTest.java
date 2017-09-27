import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import bankprojekt.verarbeitung.ObjectCloner;

public class ObjectClonerTest {

	/**
	 * Testet Fehlerbehandung bei Uebergabe von null
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_cloneNullObject() throws ClassNotFoundException, IOException {
		ObjectCloner.clone(null);
	}
	
	/**
	 * Testet korrekte Bahandlung von nicht serialisierbaren Eigenschaften
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test(expected = IOException.class)
	public void test_cloneinvalidObject() throws ClassNotFoundException, IOException {

		CloneObjectInvalid[] origin = { new CloneObjectInvalid(9) };
		CloneObjectInvalid[] clone = ObjectCloner.clone(origin);
	}

	/**
	 * Testet Klonen von validem Objekt
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test
	public void test_cloneValidObject() throws ClassNotFoundException, IOException {

		CloneObjectValid[] origin = { new CloneObjectValid(8) };
		CloneObjectValid[] clone = ObjectCloner.clone(origin);

		assertEquals("Original und Kopie an Index 0 nicht identisch", origin[0].number, clone[0].number);
		assertNotSame("Original und Kopie haben andere Referenzen", origin[0], clone[0]);

		origin[0] = new CloneObjectValid(9);

		assertTrue("Original und Kopie an Index 0 nach Veränderung immernoch identisch",
				origin[0].number != clone[0].number);
	}

}
