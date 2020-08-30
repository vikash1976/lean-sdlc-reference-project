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

	@Test
	void returnBool() {
		/*
		 * uncomment this statement and change return in ShortSet.java function to
		 * return null. This test will pass but Spotbugs will catch the bug that the
		 * function expected to return Boolean is returning null.
		 * Boolean expectedValue = null;
		 */
		
		Boolean expectedValue = false;
		Set<Short> s = new HashSet<>();
		Boolean actualValue = new ShortSet(s).returnBool();

		assertEquals(expectedValue, actualValue);
	}

}
