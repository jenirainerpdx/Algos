package greedy;

import greedy.FractionalKnapsack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class FractionalKnapsackTest {

	Object[] SIXTY_TWENTY_3 = new Object[] {60,20,3.0};
	Object[] HUNDRED_FIFTY_2 = new Object[] {100,50,2.0};
	Object[] ONETWENTY_THIRTY_4 = new Object[] {120,30,4.0};
	Object[][] INPUT = new Object[][]{SIXTY_TWENTY_3, HUNDRED_FIFTY_2, ONETWENTY_THIRTY_4};
	Object[][] EXPECTED = new Object[][]{
			ONETWENTY_THIRTY_4,
			SIXTY_TWENTY_3,
			HUNDRED_FIFTY_2};

	@Test
	@Timeout(1L)
	public void testFractionalKnapsackSimple() {
		int[] values = new int[]{60, 100, 120};
		int[] weights = new int[]{20, 50, 30};

		assertEquals(180.00,
				FractionalKnapsack.getOptimalValue(50, values, weights));
	}

	@Test
	@Timeout(1L)
	public void testFractionalKnapsackOneWeight() {
		int[] values = new int[]{500};
		int[] weights = new int[]{30};
		double expected = 166.6667;
		int capacity = 10;

		assertEquals(expected,
				FractionalKnapsack.getOptimalValue(capacity, values, weights), 0.0005);
	}

	@Test
	@Timeout(1L)
	public void testFractionalKnapsackTwoHaveSameRatio(){
		int[] values = new int[]{70, 200, 120};
		int[] weights = new int[]{20, 50, 30};
		double expected = 200.0;
		assertEquals(expected, FractionalKnapsack.getOptimalValue(50, values, weights));
	}

	@Test
	public void testDescMatrixSort() {
		Object[][] actual = FractionalKnapsack.descendingMatrixSort(INPUT);
		assertArrayEquals(EXPECTED, actual);
	}

	@Test
	public void testDeriveValueBasedOrdering() {
		int[] values = new int[] {60,100,120};
		int[] weights = new int[] {20,50,30};
		Object[][] actual = FractionalKnapsack.deriveValueBasedOrder(values, weights);
		assertArrayEquals(EXPECTED, actual);
	}
}