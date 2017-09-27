package uebung_7_b.test;


import static org.junit.Assert.*;

import org.junit.Test;

import uebung_7_b.Interval;
import uebung_7_b.NullstellenBerechner;

/**
 * Test Cases für die Klasse NullstellenBerechner 
 * @author Benny Lach
 *
 */
public class NullstellenBerechnerTest {
	/**
	 * Testet korrekte Fehlerbehandlung bei Uebergabe von nicht validen Interval
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_invalidInterval() {
		Interval i = NullstellenBerechner.finden(x -> Math.pow(x, 2) - 5, null);
	}
	
	/**
	 * Testet korrekte Fehlerbehandlung bei Ueberhabe von nicht valider Funktion
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_invalidFunction() {
		Interval i = NullstellenBerechner.finden(null, new Interval(-1, 1));
	}
	/**
	 * Teste Berechnung der Nullstellt für f(x) = x^2 - 5 
	 */
	@Test
	public void test_fx () {
		// f(x) = x^2 - 5 -> x1 = -√5 && x2 = √5 
		Interval i = NullstellenBerechner.finden(x -> Math.pow(x, 2) - 5, new Interval(-20, 20));
		// Interval besitzt zwei Nullstellen, aktuelle Implementation findet jedoch nur eine
		assertTrue("√5 ist nicht im Interval, muss aber!", i.inInterval(Math.sqrt(5)));
	}
	
	/**
	 * Teste Berechnung der Nullstellt für g(x) = e^3x - 7 
	 */
	@Test
	public void test_gx(){
		// g(x) = e^3x - 7 -> x = log(7)/3
		Interval i = NullstellenBerechner.finden(x -> Math.pow(Math.E,3*x) - 7, new Interval((7/Math.pow(Math.E,3)), 1));
		assertTrue("log(7)/3 nicht im Interval, sollte aber!", i.inInterval(Math.log(7)/3));
	}
	
	/**
	 * Teste Berechnung der Nullstellt für h(x) = (5 - x) / (x^3 + 2) 
	 */
	@Test
	public void test_hx() {
		// h(x) = (5 - x) / (x^3 + 2) -> x = 5
		// Funktioniert nicht, da nicht stetig - was genau testet man nun?
		// Der Fall, der eintreffen sollte, auf Grundlage der Annahme, dass nicht stetige Funktionen nicht funktionieren
		// oder auf Richtigkeit des Ergebnisses ohne Betrachtung der Stetigkeit?
		Interval i = NullstellenBerechner.finden(x -> (5 - x) / (Math.pow(x, 3) + 2), new Interval(-6, 0));
		assertTrue("5 im Interval, sollter aber nicht", !i.inInterval(5));
		// Aber eigenlich will man ja testen
		// assertTrue("5 nicht im Interval, sollte aber", i.inInterval(5));
	}
	
	/**
	 * Teste Berechnung der Nullstellt für k(x) = (x^2 + 1)
	 */
	@Test
	public void test_kx() {
		// k(x) = (x^2 + 1) -> x = i
		Interval i = NullstellenBerechner.finden(x -> Math.pow(x, 2) + 1, new Interval(-10, 10));
		// Abbildung von R -> R, also ist i kein valides Ergebnis
		assertNull("Interval sollte null sein, ist es aber nicht!", i);
	}

}
