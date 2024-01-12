package misc.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedSquaresTest {

	private SortedSquares cut = new SortedSquares();

	@Test
	public void testSimple(){
		int[] input = new int[]{-3,1,4};
		int[] expected = new int[]{1, 9, 16};
		assertArrayEquals(expected, cut.sortSq(input));
	}

	@Test
	public void sameTestUsingStream(){
		int[] input = new int[]{-3,1,4};
		int[] expected = new int[]{1, 9, 16};
		assertArrayEquals(expected, cut.sortedSquaresStream(input));
	}

}