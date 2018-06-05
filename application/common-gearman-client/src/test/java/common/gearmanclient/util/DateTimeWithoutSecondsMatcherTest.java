package common.gearmanclient.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateTimeWithoutSecondsMatcherTest {

	@Test
	public void matches() {
		DateTimeWithoutSecondsMatcher dateTimeMatcher = new DateTimeWithoutSecondsMatcher();
		assertFalse(dateTimeMatcher.matches("2018-03-23 13:46:47"));
		assertTrue(dateTimeMatcher.matches("2018-03-23 13:46"));
	}
}