package bankprojekt.verarbeitung;

/**
 * Ein Girokonto
 * @author Doro
 *
 */
public class Girokonto extends Konto {
	/**
	 * Wert, bis zu dem das Konto �berzogen werden darf
	 */
	private double dispo;

	/**
	 * erzeugt ein Standard-Girokonto
	 */
	public Girokonto()
	{
		super(Kunde.MUSTERMANN, 99887766);
		this.dispo = 500;
	}
	
	/**
	 * erzeugt ein Girokonto mit den angegebenen Werten
	 * @param inhaber Kontoinhaber
	 * @param nummer Kontonummer
	 * @param dispo Dispo
	 * @throws IllegalArgumentException wenn der inhaber null ist 
	 * 									oder der angegebene dispo negativ ist
	 */
	public Girokonto(Kunde inhaber, long nummer, double dispo)
	{
		super(inhaber, nummer);
		if(dispo < 0)
			throw new IllegalArgumentException("Der Dispo darf nicht negativ sein!");
		this.dispo = dispo;
	}
	
	/**
	 * liefert den Dispo
	 * @return Dispo von this
	 */
	public double getDispo() {
		return dispo;
	}

	/**
	 * setzt den Dispo neu
	 * @param dispo muss gr��er sein als 0
	 * @throw IllegalArgumentException wenn dispo negativ ist
	 */
	public void setDispo(double dispo) {
		if(dispo < 0)
			throw new IllegalArgumentException("Der Dispo darf nicht negativ sein!");
		this.dispo = dispo;
	}
	
    /**
     * vermindert den Kontostand um den angegebenen Betrag, falls das Konto nicht gesperrt ist.
     * Am Empf�ngerkonto wird keine �nderung vorgenommen, da davon ausgegangen wird, dass dieses sich
     * bei einer anderen Bank befindet.
     * @param betrag double
     * @param empfaenger String
     * @param nachKontonr int
     * @param nachBlz int
     * @param verwendungszweck String
     * @return boolean
     * @throws GesperrtException wenn das Konto gesperrt ist
     * @throws IllegalArgumentException wenn der Betrag negativ ist oder
     * 									empfaenger oder verwendungszweck null ist
     */
    public boolean ueberweisungAbsenden(double betrag, 
    		String empfaenger, long nachKontonr, 
    		long nachBlz, String verwendungszweck) 
    				throws GesperrtException 
    {
      if (this.isGesperrt())
            throw new GesperrtException(this.getKontonummer());
        if (betrag < 0 || empfaenger == null || verwendungszweck == null)
            throw new IllegalArgumentException("Betrag negativ");
        if (getKontostand() - betrag >= - dispo)
        {
            setKontostand(getKontostand() - betrag);
            return true;
        }
        else
        	return false;
    }

    /**
     * erh�ht den Kontostand um den angegebenen Betrag
     * @param betrag double
     * @param vonName String
     * @param vonKontonr int
     * @param vonBlz int
     * @param verwendungszweck String
     *      * @throws IllegalArgumentException wenn der Betrag negativ ist oder
     * 									vonName oder verwendungszweck null ist
     */
    public void ueberweisungEmpfangen(double betrag, String vonName, long vonKontonr, long vonBlz, String verwendungszweck)
    {
        if (betrag < 0 || vonName == null || verwendungszweck == null)
            throw new IllegalArgumentException("Betrag negativ");
        setKontostand(getKontostand() + betrag);
    }
    
    @Override
    public String toString()
    {
    	String ausgabe = "-- GIROKONTO --" + System.lineSeparator() +
    	super.toString()
    	+ "Dispo: " + this.dispo + System.lineSeparator();
    	return ausgabe;
    }

	@Override
	public boolean abheben(double betrag) throws GesperrtException{
		if (betrag < 0 ) {
			throw new IllegalArgumentException();
		}
		if(this.isGesperrt())
			throw new GesperrtException(this.getKontonummer());
		if (getKontostand() - betrag >= - dispo)
		{
			setKontostand(getKontostand() - betrag);
			return true;
		}
		else
			return false;
	}
	
	@Override
	public void waehrungswechsel(Waehrung neu) {
		if (neu == null) {
			throw new IllegalArgumentException("neu darf nicht null sein!");
		}
		// dispo in neue Waehrung umrechnen
		double _dispoeuro = dispo / waehrung.getUmrechnungskurs();
		dispo = neu.umrechnen(_dispoeuro);
		// kontostand in neue Waehrung umrechnen
		super.waehrungswechsel(neu);
	}

}
