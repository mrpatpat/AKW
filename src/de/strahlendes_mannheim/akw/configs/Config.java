package de.strahlendes_mannheim.akw.configs;

public abstract class Config {

	public abstract int getReactorStartTemp();
	public abstract int getReactorHeatCoefficient();
	public abstract int getReactorCriticalTemp();
	public abstract int getWaterStartTemp();
	public abstract int getWaterVolume();
	public abstract int getWaterCycleSize();
	public abstract int getReactorPos();
	public abstract int getRiverPos();
	public abstract int getRiverStartTemp();
	public abstract int getPumpPerformance();
	public abstract int getPumpCoefficient();

}
