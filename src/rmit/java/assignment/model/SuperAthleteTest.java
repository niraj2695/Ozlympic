package rmit.java.assignment.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuperAthleteTest {

	@Test
	public void test() {
		SuperAthlete sa = new SuperAthlete("oz200","Niraj" , "23","VIC");
		sa.setCurrentGame("S");	
		sa.compete();
		System.out.println(sa.compete());
		assertTrue(sa.compete() <= 200 );
		
		
		
	}

}
