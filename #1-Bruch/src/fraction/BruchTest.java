package fraction;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit testing Bruch class
 * @author Benny Lach
 *
 */
public class BruchTest {

	/**
	 * Testing values after init
	 * @throws IllegalArgumentException wenn nenner 0
	 */
	@Test
	public void test_validInstance() throws IllegalArgumentException {
		Bruch b = new Bruch(1, 4);
		
		assertTrue("Numerator does not equals 1", b.getZaehler() == 1);
		assertTrue("Denominator does not equals 4", b.getNenner() == 4);
	}
	
	/**
	 * Testing correctly excpetion handling if denominator equals 0
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_invalidInstance() {
		Bruch b = new Bruch(4, 0);
	}
	
	/**
	 * Testing toString() method
	 */
	@Test
	public void test_fractionToSring() {
		Bruch b = new Bruch(7, 8);
		
		assertTrue("7/8".compareTo(b.toString()) == 0);
	}
	
	/**
	 * Testing result of multiplication 
	 */
	@Test
	public void test_multiplyFractions() {
		Bruch b = new Bruch(1, 4);
		Bruch result = b.multiplizieren(new Bruch(3, 8));
		
		assertTrue("Numerator does not equals 3", result.getZaehler() == 3);
		assertTrue("Denominator does not equals 32", result.getNenner() == 32);
	}
	
	/**
	 * Testing calculating double value 
	 */
	@Test
	public void test_calcDoubleValue() {
		Bruch b = new Bruch(4, 5);
		assertTrue("Double value does not eqauls to 0.8", b.ausrechnen() == 0.8);
	}
	
	/**
	 * Testing correctly shorting of a fraction
	 */
	@Test
	public void test_validShorten() {
		Bruch b = new Bruch(2, 4);
		b.kuerzen();
		
		assertTrue("Numerator does not equals 1", b.getZaehler() == 1);
		assertTrue("Denominator does not equals 2", b.getNenner() == 2);
	}
	
	/**
	 * Testing exception handling if reciprocals denominator equals 0 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_invalidReciprocal() {
		Bruch b = new Bruch(0, 4);
		b.kehrwert();
	}
	
	/**
	 * Testing result of reciprocal calculation
	 * @throws IllegalArgumentException if dniminator of reciprocal eqauls 0
	 */
	@Test
	public void test_validReciprocal() throws IllegalArgumentException {
		Bruch b = new Bruch(1, 4);
		b = b.kehrwert();

		assertTrue("Numerator does not equals 4", b.getZaehler() == 4);
		assertTrue("Denominator does not equals 1", b.getNenner() == 1);
	}
	
	/**
	 * Testing result of invalid division
	 */
	@Test
	public void test_invalidDivision() {
		Bruch b = new Bruch(3, 5);
		b = b.dividieren(new Bruch(0, 4));
		
		assertNull("Result of the division is not null", b);
	}
	
	/**
	 * Testing result of valid division
	 */
	@Test
	public void test_validDivision() {
		Bruch b = new Bruch(3, 5);
		b = b.dividieren(new Bruch(1, 4));

		assertTrue("Numerator does not eqauls 12", b.getZaehler() == 12);
		assertTrue("Denominator does not equals 5", b.getNenner() == 5);
	}
	
	/**
	 * Testing comparsion of fraction instances
	 */
	@Test
	public void test_compareInstances() {
		Bruch b = new Bruch(1, 4);
		
		assertTrue("Fractions should be equal", b.compareTo(new Bruch(2, 8)) == 0);
		assertTrue("b should be bigger", b.compareTo(new Bruch(1, 5)) > 0);
		assertTrue("b should be smaller", b.compareTo(new Bruch(3, 4)) < 0);
	}

}
