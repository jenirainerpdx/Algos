package lambdas.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {


	@Test
	public void testSimpleTrackConstruction() {
		Track actual = new Track("Fake Track", "5:34");
		assertEquals(334L, actual.getTrackLength());
	}

}