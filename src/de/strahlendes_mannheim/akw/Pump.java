package de.strahlendes_mannheim.akw;
/**
 * Pumpe (ausführbar)
 * @author Adrian, Felix
 *
 */
public class Pump implements Runnable {

	/**
	 * Referenz auf die Leitwarte.
	 */
	private ControlRoom controlRoom;

	/**
	 * Referenz auf Wasserkreislauf.
	 */
	private WaterCycle cycle;

	/**
	 * Wärmetauscher zum Reaktor.
	 */
	private HeatExchanger reactorExchanger;
	
	/**
	 * Wärmetauscher zum Fluss.
	 */
	private HeatExchanger riverExchanger;

	/**
	 * Pumpleistung.
	 */
	private int performance;
	
	/**
	 * bereits gepumptes Volumen.
	 */
	private int pumpedVolume;
	
	/**
	 * Volumen eines Elementes im Kreislauf.
	 */
	private int elementVolume;

	/**
	 * Pumpkoeffizient.
	 */
	private int coefficient;

	/**
	 * Konstruktor für eine Pumpe.
	 * @param reactor Wärmetauscher zum Reaktor
	 * @param river Wärmetauscher zum Fluss
	 * @param cr Leitwarte
	 * @param c Wasserkreislauf
	 * @param performance Leistung
	 * @param coefficient Koeffizient
	 * @param elementVolume Volumen eines Elementes im Kreislauf
	 */
	public Pump(HeatExchanger reactor, HeatExchanger river, ControlRoom cr,
			WaterCycle c, int performance,int coefficient,
			int elementVolume) {
		this.reactorExchanger = reactor;
		this.riverExchanger = river;
		this.performance = performance;
		this.controlRoom = cr;
		this.cycle = c;
		this.pumpedVolume = 0;
		this.elementVolume = elementVolume;
		this.coefficient = coefficient;
	}

	@Override
	public void run() {

		//Ist bis zur Unterbrechung aktiv
		while (!Thread.currentThread().isInterrupted()) {

			//Synchronisiert über eine gemeinsame Lock
			synchronized (NuclearPlant.LOCK) {
				try {
					//pumpen
					pump();
					//warten
					NuclearPlant.LOCK.wait((long) (1000)/coefficient);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

			}

		}

	}

	/**
	 * Startet einen Wärmetausch in den Tauschern.
	 */
	private synchronized void exchange() {
		this.riverExchanger.exchange();
		this.reactorExchanger.exchange();
	}

	/**
	 * Pumpen. Sobald genug gepumpt wurde, wird rotiert.
	 */
	private synchronized void pump() {
		pumpedVolume += performance;
		if (pumpedVolume >= elementVolume) {
			pumpedVolume -= elementVolume;
			cycle.rotate();
			exchange();
			controlRoom.onPump();
			
		}
	}

}
