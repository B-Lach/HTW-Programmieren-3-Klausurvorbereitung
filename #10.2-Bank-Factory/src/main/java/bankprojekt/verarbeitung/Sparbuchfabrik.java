package bankprojekt.verarbeitung;

/**
 * Factory zum Erstellen eines Sparbuches
 * @author Benny Lach
 *
 */
public class Sparbuchfabrik extends Kontofabrik {

	@Override
	protected Konto erstelleKonto(Kunde inhaber, long kontonummer) {
		Sparbuch sb = new Sparbuch(inhaber, kontonummer);
		
		return sb;
	}

}
