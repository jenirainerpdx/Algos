package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Chapter3ExercisesTest {

	private final Chapter3Exercises classUnderTest = new Chapter3Exercises();
	private final IntStream testNumbers;
	private final IntStream largeStreamOfNumbers;
	private final Integer sumOfLargeStream;
	private final TestAlbums testAlbums = new TestAlbums();

	{
		testNumbers = IntStream.of(5, 10, 15, 20);
		largeStreamOfNumbers = IntStream.range(-50, 100_000);
		int tempTotal = 0;
		for (int i = -50; i < 100_000; i++) {
			tempTotal += i;
		}
		sumOfLargeStream = tempTotal;
	}

	@Test
	public void testAddMethodWithSmallSimpleList() {
		assertEquals(50,
				classUnderTest.addNumbersInAStream(testNumbers));
	}

	@Test
	@Timeout(1L)
	public void testAddLargeList() {
		assertEquals(sumOfLargeStream,
				classUnderTest.addNumbersInAStream(largeStreamOfNumbers));
	}

	@Test
	public void testGetNamesAndOrigins() {
		Stream<Artist> artistStream = testAlbums.getAllArtists().stream();
		List<String> actual = classUnderTest.getMusicianNamesAndOriginsFromArtists(artistStream);
		assertTrue(actual.contains("Donald Glover - California"),
				"The list should contain a reference to Donald Glover.");
		assertTrue(actual.contains("Band2 - Australia"),
				"The list should refer to Band2 from Australia.");
		assertFalse(actual.contains("Adele - London"),
				"Adele gets filtered out as she is a band rather than a musician.");
	}

	/**
	 * Trying to count the getTracks Stream once again leads to IllegalStateException:
	 * stream has already been operated upon or closed.  I am beginning to wonder if it
	 * is inadvisable to return a stream in the getter of the domain object, perhaps?
	 */
	@Test
	public void testFindSmallAlbums() {
		Stream<Album> testAlbumStream = testAlbums.getTestAlbumList().stream();
		List<Album> actual = classUnderTest.findSmallAlbums(testAlbumStream);
		assertEquals(1, actual.size());
		Album smallAlbum = actual.get(0);
		assertEquals("fake Album", smallAlbum.getName());
	//	assertEquals(0, smallAlbum.getTracks().count());
	}

	@Test
	public void testGetMemberCount(){
		Stream<Artist> artistStream = testAlbums.getAllArtists().stream();
		long actual = classUnderTest.getTotalMembersFromArtists(artistStream);
		assertEquals(8L, actual);
	}

	@Test
	public void testLimit(){
		Stream<Album> testAlbumsToLimit = testAlbums.getTestAlbumList().stream();
		Stream<Album> actual = classUnderTest.limitAListOfAlbums(testAlbumsToLimit);
		assertEquals(2, actual.count());
	}

	@ParameterizedTest
	@Timeout(1L)
	@CsvSource({
			"abcd, 4",
			"HELLO WORLD!, 0",
			"Hello Mabel., 8",
			"1008, 0"
	})
	public void testCountLCChars(String inString, int expected){
		assertEquals(expected, classUnderTest.countLowercaseLetters(inString));
	}

	@Test
	public void testFindStringWithMostLCLetters(){
		List<String> inputList = List.of(
				"Hello",
				"abcdefghijklmno",
				"a"
		);
		assertEquals(Optional.of("abcdefghijklmno"),
				classUnderTest.findStringWithMostLowercaseLetters(inputList));
	}

	@Test
	public void testFindStringWithMostLCLettersEmptyList(){
		List<String> inputList = Collections.emptyList();
		assertEquals(Optional.empty(),
				classUnderTest.findStringWithMostLowercaseLetters(inputList));
	}
}