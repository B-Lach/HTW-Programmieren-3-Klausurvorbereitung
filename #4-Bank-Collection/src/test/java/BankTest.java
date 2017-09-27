import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import bankprojekt.verarbeitung.Bank;
import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.Konto;
import bankprojekt.verarbeitung.KontoNichtVorhandenException;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.Sparbuch;
import bankprojekt.verarbeitung.Waehrung;

/**
 * Test cases für Bank.class
 * @author HorseT
 *
 */
public class BankTest {

	/**
	 * Teste BLZ nach Initialisierung
	 */
	@Test
	public void test_constructor() {
		BankMock b = new BankMock(1111111L);
		
		assertEquals("BLZ sind nicht identisch", b.getBankleitzahl(), 1111111L);
		assertEquals("Konten Map nicht 0", b.getKonten().size(), 0);
	}
	
	/**
	 * Teste generierte Kontonummer
	 */
	@Test
	public void test_kontonummberBerechnung() {
		BankMock b = new BankMock(12344556L);
		assertEquals("Erwartete Kontonummer ist nicht korrekt", b.getNeueKontonummer(), 12344556L/42L);
	}
	
	/**
	 * Testet Girokonto erstellen mit null als Kunde
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_createGiroKontoInvalidKunde() {
		Bank b = new Bank(111111111L);
		long ktonr = b.girokontoErstellen(null);
	}
	
	/**
	 * Teste Girokonto erstellen mit validen Kunden
	 */
	@Test 
	public void test_createGirokontoValidKunde() {
		BankMock b = new BankMock(111111111L);

		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		Konto neu = b.getKonten().get(ktonr);
		
		assertEquals("Kontonummer ist nicht wie erwartet", ktonr, 111111111L/42);
		assertEquals("KontenMap nicht erweitert worden", b.getKonten().size(),  1);
		assertEquals("Kontonummer des Kontos nicht korrekt", neu.getKontonummer(), ktonr);
		assertEquals("Inhaber von Konto nicht korrekt", neu.getInhaber(), Kunde.MUSTERMANN);
		assertNotNull("Konto darf nicht null sein", neu);
	}
	
	/**
	 * Testet Sparbuch erstllen mit null als Kunde
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_createSparbuchInvalidKunde() {
		Bank b = new Bank(11111111111L);
		long ktonr = b.sparbuchErstllen(null);
	}
	
	/**
	 * Testet Sparbuch erstllen mit validen Kunden
	 */
	@Test
	public void test_createSparbuchValidKunde() {
		BankMock b = new BankMock(111111111L);

		long ktonr = b.sparbuchErstllen(Kunde.MUSTERMANN);
		Konto neu = b.getKonten().get(ktonr);
		
		assertEquals("Kontonummer ist nicht wie erwartet", ktonr, 111111111L/42);
		assertEquals("KontenMap nicht erweitert worden", b.getKonten().size(),  1);
		assertEquals("Kontonummer des Kontos nicht korrekt", neu.getKontonummer(), ktonr);
		assertEquals("Inhaber von Konto nicht korrekt", neu.getInhaber(), Kunde.MUSTERMANN);
		assertNotNull("Konto darf nicht null sein", neu);
	}
	
	/**
	 * Testet Erstellem von mehrere Konten
	 */
	@Test
	public void test_createMultipleAccounts() {
		BankMock bm = new BankMock(111111111L);
		
		Kunde a = Kunde.MUSTERMANN;
		Kunde b = new Kunde("Hans", "Wurst", "zuhause", LocalDate.now());
		
		long ktonr_a = bm.girokontoErstellen(a);
		long ktonr_b = bm.sparbuchErstllen(b);
		
		assertEquals("KontenMap hat nicht korrekte Länge", bm.getKonten().size(), 2);
		
		Konto kontoA = bm.getKonten().get(ktonr_a);
		Konto kontoB = bm.getKonten().get(ktonr_b);
		
		assertNotNull("Konto für Kunde a darf nicht null sein", kontoA);
		assertNotNull("Konto für Kunde b darf nicht null sein", kontoB);
		
		assertEquals("Kunde a ist nicht Inhaber von Konto a", kontoA.getInhaber(), a);
		assertEquals("Kunde b ist nicht Inhaber von Konto b", kontoB.getInhaber(), b);
		
		assertTrue("Konto a ist kein Girokonto", kontoA instanceof Girokonto);
		assertTrue("Konto b ist kein Sparbuch", kontoB instanceof Sparbuch);
	}
	
	/**
	 * Testet alle Konten als String
	 */
	@Test
	public void test_alleKontenString() {
		Bank b = new Bank(111111111L);

		assertEquals("Kotenstring nach Instanzierung nicht korrekt", b.getAlleKonten(), "");
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		String expected = ktonr + ": " + 0.0 + System.getProperty("line.separator");
		
		assertEquals("Kontenstring nach hinzufügen von Konto nicht korrekt", b.getAlleKonten(), expected);
	}
	
	/**
	 * Testet erhalt aller vorhandener Kontonummern
	 */
	@Test
	public void test_kontonummernListe() {
		Bank b = new Bank(1111111111L);
		
		assertTrue("Kontenliste nach Instanzierung nicht leer", b.getAlleKontonummern().isEmpty());
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);

		assertEquals("Kontenliste nach Kontoerstellung nicht korrekt", b.getAlleKontonummern().size(), 1);
		assertTrue("Nummer in Kontenliste nicht korrekt", b.getAlleKontonummern().get(0) == ktonr);
	}
	
	/**
	 * Testet Geld abheben für ein nicht bekanntes Konto 
	 * @throws IllegalArgumentException wenn Betrag negativ
	 * @throws KontoNichtVorhandenException wenn Konto nicht vorhanden
	 * @throws GesperrtException wenn Konto gesperrt
	 */
	@Test(expected = KontoNichtVorhandenException.class)
	public void test_geldAbhebenInvalidKonto() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(11111111111L);
		b.geldAbheben(1232323L, 49);
	}
	
	/**
	 * Testet Geld abheben für ein gesperrtes Konto
	 * @throws IllegalArgumentException wenn Betrag negativ
	 * @throws KontoNichtVorhandenException wenn Konto nicht vorhanden
	 * @throws GesperrtException wenn Konto gesperrt
	 */
	@Test(expected = GesperrtException.class)
	public void test_geldAbhebenGesperrtesKonto() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		BankMock b = new BankMock(111111111L);
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		b.sperreKonto(ktonr);
		b.geldAbheben(ktonr, 50);
	}
	
	/**
	 * Testet Geld abheben mit negativen Betrag
	 * @throws IllegalArgumentException wenn Betrag negativ
	 * @throws KontoNichtVorhandenException wenn Kont nicht vorhanden
	 * @throws GesperrtException wenn Konto gesperrt
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_geldAbhebenNegativerBetrag() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(111111111L);
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		b.geldAbheben(ktonr, -50); 
	}
	
	/**
	 * Testet Geld abheben mit zu hohen Betrag
	 * @throws IllegalArgumentException wenn Betrag negativ
	 * @throws KontoNichtVorhandenException wenn Konto nicht vorhanden
	 * @throws GesperrtException wenn Konto gepserrt
	 */
	@Test
	public void test_geldAbhebenBetragZuHoch() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(111111111L);
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		assertFalse("Betrag dürfte von Girokonto nicht abgezogen werden", b.geldAbheben(ktonr, 1000));
		
		ktonr = b.sparbuchErstllen(Kunde.MUSTERMANN);
		assertFalse("Betrag dürfte von Sparbuch nicht abgezogen werden", b.geldAbheben(ktonr, 1000));
	}
	
	/**
	 * Teste Geld abheben mit validen Betrag
	 * @throws IllegalArgumentException wenn Betrag negativ
	 * @throws KontoNichtVorhandenException wenn Kont nicht vorhanden
	 * @throws GesperrtException wenn Konto gesperrt
	 */
	@Test
	public void test_geldAbhebenValid() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(111111111L);
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		assertTrue(b.geldAbheben(ktonr, 50));
	}
	
	/**
	 * Testet Geld einzahlen auf nicht bekanntes Konto
	 * @throws IllegalArgumentException wenn Betrag negativ
	 * @throws KontoNichtVorhandenException wenn Konto nicht bekannt
	 */
	@Test(expected = KontoNichtVorhandenException.class)
	public void test_geldEinzahlenInvalidKonto() throws IllegalArgumentException, KontoNichtVorhandenException {
		Bank b = new Bank(111111111L);
		b.geldEinzahlen(1212232L, 50);
	}
	
	/**
	 * Testet negativen Betrag auf bekanntes Konto einzahlen
	 * @throws IllegalArgumentException wenn Betrag negativ
	 * @throws KontoNichtVorhandenException wenn Konto nicht bekannt
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_geldEinzahlenNegativerBetrag() throws IllegalArgumentException, KontoNichtVorhandenException {
		Bank b = new Bank(111111111L);
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		b.geldEinzahlen(ktonr, -50);
	}
	
	/**
	 * Testet validen Betrag auf ein bekanntes Konto einzahlen
	 * @throws IllegalArgumentException wenn Betrag negativ
	 * @throws KontoNichtVorhandenException wenn Konto nicht bekannt
	 */
	@Test
	public void test_geldEinzahlenValid() throws IllegalArgumentException, KontoNichtVorhandenException {
		BankMock b = new BankMock(111111111L);
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		b.geldEinzahlen(ktonr, 50);
		assertTrue("Aktuelles Kontostand nicht korrekt", b.getKonten().get(ktonr).getKontostand() == 50);
	}
	
	/**
	 * Testet das Löschen eines nicht bekannten Kontos
	 */
	@Test
	public void test_kontoLoeschenInvalid() {
		Bank b = new Bank(11111111L);
		
		assertFalse("Nicht vorhandens Konto kann nicht gelöscht werden", b.kontoLoeschen(12133L));
	}
	
	/**
	 * Testet das Löschen eines bekannten Kontos
	 */
	@Test
	public void test_kontoLoeschenValid() {
		Bank b = new Bank(111111111L);
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		assertTrue("Vorhandenes Konto sollte gelöscht werden", b.kontoLoeschen(ktonr));
	}
	
	/**
	 * Testet Kontostand von nicht bekannten Konto
	 * @throws KontoNichtVorhandenException wenn Konto nicht bekannt
	 */
	@Test(expected = KontoNichtVorhandenException.class)
	public void test_getKontostandInvalid() throws KontoNichtVorhandenException {
		Bank b = new Bank(11111111L);
		b.getKontostand(123L);
	}
	
	/**
	 * Testet Kontostand von bekanntne Konto
	 * @throws KontoNichtVorhandenException wenn Konto nicht bekannt
	 */
	@Test
	public void test_getKontoStandValid() throws KontoNichtVorhandenException {
		Bank b = new Bank(111111111L);
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		assertTrue("Kontostand ist nicht korrekt", 0 == b.getKontostand(ktonr));
	}
	
	/**
	 * Testet Überweisung mit nicht bekannten Sender
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException Wenn Sender gesperrt
	 */
	@Test(expected = KontoNichtVorhandenException.class)
	public void test_ueberweisenInvalidSender() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(1111111111L);
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		b.geldUeberweisen(123L, ktonr, 40, "foo");
	}
	
	/**
	 * Testet Überweisung mit nicht bekannten Empfänger
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test(expected = KontoNichtVorhandenException.class)
	public void test_ueberweisenInvalidReceiver() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(1111111111L);
		
		long ktonr = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		b.geldUeberweisen(ktonr, 124L, 40, "foo");
	}
	
	/**
	 * Tetest Überweisung ohne Verwendunszweck
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ueberweisenOhneVerwendungszweck() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(111111111L);
		
		long ktonr_a = b.girokontoErstellen(Kunde.MUSTERMANN);
		long ktonr_b = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		b.geldUeberweisen(ktonr_a, ktonr_b, 50, null);
	}
	
	/**
	 * Tetest Überweisung mit leeren Verwendunszweck
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ueberweisenLeererVerwendungszweck() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(111111111L);
		
		long ktonr_a = b.girokontoErstellen(Kunde.MUSTERMANN);
		long ktonr_b = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		b.geldUeberweisen(ktonr_a, ktonr_b, 50, "");
	}
	
	/**
	 * Tetest Überweisung mit negativen Betrag
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ueberweisenInvalidBetrag() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(111111111L);
		
		long ktonr_a = b.girokontoErstellen(Kunde.MUSTERMANN);
		long ktonr_b = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		b.geldUeberweisen(ktonr_a, ktonr_b, -50, "bar");
	}
	
	/**
	 * Tetest Überweisung mit gesperrten Sender
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test(expected = GesperrtException.class)
	public void test_ueberweisenSenderGesperrt() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		BankMock b = new BankMock(111111111L);
		
		long ktonr_a = b.girokontoErstellen(Kunde.MUSTERMANN);
		b.sperreKonto(ktonr_a);
		
		long ktonr_b = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		b.geldUeberweisen(ktonr_a, ktonr_b, 50, "foo");
	}
	
	/**
	 * Testet Überweisung mit Sender = Sparbuch
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ueberweisenMitSparbuchsender() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(111111111L);
		
		long ktonr_a = b.sparbuchErstllen(Kunde.MUSTERMANN);
		long ktonr_b = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		b.geldUeberweisen(ktonr_a, ktonr_b, 50, "foo");
	}
	
	/**
	 * Testet Überweisung mit Empfänger = Sparbuch
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ueberweisenMitSparbuchempfaenger() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(111111111L);
		
		long ktonr_a = b.girokontoErstellen(Kunde.MUSTERMANN);
		long ktonr_b = b.sparbuchErstllen(Kunde.MUSTERMANN);
		
		b.geldUeberweisen(ktonr_a, ktonr_b, 50, "foo");
	}
	
	/**
	 * Testet Überweisung mit zu hohen Betrag
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test
	public void test_ueberweisenKontoNichtGedeckt() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(111111111L);
		
		long ktonr_a = b.girokontoErstellen(Kunde.MUSTERMANN);
		long ktonr_b = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		assertFalse("Überweisung darf nicht funktioniern", b.geldUeberweisen(ktonr_a, ktonr_b, 5000, "foo"));
		assertTrue("Kontostand von Sender nicht korrekt", b.getKontostand(ktonr_a) == 0);
		assertTrue("Kontostand von Empfänger nicht korrekt", b.getKontostand(ktonr_b) == 0);
	}
	
	/**
	 * Testet valide Überweisung von EUR nach EUR
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test
	public void test_ueberweisenValidEurToEur() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		Bank b = new Bank(111111111L);
		
		long ktonr_a = b.girokontoErstellen(Kunde.MUSTERMANN);
		long ktonr_b = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		assertTrue("Überweisung hat nicht funktioniert", b.geldUeberweisen(ktonr_a, ktonr_b, 50, "foo"));
		assertTrue("Kontostand von Sender nicht korrekt", b.getKontostand(ktonr_a) == -50);
		assertTrue("Kontostand von Empfänger nicht korrekt", b.getKontostand(ktonr_b) == 50);
	}
	
	/**
	 * Testet valide Überweisung von BGN nach EUR
	 * @throws IllegalArgumentException wenn Betrag negativ oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Emfpfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test
	public void test_ueberweisenValidBgnToEur() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		BankMock b = new BankMock(111111111L);
		
		long ktonr_a = b.girokontoErstellen(Kunde.MUSTERMANN);
		Konto konto_a = b.getKonten().get(ktonr_a);
		konto_a.waehrungswechsel(Waehrung.BGN);
		
		long ktonr_b = b.girokontoErstellen(Kunde.MUSTERMANN);
		
		assertTrue("Überweisung hat nicht funktioniert", b.geldUeberweisen(ktonr_a, ktonr_b, 50, "foo"));
		assertTrue("Kontostand von Sender nicht korrekt", b.getKontostand(ktonr_a) == -50);
		assertTrue("Kontostand von Empfänger nicht korrekt", b.getKontostand(ktonr_b) == 50 / Waehrung.BGN.getUmrechnungskurs());
	}
	
	/**
	 * Testet valide Überweisung von BGN nach LTL
	 * @throws IllegalArgumentException wenn Betrag negaiv oder Verwendungszweck leer
	 * @throws KontoNichtVorhandenException wenn Sender oder Empfänger nicht bekannt
	 * @throws GesperrtException wenn Sender gesperrt
	 */
	@Test
	public void test_ueberweisenValidBgnToLtl() throws IllegalArgumentException, KontoNichtVorhandenException, GesperrtException {
		BankMock b = new BankMock(111111111L);
		
		long ktonr_a = b.girokontoErstellen(Kunde.MUSTERMANN);
		Konto konto_a = b.getKonten().get(ktonr_a);
		konto_a.waehrungswechsel(Waehrung.BGN);
		
		long ktonr_b = b.girokontoErstellen(Kunde.MUSTERMANN);
		Konto konto_b = b.getKonten().get(ktonr_b);
		konto_b.waehrungswechsel(Waehrung.LTL);
		
		assertTrue("Überweisung hat nicht funktioniert", b.geldUeberweisen(ktonr_a, ktonr_b, 50, "foo"));
		assertTrue("Kontostand von Sender nicht korrekt", b.getKontostand(ktonr_a) == -50);
		
		double expected = 50 / Waehrung.BGN.getUmrechnungskurs();
		expected = expected * Waehrung.LTL.getUmrechnungskurs();
		
		assertTrue("Kontostand von Empfänger nicht korrekt", b.getKontostand(ktonr_b) == expected);
	}
	
	
}
