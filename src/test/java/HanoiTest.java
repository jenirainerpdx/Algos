import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HanoiTest {

	@Test
	public void testHanoiSolverSimple() {
		Hanoi testInput = new Hanoi(3);
		Hanoi actual = testInput.solve();
		assertTrue(actual.towerA.empty());
		assertTrue(actual.towerB.empty());
		Integer topElement = actual.towerC.pop();
		Integer middleElement = actual.towerC.pop();
		Integer bottom = actual.towerC.pop();
		assertTrue(bottom < middleElement);
		assertTrue(middleElement < topElement);
	}

	/**
	 * 29 is running at slightly under 9 seconds
	 * 32 is 1 min 10 seconds
	 */
	@Test
	public void testHanoiBigger(){
		int discNum = 32;
		Hanoi testInput = new Hanoi(discNum);
		Hanoi actual = testInput.solve();
		assertTrue(actual.towerA.empty(), "Start tower should be empty; it was not.");
		assertTrue(actual.towerB.empty(), "Temp tower should be empty; it was not.");
		assertEquals((discNum - 1), actual.towerC.pop(), "Top element of end tower was not numDiscs - 1");
	}

}