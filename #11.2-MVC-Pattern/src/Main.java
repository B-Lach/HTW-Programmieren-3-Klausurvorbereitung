import uhr.controller.UhrController;
import uhr.controller.UhrController.UhrenTyp;

public class Main {
	
	
	public static void main(String[] args) {
		analog();
		digital();
	}
	
	private static void analog() {
		new UhrController(UhrenTyp.ANALOG);
	}

	private static void digital() {
		new UhrController(UhrenTyp.DIGITAL);
	}
}
