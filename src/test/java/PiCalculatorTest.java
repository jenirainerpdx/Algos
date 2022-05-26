import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PiCalculatorTest {

	private static final double piToTheFifthPosition = 3.14159;

	@Test
	public void testSimple(){
		double actual = PiCalculator.calculatePi(5);
		assertEquals(piToTheFifthPosition, actual);
	}

	@Test
	public void testHuh(){
		double actual = PiCalculator.calculatePi(500000000);
		assertEquals(piToTheFifthPosition, actual, 0.0000009);
	}

}