package bankprojekt.verarbeitung;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Klasse, die einen Studenten repräsentiert
 * @author Benny Lach
 *
 */
public class Student extends Kunde {
	
	private String hochschule;
	private String studienfach;
	private LocalDate studienende;
	protected Semester bescheinigung = null;
	
	/**
	 * Default Konstruktor
	 * @param hochschule Name der Hochschule
	 * @param studienfach Name des Studienfachs
	 * @param studienende Vorraussichtliches Studienende
	 * @param vorname Vorname des Studenten
	 * @param nachname Nachname des Studenten
	 * @param adresse Addresse des Studenten
	 * @param gebdat Geburtstag des Studenten
	 * @throws IllegalArgumentException wenn einer der Parameter null ist
	 */
	public Student(String hochschule,String studienfach, 
			LocalDate studienende, String vorname,
			String nachname, String adresse,
			LocalDate gebdat) throws IllegalArgumentException {
		
		super(vorname, nachname, adresse, gebdat);
		
		if (hochschule == null || studienfach == null || studienende == null) {
			throw new IllegalArgumentException("Null ist kein valider Parameter");
		}
		
		this.hochschule = hochschule;
		this.studienfach = studienfach;
		this.studienende = studienende;
	}
	
	/**
	 * Name der Hochschule
	 * @return Name als String
	 */
	public String getHochschule() {
		return hochschule;
	}
	
	/**
	 * Studienfach des Studenten
	 * @return Fach als String
	 */
	public String getStudienfach() {
		return studienfach;
	}
	
	/**
	 * Vorraussichtliches Studienende
	 * @return Studienende als LocalDate
	 */
	public LocalDate getStudienende() {
		return studienende;
	}
	
	/**
	 * Methode zum Abruf, ob aktuelle Semesterbescheinigung vorliegt
	 * 
	 * @return Boolean - true wenn vorhanden, sonst false
	 */
	public boolean hatAktuelleBescheinigung() {
		if (bescheinigung == null) { return false; }
		
		LocalDate beginn = bescheinigung.getSemesterbeginn();
		LocalDate ende = bescheinigung.getSemesterende();
		LocalDate jetzt = LocalDate.now();
		
		return jetzt.compareTo(beginn) >= 0 && jetzt.compareTo(ende) <= 0;
	}
	
	/**
	 * Setzt eine vorhandene Bescheinigung für das aktuelles Semester
	 */
	public void aktualisiereBescheinigung() {
		bescheinigung = Semester.aktuell();
	}
	
	@Override
	public String toString() {
		DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		
		String ausgabe = this.hochschule + "-" + this.studienfach + System.getProperty("line.separator");
		ausgabe += "Vorraussichtliches Ende: " + df.format(this.studienende) + System.getProperty("line.separator");
		
		return ausgabe + super.toString();
	}
 }
