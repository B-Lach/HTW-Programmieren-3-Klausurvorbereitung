package bankprojekt.oberflaeche;

import bankprojekt.verarbeitung.Girokonto;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Eine Oberfl�che f�r ein einzelnes Konto. Man kann einzahlen
 * und abheben und sperren und die Adresse des Kontoinhabers 
 * �ndern
 * @author Doro
 *
 */
public class KontoOberflaeche extends BorderPane {
	private Girokonto k;
	
	private Text ueberschrift;
	private GridPane anzeige;
	private Text txtNummer;
	/**
	 * Anzeige der Kontonummer
	 */
	private Text nummer;
	private Text txtStand;
	/**
	 * Anzeige des Kontostandes
	 */
	private Text stand;
	private Text txtGesperrt;
	/**
	 * Anzeige und �nderung des Gesperrt-Zustandes
	 */
	private CheckBox gesperrt;
	private Text txtAdresse;
	/**
	 * Anzeige und �nderung der Adresse des Kontoinhabers
	 */
	private TextArea adresse;
	/**
	 * Anzeige von Meldungen �ber Kontoaktionen
	 */
	private Text meldung;
	private HBox aktionen;
	/**
	 * Auswahl des Betrags f�r eine Kontoaktion
	 */
	
	private Spinner<Double> betrag;
	/**
	 * l�st eine Einzahlung aus
	 */
	private Button einzahlen;
	/**
	 * l�st eine Abhebung aus
	 */
	private Button abheben;
	
	/**
	 * erstellt die Oberfl�che
	 */
	public KontoOberflaeche(Girokonto k, KontoController kc)
	{
		this.k = k;
		ueberschrift = new Text("Ein Konto verändern");
		ueberschrift.setFont(new Font("Sans Serif", 25));
		BorderPane.setAlignment(ueberschrift, Pos.CENTER);
		this.setTop(ueberschrift);
		
		anzeige = new GridPane();
		anzeige.setPadding(new Insets(20));
		anzeige.setVgap(10);
		anzeige.setAlignment(Pos.CENTER);
		
		txtNummer = new Text("Kontonummer:");
		txtNummer.setFont(new Font("Sans Serif", 15));
		anzeige.add(txtNummer, 0, 0);
		nummer = new Text(k.getKontonummerFormatiert());
		nummer.setFont(new Font("Sans Serif", 15));
		GridPane.setHalignment(nummer, HPos.RIGHT);
		anzeige.add(nummer, 1, 0);
		
		txtStand = new Text("Kontostand:");
		txtStand.setFont(new Font("Sans Serif", 15));
		anzeige.add(txtStand, 0, 1);
		
		stand = new Text(k.getKontostandProperty().toString());
		stand.setFont(new Font("Sans Serif", 15));
		stand.textProperty().bind(k.getKontostandProperty().asString());
		
		GridPane.setHalignment(stand, HPos.RIGHT);
		anzeige.add(stand, 1, 1);
		
		txtGesperrt = new Text("Gesperrt: ");
		txtGesperrt.setFont(new Font("Sans Serif", 15));
		anzeige.add(txtGesperrt, 0, 2);
		
		gesperrt = new CheckBox();
		gesperrt.selectedProperty().bindBidirectional(k.getGesperrtProperty());
		GridPane.setHalignment(gesperrt, HPos.RIGHT);
		anzeige.add(gesperrt, 1, 2);
		
		txtAdresse = new Text("Adresse: ");
		txtAdresse.setFont(new Font("Sans Serif", 15));
		
		anzeige.add(txtAdresse, 0, 3);
		adresse = new TextArea(k.getInhaber().getAdresse());
		adresse.textProperty().bindBidirectional(k.getInhaber().getAdressProperty());
		adresse.setPrefColumnCount(25);
		adresse.setPrefRowCount(2);
		
		GridPane.setHalignment(adresse, HPos.RIGHT);
		anzeige.add(adresse, 1, 3);
		
		meldung = new Text("Willkommen lieber Benutzer");
		meldung.setFont(new Font("Sans Serif", 15));
		meldung.setFill(Color.RED);
		anzeige.add(meldung,  0, 4, 2, 1);
		
		this.setCenter(anzeige);
		
		aktionen = new HBox();
		aktionen.setSpacing(10);
		aktionen.setAlignment(Pos.CENTER);
		
		// An dieser Stelle Integer Werte zu nutzen sorgt für eine ClassCastException zur Runtime
//		betrag = new Spinner<>(10, 100, 50, 10);
		betrag = new Spinner<>(10.0, 100.0, 50.0, 10.0);
		aktionen.getChildren().add(betrag);
		einzahlen = new Button("Einzahlen");
		einzahlen.setOnAction( e -> kc.einzahlen(betrag.getValue().doubleValue()) );
		aktionen.getChildren().add(einzahlen);
		
		abheben = new Button("Abheben");
		abheben.setOnAction(e -> kc.abheben(betrag.getValue()) );
		aktionen.getChildren().add(abheben);
		
		this.setBottom(aktionen);
	}
}
