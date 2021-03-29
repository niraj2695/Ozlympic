package rmit.java.assignment.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwimmerTest {

	@Test
	public void Compete() {
		Swimmer at = new Swimmer("Oz","Niraj" , "23" ,"VIC" );
		at.compete();
		assertTrue(at.compete() <= 200 );
	}

}
