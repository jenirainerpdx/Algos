

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;


public class InsertionSortTest {

	int[] ratio3 = new int[] {60,20,3};
	int[] ratio2 = new int[] {100,50,2};
	int[] ratio4 = new int[] {120,30,4};
	int[][] input = new int[][]{ratio3, ratio2, ratio4};


	@Test
	public void testSimpleSort(){
		int[] input = new int[]{8,7,3,1,9};
		int[] expected = new int[]{1,3,7,8,9};
		assertArrayEquals(expected, InsertionSort.insertionSort(input));
	}

	@Test
	public void testSortDescending() {
		int[] input = new int[]{8,7,3,1,9};
		int[] expected = new int[]{9,8,7,3,1};
		assertArrayEquals(expected, InsertionSort.insertionSortDescending(input));
	}

	@Test
	public void testInsertionSortOnMatrix() {
		int[][] expected = new int[][]{ratio2, ratio3, ratio4};
		assertArrayEquals(expected, InsertionSort.insertionSortOnMatrix(input));
	}

	@Test
	public void testDescMatrixSort() {
		int[][] expected = new int[][]{ratio4, ratio3, ratio2};
		assertArrayEquals(expected, InsertionSort.descendingMatrixSort(input));
	}

	@Test
	@Timeout(1L)
	public void testDescMatrixTwoElementsTheSameRatio() {
		int[] another3 = new int[]{30,10,3};
		int[][] input = new int[][]{ratio2, ratio3, ratio4, another3};
		int[][] actual = InsertionSort.descendingMatrixSort(input);
		assertEquals(ratio4, actual[0]);
		assertEquals(ratio2, actual[3]);
	}

}
