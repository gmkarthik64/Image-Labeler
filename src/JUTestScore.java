import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

public class JUTestScore {
	@Test
	public void mScore() {
		Score s = new Score();
		assertEquals(s.toString(), " 0");
	}

	@Test
	public void mScore_toString() {
		Score s = new Score("Shana", 111);
		assertEquals(s.toString(), "Shana 111");
	}

	@Test
	public void mScore_getName_getCount() {
		Score s = new Score("Holo", 337);
		assertEquals(s.getName(), "Holo");
		assertEquals(s.getCount(), 337);
	}

	@Test
	public void mScore_compareTo() {
		Score s1 = new Score("Train Heartnet", 13);
		Score s2 = new Score("Sven Vollified", 8);
		assertTrue(s2.compareTo(s1) < 0);
	}

	@Test
	public void mScore_equals() {

	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(JUTestScore.class);
	}

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("JUTestScore");
	}
}
