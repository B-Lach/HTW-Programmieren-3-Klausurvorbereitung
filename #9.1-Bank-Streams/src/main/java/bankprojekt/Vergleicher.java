package bankprojekt;
/**
 * Interface f�r den Vergleich zweier Objekte
 * @author Doro
 *
 */
public interface Vergleicher {
	/**
	 * vergleicht a und b
	 * @param a
	 * @param b
	 * @return positiver Wert, wenn a > b, negativer Wert, wenn a < b
	 *         und 0, wenn a == b 
	 */
	public int vergleichen(Object a, Object b);
	
	/**
	 * pr�ft, ob a > b gem�� der durch vergleichen() definierten Ordnung
	 * @param a
	 * @param b
	 * @return true, falls a > b
	 */
	default public boolean istGroesser(Object a, Object b)
	{
		return (this.vergleichen(a, b) > 0);
	}
}
