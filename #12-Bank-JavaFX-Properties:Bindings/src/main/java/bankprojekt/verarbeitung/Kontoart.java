package bankprojekt.verarbeitung;

/**
 * alle angebotenen Kontoarten
 * @author Doro
 *
 */
public enum Kontoart {
	/**
	 * ein Girokonto
	 */
	GIROKONTO("Mit ganz viel Dispo"),
	/**
	 * ein Sparbuch
	 */
	SPARBUCH("viele Zinsen"),
	/**
	 * ein Festgeldkonto
	 */
	FESTGELDKONTO("gibts noch nicht...");
	
	private String werbebotschaft;

	/**
	 * liefert die zur Kontoart gehörende Werbebotschaft
	 * @return
	 */
	public String getWerbebotschaft() {
		return werbebotschaft;
	}
	
	private Kontoart(String werbebotschaft)
	{
		this.werbebotschaft = werbebotschaft;
	}
	
}
