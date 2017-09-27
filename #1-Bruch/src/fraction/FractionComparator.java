package fraction;

import java.util.Comparator;

/**
 * Comparator class used to sort an array of Bruch objects in ascending order based on its numerators 
 * @author Benny Lach
 *
 */
public class FractionComparator implements Comparator<Bruch> {

	@Override
	public int compare(Bruch o1, Bruch o2) {
		return Integer.compare(o1.getZaehler(), o2.getZaehler());
	}
}
