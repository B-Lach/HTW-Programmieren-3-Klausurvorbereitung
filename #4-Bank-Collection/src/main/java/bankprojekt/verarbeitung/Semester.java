package bankprojekt.verarbeitung;

import java.time.LocalDate;

/**
 * Klasse, die ein Semester an der Hochschule repräsentiert 
 * @author Benny Lach
 *
 */
public class Semester {
	/**
	 * Enum zur Unterscheidung zw. Sommer- und Wintersemester
	 * @author Benny Lach
	 *
	 */
	public enum SemesterTyp {
		/**
		 * Sommersemester
		 */
		SoSe,
		/**
		 * Wintersemester
		 */
		WiSe;
	}
	
	private LocalDate beginn;
	private LocalDate ende;
	
	private Semester(SemesterTyp typ, int jahr) {
		// Plausibilitätsprüfung des Jahres
		// kein Bestandteil der Aufgabe
		// => Keine Überprüfung von jahr
		if (typ == SemesterTyp.SoSe) {
			this.beginn = LocalDate.of(jahr, 4, 1);
			this.ende = LocalDate.of(jahr, 9, 30);
		} else {
			this.beginn = LocalDate.of(jahr, 10, 1);
			this.ende = LocalDate.of(jahr+1, 3, 31);
		}
	}
	
	/**
	 * Funktion um ein Sommersemester für ein bestimmtes Jahr zu initialisieren
	 * @param jahr Das zu verwendende Jahr
	 * @return Das Semester
	 */
	public static Semester sommer(int jahr) {
		return new Semester(SemesterTyp.SoSe, jahr);
	}
	
	/**
	 * Funktion um ein Wintersemester für ein bestimmtes Jahr zu initialisieren
	 * @param jahr Das zu verwendende Jahr
	 * @return Das Semester
	 */
	public static Semester winter(int jahr) {
		return new Semester(SemesterTyp.WiSe, jahr);
	}
	
	/**
	 * Erstellt, basierend auf das jetzige Datum, das dazugehörige Semester
	 * @return Aktuelles Semester
	 */
	public static Semester aktuell() {
		LocalDate jetzt = LocalDate.now();
		
		if (jetzt.getMonthValue() >= 4 && jetzt.getMonthValue() <= 9) {
			return Semester.sommer(jetzt.getYear());
		}
		return Semester.winter(jetzt.getYear());
	}
	/**
	 * Methode zum Erhhalt des Semesterbeginns
	 * @return Semesterbeginn
	 */
	public LocalDate getSemesterbeginn() {
		return beginn;
	}
	
	/**
	 * Methode zum Erhalt des Semesterendes
	 * @return Semesterende
	 */
	public LocalDate getSemesterende() {
		return ende;
	}
}
