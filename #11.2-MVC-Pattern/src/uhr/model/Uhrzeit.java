package uhr.model;

public class Uhrzeit {
	private int h, m, s;
	
	public Uhrzeit(int h, int m, int s) {
		this.h = h;
		this.m = m;
		this.s = s;
	}

    /**
     * liefert die aktuelle Stunde
     * @return
     */
	public int getStunde() {
		return h;
	}

	/**
	 * liefert die aktuelle Minute
	 * @return
	 */
	public int getMinute() {
		return m;
	}

	/**
	 * liefert die aktuelle Sekunde
	 * @return
	 */
	public int getSekunde() {
		return s;
	}
}
