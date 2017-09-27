package uebung_5;

/**
 * Repräsentiert einen Erwachsenen
 * @author Benny Lach
 *
 */
public class Erwachsener {
	private Kind[] hat;
	
	/**
	 * Default Konstruktor
	 * @param kind
	 * @throws IllegalArgumentException wenn Kind null
	 */
	public Erwachsener(Kind[] kinder) {
		if (kinder == null) {
			throw new IllegalArgumentException("Kinder darf nicht null sein");
		}
		hat = kinder;
	}
	
	final static String WUTSCHREI() {
		return "WUTSCHEI";
	}
}
