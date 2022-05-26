import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class CarFuelingTest {

	@Test
	@Timeout(1L)
	public void testMinRefillsNoRefillNeeded() {
		int dist = 200;
		int tankSize = 250;
		int[] stops = new int[]{100, 150};
		int expected = 0;
		int actual = CarFueling.computeMinRefills(dist, tankSize, stops);
		assertEquals(expected, actual);
	}

	@Test
	@Timeout(1L)
	public void testMinRefillsSimple() {
		int dist = 950;
		int tankSize = 400;
		int[] stops = new int[]{200,375,550,750};
		int expected = 2;
		int actual = CarFueling.computeMinRefills(dist, tankSize, stops);
		assertEquals(expected, actual);
	}

	@Test
	@Timeout(1L)
	public void testMinRefillsImpossible() {
		int dist = 500;
		int tankSize = 1;
		int[] stops = new int[]{1,2,5,9};
		int expected = -1;
		int actual = CarFueling.computeMinRefills(dist, tankSize, stops);
		assertEquals(expected, actual);
	}

	@Test
	@Timeout(1L)
	public void testMinRefillsAlsoImpossible() {
		int dist = 700;
		int tankSize = 200;
		int[] stops = new int[]{100,200,300,400};
		int expected = -1;
		int actual = CarFueling.computeMinRefills(dist, tankSize, stops);
		assertEquals(expected, actual);
	}

	@Test
	@Timeout(1L)
	public void testMinRefillsWithNoStopsDistanceLessThanTankSize(){
		int dist = 200;
		int tankSize = 400;
		int[] stops = new int[0];
		int expected = 0;
		int actual = CarFueling.computeMinRefills(dist, tankSize, stops);
		assertEquals(expected, actual);
	}

	@Test
	@Timeout(1L)
	public void testMinRefillsNoStopsDistanceGreaterThanTankSize(){
		int dist = 600;
		int tankSize = 400;
		int[] stops = new int[0];
		int expected = -1;
		int actual = CarFueling.computeMinRefills(dist, tankSize, stops);
		assertEquals(expected, actual);
	}

}