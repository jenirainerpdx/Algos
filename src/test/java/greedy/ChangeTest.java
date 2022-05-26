package greedy;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeTest {

	@ParameterizedTest
	@Timeout(1L)
	@CsvSource({
			"15, 2",
			"38, 7",
			"1538329, 153837",
			"2, 2",
			"28, 6",
			"1, 1",
			"9, 5"
	})
	public void testGetChangeSimple(int input, int expected){
		assertEquals(expected, Change.getChange(input), "This should require 1x10 and 1x5 == 2.");
	}

}