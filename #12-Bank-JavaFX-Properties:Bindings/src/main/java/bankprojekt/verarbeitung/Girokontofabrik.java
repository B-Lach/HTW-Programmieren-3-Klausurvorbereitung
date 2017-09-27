package bankprojekt.verarbeitung;

/**
 * Factory zum Erstellen eines Girokontos
 * @author Benny Lach
 *
 */
public class Girokontofabrik extends Kontofabrik {
	// Default Dispo-Rahmen
	private final static double DISPO = 500;
	
	@Override
	protected Konto erstelleKonto(Kunde inhaber, long kontonummer) {
		Girokonto gk = new Girokonto(inhaber, kontonummer, Girokontofabrik.DISPO);

		return gk;
	}

}
