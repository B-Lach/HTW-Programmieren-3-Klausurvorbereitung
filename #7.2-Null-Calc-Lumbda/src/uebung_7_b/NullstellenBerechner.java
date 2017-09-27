package uebung_7_b;


import java.util.function.DoubleFunction;
/**
 * Klasse zum Berechnen von Nullstellen
 * @author HorseT
 *
 */
public class NullstellenBerechner {
	
	/**
	 * Funktion zur Berechnung einer Nullstelle in einem angegebenen Interval für eine uebergebene Funktion 
	 * @param func Die zu benutzende Funktion
	 * @param i Das Interval, in welchem nach einer Nullstelle gesucht werden soll
	 * @throws IllgalArgumentException wenn func oder i null
	 * @return Interval der gefundenen Nullstelle, null wenn keine Nullstelle gefunden
	 * 
	 *
	 */
	public static Interval finden(DoubleFunction<Double> func, Interval i) {
		if (func == null || i == null) {
			throw new IllegalArgumentException("Funktion und Interval müssen valide sein");
		}
		
		if (i.getMax() - i.getMin() < 0.01) {
			return i;
		}
		
		double min = func.apply(i.getMin());
		double mid = func.apply(i.getMid());
		double max = func.apply(i.getMax());
		
		try {
			if (min < 0 && mid >= 0) {
				return NullstellenBerechner.finden(func, new Interval(i.getMin(), i.getMid()));
			} else if (mid < 0 && max >= 0) {
				return NullstellenBerechner.finden(func, new Interval(i.getMid(), i.getMax()));
			}
		} catch(Exception e) {
			// Kein gültiges Interval mehr (min == max) -> Keine Nullstelle
			return null;
		}
		// keine unterschiedlichen Vorzeichen -> Keine valide Nullstelle im Interval
		return null;
	}
}
