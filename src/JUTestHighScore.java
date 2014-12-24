import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

public class JUTestHighScore {
	HighScore h = new HighScore();

	@Test
	public void mHighScore() {
		assertEquals(h.toString(),
				"Never 69\nGonna 42\nGive 36\nYou 21\nUp 13\n");
	}

	@Test
	public void add() {
		Score s = new Score("Shana", 111);
		h.add(s);
		assertEquals(h.toString(),
				"Shana 111\nNever 69\nGonna 42\nGive 36\nYou 21\n");
	}

	@Test
	public void add2() {
		Score s = new Score("Shana", 111);
		h.add(s);
		assertEquals(h.toString(),
				"Shana 111\nNever 69\nGonna 42\nGive 36\nYou 21\n");
	}

	@Test
	public void add3() {
		Score s1 = new Score("Train Heartnet", 8);
		h.add(s1);
		assertEquals(h.toString(),
				"Never 69\nGonna 42\nGive 36\nYou 21\nUp 13\n");
	}

	@Test
	public void add4() {
		Score s2 = new Score("Sven Vollified", 28);
		h.add(s2);
		assertEquals(h.toString(),
				"Never 69\nGonna 42\nGive 36\nSven Vollified 28\nYou 21\n");
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(JUTestHighScore.class);
	}

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("JUTestHighScore");
	}
}
