
/**
 * Fluss (Wärmespeicher)
 * @author Adrian, Felix
 */
public class River extends HeatStorage{
	
	/**
	 * Flusstemperatur(konstant, da immer neuer Zufluss).
	 */
	private final int riverTemp;
	
	/**
	 * Referenz auf die Leitwarte.
	 */
	private ControlRoom controlRoom;

	/**
	 * Konstruktor.
	 * @param controlRoom Referenz auf Leitwarte
	 * @param riverTemp Flusstemperatur
	 */
	public River(ControlRoom controlRoom, int riverTemp) {
		super(riverTemp);
		this.riverTemp = riverTemp;
		this.controlRoom = controlRoom;
		this.setTemperature(riverTemp);
	}
	
	/**
	 * Gibt die Flusstemperatur zurück (konstant)
	 */
	public int getTemperature(){
		return riverTemp;
	}
	
	/**
	 * Beim Setzen der neuen Temperatur, wird nur die Leitwarte benachrichtigt, aber der Fluss nicht verändert.
	 */
	public void setTemperature(int temp){
		controlRoom.onRiverTempChange(temp);
	}

}
