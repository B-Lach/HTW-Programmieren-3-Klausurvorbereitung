package bankprojekt.verarbeitung.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.Waehrung;

public class KontoTests {
	
	/**
	 * Testet das Verhalten der Instanz, wenn Geld in anderer Waehrung abgehoben wird
	 * @throws GesperrtException wenn konto gesperrt
	 */
	@Test
	public void test_abhebenValidBGN() throws GesperrtException {
		Girokonto gk = new Girokonto();
		gk.einzahlen(500);
		gk.abheben(200, Waehrung.BGN);
		assertTrue("Kontostand nach Abhebung in anderer Waehrung ist nicht korrekt",
				gk.getKontostand() == 500 - 200/Waehrung.BGN.getUmrechnungskurs());
	}
	
	/**
	 * Testet das Verhalten der Instanz, wenn ein negativer Betrag abgehoben wird
	 * @throws GesperrtException wenn konto gesperrt
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_abhebenInvalidBetrag() throws GesperrtException {
		Girokonto gk = new Girokonto();
		gk.abheben(-20, Waehrung.BGN);
	}
	
	/**
	 * Testet das Verhalten der Instanz, wenn Konto gesperrt und Geld in andere Waehrung abgehoben wird
	 * @throws GesperrtException wenn konto gesperrt
	 */
	@Test(expected = GesperrtException.class) 
	public void test_abhebenKontoGesperrt() throws GesperrtException {
		Girokonto gk = new Girokonto();
		gk.sperren();
		gk.abheben(20, Waehrung.KM);
	}
	
	/**
	 * Testet das Verhalten der Instanz, wenn null als Waehrung uebergeben wird
	 * @throws GesperrtException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_abhebenInvalidWaehrung() throws GesperrtException {
		Girokonto gk = new Girokonto();
		gk.abheben(20, null);
	}
	
	/**
	 * Testet das Verhalten der Instanz, wenn Geld in anderer Waehrung eingezahlt wird
	 * @throws IllegalArgumentException
	 */
	@Test
	public void test_einzahlenBGN() throws IllegalArgumentException {
		Girokonto gk = new Girokonto();
		gk.einzahlen(500);
		gk.einzahlen(200, Waehrung.BGN);
		
		assertTrue("Kontostand nach Einzahlung in anderer Waehrung ist nicht korrekt",
				gk.getKontostand() == 500 + 200/Waehrung.BGN.getUmrechnungskurs());
	}
	
	/**
	 * Ueberprueft Standard-Waehrung nach Initialisierung
	 */
	@Test
	public void test_standardWaehrung() {
		Girokonto gk = new Girokonto();
		
		assertTrue("Standard Waehrung ist nicht EURO",
				gk.getAktuelleWaehrung() == Waehrung.EUR);
	}

	/**
	 * Ueberprueft Waehrung nach Waehrungswechsel
	 * @throws IllegalArgumentException wenn Waehrung null
	 */
	@Test
	public void test_waehrungNachWechsel() throws IllegalArgumentException {
		Girokonto gk = new Girokonto();
		gk.waehrungswechsel(Waehrung.KM);
		
		assertTrue("Waehrung nach Wechsel ist nicht korrekt",
				gk.getAktuelleWaehrung() == Waehrung.KM);
	}
	
	/**
	 * Testet Verhalten der Instanz nach Waehrungswechsel
	 * @throws IllegalArgumentException
	 */
	@Test
	public void test_waehrungswechselValid() throws IllegalArgumentException{
		Girokonto gk = new Girokonto();
		gk.einzahlen(500);
		gk.waehrungswechsel(Waehrung.KM);
		
		assertTrue("Kontostand nach Waehrungswechsel ist nicht korrekt",
				gk.getKontostand() == Waehrung.KM.umrechnen(500));
		assertTrue("Dispo nach Waehrungswechsel ist nicht korrekt",
				gk.getDispo() == Waehrung.KM.umrechnen(500));
	}
	
	/**
	 * Testet Verhalten der Instanz, wenn Waehrungswechsel null uebergeben wird
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_waehrungswechselInvalid() {
		Girokonto gk = new Girokonto();
		gk.waehrungswechsel(null);
	}
	
	
}
