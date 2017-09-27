package uebung_9_b;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

/**
 * Statische Klasse zum Schreiben von Streams
 * @author Benny Lach
 *
 */
public class Writer {
	private static PrintWriter pw;
	
	/**
	 * Funktion zum formatierten speichern zweier Variablen
	 * @param i zu formatierender int Wert
	 * @param d zu formatierender double Wert
	 * @param path Ort der zur Speicherung der Datei genutzt werden soll
	 * @throws IOException Datei an Pfad nicht lesbar
	 */
	public static void write(int i, double d, String path) throws IOException {
		pw = getWriter(path);
		
//		1. int reinschreiben
		write("%d",i);
//		2. int mit 10 Stellen schreiben, wenn zu klein -> mit 0 auffuellen
		write("%010d", i);
//		3. int mit Vorzeichen und 1000er-Trenner
		write("%,+d",i);
//		4. double reinschreiben
		write("%f",d);
//		5. double mit Vorzeichen und 2 Nachkommastellen
		write("%+.2f", d);
//		6. double in wissenschaftlicher Darstellung mit Exponent
		write("%e",d);
//		7. double mit 3 Nachkommastellen und 1000er-Trenner (USA)
		write(Locale.US, "%,.3f", d);
//		8. Prozentzeichen schreiben
		write("%%");
//		9. Aktuelles Datum - Wochentag 	(ausgeschrieben), Tag ohne 0, Monat (ausgeschrieben), Jahr (vierstellig)
		write("%tA %1$te. %1$tB %1$tY", LocalDate.now());
//		10. Wie 9. nur in italienisch
		write(Locale.ITALY, "%tA %1$te. %1$tB %1$tY", LocalDate.now());
//		11. Aktuelle Uhrzeit - englisches Format Stunde:Minute am/pm - keine Angbae ueber mit/ohne fuehrende 0 
		write(Locale.ENGLISH, "%tI:%1$tM %1$tp", LocalTime.now());
		
		pw.flush();
		pw.close();
	}
	
	// Benoetigte Abstraktionen zum sich das %n zu sparen
	private static void write(String format, Object... args) {
		write(Locale.getDefault(), format, args);
	}
	
	private static void write(Locale l, String format, Object... args) {
		pw.printf(l, format + "%n", args);
	}
	
	
	private static PrintWriter getWriter(String path) throws IOException {
		FileWriter fr = new FileWriter(path);
		
		return new PrintWriter(fr);
		
	}
}
