package bankprojekt.verarbeitung;

/**
 * Tritt bei Zugriff auf Konto auf, das nicht existiert
 * @author Benny Lach
 *
 */
@SuppressWarnings("serial")
public class KontoNichtVorhandenException extends Exception {
	
	/**
	 * Standard Konstruktor
	 * @param kontonummer nicht gefundene Kontonummer
	 */
	public KontoNichtVorhandenException(long kontonummer) {
		super("Konto mit Kontonummer " + kontonummer + " nicht vorhnden");
	}

}
