

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import bankprojekt.verarbeitung.Semester;

/**
 * Test cases für Semester.class
 * @author Benny Lach
 *
 */
public class SemesterTest {

	/**
	 * Testet Daten für SoSe
	 */
	@Test
	public void test_SommerSemester() {
		LocalDate begin = LocalDate.of(2017, 4, 1);
		LocalDate end = LocalDate.of(2017, 9, 30);
		
		Semester s = Semester.sommer(2017);
		
		assertEquals("Semesterbeginn Sommer ist nicht korrekt", s.getSemesterbeginn(), begin);
		assertEquals("Semesterende Sommer ist nicht korrekt", s.getSemesterende(), end);
	}
	
	/**
	 * Testet Daten für WiSe
	 */
	@Test
	public void test_WinterSemester() {
		LocalDate begin = LocalDate.of(2017, 10, 1);
		LocalDate end = LocalDate.of(2018, 3, 31);
		
		Semester s = Semester.winter(2017);
		
		assertEquals("Semesterbeginn Winter ist nicht korrekt", s.getSemesterbeginn(), begin);
		assertEquals("Semesterende Winter ist nicht korrekt", s.getSemesterende(), end);
	}
	
	/**
	 * Testet Daten für aktuelles Semester
	 */
	@Test
	public void test_aktuellesSemester() {
		Semester s = Semester.aktuell();
		
		LocalDate now = LocalDate.now();
		LocalDate begin;
		LocalDate end;
		
		if(now.getMonthValue() >= 4 && now.getMonthValue() <= 9) {
			begin = LocalDate.of(now.getYear(), 4, 1);
			end = LocalDate.of(now.getYear(), 9, 30);
		} else {
			begin = LocalDate.of(now.getYear(), 10, 1);
			end = LocalDate.of(now.getYear()+1, 3, 31);
		}
		
		assertEquals("Begin für aktuelles Semester nicht korrekt", begin, s.getSemesterbeginn());
		assertEquals("Ende für aktuelles Semester nicht korrekt", end,s.getSemesterende());
	}

}
