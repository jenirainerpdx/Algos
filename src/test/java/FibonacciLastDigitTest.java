import fibonacci.FibonacciLastDigit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciLastDigitTest {

	// 86
	//420196140727489673
	private static int LAST_DIGIT_FOR_86 = 3;

	@Test
	@Timeout(1L)
	public void testLastDigit() {
		assertEquals(LAST_DIGIT_FOR_86, FibonacciLastDigit.getFibonacciLastDigitNaive(86));
	}

	@Test
	@Timeout(1L)
	public void testLastDigit331() {
		assertEquals(9, FibonacciLastDigit.getFibonacciLastDigitNaive(331));
	}

	@Test
	@Timeout(5L)
	// Won't pass with a 1 second limit.
	public void testLastDigit327305() {
		assertEquals(5, FibonacciLastDigit.getFibonacciLastDigitNaive(327305));
	}

	@Test
	@Timeout(1L)
	public void testLastDigitOptimized(){
		assertEquals(5, FibonacciLastDigit.getFibLastDigit(327305));
	}

	@Test
	public void testOptimizedSimple() {
		assertEquals(3, FibonacciLastDigit.getFibLastDigit(4));
	}
}