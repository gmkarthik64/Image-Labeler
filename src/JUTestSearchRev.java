import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

public class JUTestSearchRev {
	@Test
	public void mSearchRev() {
		SearchRev sr = new SearchRev("Kanon", 3, 4);
		assertEquals(sr.toString(), "Kanon 0.75");
	}

	@Test
	public void mSearchRev_getName() {
		SearchRev sr = new SearchRev("Shana", 0, 111);
		assertEquals(sr.getName(), "Shana");
		assertEquals(sr.toString(), "Shana 0.0");
	}

	@Test
	public void mSearchRev_getRatio() {
		SearchRev sr = new SearchRev("Holo", 111, 222);
		assertEquals("" + sr.getRatio(), "0.5");
	}

	@Test
	public void mSearchRev_compareTo() {
		SearchRev sr = new SearchRev("Shana", 0, 111);
		SearchRev sr2 = new SearchRev("Holo", 111, 222);
		assertTrue(sr.compareTo(sr2) < 0);
		// System.out.println(sr.compareTo(sr2));
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(JUTestSearchRev.class);
	}

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("JUTestSearchRev");
	}
}
