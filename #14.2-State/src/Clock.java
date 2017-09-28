/**
 * eine Uhr
*/
public class Clock {
	private int hr;
	private int min;
	
	private ClockState state = new NormalState();
	
    /**
     * erzeugt eine Uhr, die die Uhrzeit 0:0 anzeigt
     */
	public Clock(){
		hr =0;
		min = 0;
	}
	
	void setState(ClockState newState) {
		state = newState;
	}
    /**
     * gibt die aktuelle Urzeit auf der Konsole aus
     */
	public void showTime() {
	    System.out.println("Current time is Hr : " + hr + " Min: " + min);
	}
	
    /**
     * Uhr um eine Stunde vorstellen
     */
	public void changeHour() {
	    hr++;
	    if (hr == 24)
	        hr = 0;
	    System.out.println("CHANGE pressed - ");
	    showTime();
	}
	
    /**
     * Uhr um eine Minute vorstellen
     */
	public void changeMinute() {
	    min++;
	    if (min == 60)
	        min = 0;
	    System.out.println("CHANGE pressed - ");
	    showTime();
	}
	
    /**
     * simuliert den Druck auf den Change-Knopf der Uhr. Im Normal-Modus wird die Uhrzeit mit eingeschaltetem Licht
     * angezeigt, im �nderungsmodus wird die Uhr um eine Stunde bzw. um eine Minute vorgestellt.
     */
	public void changeButton() {
		state.changeButtonAction(this);
	}
	
    /**
     * simuliert den Knopf auf den Mode-Knopf der Uhr. Er wechselt zwischen dem Normalmodus, dem �nderungsmodus f�r
     * die Stunde und dem �nderungsmodus f�r die Minute.
     */
	public void modeButton() {
		state.modeButtonAction(this);
	}
	
    /**
     * Anzeige der aktuellen Uhrzeit mit Licht
     */
	public void displayTimeWithLight() {
		System.out.println("LIGHT ON: ");
	    showTime();
	}
}
