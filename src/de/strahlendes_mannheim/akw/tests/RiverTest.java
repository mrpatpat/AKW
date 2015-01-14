package de.strahlendes_mannheim.akw.tests;

import static org.junit.Assert.fail;

import org.junit.Test;

import de.strahlendes_mannheim.akw.ControlRoom;
import de.strahlendes_mannheim.akw.River;

public class RiverTest {

	@Test
	public void testGetTemperature() {
		
		River r = new River(new ControlRoom(null,0),10);
		
		r.setTemperature(200);
		
		if(r.getTemperature()!=10){
			fail("Sollte immer 10 sein");
		}
		
	}

}
