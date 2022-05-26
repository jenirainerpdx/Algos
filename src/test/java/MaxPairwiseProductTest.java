import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MaxPairwiseProductTest {

	int[] smallInputSet;
	int[] largeInputSet;

	@BeforeEach
	public void setUp() {
		smallInputSet = new int[]{5, 7, 9, 100, 8};
		largeInputSet = new int[]{100000, 500000, 100000, 999999999}; // really not that large.

	}

	private int[] generateVeryLargeInputRandomly(int howBig) {
		int[] foo = new int[howBig];
		Random random = new Random();
		for (int i = 0; i < foo.length; i++) {
			foo[i] = Math.abs(random.nextInt());
		}
		return foo;
	}

	@Test
	public void testFindLargest() {
		long[] actual = MaxPairwiseProduct.findTwoLargest(smallInputSet);
		assertEquals(100, actual[0]);
		assertEquals(9, actual[1]);
	}

	@RepeatedTest(50)
	public void fastMatchesBruteForce() {
		int howBig = 4;
		int[] testInput = generateVeryLargeInputRandomly(howBig);
		long slow = MaxPairwiseProduct.getMaxPairwiseProduct(testInput);
		long fast = MaxPairwiseProduct.getMaxPairwiseProductFast(testInput);
		assertEquals(slow, fast, getFailMessage(testInput, slow, fast));
	}

	private String getFailMessage(int[] input, long slow, long fast) {
		StringBuilder sb = new StringBuilder("For the following inputs:  ");
		for (int i : input) {
			sb.append(i + ", ");
		}
		sb.append("  the brute force solution was:  " + slow);
		sb.append("  The fast solution was:  " + fast);
		return sb.toString();
	}

	@RepeatedTest(10)
	public void testIsFastEnough() {
		int[] input = generateVeryLargeInputRandomly(99999999);
		assertTimeout(Duration.ofSeconds(1L), () -> {
			MaxPairwiseProduct.getMaxPairwiseProductFast(input);
		});
	}

	@Test
	@Timeout(1L)
	public void testFindLargestWithNegatives() {
		int[] testSet = new int[]{-5, -1, 0, 5000, 43};
		long[] actual = MaxPairwiseProduct.findTwoLargest(testSet);
		assertEquals(5000, actual[0]);
		assertEquals(43, actual[1]);
	}

	@Test
	public void testFindMaxFaster() {
		long actual = MaxPairwiseProduct.getMaxPairwiseProductFast(smallInputSet);
		assertEquals(900L, actual);
	}
}