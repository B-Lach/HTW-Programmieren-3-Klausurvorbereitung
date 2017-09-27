package uhr.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Observable;
import java.util.Observer;

import uhr.model.Uhr;
import uhr.model.Uhrzeit;
import uhr.view.DigitalUhr;
import uhr.view.KreisUhr;
import uhr.view.UhrInterface;

public class UhrController extends KeyAdapter implements Observer, ActionListener {
	private static final String KNOPF_EIN = "Ein";
	private static final String KNOPF_AUS = "Aus";
	
	private UhrInterface view;
	private Uhr uhr;
	
	private boolean update;
	
	public enum UhrenTyp {
		ANALOG,
		DIGITAL
	}
	
	public UhrController(UhrenTyp type) {
		if (type == null) {
			throw new IllegalArgumentException("Uhrentyp muss valide sein");
		}
		
		update = true;
		
		if (type == UhrenTyp.ANALOG) {
			view = new KreisUhr(this);  
		} else {
			view = new DigitalUhr(this);
		}

		uhr = new Uhr();
		uhr.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (update && arg.getClass() == Uhrzeit.class) {
			view.aktualisiereUhrzeit((Uhrzeit) arg);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Received event: " + e);

		switch (e.getActionCommand()) {
		case KNOPF_EIN:
			update = true;
			break;
		case KNOPF_AUS:
			update = false;
			break;
		}
		view.aktualisiereButtonsFallsNötig(update);		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (Character.toUpperCase(e.getKeyChar())) {
		case 'E':
			update = true;
			break; // "Ein"
		case 'A':
			update = false;
			break; // "Aus"
		}
		
		view.aktualisiereButtonsFallsNötig(update);
	}
}
