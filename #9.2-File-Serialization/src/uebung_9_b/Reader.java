package uebung_9_b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Statische Klasse zum Lesen von Streams
 * @author Benny Lach
 *
 */
public class Reader {
	
	/**
	 * Funktion zum Lesen einer Datei
	 * @param path Der Pfad zur Datei
	 * @throws IOException Datei an path konnte nicht gelesen werden
	 */
	public static void readFile(String path) throws IOException {
		FileReader fr = new FileReader(path);
		LineNumberReader lnr = new LineNumberReader(fr);
		
		while(lnr.ready()) {
			
			System.out.printf("%02d: %s%n", lnr.getLineNumber() + 1, lnr.readLine());
		}
		lnr.close();
	}
	
	/**
	 * Funktion zum Lesen eines Integers
	 * @param input InputStream, aus dem gelesen werden soll
	 * @return int
	 * @throws IOException InputStream konnte nicht gelesen werden
	 * @throws NumberFormatException Gelesenes war kein int 
	 */
	public static int readInteger(InputStream input) throws IOException, NumberFormatException {
		String res = readLine(input);
		
		return new Integer(res).intValue();
	}
	
	/**
	 * Funktion zum Lesen eines Doubles
	 * @param input InputStream, aus dem gelesen werden soll
	 * @return double
	 * @throws IOException InputStream konnte nicht gelesen werden
	 * @throws NumberFormatException Gelesenes war kein double
	 */
	public static double readDouble(InputStream input) throws IOException, NumberFormatException {
		String res = readLine(input);
		
		return new Double(res).doubleValue();
	}
	
	/**
	 * Funktion zum Lesen einer Zeile
	 * @param input InputStream, aus dem gelesen werden soll
	 * @return String
	 * @throws IOException InputStream konnte nicht gelesen werden
	 */
	private static String readLine(InputStream input) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String line = br.readLine();
		
		return line;
	}
}
