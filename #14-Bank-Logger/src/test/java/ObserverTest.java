import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Observer;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.observe.UniqueObservable;

/**
 * Test für observable Logik
 * @author Benny Lach
 *
 */
public class ObserverTest {
	private SecureObserverManagerMock<UniqueObservable> manager;
	
	@Before
	public void setUp() {
		manager = new SecureObserverManagerMock<>();
	}
	
	@After
	public void tearDown() {
		manager = null;
	}
	
	/**
	 * Teste Verhalten des Managers nach Initialisierung
	 */
	@Test
	public void test_observerMapOfManagerAfterInit() {
		assertNotNull("Observer TreeMap ist null", manager.getObservers().size());
		assertTrue("Observer TreeMap ist nicht leer", manager.getObservers().size() == 0);
	}
	
	/**
	 * Teste Fehlerbehandlung Registrieren eines Observers bei Uebergabe von null als Observer und Observable
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_addNullObserverForNullObservable() {
		manager.addObserverForObservable(null, null);
	}
	
	/**
	 * Teste Fehlerbehandlung Registrieren eines Observers  bei Uebergabe von null als Observer
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_addObserverForNullObservable() {
		manager.addObserverForObservable(null, new Girokonto());
	}
	
	/**
	 * Teste Fehlerbehandlung Registrieren eines Observers  bei Uebergabe von null als Observable
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_addNullObserverForObservable() {
		manager.addObserverForObservable( (o, arg) -> {} , null);
	}
	
	/**
	 * Teste Behandlung Registrieren eines Observers mit validen Parametern
	 */
	@Test
	public void test_addValidObserver() {
		Girokonto k = new Girokonto();
		Observer ob = (o, arg) -> {};

		manager.addObserverForObservable(ob, k);
		
		TreeMap<UUID, ArrayList<Observer>> observers = manager.getObservers();

		assertTrue("Größe der TreeMap stimmt nicht", observers.size() == 1);
		assertTrue("Identifier stimmt nicht", observers.keySet().contains(k.getId()));
		assertTrue("Gepeicherter Observer stimmt nicht", observers.get(k.getId()).get(0) == ob);
		
		manager.addObserverForObservable(ob, k);
		
		observers = manager.getObservers();
		// Adding an observer the second time shouldn't increase the number of observers
		assertTrue("Größe der TreeMap stimmt nicht", observers.size() == 1);
		assertTrue("Identifier stimmt nicht", observers.keySet().contains(k.getId()));
		assertTrue("Gepeicherter Observer stimmt nicht", observers.get(k.getId()).get(0) == ob);
	}
	
	/**
	 * Teste Fehlerbehandlung Entfernen eines Observers bei Uebergabe von null als Observer und Observable
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_removeNullObserverForNullObservable() {
		manager.removeObserverForObservable(null, null);
	}
	
	/**
	 * Teste Fehlerbehandlung Entfernen eines Observers bei Uebergabe von null als Observer
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_removeObserverForNullObservable() {
		manager.removeObserverForObservable(null, new Girokonto());
	}
	
	/**
	 * Teste Fehlerbehandlung Entfernen eines Observers bei Uebergabe von null als Observable
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_removeNullObserverForObservable() {
		manager.removeObserverForObservable( (o, arg) -> {} , null);
	}
	
	/**
	 * Teste Behandlung Entfernen eines Observers mit validen Parametern
	 */
	@Test
	public void test_removeValidObserver() {
		Girokonto k = new Girokonto();
		Observer ob = (o, arg) -> {};

		manager.addObserverForObservable(ob, k);		
		manager.removeObserverForObservable(ob, k);
		
		TreeMap<UUID, ArrayList<Observer>> obs = manager.getObservers();
		
		assertTrue("Größe der TreeMap stimmt nicht", obs.size() == 0);
	}
	
	/**
	 * Testet Observer Calling und erstellen von Klonen
	 */
	@Test(timeout = 100)
	public void test_observerCalling() {
		Girokonto k = new Girokonto();
		Observer ob = (o, arg) -> {
			Girokonto clone = ((Girokonto) o);
			
			assertTrue("Kontostände stimmen nicht überein", clone.getKontostand() == k.getKontostand());
			
			clone.einzahlen(100);
			
			assertTrue("Kontostände stimmen überein", clone.getKontostand() != k.getKontostand());
		};
		manager.addObserverForObservable(ob, k);
		k.einzahlen(100);
	}

}
