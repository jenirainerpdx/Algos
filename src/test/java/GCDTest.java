import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class GCDTest {

	@Test
	@Timeout(1L)
	public void testGCDSimple() {
		assertEquals(1, GCD.gcd_naive(3, 4));
	}

	@Test
	@Timeout(1L)
	public void testGCDNonNaiveSimple() {
		assertEquals(1, GCD.gcd_attempt2(3, 4));
	}

	@Test
	@Timeout(1L)
	public void testGCDNonNaiveBig() {
		assertEquals(6, GCD.gcd_attempt2(18, 24));
	}

	@Test
	@Timeout(1L)
	public void testGCDHuge() {
		assertEquals(17657, GCD.gcd_attempt2(28851538, 1183019));
	}

	@Test
	@Timeout(1L)
	public void testGCD_WhereOneItemDivisibleByTheOther() {
		assertEquals(2, GCD.gcd_attempt2(1000000000, 2));
	}

	@Test
	@Timeout(1L)
	public void testGCD_WhenAnInputIs1AnswerIs1() {
		assertEquals(1, GCD.gcd_attempt2(500000000, 1));
	}
}