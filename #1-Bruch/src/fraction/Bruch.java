package fraction;

import java.math.BigInteger;

/**
 * Class that represents a mathematical fraction
 * @author Benny Lach
 *
 */
public class Bruch implements Comparable<Bruch> {
	private int zaehler;
	private int nenner;
	
	/**
	 * Constructor to init a new fraction
	 * @param zaehler 
	 * @param nenner
	 * @throws IllegalArgumentException if denominator is equal to 0
	 */
	public Bruch(int zaehler, int nenner) {
		if (nenner == 0) {
			throw new IllegalArgumentException();
		}
		this.zaehler = zaehler;
		this.nenner = nenner;
	}
	
	/**
	 * Public method to get the numerator of the fraction
	 * @return Value of zaehler
	 */
	public int getZaehler() {
		return zaehler;
	}
	
	/**
	 * Public Method to get the denominator of the fraction
	 * @return Value of nenner
	 */
	public int getNenner() {
		return nenner;
	}

	/**
	 * Multiply with another fraction and return the result 
	 * @param b The fraction to multiply with
	 * @return Result of the multiplication
	 */
	public Bruch multiplizieren(Bruch b) {
		return new Bruch(zaehler * b.zaehler, nenner * b.nenner);
	}
	
	/**
	 * Calculates the double value for the current fraction
	 * @return fraction value as double
	 */
	public double ausrechnen() {
		return (double) zaehler / nenner;
	}
	
	/**
	 * Shorten the current fraction if possible
	 */
	public void kuerzen() {
		BigInteger i = new BigInteger(String.valueOf(zaehler));
		int gcd = i.gcd(new BigInteger(String.valueOf(nenner))).intValue();
		
		zaehler = zaehler / gcd;
		nenner = nenner / gcd;
		
	}
	
	/**
	 * Create the reciprocal of the current fraction
	 * @return The result of the reciprocal operation
	 * @throws IllegalArgumentException if numerator of the reciprocal equals 0 
	 */
	public Bruch kehrwert() {
		return new Bruch(nenner, zaehler);
	}
	/**
	 * Divide with another fraction and return the result
	 * @param b The fraction to divide with
	 * @return The result of the division. Will be null if b.nenner equals 0
	 */
	public Bruch dividieren(Bruch b) {
		try {
			return multiplizieren(b.kehrwert());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return zaehler + "/" + nenner;
	}
	
	@Override
	public int compareTo(Bruch o) {
		return Double.compare(this.ausrechnen(), o.ausrechnen());
	}
	
	
}
