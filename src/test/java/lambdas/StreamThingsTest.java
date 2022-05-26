package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreamThingsTest {

	StreamThings classUnderTest = new StreamThings();
	TestAlbums testAlbums = new TestAlbums();
	Album malformedAlbum = new Album(null,
			Collections.EMPTY_LIST,
			Collections.EMPTY_LIST);
	List<Album> albumsWithMalformed = buildMalformedList();

	List<String> albumNames = List.of(
			"21",
			"25",
			"BECAUSE THE INTERNET",
			"RATTLE AND HUM",
			"ACHTUNG BABY"
	);

	private List<Album> buildMalformedList() {
		List<Album> albumsList = new ArrayList<>(testAlbums.getTestAlbumList());
		albumsList.add(malformedAlbum);
		albumsList.add(null);
		return albumsList;
	}


	@Test
	public void testGetArtistsFromLondon() {
		List<Artist> artistsFromLondon = classUnderTest.findArtistsFromLocation("London",
				testAlbums.getAllArtists());
		assertEquals(1, artistsFromLondon.size(), "Only Adele is from London.");
	}

	@Test
	public void testGetArtistOfUnknownOrigin() {
		List<Artist> actual = classUnderTest.findArtistsFromLocation(TestAlbums.UNKNOWN_ORIGIN,
				testAlbums.getAllArtists());
		assertEquals(3, actual.size(), "There are 3 in the group which have unknown origin.");
	}

	@Test
	public void testGetAlbumNames() {
		List<String> actual = classUnderTest.getAllAlbumNamesInAllCaps(testAlbums.getTestAlbumList());
		assertEquals(albumNames, actual);
	}

	@Test
	public void testGetAlbumNamesWhenOneAlbumNameIsNull() {
		List<String> actual = classUnderTest.getAllAlbumNamesInAllCaps(albumsWithMalformed);
		assertTrue(actual.containsAll(albumNames));
		assertTrue(actual.contains("NO NAME"));
		assertTrue(actual.contains("NULL ALBUM"));
	}

}