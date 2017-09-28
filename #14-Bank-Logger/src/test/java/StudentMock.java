import java.time.LocalDate;

import bankprojekt.verarbeitung.Semester;
import bankprojekt.verarbeitung.Student;

class StudentMock extends Student {

	public StudentMock(String hochschule, String studienfach, LocalDate studienende, String vorname, String nachname,
			String adresse, LocalDate gebdat) throws IllegalArgumentException {
		super(hochschule, studienfach, studienende, vorname, nachname, adresse, gebdat);
		// TODO Auto-generated constructor stub
	}
	
	public void setBescheinigung(Semester bescheinigung) {
		this.bescheinigung = bescheinigung;
	}

}
