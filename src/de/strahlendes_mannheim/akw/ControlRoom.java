package de.strahlendes_mannheim.akw;
/**
 * Leitwarte. Überwacht das System und gibt Informationen aus.
 * @author Adrian. Felix
 *
 */
public class ControlRoom {

	/**
	 * Reaktortemperatur.
	 */
	private int reactorTemp;
	
	/**
	 * Flusstemperatur.
	 */
	private int riverTemp;

	/**
	 * Referenz auf das Kraftwerk.
	 */
	private NuclearPlant nuclearPlant;
	
	/**
	 * Kritische Temperatur.
	 */
	private int criticalTemp;

	/**
	 * Konstruktor.
	 * @param nuclearPlant Referenz auf das Kraftwerk
	 * @param criticalTemp Kritische Temperatur
	 */
	public ControlRoom(NuclearPlant nuclearPlant, int criticalTemp) {
		this.nuclearPlant = nuclearPlant;
		this.criticalTemp = criticalTemp;
	}

	/**
	 * Druckt die aktuellen Informationen.
	 */
	private synchronized void print() {
		System.out.println("Temperatur Reaktor: "+reactorTemp+", Temperatur Rückfluss in Rhein "+riverTemp);
	}

	/**
	 * Wird aufgerufen, wenn sich die Reaktortemperatur ändert.
	 * @param temp neue Temperatur
	 */
	public synchronized void onReacterTempChange(int temp) {
		
		//aktualisieren
		this.reactorTemp = temp;

		//kritische Temperatur ?
		if (reactorTemp > criticalTemp) {
			System.out
					.println("Kritische Temperatur erreicht! AKW wird heruntergefahren.");
			nuclearPlant.shutDownPlant();
		}

	}

	/**
	 * Wird aufgerufen, wenn sich die Flusstemperatur ändert.
	 * @param temp neue Temperatur
	 */
	public synchronized void onRiverTempChange(int temp) {
		this.riverTemp = temp;
	}

	/**
	 * Wird aufgerufen, wenn die Pumpe pumpt.
	 */
	public synchronized void onPump() {
		this.print();
	}

}
