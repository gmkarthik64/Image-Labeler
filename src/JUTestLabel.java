import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

public class JUTestLabel {
	@Test
	public void mLabel() {
		Label l = new Label("Kanon");
		assertEquals(l.toString(), " Kanon 1");
	}

	@Test
	public void mLabel_getName() {
		Label l = new Label("Shana", 111);
		assertEquals(l.getName(), "shana");
		assertEquals(l.toString(), " shana 111");
	}

	@Test
	public void mLabel_increment() {
		Label s = new Label("*Spice and Wolf?", 337);
		assertEquals(s.toString(), " spice?and?wolf 337");
		s.increment();
		assertEquals(s.toString(), " spice?and?wolf 338");
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(JUTestLabel.class);
	}

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("JUTestLabel");
	}
}
