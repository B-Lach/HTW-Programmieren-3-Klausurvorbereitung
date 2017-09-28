package bankprojekt.verarbeitung.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Logger {
	private final static String PATH = "logger/log";
	private static Logger shared;
	
	public static Logger getInstance() throws IOException {
		if (Logger.shared == null) {
			Logger.shared = new Logger();
		}
		return Logger.shared;
	}
	
	private PrintWriter writer;
	
	private Logger() throws IOException {
		setupWriter();
	}
	
	private void setupWriter() throws IOException {
		FileWriter fr = new FileWriter(Logger.PATH);
		
		writer = new PrintWriter(fr);
	}
	
	public void log(Exception e) {
		String date = String.format("%tA %1$te. %1$tB %1$tY", LocalDate.now());
		String time = String.format("%tI:%1$tM %1$tp", LocalTime.now());

		writer.write(date + " " + time + ": " + e.getMessage());
		writer.flush();
	}
}
