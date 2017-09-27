import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import bankprojekt.verarbeitung.Student;
import bankprojekt.verarbeitung.Semester;

/**
 * Test cases for Student.class
 * @author Benny Lach
 *
 */
public class StudentTest {

	/**
	 * Testet nicht erlaubte Übergabe von null als Hochschule
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_invalidInitNoUniversity() {
		Student s = new Student(null, "AI", LocalDate.of(2019, 3, 31),
				"Max", "Mustermann", "hier", LocalDate.of(1987, 4, 24));
	}
	
	/**
	 * Testet nicht erlaubte Übergabe von null als Studienfach
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_invalidInitNoCourse() {
		Student s = new Student("HTW Berlin", null, LocalDate.of(2019, 3, 31),
				"Max", "Mustermann", "hier", LocalDate.of(1987, 4, 24));
	}
	
	/**
	 * Testet nicht erlaubte Übergabe von null als Studienende
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_invalidInitNoEndDate() {
		Student s = new Student("HTW Berlin", "AI", null,
				"Max", "Mustermann", "hier", LocalDate.of(1987, 4, 24));
	}
//	Exceptions von Konto.class brauchen an der Stelle nicht überprüft werden. 
//	Check der einzelnene Variablen geerbt von Konto sollte für Funktionalität reichen 
	
	/**
	 * Testet valide Initialisierung
	 */
	@Test
	public void test_validInit() {
		Student s = new Student("HTW Berlin", "AI", LocalDate.of(2019, 3, 31),
				"Max", "Mustermann", "hier", LocalDate.of(1987, 4, 24));
		
		assertTrue("Uni ist nicht korrekt", s.getHochschule().equals("HTW Berlin"));
		assertTrue("Studiengang ist nicht korrekt", s.getStudienfach().equals("AI"));
		assertTrue("Studienende ist nicht korrekt", s.getStudienende().compareTo(LocalDate.of(2019, 3, 31)) == 0);
		assertTrue("Vorname ist nicht korrekt", s.getVorname().equals("Max"));
		assertTrue("Nachname ist nicht korrekt", s.getNachname().equals("Mustermann"));
		assertTrue("Adresse ist nicht korrekt", s.getAdresse().equals("hier"));
		assertTrue("Geburtsdatum ist nicht korrekt", s.getGeburtstag().compareTo(LocalDate.of(1987, 4, 24)) == 0);
	}
	
	/**
	 * Überprüft Semestrebescheinigung nach Initialisierung
	 */
	@Test
	public void test_beschreibungAfterInit() {
		Student s = new Student("HTW Berlin", "AI", LocalDate.of(2019, 3, 31), 
				"Max", "Mustermann", "hier", LocalDate.of(1987, 4, 24));
		
		assertFalse("Student sollte nach Initialisierung keine aktuelle Bescheinigung besitzen",
				s.hatAktuelleBescheinigung());
	}
	
	/**
	 * Überprüft setzten von aktueller Bescheinigung
	 */
	@Test
	public void test_validBescheinigung() {
		Student s = new Student("HTW Berlin", "AI", LocalDate.of(2019, 3, 31), 
				"Max", "Mustermann", "hier", LocalDate.of(1987, 4, 24));
		s.aktualisiereBescheinigung();
		
		assertTrue("Student sollte aktuelle Bescheinigung besitzen", s.hatAktuelleBescheinigung());
	}
	
	/**
	 * Überprüft veraltete Semesterbescheinigung
	 */
	@Test
	public void test_invalidBescheinigung() {
		StudentMock s = new StudentMock("HTW Berlin", "AI", LocalDate.of(2019, 3, 31), 
				"Max", "Mustermann", "hier", LocalDate.of(1987, 4, 24));
		s.setBescheinigung(Semester.sommer(2012));
		
		assertFalse("Student sollte keine aktuelle Bescheinigung besitzen", s.hatAktuelleBescheinigung());
	}
	
	/**
	 * Überprüft Student.toString()
	 */
	@Test
	public void test_toString() {
		Student s = new Student("HTW Berlin", "AI", LocalDate.of(2019, 3, 31), 
				"Max", "Mustermann", "hier", LocalDate.of(1987, 4, 24));
		
		String expected = "HTW Berlin-AI"+ System.getProperty("line.separator");
		expected += "Vorraussichtliches Ende: 31.03.19" + System.getProperty("line.separator");
		expected += "Max Mustermann" + System.getProperty("line.separator");
		expected += "hier" + System.getProperty("line.separator");
		expected += "24.04.87" + System.getProperty("line.separator");
		
		assertEquals("Student.toString() ist nicht korrekt", expected, s.toString()); 
	}
}
