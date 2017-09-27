package bankprojekt.verarbeitung;

import java.time.LocalDate;

/**
 * ein Sparbuch
 * @author Doro
 *
 */
public class Sparbuch extends Konto {
	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 649451067474314375L;

	/**
	 * Zinssatz, mit dem das Sparbuch verzinst wird. 0,03 entspricht 3%
	 */
	private double zinssatz;
	
	/**
	 * Monatlich erlaubter Gesamtbetrag f�r Abhebungen
	 */
	public static final double ABHEBESUMME = 2000;
	
	/**
	 * Betrag, der im aktuellen Monat bereits abgehoben wurde
	 */
	private double bereitsAbgehoben = 0;
	/**
	 * Monat und Jahr der letzten Abhebung
	 */
	private LocalDate zeitpunkt = LocalDate.now();
	
	public Sparbuch() {
		zinssatz = 0.03;
	}

	public Sparbuch(Kunde inhaber, long kontonummer) {
		super(inhaber, kontonummer);
		zinssatz = 0.03;
	}
	
	@Override
	public String toString()
	{
    	String ausgabe = "-- SPARBUCH --" + System.lineSeparator() +
    	super.toString()
    	+ "Zinssatz: " + this.zinssatz * 100 +"%" + System.lineSeparator();
    	return ausgabe;
	}

	@Override
	protected boolean abhebungValide(double betrag) {
		bereitsAbgehobenAktualisierenFallsNoetig();
		
		return (getKontostand() - betrag >= 0.50 && bereitsAbgehoben + betrag <= Sparbuch.ABHEBESUMME);
	}
	
	@Override
	protected void nachAbhebungHook(double betrag) {
		bereitsAbgehoben += betrag;
	}
	
	/**
	 * Aktualisiert bereitsAbgehoben falls noetig
	 */
	private void bereitsAbgehobenAktualisierenFallsNoetig() {
		LocalDate heute = LocalDate.now();
		if(heute.getMonth() != zeitpunkt.getMonth() || heute.getYear() != zeitpunkt.getYear())
		{
			this.bereitsAbgehoben = 0;
		}
	}

}
