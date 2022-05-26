package divideAndConquer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class SumArrayTest {

	private int[] smallInput = new int[]{0,1,2};
	private SumArray cut = new SumArray();
	private int largeArraySize = 99999;
	private int[] largeArray = buildLargeArray();


	private int[] buildLargeArray() {
		int[] array = new int[largeArraySize];
		for (int i = 0; i < largeArraySize; i++){
			array[i] = 1;
		}
		return array;
	}

	@Test
	@Timeout(1L)
	public void testSimpleShortArray(){
		assertEquals(3, cut.sumElements(smallInput));
	}

	@Test
	@Timeout(1L)
	public void testEmptyArray(){
		assertEquals(0, cut.sumElements(new int[0]));
	}

	@Test
	@Timeout(1L)
	public void testLargeArray(){
		// will this overflow due to recursion? Yes...
		assertEquals(largeArraySize, cut.sumElements(largeArray));
	}


}