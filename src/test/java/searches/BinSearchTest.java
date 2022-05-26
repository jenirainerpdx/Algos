package searches;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BinSearchTest {

	private BinSearch cut = new BinSearch();
	private int[] simpleList = new int[]{ 1, 20, 13, 15, 17, 49, 34, 1008, 29};
	private int willBeThere = 53;
	private int willNotBeThere = 9985;
	private int listSize = 999999;
	private int[] veryLargeList = generateLargeListOfInt(listSize, willBeThere, willNotBeThere);

	private int[] generateLargeListOfInt(int listSize, int alwaysInclude, int alwaysExclude) {
		Random rand = new Random();
		int[] thisList = new int[listSize];
		boolean alwaysIncludeValueIncluded = false;
		for (int i = 0; i < thisList.length; i++) {
			int randInt = rand.nextInt();
			if (randInt == alwaysInclude) {
				alwaysIncludeValueIncluded = true;
			} else if (randInt == alwaysExclude) {
				// put in the alwaysInclude value instead. (even if this happens more than 1 x.
				thisList[i] = alwaysInclude;
				alwaysIncludeValueIncluded = true;
			} else {
				thisList[i] = randInt;
			}
		}
		if (!alwaysIncludeValueIncluded) {
			thisList[0] = alwaysInclude;
		}
		return thisList;
	}

	@ParameterizedTest
	@Timeout(1L)
	@CsvSource({
			"1, 0",
			"13, 1",
			"15, 2",
			"17, 3",
			"20, 4",
			"29, 5",
			"34, 6",
			"49, 7",
			"1008, 8"
	})
	public void simpleFindTest(int x, int expected) {
		// sorted, this would look like:  1, 13, 15, 17, 20, 29, 34, 49, 1008
		int where = -15; // some absurd incorrect number.
		try {
			where = cut.findIt(simpleList, x);
		} catch (Exception e) {
			fail("The method returned exception as item not found; but, it should have been found.");
		}
		assertEquals(expected, where);
	}

	@Test
	@Timeout(1L)
	public void simpleFindElementNotThere() {
		assertThrows(Exception.class, () -> {
			int where = cut.findIt(simpleList, 99);
		});
	}

	@Test
	@Timeout(1L)
	public void simpleFindElementNotThereGreaterThanEach() {
		assertThrows(Exception.class, () -> {
			int where = cut.findIt(simpleList, 99999);
		});
	}

	@Test
	@Timeout(1L)
	public void testVeryLargeList() throws Exception {
		int where = cut.findIt(veryLargeList, willBeThere);
		System.out.println("Our value was " + where + " in our list.");
	}

	@Test
	@Timeout(1L)
	public void testVeryLargeListDoesntInclude() {
		assertThrows(Exception.class, () -> {
			int where = cut.findIt(veryLargeList, willNotBeThere);
		});
	}

}