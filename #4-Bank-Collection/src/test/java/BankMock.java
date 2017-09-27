import java.util.TreeMap;

import bankprojekt.verarbeitung.Bank;
import bankprojekt.verarbeitung.Konto;

/**
 * Mock Klasse zum Testen von verschiedenen Implementationen
 * @author Benny Lach
 *
 */
public class BankMock extends Bank {
	
	/**
	 * Standard Konstruktor
	 * @param blz
	 */
	public BankMock(long blz) {
		super(blz);
	}

	/**
	 * Erhalte eine Kontonummer
	 * @return Kontonummer
	 */
	public long getNeueKontonummer() {
		return erstelleKontonummer();
	}
	
	/**
	 * Erhalte die aktuelle Kontenliste
	 * @return Kontenliste
	 */
	public TreeMap<Long, Konto> getKonten() {
		return konten;
	}
	
	/**
	 * Sperrt das zugehörige Konto ener Kontonummer
	 * @param kontonummer Die Kontonumer
	 * @return True, wenn gepserrt, false wenn nicht vorhanden
	 */
	public boolean sperreKonto(long kontonummer) {
		Konto k = konten.get(kontonummer);
		if (k == null) { return false; }
		k.sperren();
		
		return true;
	}
}
