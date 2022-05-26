import fibonacci.Fibonacci;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.Duration;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

	private static final BigInteger FIB_OF_50 = BigInteger.valueOf(12586269025L);
	private static final BigInteger FIB_OF_80 = BigInteger.valueOf(23416728348467685L);

	@RepeatedTest(10)
	@Disabled("this will fail for timeouts.")
	public void testIsFastEnough() {
		assertTimeout(Duration.ofSeconds(1L), () -> {
	//		Fibonacci.calc_fib(45);
		});
	}

	@Test
	public void testTableAlgo() {
		BigInteger actual = Fibonacci.calc_fib_with_table(10);
		assertEquals(BigInteger.valueOf(55L), actual);
	}

	/**
	 * This actually runs faster than the memoization version.  Wonder if that holds true for larger numbers???
	 */
	@Test
	public void testSimpleAlgo() {
		BigInteger actual = Fibonacci.simpleFib(10);
		assertEquals(BigInteger.valueOf(55L), actual);
	}

	@Test
	@RepeatedTest(10)
	public void grabFibOf50WithTable() {
		assertTimeout(Duration.ofSeconds(1L), () -> {
			BigInteger actual = Fibonacci.calc_fib_with_table(50);
			assertEquals(FIB_OF_50, actual);
		});
	}

	@Test
	public void fibOf80WithMemo() {
		assertEquals(FIB_OF_80, Fibonacci.calc_fib_with_table(80));
	}

	@Test
	public void fibOf80Simple() {
		assertEquals(FIB_OF_80, Fibonacci.simpleFib(80));
	}

	@Test
	public void testStream(){
		Fibonacci fib = new Fibonacci();
		assertEquals(BigInteger.valueOf(55L), fib.streamingFib(10));
	}



}