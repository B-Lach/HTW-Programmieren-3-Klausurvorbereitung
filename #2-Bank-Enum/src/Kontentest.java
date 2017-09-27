import java.time.LocalDate;
import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.Konto;
import bankprojekt.verarbeitung.Kontoart;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.Sparbuch;

/**
 * Vergleicher für zwei Konten
 * @author Doro
 *
 */
class KontoVergleich implements Vergleicher
{

	@Override
	public int vergleichen(Object a, Object b) {
		if(a instanceof Konto && b instanceof Konto)
		{
			return (int)(((Konto) a).getKontostand() 
					- ((Konto)b).getKontostand());
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
}

/**
 * Testprogramm für Konten
 * @author Doro
 *
 */
public class Kontentest {

	/**
	 * Testprogramm für Konten
	 * @param args wird nicht benutzt
	 */
	public static void main(String[] args) {
		
		Kontoart art = Kontoart.GIROKONTO;
		System.out.println(art.name() + " " + art.ordinal());
		art = Kontoart.valueOf("SPARBUCH");
		System.out.println(art.name() + " " + art.ordinal());
		
		for(int i=0; i< Kontoart.values().length; i++)
			System.out.println(Kontoart.values()[i].getWerbebotschaft());
		
		
		int a = 50;
		int b = a;
		a += 50;
		System.out.println(b); //50
		
		
		Kunde ich = new Kunde("Dorothea", "Hubrich", "zuhause", LocalDate.parse("1976-07-13"));
		
		Konto meinGiro = new Girokonto(ich, 1234, 1000.0);
		meinGiro.einzahlen(50);
		System.out.println(meinGiro);
		System.out.println("-------------------------");
		meinGiro.aufDieKonsoleSchreiben();
		
		Konto referenz = meinGiro;
		meinGiro.einzahlen(50);
		System.out.println(referenz.getKontostand());
		
		System.out.println("Nach dem Umzug:");
		ich.setAdresse("Woanders");
		System.out.println(meinGiro);
		
		Konto meinSpar = new Sparbuch(ich, 9876);
		meinSpar.einzahlen(50);
		try
		{
			boolean hatGeklappt = meinSpar.abheben(70);
			System.out.println("Abhebung hat geklappt: " + hatGeklappt);
			System.out.println(meinSpar);
		}
		catch (GesperrtException e)
		{
			System.out.println("Zugriff auf gesperrtes Konto - Polizei rufen!");
		}

		Vergleicher v = new KontoVergleich();
		if(v.vergleichen(meinGiro, meinSpar) > 0)
		{
			System.out.println("Auf dem Girokonto ist mehr Geld");
		}
		else
		{
			System.out.println("Auf dem Sparbuch ist mehr Geld");
		}
		
	}

}
