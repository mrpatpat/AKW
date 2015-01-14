package de.strahlendes_mannheim.akw;
/**
 * W�rmetauscher.
 * @author Adrian, Felix
 *
 */
public class HeatExchanger {
	
	/**
	 * Linker W�rmespeicher.
	 */
	private volatile HeatStorage left;
	
	/**
	 * Rechter W�rmespeicher.
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
	 * @param hs W�rmetauschpartner
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
	 * F�hrt einen W�rmetausch zwischen den beiden W�rmespeichern durch.
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
	 * Aktualisiert den W�rmespeicher "Kreislauf".
	 */
	private synchronized void refreshCycle(){
		this.right = cycle.get(position);
	}

}
