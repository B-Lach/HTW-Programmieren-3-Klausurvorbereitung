package uhr.model;

import java.time.LocalTime;
import java.util.Observable;

/**
* eine Uhr mit Sekundenz�hlung
*/
public class Uhr extends Observable
{
	     
	private Uhrzeit zeit;
	/**
	 * erstellt die Uhr
	 */
    public Uhr() {
		//Thread starten, um die Uhrzeit laufen zu lassen:
		new Thread() {
			/**
			 * l�st jede Sekunde die Aktualisierung der Uhrzeit aus
			 */
			@Override
			public void run() {
				try {
					while (true) {
						laufen();
						Thread.sleep(1000);
					}
				}
				catch (InterruptedException e) {}
			}
		}.start();
    }
    
    public Uhrzeit getUhrzeit() {
    	return this.zeit;
    }  
	
	private void laufen()
	{
		LocalTime jetzt = LocalTime.now();
		
		zeit = new Uhrzeit(jetzt.getHour(), jetzt.getMinute(), jetzt.getSecond());
		
		this.setChanged();
		this.notifyObservers(zeit);
	}

}
