package sorts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

	private final SelectionSort cut = new SelectionSort();
	private int[] testInput = new int[]{0, 5, 9, 43, -383, 9532, 24};
	private final int largeNumber = 999999;
	private final int smallNumber = -9999999;
	private final int listSize = 9_999_999;
	private int[] testLargeArray = generateLargeArray(listSize);


	private int[] generateLargeArray(int listSize) {
		int[] testArray = new int[listSize];
		Random rand = new Random();
		for (int i = 0; i < listSize - 1; i++) {
			testArray[i] = rand.nextInt(largeNumber - 1);
		}
		testArray[listSize - 1] = largeNumber;
		testArray[listSize - 2] = smallNumber;
		return testArray;
	}

	@Test
	@Timeout(1L)
	public void testFindLargestInLargeArray(){
		assertEquals(largeNumber, cut.findLargest(testLargeArray));
	}

	@Test
	@Timeout(1L)
	public void testFindSmallestInLargeArray(){
		assertEquals(smallNumber, cut.findSmallest(testLargeArray));
	}

	@Test
	@Timeout(1L)
	public void testFindLargest(){
		assertEquals(9532, cut.findLargest(testInput));
	}

	@Test
	@Timeout(1L)
	public void testFindSmallest(){
		assertEquals(-383, cut.findSmallest(testInput));
	}

	@Test
	@Timeout(1L)
	public void testSortAscendingSimple() {
		int[] input = new int[]{0, 5, 9, 43, -383, 9532, 24};
		int[] expected = new int[]{-383, 0, 5, 9, 24, 43, 9532};
		assertArrayEquals(expected, cut.sort(input, SelectionSort.SortOrder.ASC));
	}

	@Test
	@Timeout(1L)
	public void testSortDescendingSimple() {
		int[] input = new int[]{0, 5, 9, 43, -383, 9532, 24};
		int[] expected = new int[]{9532, 43, 24, 9, 5, 0, -383};
		assertArrayEquals(expected, cut.sort(input, SelectionSort.SortOrder.DESC));
	}

	@Test
	public void testSortTimesForLargeArray() {
		int[] input = generateLargeArray(99999);
		long start = System.nanoTime();
		cut.sort(input, SelectionSort.SortOrder.ASC);
		long end = System.nanoTime();
		assertEquals(smallNumber, input[0]);
		cut.sort(input, SelectionSort.SortOrder.DESC);
		assertEquals(largeNumber, input[0]);
		long secondEnd = System.nanoTime();
		System.out.println("first sort took: " + (end - start) + " nanos");
		System.out.println("second sort took:  " + (secondEnd - end) + " nanos");
 	}


}