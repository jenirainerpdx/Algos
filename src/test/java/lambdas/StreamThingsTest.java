package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;
import lambdas.domain.Track;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StreamThingsTest {

	private static final String BECAUSE_THE_INTERNET = "Because the Internet";
	private StreamThings classUnderTest = new StreamThings();
	private TestAlbums testAlbums = new TestAlbums();
	private Album malformedAlbum;

	{
		malformedAlbum = new Album(null,
				Collections.EMPTY_LIST,
				Collections.EMPTY_LIST);
	}

	private List<Album> albumsWithMalformed = buildMalformedList();

	private List<String> albumNames = List.of(
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
		assertEquals(4, actual.size(), "There are 4 in the group which have unknown origin.");
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

	@Test
	public void testGetTracksSortedByTrackName() {
		List<Track> actual = classUnderTest.getAllTracksSortedByTrackName(testAlbums.getTestAlbumList());
		Track first = actual.get(0);
		Track last = actual.get(actual.size() - 1);
		assertEquals("3005", first.getName());
		assertEquals("When We Were Young", last.getName());

	}

	@Test
	public void testGetAlbumsByTrackCount() {
		Map<String, Integer> actual = classUnderTest.getTrackCountsByAlbumName(testAlbums.getTestAlbumList());
		int previousAlbumCount = 0;
		for (String album : actual.keySet()) {
			int currentVal = actual.get(album);
			assertTrue(currentVal > previousAlbumCount);
			previousAlbumCount = currentVal;
		}
	}

	@Test
	public void testGetAlbumWithLargestTrackCount() {
		Album mostTracks = classUnderTest.findAlbumWithMostTracks(testAlbums.getTestAlbumList());
		assertEquals(BECAUSE_THE_INTERNET, mostTracks.getName());
	}

	@Test
	public void testGetAlbumWithMostArtists() throws Exception {
		Album mostArtists = classUnderTest.findAlbumWithBiggestBand(testAlbums.getTestAlbumList());
		assertEquals(BECAUSE_THE_INTERNET, mostArtists.getName());
	}

	@Test
	public void testFindByName() {
		Optional<Album> maybeBecause = classUnderTest
				.findAlbumByName(BECAUSE_THE_INTERNET, testAlbums.getTestAlbumList());
		assertTrue(maybeBecause.isPresent());
		Album maybe = maybeBecause.get();
		assertEquals("Childish Gambino", maybe.getMusicians().get(0).getName());
	}

	@Test
	public void testGetListOfMusiciansOnAlbum() {
		Album because = classUnderTest
				.findAlbumByName(BECAUSE_THE_INTERNET, testAlbums.getTestAlbumList())
				.get();
		List<String> musicians = classUnderTest.getListOfMusiciansOnAlbum(because);
		assertEquals(5, musicians.size());
	}

	@Test
	public void testGetAlbumSetTrackCount() {
		Long count = classUnderTest.getCountOfAllTracksInCollection(testAlbums.getTestAlbumList());
		assertEquals(68L, count);
	}

}