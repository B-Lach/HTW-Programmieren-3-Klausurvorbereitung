package uebung_5;

import java.util.Date;

public class Kind extends Person implements Frech<Erwachsener> {
	
	Kuscheltier[] besitzt = new Kuscheltier[2];
	/**
	 * Default Konstruktor
	 * @param name Der Name
	 * @param geburtstag Der Geburtstag
	 */
	public Kind(String name, Date geburtstag, Kuscheltier eins, Kuscheltier zwei) {
		super(name, geburtstag);
		
		if (eins == null || zwei == null) {
			throw new IllegalArgumentException("Kuscheltiere dürfen nicht null sein");
		}
		besitzt[0] = eins;
		besitzt[1] = zwei;
	}
	
	/**
	 * Methode zum Waschen des Kindes
	 * @param schnell schnell oder normal
	 */
	public void waschen(boolean schnell) {
		String mode = schnell == true ? "schnell" : "normal";
		
		System.out.println("Kind wird " + mode + " gewaschen");
	}

	@Override
	public boolean aergern(Erwachsener p) {
		return true;
	}

	@Override
	public void zungeRausstecken() {
		System.out.println("Kind steckt Zunge raus");		
	}
	
}
