package uhr.view;

import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import uhr.model.Uhrzeit;

/**
 * Stellt eine Digitale Uhr dar, die man anhalten und weiterlaufen lassen kann
 *
 */
public class DigitalUhr extends JFrame implements UhrInterface  
{
	private static final long serialVersionUID = 1L;
	private static final String TITEL = "Digitaluhr";
	private static final String KNOPF_EIN = "Ein";
	private static final String KNOPF_AUS = "Aus";
	private static final int BREITE = 400;
	private static final int HOEHE = 300;

	private JLabel anzeige;
	private JButton[] knoepfe; // Ein = Einschalten der Anzeige, 
							  // Aus = Ausschalten der Anzeige, 
	/**
	 * erstellt das Fenster f�r die digitale Uhr und bringt es auf den
	 * Bildschirm; zu Beginn l�uft die Uhr im 1-Sekunden-Takt
	 */
	public DigitalUhr(ActionListener al) {

		// Erstellung der Oberfl�chenelemente:
		setTitle(TITEL);
		setSize(BREITE, HOEHE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		add(new JLabel(TITEL));
		anzeige = new JLabel();
		add(anzeige);
		knoepfe = new JButton[2];
		knoepfe[0] = new JButton(KNOPF_EIN);
		knoepfe[1] = new JButton(KNOPF_AUS);
		for (JButton knopf : knoepfe) {
			super.add(knopf);
		}
		knoepfe[0].setEnabled(false); // "Ein"

		// Zuf�gen des ActionListeners zu den Buttons
		for (JButton knopf : knoepfe)
		{
			knopf.addActionListener(al);
		}
		
		pack();
		setVisible(true);
	}

	@Override
	public void aktualisiereUhrzeit(Uhrzeit zeit) {
		anzeige.setText(String.format("%02d:%02d:%02d", zeit.getStunde(), zeit.getMinute(),
				zeit.getSekunde()));
	}
	
	@Override
	public void aktualisiereButtonsFallsNötig(boolean an) {
		knoepfe[0].setEnabled(!an);
		knoepfe[1].setEnabled(an);
	}
}
