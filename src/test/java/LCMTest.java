import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class LCMTest {

	@Test
	@Timeout(1L)
	public void testLCMSmallish() {
		assertEquals(36, LCM.lcm_naive(18, 12));
	}

	@Test
	@Timeout(1l)
	public void testLCMSmallishReal() {
		assertEquals(36, LCM.lcm(18, 12));
	}

	@Test
	@Timeout(1L)
	public void testLCMLarge() {
		assertEquals(1933053046, LCM.lcm(28851538, 1183019));
	}

	@Test
	@Timeout(1L)
	public void testMultiplyItOutSortOfBig() {
		Long[] first = new Long[]{2L, 2L, 2L, 2L, 2L, 2L, 2L, 3L, 4L, 5L}; //shorter
		Long[] second = new Long[]{2L, 2L, 2L, 2L, 2L, 3L, 3L, 3L, 3L, 3L, 5L, 5L, 5L, 7L, 7L, 7L}; //longer
		// with this, the resulting set of factors would be:
		// 7 2's; 5 3's, 4, 3 5's, 3 7's
		long expected = 5334336000L;
		assertEquals(expected, LCM.multiplyItOut(second, first));
	}

	@Test
	@Timeout(1L)
	public void testReduceInputToPrimes() {
		Long[] actual = LCM.reduceInputToPrimes(24);
		Long[] expected = new Long[]{2L, 2L, 2L, 3L};
		assertArrayEquals(expected, actual);
	}

	@Test
	@Timeout(1L)
	public void testReduceInputToPrimesAnother() {
		int input = 524;
		// 524 => 2, 2, 131 -- I think.
		Long[] expected = new Long[]{2L, 2L, 131L};
		Long[] actual = LCM.reduceInputToPrimes(input);
		assertArrayEquals(expected, actual);
	}

	@Test
	@Timeout(1l)
	public void testDerivePrimes() {
		Long[] expected = new Long[]{2L, 3L, 3L};
		Long[] actual = LCM.reduceInputToPrimes(18);
		assertArrayEquals(expected, actual);
	}

	@Test
	@Timeout(1L)
	public void testDerivePrimesFromSimplePrime() {
		Long[] expected = new Long[]{791347L};
		Long[] actual = LCM.reduceInputToPrimes(791347);
		assertArrayEquals(expected, actual);
	}
}