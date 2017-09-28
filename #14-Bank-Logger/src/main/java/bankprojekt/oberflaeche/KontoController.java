package bankprojekt.oberflaeche;

import bankprojekt.verarbeitung.Girokonto;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KontoController extends Application {
	
	private Girokonto k;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		k = new Girokonto();
		
		Parent root = new KontoOberflaeche(k, this);
		Scene scene = new Scene(root, 400, 275);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Konto Oberfläche");
		primaryStage.show();
	}
	
	/**
	 * Methode zum Einzahlen eines Betrages
	 * @param betrag
	 */
	public void einzahlen(double betrag) {
		k.einzahlen(betrag);
	}
	
	/**
	 * Methode zum Abheben eines Betrages
	 * @param betrag
	 */
	public void abheben(double betrag) {
		try {
			k.abheben(betrag);
		} catch (Exception e) {}
	}
	
	/**
	 * Methode zum sperren / entsperren des Kontos
	 * @param sperren
	 */
	public void kontoSperren(boolean sperren) {
		if (sperren) { 
			k.sperren();
		} else {
			k.entsperren();
		}
	}

}
