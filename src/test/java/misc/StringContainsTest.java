package misc;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StringContainsTest {

	private StringContains classUnderTest = new StringContains();

	@ParameterizedTest
	@Timeout(1L)
	@CsvSource({
			"a, aa, true",
			"zxyw, zz, false",
			"zzz, zzzz, true"
	})
	public void testSimpleCanConstruct(String input, String dictionary, String expectation) {
		if (expectation.equalsIgnoreCase("true")) {
			assertTrue(classUnderTest.canConstruct(input, dictionary));
		} else {
			assertFalse(classUnderTest.canConstruct(input, dictionary));
		}
	}
}