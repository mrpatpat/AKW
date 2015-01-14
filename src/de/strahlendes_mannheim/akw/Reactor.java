package de.strahlendes_mannheim.akw;
/**
 * Reaktor (ausführbar).
 * @author Adrian, Felix
 *
 */
public class Reactor extends HeatStorage implements Runnable {

	/**
	 * Referenz auf die Leitwarte.
	 */
	private ControlRoom controlRoom;
	
	/**
	 * Hitzekoeffizient.
	 */
	private int heatCoefficient;

	/**
	 * Kosntruktor.
	 * @param temp
	 * @param cr
	 * @param heatCoefficient
	 */
	public Reactor(int temp, ControlRoom cr, int heatCoefficient) {
		super(temp);
		this.controlRoom = cr;
		this.heatCoefficient = heatCoefficient;
	}

	@Override
	public void run() {

		//aktiv, solange nicht unterbrochen
		while (!Thread.currentThread().isInterrupted()) {

			//gemeinsames Lock
			synchronized (NuclearPlant.LOCK) {
				try {
					//erhitzen um 1
					heatUp();
					//Warte solange, dass in 1 sekunde um den Hitzekoeffizienten erhitzt wird.
					NuclearPlant.LOCK.wait(1000/heatCoefficient);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

		}

	}

	/**
	 * Erhitzen.
	 */
	private synchronized void heatUp() {
		this.setTemperature(getTemperature() + 1);
		controlRoom.onReacterTempChange(getTemperature());
	}

}
