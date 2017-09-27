package bankprojekt.verarbeitung;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Klasse, die eine Bank repräsentiert
 * @author Benny Lach
 *
 */
public class Bank {
	
	protected TreeMap<Long, Konto> konten;
	private long blz;
	
	/**
	 * Konstruktor zum erstllen einer Bank mit einer angegebenen BLZ
	 * @param bankleitzahl Die Bankleitzahl der Bank
	 */
	public Bank(long blz) {
		konten = new TreeMap<>();
		this.blz = blz;
	}
	
	/**
	 * Methode zum Erhalt der Bankleitzahl
	 * @return Bankleitzahl
	 */
	public long getBankleitzahl() {
		return blz;
	}
	
	/**
	 * Erstellt Girokonto für angegebenen Inhaber
	 * @param inhaber Der Inhaber
	 * @return Kontonummer des erstellten Kontos
	 * @throws IllegalArgumentException wenn Inhaber null
	 */
	public long girokontoErstellen(Kunde inhaber) {
		if (inhaber == null) {
			throw new IllegalArgumentException("Kunde darf nicht null sein");
		}
		
		Girokonto gk = new Girokonto(inhaber, erstelleKontonummer(), 500);
		konten.put(gk.getKontonummer(), gk);
		
		return gk.getKontonummer();
	}
	
	/**
	 * Erstellt Sparbuch für angegebenen Inhaber
	 * @param inhaber Der Inhaber
	 * @return Kontonummer des erstellten Sparbuches
	 * @throws IllegalArgumentException wenn Inhaber null
	 */
	public long sparbuchErstllen(Kunde inhaber) {
		if(inhaber == null) {
			throw new IllegalArgumentException("Kunde darf nicht null sein");
		}
		
		Sparbuch sb = new Sparbuch(inhaber, erstelleKontonummer());
		konten.put(sb.getKontonummer(), sb);
		
		return sb.getKontonummer();
	}
	
	/**
	 * Erhalte Liste aller Konten als String im Format Kontonummer: Kontostand
	 * @return Kontenliste 
	 */
	public String getAlleKonten() {
		String kontenString = "";
		
		for(long key: konten.keySet()) {
			kontenString += key + ": " + konten.get(key).getKontostand() + System.getProperty("line.separator");
		}
		return kontenString;
	}
	
	/**
	 * Erhalte Liste aller vorhandener Kontonummern
	 * @return Kontonummern
	 */
	public List<Long> getAlleKontonummern() {
		List<Long> nummern = new ArrayList<>();
		nummern.addAll(konten.keySet());
		
		return nummern;
	}
	
	/**
	 * Betrag von Konto abheben
	 * @param von Kontonummer des Kontos
	 * @param betrag Der Betrag
	 * @return True, wenn geklappt, false wenn abgelehnt
	 * @throws IllegalArgumentExceptio wenn Betrag negativ
	 * @throws KontoNichtVorhandenException wenn Kontonummer der Bank nicht bekannt
	 * @throws GesperrtException wenn Konto gesperrt
	 */
	public boolean geldAbheben(long von, double betrag) throws KontoNichtVorhandenException, GesperrtException, IllegalArgumentException {
		Konto k = konten.get(von);
		
		if(k == null) {
			throw new KontoNichtVorhandenException(von);
		}
		return k.abheben(betrag);
	}
	
	/**
	 * Betrag auf ein Konto einzahlen
	 * @param auf Kontonummer des Kontos
	 * @param betrag Der Betrag
	 * @throws KontoNichtVorhandenException wenn Kononummer der Bank nicht bekannt
	 * @throws IllegalArgumentException wenn Betrag negativ
	 */
	public void geldEinzahlen(long auf, double betrag) throws KontoNichtVorhandenException, IllegalArgumentException {
		Konto k = konten.get(auf);
		
		if(k == null) {
			throw new KontoNichtVorhandenException(auf);
		}
		k.einzahlen(betrag);
	}

	/**
	 * Löscht das Konto einer übermittelten Kontonummer
	 * @param nummer Die Kontonummer
	 * @return True, wenn gelöscht, false wenn Konto nicht bekannt
	 */
	public boolean kontoLoeschen(long nummer) {
		return konten.remove(nummer) != null;
	}
	
	/**
	 * Gibt aktuellen Kontostand zu einer Kontonummer zurück
	 * @param nummer Die zu verwendene Kontonummer
	 * @return Aktuellen Kontostand
	 * @throws KontoNichtVorhandenException wenn Konto nicht bekannt
	 */
	public double getKontostand(long nummer) throws KontoNichtVorhandenException {
		Konto k = konten.get(nummer);
		
		if(k == null) {
			throw new KontoNichtVorhandenException(nummer);
		}
		return k.getKontostand();
	}
	
	/**
	 * Geld von ein Konto auf ein andere überweisen
	 * @param von Sender
	 * @param nach Empfänger
	 * @param betrag der Betrag
	 * @param verwendungszweck Der Verwendungszweck
	 * @return True wenn Überweisung valide, sonst false 
	 * @throws KontoNichtVorhandenException Wenn Sender oder Empfänger nicht bekannt  
	 * @throws IllegalArgumentException Wenn Verwendungszweck leer, Sender/Emfpänger kein Girokonto oder Betrag negativ
	 * @throws GesperrtException Wenn Senderkonto gesperrt
	 */
	public boolean geldUeberweisen(long von, long nach, double betrag, String verwendungszweck) 
			throws KontoNichtVorhandenException, IllegalArgumentException, GesperrtException {
		Konto konto_von = konten.get(von);
		// Sender nicht bekannt
		if(konto_von == null) {
			throw new KontoNichtVorhandenException(von);
		}
		// Empfänger nicht bekannt
		Konto konto_nach = konten.get(nach);
		if(konto_nach == null) {
			throw new KontoNichtVorhandenException(nach);
		}
		// Empfänger oder Sender ist Sparbuch
		if(!(konto_von instanceof Girokonto) || !(konto_nach instanceof Girokonto)) {
			throw new IllegalArgumentException("Überweisung nur mit Girokonten valide");
		}
		
		// Casting
		Girokonto _von = ((Girokonto) konto_von);
		Girokonto _nach = ((Girokonto) konto_nach);
		
		if(_von.ueberweisungAbsenden(betrag, _nach.getInhaber().getName(), nach, blz, verwendungszweck)) {
			// Umwandeln von Absenderwährung nach Empfängerwährung
			double euro = betrag / _von.getAktuelleWaehrung().getUmrechnungskurs();
			double zielbetrag = _nach.getAktuelleWaehrung().umrechnen(euro);
			
			_nach.ueberweisungEmpfangen(zielbetrag, _von.getInhaber().getName(), von, blz, verwendungszweck);
			
			return true;
		}
		return false;
	}
	
	protected long erstelleKontonummer()  {
		return blz / 42L + konten.size();
	}
}
