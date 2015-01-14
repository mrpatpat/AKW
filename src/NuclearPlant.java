/**
 * Das Atomkraftwerk.
 * @author Adrian, Felix
 *
 */
public class NuclearPlant {

	/**
	 * Starttemperatur des Reaktors.
	 */
	private static final int REACTOR_START_TEMP = 10;
	
	/**
	 * Erwärmungskoeffizient des Reaktors.
	 */
	private static final int REACTOR_HEAT_COEFFICIENT = 42;
	
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
	private static final int RIVER_START_TEMP = 10;

	/**
	 * Pumpleistung.
	 */
	private static final int PUMP_PERFORMANCE = 100;
	
	/**
	 * Pumpkoeffizient.
	 */
	private static final int PUMP_COEFFICIENT = 1;
	
	/**
	 * Gemeinsame Lock für die Threads, damit nicht gleichzeitig gepumpt und erwärmt wird.
	 */
	public static final Object LOCK = new Object();
	
	//Elemente des AKW
	private ControlRoom controlRoom;
	private Reactor reactor;
	private River river;
	private WaterCycle cycle;
	private HeatExchanger reactorToCycle;
	private HeatExchanger cycleToRiver;
	private Pump pump;

	//Threads
	private Thread reactorT;
	private Thread pumpT;

	/**
	 * Startpunkt
	 * @param args
	 */
	public static void main(String[] args) {
		new NuclearPlant();
	}

	/**
	 * Konstruktor.
	 */
	public NuclearPlant() {
		init();
		run();
	}

	/**
	 * AKW Durchlauf.
	 */
	public void run() {

		// Start
		startPlant();

		// 20s schlafen
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Stopp
		shutDownPlant();

	}

	/**
	 * Beenden des AKW.
	 */
	public void shutDownPlant() {

		// unterbricht die Threads
		pumpT.interrupt();
		reactorT.interrupt();

		// beendet die VM
		System.exit(0);

	}

	/**
	 * Starten des AKW.
	 */
	private void startPlant() {

		// erstellt die Threads
		reactorT = new Thread(reactor);
		pumpT = new Thread(pump);

		// startet die Threads
		reactorT.start();
		pumpT.start();

	}

	/**
	 * Initialisierung aller Komponenten.
	 */
	public void init() {

		// Elemente
		controlRoom = new ControlRoom(this, REACTOR_CRITICAL_TEMP);
		reactor = new Reactor(REACTOR_START_TEMP, controlRoom,
				REACTOR_HEAT_COEFFICIENT);
		river = new River(controlRoom, RIVER_START_TEMP);
		cycle = new WaterCycle(WATER_START_TEMP, WATER_CYCLE_SIZE);

		// Tauscher
		reactorToCycle = new HeatExchanger(reactor, cycle, REACTOR_POS);
		cycleToRiver = new HeatExchanger(river, cycle, RIVER_POS);

		// Pumpe
		pump = new Pump(reactorToCycle, cycleToRiver, controlRoom, cycle,
				PUMP_PERFORMANCE, PUMP_COEFFICIENT, WATER_VOLUME);

	}

}
