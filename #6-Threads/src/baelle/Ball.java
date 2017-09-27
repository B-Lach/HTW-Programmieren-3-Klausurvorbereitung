package baelle;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * ein h�pfender Ball
 * @author Doro
 *
 */
public class Ball extends Thread {
	// Lock object zum pausieren/fortsetzen des Threads
	private Object lock = new Object();
	// Dauer, die der Ball hüpfen soll
	private int dauer;
	// Boolean, um pausieren festzustellen
	private boolean pausieren = false;
	// Boolean, um loeschen festzustellen
	private boolean loeschen = false;
	
	/**
	 * erstellt einen Ball, der in das angegebene Panel gezeichnet wird
	 * @param b Die Zeichenfl�che
	 */
	public Ball(JPanel b) {
		box = b;
	}

	/**
	 * zeichnet den Ball an seiner aktuellen Position
	 */
	public void draw() {
		Graphics g = box.getGraphics();
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose();
	}
	
	/**
	 * l�scht den Ball von der Oberfl�che
	 */
	private void loeschen()
	{
		Graphics g = box.getGraphics();
		g.setXORMode(box.getBackground());
		g.fillOval(x, y, XSIZE, YSIZE);
	}

	/**
	 * bewegt den Ball einen Schritt weiter
	 */
	public void move() {
		if (!box.isVisible())
			return;
		Graphics g = box.getGraphics();
		g.setXORMode(box.getBackground());
		g.fillOval(x, y, XSIZE, YSIZE);
		x += dx;
		y += dy;
		Dimension d = box.getSize();
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		if (x + XSIZE >= d.width) {
			x = d.width - XSIZE;
			dx = -dx;
		}
		if (y < 0) {
			y = 0;
			dy = -dy;
		}
		if (y + YSIZE >= d.height) {
			y = d.height - YSIZE;
			dy = -dy;
		}
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose();
	}
	
	/**
	 * bewegt den Ball dauer viele Schritte weiter in der Oberfl�che. Um eine angenehme Animation
	 * zu erhalten, wird nach jedem Schritt eine Pause eingelegt.
	 * @param dauer Anzahl der Schritte
	 */
	public void huepfen(int dauer)
	{
		this.dauer = dauer;
		this.start();
	}
	
	/**
	 * Pausiert den Ball
	 */
	public void pausieren() {
		pausieren = true;
	}
	
	/**
	 * Löscht den Ball
	 */
	public void beenden() {
		loeschen = true;
		// Wenn Thread pausiert, benachrichtige diesen um Execution fortzuführen
		weiter();
	}
	
	/**
	 * Bewegt pausierten Ball
	 */
	public void weiter() {
		if (pausieren) {
			pausieren = false;
			
			synchronized(lock) { lock.notify(); }
		}
	}
	
	/**
	 * Run Methode
	 */
	public void run() {
		this.draw();
		
		for (int i = 1; i <= dauer; i++) {
			// Pausiere Thread, wenn pausieren true
			while (pausieren) {
				synchronized(lock) {
					try { lock.wait(); } 
					catch (Exception e) {}
				}
			}
			// Verlasse loop, wenn loeschen true
			if(loeschen) { break; }
			this.move();
				
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {}
		}
		this.loeschen();
	}
	

	private JPanel box;
	private static final int XSIZE = 10;
	private static final int YSIZE = 10;
	private int x = 0;
	private int y = 0;
	private int dx = 2;
	private int dy = 2;
}