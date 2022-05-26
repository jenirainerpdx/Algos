package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreamThingsTest {

	StreamThings classUnderTest = new StreamThings();
	TestAlbums testAlbums = new TestAlbums();


	@Test
	public void testGetArtistsFromLondon() {
		List<Artist> artistsFromLondon = classUnderTest.findArtistsFromLocation("London",
				testAlbums.getAllArtists());
		assertEquals(1, artistsFromLondon.size(), "Only Adele is from London.");
	}

	@Test
	public void testGetArtistOfUnknownOrigin() {
		List<Artist> actual = classUnderTest.findArtistsFromLocation(testAlbums.UNKNOWN_ORIGIN,
				testAlbums.getAllArtists());
		assertEquals(3, actual.size(), "There are 3 in the group which have unknown origin.");
	}

	@Test
	public void testGetAlbumNames() {
		List<String> expected = List.of(
				"21",
				"25",
				"BECAUSE THE INTERNET",
				"RATTLE AND HUM",
				"ACHTUNG BABY"
		);
		List<String> actual = classUnderTest.getAllAlbumNamesInAllCaps(testAlbums.getTestAlbumList());
		assertEquals(expected, actual);
	}


}