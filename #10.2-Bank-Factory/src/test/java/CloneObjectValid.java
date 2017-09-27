import java.io.Serializable;

/**
 * Klasse zum Testen des ObjectCloners - Nur für Testzwecke zu gebrauchen!
 * @author Benny Lach
 *
 */
public class CloneObjectValid implements Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -4486295849127004109L;
	
	int number;
	
	public CloneObjectValid(int number) {
		this.number = number;
	}

}
