package de.strahlendes_mannheim.akw.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.strahlendes_mannheim.akw.HeatExchanger;
import de.strahlendes_mannheim.akw.HeatStorage;
import de.strahlendes_mannheim.akw.WaterCycle;

public class HeatExchangerTest {

	@Test
	public void testExchange() {
		
		HeatStorage s = new HeatStorage(10) {};
		WaterCycle w = new WaterCycle(0, 1);
		
		HeatExchanger h = new HeatExchanger(s, w, 0);
		
		h.exchange();
		
		assertEquals(5, s.getTemperature());
		assertEquals(5, w.get(0).getTemperature());
		
	}

}
