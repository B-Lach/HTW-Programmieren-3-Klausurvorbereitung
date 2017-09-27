package uhr.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;

import uhr.model.Uhrzeit;

/**
 * stellt eine analoge Uhr dar
 * 
 * @author Doro
 *
 */
public class KreisUhr extends JFrame implements UhrInterface 
{
	private static final long serialVersionUID = 1L;
	private static final String TITEL = "Kreisuhr";
	private static final String INFO = "Tasten: A(usschalten), E(inschalten)";
	private static final int BREITE = 400;
	private static final int HOEHE = 300;
	private static final int ZENTRUM_X = BREITE / 2;
	private static final int ZENTRUM_Y = HOEHE / 2;
	private static final int RADIUS = 100;
	private static final int DURCHMESSER = 2 * RADIUS;
	private static final int POS_INFO_X = 20;
	private static final int POS_INFO_Y = 40;
	private static final int POS_UHRZEIT = 60;
	private static final int[] ZEIGERLAENGE = new int[] { 5 * RADIUS / 10, 6 * RADIUS / 10, 7 * RADIUS / 10 }; // L�ngen der 3 Zeiger
	private static final Color[] ZEIGERFARBE = new Color[] { Color.red, Color.green, Color.blue }; // Farben der 3 Zeiger
	private static final Color HINTERGRUND_FARBE = Color.white;
	private static final Color KREIS_FARBE = Color.black;
	private static final int[][] END_X = new int[3][60];
	private static final int[][] END_Y = new int[3][60];
	private static final double ZWEI_PI = 2 * Math.PI;
	private static final double[] KONST = new double[] { ZWEI_PI / 12, ZWEI_PI / 60, ZWEI_PI / 60 };

	private Uhrzeit uhrzeit;

	/**
	 * erstellt die analoge Uhr und bringt sie auf den Bildschirm
	 */
	public KreisUhr(KeyAdapter ka) {
		// Erstellen der Oberfl�chenelemente:
		setTitle(TITEL);
		setSize(BREITE, HOEHE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Einrichten des KeyListeners, d.h. die Uhr reagiert auf Tastendruck
		this.addKeyListener(ka);
	}

	/**
	 * Analoge Uhr zeichnen
	 */
	@Override 
	public void paint(Graphics g) {
		g.clearRect(0, 0, BREITE, HOEHE);
		g.setColor(HINTERGRUND_FARBE);
		g.fillRect(0, 0, BREITE, HOEHE);
		g.setColor(KREIS_FARBE);
		g.drawOval((BREITE - DURCHMESSER) / 2, (HOEHE - DURCHMESSER) / 2, DURCHMESSER, DURCHMESSER); // Kreis
		g.drawString(INFO , POS_INFO_X, POS_INFO_Y);

		if (uhrzeit != null) {
			final int[] zeit = new int[] { uhrzeit.getStunde(), uhrzeit.getMinute(), uhrzeit.getSekunde() };
			
			g.drawString(String.format("%02d:%02d:%02d", zeit[0], zeit[1], zeit[2]), POS_INFO_X, POS_UHRZEIT); // Uhrzeit digital
			
			for (int i = 0; i < END_X.length; i++) { // f�r jeden Zeiger
				final int z = zeit[i]; // Stunde, Minute oder Sekunde
				if (END_X[i][z] == 0) { // wurde noch nicht berechnet
					final double grad = z * KONST[i];
					END_X[i][z] = (int) (ZENTRUM_X + ZEIGERLAENGE[i] * Math.sin(grad));
					END_Y[i][z] = (int) (ZENTRUM_Y - ZEIGERLAENGE[i] * Math.cos(grad));
				}
				g.setColor(ZEIGERFARBE[i]);
				g.drawLine(ZENTRUM_X, ZENTRUM_Y, END_X[i][zeit[i]], END_Y[i][zeit[i]]);
			}
		}
	}

	@Override
	public void aktualisiereUhrzeit(Uhrzeit zeit) {
		uhrzeit = zeit;
		repaint();
	}
}