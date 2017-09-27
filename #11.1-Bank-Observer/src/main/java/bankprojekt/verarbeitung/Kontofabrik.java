package bankprojekt.verarbeitung;

/**
 * Abstrakte Konto Factory zum erstellen von KontenTypen 
 * @author Benny Lach
 *
 */
public abstract class Kontofabrik {
	/**
	 * Abstrakte Methode zum Erstellen des Kontos 
	 * @param inhaber Inhaber des Kontos
	 * @param kontonummer Kontonummer des Kontos
	 * @return Konto
	 */
	protected abstract Konto erstelleKonto(Kunde inhaber, long kontonummer);
}
