package baelle;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Oberfl�che f�r ein Ballspiel
 * @author Doro
 *
 */
public class BallFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Ballspiel spiel;
	
	/**
	 * erstellt eine Oberfl�che mit 4 Buttons
	 */
	public BallFrame() {
		setSize(400, 200);
		setTitle("Tanzende B�lle");
		spiel = new Ballspiel(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		Container contentPane = getContentPane();
		uhrzeit = new JTextField();
		contentPane.add(uhrzeit, BorderLayout.NORTH);
		canvas = new JPanel();
		contentPane.add(canvas, BorderLayout.CENTER);
		JPanel p = new JPanel();
		addButton(p, "Ball starten", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				spiel.ballStarten();
			}
		});

		addButton(p, "Anhalten", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				spiel.baelleStoppen();
			}
		});
		
		addButton(p, "Weiter", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				spiel.baelleWeiter();
			}
		});
		
		addButton(p, "Leeren", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				spiel.alleLoeschen();
			}
		});
		contentPane.add(p, BorderLayout.SOUTH);
	}

	private void zeitAnzeigen() {
		// Anonyme Klasse zum Händeln der Zeitanzeige
		class Uhrzeit extends Thread {
			private JTextField tf;
			
			public void anzeigen(JTextField tf) {
				if(tf == null) {
					throw new IllegalArgumentException("Textfield must be valid");
				}
				this.tf = tf;
				run();
			}
			
			public void run() {
				while(true) {
					DateFormat df = new SimpleDateFormat("HH:mm:ss");
					Date date = new Date();

					this.tf.setText(df.format(date));
					try {
						Thread.currentThread();
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		Uhrzeit zeit = new Uhrzeit();
		zeit.anzeigen(uhrzeit);
	}
	
	private void addButton(Container c, String title, ActionListener a) {
		JButton b = new JButton(title);
		c.add(b);
		b.addActionListener(a);
	}
	
	/**
	 * liefert die Zeichenfl�che von this
	 * @return Zeichenfl�che
	 */
	public JPanel getZeichenflaeche()
	{
		return this.canvas;
	}

	private JPanel canvas;
	private JTextField uhrzeit;
	
	/**
	 * startet die Ballspiel-Oberfl�che und macht sie sichtbar
	 * @param args wird nicht benutzt
	 */
	public static void main(String[] args) {
		BallFrame frame = new BallFrame();
		frame.setVisible(true);
		frame.zeitAnzeigen();
	}
}