package de.strahlendes_mannheim.akw;
/**
 * Wärmetauscher.
 * @author Adrian, Felix
 *
 */
public class HeatExchanger {
	
	/**
	 * Linker Wärmespeicher.
	 */
	private volatile HeatStorage left;
	
	/**
	 * Rechter Wärmespeicher.
	 */
	private volatile HeatStorage right;
	
	/**
	 * Wasserkreislauf.
	 */
	private volatile WaterCycle cycle;
	
	/**
	 * Position im Kreislauf.
	 */
	private volatile int position;
	
	/**
	 * Konstruktor.
	 * @param hs Wärmetauschpartner
	 * @param cycle Wasserkreislauf(Partner 2)
	 * @param position Position im Kreislauf
	 */
	public HeatExchanger(HeatStorage hs, WaterCycle cycle, int position) {
		this.cycle = cycle;
		this.position = position;
		this.left = hs;
		refreshCycle();
	}

	/**
	 * Führt einen Wärmetausch zwischen den beiden Wärmespeichern durch.
	 */
	public synchronized void exchange(){
		refreshCycle();
		int t_left = left.getTemperature();
		int t_right = right.getTemperature();
		int t_out = (t_left+t_right)/2;
		left.setTemperature(t_out);
		right.setTemperature(t_out);
	}
	
	/**
	 * Aktualisiert den Wärmespeicher "Kreislauf".
	 */
	private synchronized void refreshCycle(){
		this.right = cycle.get(position);
	}

}
