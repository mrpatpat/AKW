/**
 * Abstrakte Klasse f�r einen W�rmespeicher z.B. Wasser 
 * @author Adrian, Felix
 *
 */
public abstract class HeatStorage {
	
	/**
	 * Temperatur eines W�rmespeichers.
	 */
	private volatile int temperature;
	
	/**
	 * Konstruktor.
	 * @param temp Starttemperatur
	 */
	public HeatStorage(int temp){
		temperature = temp;
	}
	
	/**
	 * Gibt die aktuelle Temperatur zur�ck.
	 * @return Temperatur
	 */
	public int getTemperature(){
		return temperature;
	}
	
	/**
	 * Setzt die Temperatur.
	 * @param temp Temperatur
	 */
	public void setTemperature(int temp){
		this.temperature = temp;
	}

}
