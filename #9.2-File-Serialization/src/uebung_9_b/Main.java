package uebung_9_b;

import java.io.*;

/**
 * Einstiegspunkt
 * @author Benny Lach
 *
 */
public class Main {
	private final static String PATH = "resources/output.txt";
	
	public static void main(String[] args) {
		int i = getIntegerInput();
		double d = getDoubleInput();
		
		try {
			Writer.write(i, d, Main.PATH);
			Reader.readFile(Main.PATH);
		} catch(Exception e) {e.printStackTrace();}
	}

	/**
	 * Funktion zum Erhalt einer int Eingabe
	 * @return int
	 */
	private static int getIntegerInput() {
		System.out.println("Dezimalzahl eingeben");

		while (true) {
			try {
				return Reader.readInteger(System.in);
			} catch (NumberFormatException | IOException e) {}
		}
	}
	
	/**
	 * Funktion zum Erhalt einer double Eingabe
	 * @return double
	 */
	private static double getDoubleInput() {
		System.out.println("Fließkommazahl eingeben");

		while (true) {
			try {
				return Reader.readDouble(System.in);
			} catch (NumberFormatException | IOException e) {}
		}
	}
}
