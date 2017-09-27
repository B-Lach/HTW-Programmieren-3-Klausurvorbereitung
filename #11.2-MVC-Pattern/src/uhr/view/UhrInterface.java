package uhr.view;

import uhr.model.Uhrzeit;

/**
 * Interface um mit Views zu kommunizieren
 * @author Benny Lach
 *
 */
public interface UhrInterface {
	/**
	 * Callback um aktuelle Uhrzeit anzuzeigen
	 * @param zeit 
	 */
	public void aktualisiereUhrzeit(Uhrzeit zeit);
	
	/**
	 * Callback um Buttons zu ändern, fals nötig
	 * @param an
	 */
	default public void aktualisiereButtonsFallsNötig(boolean an) {
		
	}
}

