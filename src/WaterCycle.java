public class WaterCycle {
	
	private volatile HeatStorage[] cycle;
	private volatile int first;

	private final int size;

	public WaterCycle(int startTemp, int size){
		
		this.size = size;
	
		cycle = new HeatStorage[size];
		
		for(int i=0;i<size;i++){
			cycle[i] = new Water(startTemp);
		}
		
		first = 0;
		
	}
	
	public synchronized void rotate(){
		first++;
		if(first>=size){
			first = 0;
		}
	}
	
	public synchronized HeatStorage get(int i){
		return cycle[(i+first)%size];
	}
	
	private class Water extends HeatStorage{

		public Water(int temp) {
			super(temp);
		}
		
	}
	
}
