package bankprojekt.verarbeitung;

/**
 * stellt ein allgemeines Konto dar
 */
public abstract class Konto 
{
	/** 
	 * der Kontoinhaber
	 */
	private Kunde inhaber;

	/**
	 * die Kontonummer
	 */
	private final long nummer;

	/**
	 * der aktuelle Kontostand
	 */
	private double kontostand;
	
	/**
	 * Die Waehrung des Kontos - Default is Euro
	 */
	protected Waehrung waehrung = Waehrung.EUR;
	
	/**
	 * setzt den aktuellen Kontostand
	 * @param kontostand neuer Kontostand
	 */
	protected void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	/**
	 * Wenn das Konto gesperrt ist (gesperrt = true), k�nnen keine Aktionen daran mehr vorgenommen werden,
	 * die zum Schaden des Kontoinhabers w�ren (abheben, Inhaberwechsel)
	 */
	private boolean gesperrt;

	/**
	 * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die angegebenen Werte,
	 * der anf�ngliche Kontostand wird auf 0 gesetzt.
	 *
	 * @param inhaber Kunde
	 * @param kontonummer long
	 * @throws IllegalArgumentException wenn der Inhaber null
	 */
	public Konto(Kunde inhaber, long kontonummer) {
		if(inhaber == null)
			throw new IllegalArgumentException("Inhaber darf nicht null sein!");
		this.inhaber = inhaber;
		this.nummer = kontonummer;
		this.kontostand = 0;
		this.gesperrt = false;
	}
	
	/**
	 * setzt alle Eigenschaften des Kontos auf Standardwerte
	 */
	public Konto() {
		//hier ist kein weiterer Code erlaubt
		this(Kunde.MUSTERMANN, 1234567);
	}


	/**
	 * liefert den Kontoinhaber zur�ck
	 * @return   Kunde
	 */
	public final Kunde getInhaber() {
		return this.inhaber;
	}
	
	/**
	 * setzt den Kontoinhaber
	 * @param kinh   neuer Kontoinhaber
	 * @throws GesperrtException wenn das Konto gesperrt ist
	 * @throws IllegalArgumentException wenn kinh null ist
	 */
	public final void setInhaber(Kunde kinh) throws GesperrtException{
		if (kinh == null)
			throw new IllegalArgumentException("Der Inhaber darf nicht null sein!");
		if(this.gesperrt)
			throw new GesperrtException(this.nummer);        
		this.inhaber = kinh;

	}
	
	/**
	 * liefert die aktuelle Waehrung
	 * @return Waehrung
	 */
	public Waehrung getAktuelleWaehrung() {
		return waehrung;
	}
	
	/**
	 * Wecheslt aktuelle Waehrung und Kontostand des Kontos 
	 * @param neu die neue zu verwendene Waehrung
	 * @throws IllegalArgumentException, wenn neu null ist
	 */
	public void waehrungswechsel(Waehrung neu) {
		if (neu == null) {
			throw new IllegalArgumentException("Waehrung darf nicht null sein!");
		}
		
		if (this.waehrung != neu) {
			// 1. Rechne aktuellen kontostand in Euro um
			double _standeuro = kontostand / waehrung.getUmrechnungskurs();
			// 2. Rechne Euro in neue Waehrung um
			kontostand = neu.umrechnen(_standeuro);
			waehrung = neu;
		}
	}
	/**
	 * liefert den aktuellen Kontostand
	 * @return   double
	 */
	public final double getKontostand() {
		return kontostand;
	}

	/**
	 * liefert die Kontonummer zur�ck
	 * @return   long
	 */
	public final long getKontonummer() {
		return nummer;
	}

	/**
	 * liefert zur�ck, ob das Konto gesperrt ist oder nicht
	 * @return
	 */
	public final boolean isGesperrt() {
		return gesperrt;
	}
	
	/**
	 * Erhöht den Kontostand um den eingezahltenn Betrag in der angegebenden Waehrung
	 * @param betrag der einzuzahlende Betrag
	 * @param w die Waehrung des Betrages
	 * @throws IllegalArgumentException wenn w null ist
	 * @throws IllegalArgumentException wenn betrag negativ ist
	 */
	public void einzahlen(double betrag, Waehrung w) {
		if (w == null) {
			throw new IllegalArgumentException("Waehrung darf nicht null sein");
		}
		// 1. Rechne Betrag in euro um
		// 2. Rechne Euro Betrag in Konto Waehrung um
		this.einzahlen(waehrung.umrechnen( betrag/w.getUmrechnungskurs()));
	}
	
	/**
	 * Erh�ht den Kontostand um den eingezahlten Betrag.
	 *
	 * @param betrag double
	 * @throws IllegalArgumentException wenn der betrag negativ ist 
	 */
	public void einzahlen(double betrag) {
		if (betrag < 0) {
			throw new IllegalArgumentException("Negativer Betrag");
		}
		setKontostand(getKontostand() + betrag);
	}
	
	/**
	 * Gibt eine Zeichenkettendarstellung der Kontodaten zur�ck.
	 */
	@Override
	public String toString() {
		String ausgabe;
		ausgabe = "Kontonummer: " + this.getKontonummerFormatiert()
				+ System.getProperty("line.separator");
		ausgabe += "Inhaber: " + this.inhaber;
		ausgabe += "Aktueller Kontostand: " + this.kontostand + " Euro ";
		ausgabe += this.getGesperrtText() + System.getProperty("line.separator");
		return ausgabe;
	}
	
	/**
	 * Hebt einen Betrag in der angegebenen Waehrung ab
	 * @param betrag der abzuhebene Betrag
	 * @param w die zu erwendene Waehrung
	 * @throws IllegalArgumentException wenn w null ist
	 * @throws GesperrtException wenn das Konto gesperrt ist
	 * @return true, wenn Abhebung erfolgreich
	 * 		   false, wenn Abhebung nicht geklappt hat	
	 */
	public boolean abheben(double betrag, Waehrung w) throws GesperrtException {
		if (w == null) {
			throw new IllegalArgumentException("Waehrung darf nicht null sein!");
		}
		if(this.gesperrt) {
			throw new GesperrtException(this.nummer);
		}
		return this.abheben(this.waehrung.umrechnen(betrag/w.getUmrechnungskurs()));	
	}
	
	/**
	 * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es nicht gesperrt ist.
	 *
	 * @param betrag double
	 * @throws GesperrtException wenn das Konto gesperrt ist
	 * @throws IllegalArgumentException wenn der betrag negativ ist 
	 * @return true, wenn die Abhebung geklappt hat, 
	 * 		   false, wenn sie abgelehnt wurde
	 */
	public abstract boolean abheben(double betrag) throws GesperrtException;
	
	/**
	 * sperrt das Konto, Aktionen zum Schaden des Benutzers sind nicht mehr m�glich.
	 */
	public final void sperren() {
		this.gesperrt = true;
	}

	/**
	 * entsperrt das Konto, alle Kontoaktionen sind wieder m�glich.
	 */
	public final void entsperren() {
		this.gesperrt = false;
	}
	
	
	/**
	 * liefert eine String-Ausgabe, wenn das Konto gesperrt ist
	 * @return "GESPERRT", wenn das Konto gesperrt ist, ansonsten ""
	 */
	public final String getGesperrtText()
	{
		if (this.gesperrt)
		{
			return "GESPERRT";
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * liefert die ordentlich formatierte Kontonummer
	 * @return auf 10 Stellen formatierte Kontonummer
	 */
	public String getKontonummerFormatiert()
	{
		return String.format("%10d", this.nummer);
	}
	
	/**
	 * liefert den ordentlich formatierten Kontostand
	 * @return formatierter Kontostand mit 2 Nachkommastellen und W�hrungssymbol �
	 */
	public String getKontostandFormatiert()
	{
		return String.format("%10.2f Euro" , this.getKontostand());
	}
	
	/**
	 * Vergleich von this mit other; Zwei Konten gelten als gleich,
	 * wen sie die gleiche Kontonummer haben
	 * @param other
	 * @return true, wenn beide Konten die gleiche Nummer haben
	 */
	@Override
	public boolean equals(Object other)
	{
		if(this == other)
			return true;
		if(other == null)
			return false;
		if(this.getClass() != other.getClass())
			return false;
		if(this.nummer == ((Konto)other).nummer)
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return 31 + (int) (this.nummer ^ (this.nummer >>> 32));
	}
	
	/**
	 * schreibt this aUF DIE kONSOLE
	 */
	public void aufDieKonsoleSchreiben()
	{
		System.out.println(this.toString());
	}
}
