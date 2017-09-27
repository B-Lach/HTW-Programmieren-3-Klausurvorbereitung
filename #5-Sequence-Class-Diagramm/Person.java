package uebung_5;

import java.util.Date;

/**
 * Klasse die eine Persone repränsentiert 
 * @author Benny Lach
 *
 */
public class Person {
	
	private Date geburtstag;
	protected String name;
	
	/**
	 * Default Konsturktor 
	 * @param name Name der Person
	 * @param geburtstag Gebrutstag der Person
	 */
	public Person(String name, Date geburtstag) {
		this.geburtstag = geburtstag;
		this.name = name;
	}
	
	/**
	 * Erhöht das alter der Person
	 */
	public final void alterWaerden() {
		System.out.println("Aelter geworden");
	}
}
