package baelle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Steuerungsklasse f�r eine Ball-Animation
 * @author Doro
 *
 */
public class Ballspiel {
	
	private BallFrame f;
	private ArrayList<Ball> baelle = new ArrayList<Ball>();
	
	/**
	 * erstellt die Steuerungsklasse f�r die angegebene Oberfl�che
	 * @param f
	 */
	public Ballspiel(BallFrame f)
	{
		this.f = f;
	}
	
	/**
	 * startet einen Ball auf der Oberfl�che und l�sst ihn h�pfen
	 */
	public void ballStarten()
	{
		
		Random r = new Random();
		int dauer = r.nextInt(500) + 1000; //Zufallszahl zwischen 1000 und 1500
		Ball b = new Ball(f.getZeichenflaeche());
		b.huepfen(dauer);
		
		baelle.add(b);
	}
	
	/**
	 * h�lt alle B�lle auf der Oberfl�che an, so dass sie an ihrer aktuellen Position
	 * stehen bleiben
	 */
	public void baelleStoppen()
	{
		clearDeadBallsAndExecute(b -> b.pausieren());
	}

	/**
	 * l�sst alle angehaltenen B�lle wieder weiter h�pfen
	 */
	public void baelleWeiter() {
		clearDeadBallsAndExecute(b -> b.weiter());
	}

	/**
	 * l�scht alle B�lle von der Oberfl�che
	 */
	public void alleLoeschen() {
		clearDeadBallsAndExecute(b -> b.beenden());
		baelle.clear();
	}
	
	/**
	 * Entfernt alle "toten" Baelle und fuehrt für valide BallExecuter aus 
	 * @param e BallExecuter
	 */
	private void clearDeadBallsAndExecute(BallExecuter e) {
		ArrayList<Ball> dead = new ArrayList<Ball>();
		
		for (Ball b: baelle) {
			// Alle toten Threads merken
			if(!b.isAlive()) {dead.add(b);}
			// Uebergebenen Executer ausführen
			else { e.execute(b);}
		}
		// Alle toten Baelle aus Liste entfernen
		for(Ball d: dead) {baelle.remove(d);}
		// Ünnotigen Speicher freigeben
		dead = null;
	}
	
	/**
	 * Interface, welches für Lambda Expressions genutzt wird
	 * @author Benny Lach
	 *
	 */
	private interface BallExecuter {
		void execute(Ball b);
	}
}




