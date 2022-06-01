package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class encapsulates methods that respond to the exercises at the end of Chapter 3.
 */
public class Chapter3Exercises {

	/**
	 * Question #1a.
	 * @param numbers a Stream of type Integer.  (should this be IntStream instead?)
	 * @return an Integer
	 */
	public Integer addNumbersInAStream(IntStream numbers) {
		return numbers.reduce(0, Integer::sum);
	}

	/**
	 * Question 1b.
	 * @param artists a Stream of Artist objects
	 * @return List of Strings.  Each String in the list is the concatenation of artist name '-' origin
	 */
	public List<String> getMusicianNamesAndOriginsFromArtists(Stream<Artist> artists) {
		return artists
				.flatMap(artist -> artist.getMembers().stream())
				.map(this::grabNameAndOrigin)
				.collect(Collectors.toList());
	}

	private String grabNameAndOrigin(Artist artist) {
		return artist.getName() + " - " + artist.getOrigin();
	}

	/**
	 * Question 1c.
	 * @param albums a Stream of type Album
	 * @return a List of type Album which is filtered to only include albums that have less than 3 tracks.
	 */
	public List<Album> findSmallAlbums(Stream<Album> albums) {
		return albums.filter(album -> album.getTracks().count() < 3)
				.collect(Collectors.toList());
	}

	/**
	 * Question 2
	 * @param artistStream Stream of type Artist
	 * @return long the count of total members.
	 */
	public long getTotalMembersFromArtists(Stream<Artist> artistStream) {
		return artistStream
				.map(artist -> artist.getMembers().size())
				.count();
	}

	/**
	 * Question 3:  Eager or lazy:
	 * a.  boolean anyMatch(Predicate predicate)  eager (anyMatch is a short circuiting, terminal operation)
	 * b.  Stream<T> limit(long maxSize)  - lazy (code below tries using limit to see what it does.)
	 * Question 4: are these higher order functions:
	 * a.  anyMatch:  yes.  Predicate is a Functional Interface.
	 * b.  limit:  no - neither the params (long) nor the return types are FI.
	 * Question 5:  the lambda expression passed in is not a pure function.  It increments the atomicInteger count.
	 */
	public Stream<Album> limitAListOfAlbums(Stream<Album> albumsIn) {
		return albumsIn.limit(2);
	}

	/**
	 * Question 6:  Count number of lc letters in a String.
	 * 65-90 are uppercase letters
	 * 97-122 are LC letters
	 */
	public long countLowercaseLetters(String inString) {
		return inString
				.chars()
				.filter(i -> (i > 96 && i < 123))
				.count();
	}

	/**
	 * Question 7:  find the String with largest count of LC letters
	 * from a list of Strings.
	 */
	public Optional<String> findStringWithMostLowercaseLetters(List<String> inStrings) {
		return inStrings.stream()
				.max(Comparator.comparing(s -> countLowercaseLetters(s)));
	}

}
