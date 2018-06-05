package common.gearmanclient.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateTimeMatcherTest {

	@Test
	public void matches() {
		DateTimeMatcher dateTimeMatcher = new DateTimeMatcher();
		assertTrue(dateTimeMatcher.matches("2018-03-23 13:46:47"));
		assertFalse(dateTimeMatcher.matches("2018-03-23 13:46"));
	}
}