package id.test.springboottesting.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class ShortSetTest {

	@Test
	void testJustThat() {
		int expectedSize = 100;
		Set<Short> s = new HashSet<>();
		int actualSize = new ShortSet(s).justThat();

		assertEquals(expectedSize, actualSize);
	}
	/*
	 * @Test void returnBool() { Boolean expectedValue = null; Set<Short> s = new
	 * HashSet<>(); Boolean actualValue = new ShortSet(s).returnBool();
	 * 
	 * assertEquals(expectedValue, actualValue); }
	 */
}
