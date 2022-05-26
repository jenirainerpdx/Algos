import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertionSorterTest {

	private InsertionSorter cut;
	private int[] input1;
	private List inputWithNegatives;
	private List veryLargeInput;

	@BeforeEach
	public void setUp() throws Exception {
		cut = new InsertionSorter();
		input1 = new int[]{5, 2, 7, 1, 3};

		for (Number i : input1) {
			// note: how will I abstract out the types of list... use of generics.
			System.out.println(i);
		}
	}

	@Test
	public void sort() {
		//properly sorted of input1 would be:  1, 2, 3, 5, 7
		int[] actual = cut.sort(input1);
		assertEquals(1, actual[0]);
		assertEquals(2, actual[1]);
		assertEquals(3, actual[2]);
		assertEquals(5, actual[3]);
		assertEquals(7, actual[4]);
	}
}