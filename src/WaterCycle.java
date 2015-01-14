/**
 * Datenstruktur für einen Wasserkreislauf.
 * 
 * @author Adrian, Felix
 *
 */
public class WaterCycle {

	/**
	 * Jedes Element ist ein Wärmespeicher.
	 */
	private volatile HeatStorage[] cycle;

	/**
	 * Zeiger auf das erste Element.
	 */
	private volatile int first;

	/**
	 * Größe des Kreislaufes.
	 */
	private final int size;

	/**
	 * Konstruktor für den Kreislauf.
	 * 
	 * @param startTemp
	 *            Starttemperatur aller Elemente
	 * @param size
	 *            Größe des Kreislaufes
	 */
	public WaterCycle(int startTemp, int size) {

		this.size = size;

		cycle = new HeatStorage[size];

		for (int i = 0; i < size; i++) {
			cycle[i] = new Water(startTemp);
		}

		first = 0;

	}

	/**
	 * Rotiert alle Wasserelemente um 1.
	 */
	public synchronized void rotate() {
		first++;
		if (first >= size) {
			first = 0;
		}
	}

	/**
	 * Gibt das i. Element zurück.
	 * 
	 * @param i
	 *            Index
	 * @return Element an i
	 */
	public synchronized HeatStorage get(int i) {
		return cycle[(i + first) % size];
	}

	/**
	 * Innere Klasse für ein Wasserelement. Nur der Kreislauf benutzt es.
	 * @author Adrian, Felix
	 *
	 */
	private class Water extends HeatStorage {

		/**
		 * Konstruktor.
		 * @param temp Starttemperatur
		 */
		public Water(int temp) {
			super(temp);
		}

	}

}
