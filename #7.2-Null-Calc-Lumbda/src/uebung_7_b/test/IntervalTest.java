package uebung_7_b.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uebung_7_b.Interval;

/**
 * Test Cases für die Klasse Interval
 * @author Benny Lach
 *
 */
public class IntervalTest {
	
	/**
	 * Testet korrekte Fehlerbehandlung bei Instanziierung mit gleichen Werten
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_invalidInit() {
		Interval i = new Interval(0,0);
	}
	
	/**
	 * Teste Instanziierung und korrekte Zuweisung der Variablen einer validen Instanz
	 */
	@Test
	public void test_validInit() {
		Interval i = new Interval(0, 3);
		assertTrue("Minimum ist nicht korrekt zugewiesen wordek", i.getMin() == 0);
		assertTrue("Maximum ist nicht korrekt zugewiesen worden", i.getMax() == 3);
		
		i = new Interval(14, -30);
		assertTrue("Minimum ist nicht korrekt zugewiesen wordek", i.getMin() == -30);
		assertTrue("Maximum ist nicht korrekt zugewiesen worden", i.getMax() == 14);
	}
	
	/**
	 * Testet korrekte Rückgabe der Mitte des Intervals
	 */
	@Test
	public void test_calcMiddle() {
		Interval i = new Interval(0, 4);
		assertTrue("Mitte des Intervals nicht korrekt berechnet", i.getMid() == 2);
		
		i = new Interval(-10, 15);
		assertTrue("Mitte des Intervals nicht korrekt berechnet", i.getMid() == 2.5);
	}
	
	/**
	 * Testet korrekte Aussage über Intervallzugehoerigkeit
	 */
	@Test
	public void test_intervalMembership() {
		Interval i = new Interval(-4, 9);
		
		assertFalse("-5 liegt nicht innerhalb des Intervalls", i.inInterval(-5));
		assertTrue("-4 ist innerhalb des Intervalls", i.inInterval(-4));
		assertTrue("3 ist innerhalb des Intervalls", i.inInterval(3));
		assertTrue("9 ist innerhalb des Intervalls", i.inInterval(9));
		assertFalse("9.1 liegt nicht innerhalb des Intervalls", i.inInterval(9.1));
	}
	
	/**
	 * Testet korrekte String-Repraesentation der Instanz
	 */
	@Test
	public void test_toString() {
		Interval i = new Interval(0, 4);

		assertTrue("String-Repraesentation ist nicht korrekt", ("[0.0:4.0]" + System.lineSeparator()).equals(i.toString()));
	}
}
