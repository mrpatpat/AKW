package de.strahlendes_mannheim.akw.configs;

public class FailingConfig extends Config{

	/**
	 * Starttemperatur des Reaktors.
	 */
	private static final int REACTOR_START_TEMP = 10;

	/**
	 * Erwärmungskoeffizient des Reaktors.
	 */
	private static final int REACTOR_HEAT_COEFFICIENT = 1000;

	/**
	 * Kritische Temperatur des Reaktors.
	 */
	private static final int REACTOR_CRITICAL_TEMP = 2878;

	/**
	 * Starttemperatur des Wassers.
	 */
	private static final int WATER_START_TEMP = 10;

	/**
	 * Volumen eines Wasserelements.
	 */
	private static final int WATER_VOLUME = 100;

	/**
	 * Größe des Wasserkreislaufes.
	 */
	private static final int WATER_CYCLE_SIZE = 12;

	/**
	 * Position des Reaktorwärmetauschers im Kreislauf.
	 */
	private static final int REACTOR_POS = 0;

	/**
	 * Position des Flusswärmetauschers im Kreislauf.
	 */
	private static final int RIVER_POS = 6;

	/**
	 * Starttemperatur des Flusses.
	 */
	private static final int RIVER_START_TEMP = 700;

	/**
	 * Pumpleistung.
	 */
	private static final int PUMP_PERFORMANCE = 100;

	/**
	 * Pumpkoeffizient.
	 */
	private static final int PUMP_COEFFICIENT = 1;

	public int getReactorStartTemp() {
		return REACTOR_START_TEMP;
	}

	public int getReactorHeatCoefficient() {
		return REACTOR_HEAT_COEFFICIENT;
	}

	public int getReactorCriticalTemp() {
		return REACTOR_CRITICAL_TEMP;
	}

	public int getWaterStartTemp() {
		return WATER_START_TEMP;
	}

	public int getWaterVolume() {
		return WATER_VOLUME;
	}

	public int getWaterCycleSize() {
		return WATER_CYCLE_SIZE;
	}

	public int getReactorPos() {
		return REACTOR_POS;
	}

	public int getRiverPos() {
		return RIVER_POS;
	}

	public int getRiverStartTemp() {
		return RIVER_START_TEMP;
	}

	public int getPumpPerformance() {
		return PUMP_PERFORMANCE;
	}

	public int getPumpCoefficient() {
		return PUMP_COEFFICIENT;
	}
	
}
