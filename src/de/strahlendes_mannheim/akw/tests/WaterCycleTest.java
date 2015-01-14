package de.strahlendes_mannheim.akw.tests;


import static org.junit.Assert.fail;

import org.junit.Test;

import de.strahlendes_mannheim.akw.HeatStorage;
import de.strahlendes_mannheim.akw.WaterCycle;

public class WaterCycleTest {

	@Test
	public void testWaterCycle() {
		WaterCycle c = new WaterCycle(0, 2);
		if(c==null){
			fail("Konstruktion fehlgeschlagen");
		}
	}

	@Test
	public void testRotate() {
		
		WaterCycle c = new WaterCycle(0, 2);
		HeatStorage first = c.get(0);
		
		if(first==null){
			fail("Sollte nciht null sein");
		}
		
		c.rotate();
		
		if(first==c.get(0)){
			fail("Rotation fehlgeschlagen");
		}
		
		if(first!=c.get(1)){
			fail("Rotation fehlgeschlagen");
		}
	}

}
