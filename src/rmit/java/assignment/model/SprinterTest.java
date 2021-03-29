package rmit.java.assignment.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SprinterTest {

	@Test
	public void competeAdd() {
		Sprinter st = new Sprinter ("oz100","Niraj","Bohra","VIC");
		st.addPoints(200);
		assertEquals(st.getPoints(),200);
	}

}
