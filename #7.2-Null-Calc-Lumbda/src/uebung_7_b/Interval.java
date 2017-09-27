package uebung_7_b;

/**
 * Klasse, das ein Double Interval repraesentiert
 * @author Benny Lach
 *
 */
public class Interval {
	private double a;
	private double b;
	
	/**
	 * Default Konstruktor zur Initialisierung eines Intervals
	 * Der Instanz ist egal ob Beginn und Ende korrekt angegeben wurden. 
	 * Diese werden intern trotzdem korrekt behandelt.
	 *  
	 * @param a Beginn des Intervals
	 * @param b Ende des Intervals
	 * @throws IllegalArgumentException, wenn a und b identisch -> kein Interval
	 */
	public Interval(double a, double b) throws IllegalArgumentException {
		if (a == b) {
			throw new IllegalArgumentException("Interval muss unterschiedliche Werte besitzen!");
		}
		this.a = a;
		this.b = b;
	}
	
	/**
	 * Gibt das Minimum des Intervals zurück
	 * @return Minimum
	 */
	public double getMin() {
		return Math.min(a, b);
	}
	
	/**
	 * Gibt das Maximum des Intervals zurück
	 * @return Maximum
	 */
	public double getMax() {
		return Math.max(a, b); 
	}
	
	/**
	 * Gibt die Mitte des Intervals zurück, die durch das Minimum und Maximum definiert werden
	 * @return Mitte
	 */
	public double getMid() {
		return (getMin() + getMax())/2;
	}
	
	/**
	 * Prueft, ob ein Wert Teil des Intervals ist und gibt ein Boolean zurueck - true wenn im Interval, andernfalls false 
	 * @param a zu pruefender Wert
	 * @return boolean
	 */
	public boolean inInterval(double a) {
		return a >= getMin() && a <= getMax();
	}
	
	@Override
	public String toString() {
		return "[" + getMin() + ":" + getMax() + "]" + System.lineSeparator();
	}
}
