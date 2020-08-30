package id.test.springboottesting.model;

import java.util.Set;

/**
 * @author e1077326
 *
 */
/*
 * if you would like to see errorProne plugin coming into play, please add this
 * - s.remove(i - 1); after s.add(i)
 */
public class ShortSet {
	private Set<Short> s;

	public ShortSet(Set<Short> s) {
		super();
		this.s = s;
	}

	public int justThat() {
		for (short i = 0; i < 100; i++) {
			s.add(i);
		}
		return s.size();
	}

	public Boolean returnBool() {
		/*
		 * uncomment this statement, the respective test will pass but Spotbugs will catch the bug that the
		 * function expected to return Boolean is returning null.
		 * return null
		 */
		return false;
	}

}
