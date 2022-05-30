package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class encapsulates methods that respond to the exercises at the end of Chapter 3.
 */
public class Chapter3Exercises {

	/**
	 * @param numbers a Stream of type Integer.  (should this be IntStream instead?)
	 * @return an Integer
	 */
	public Integer addNumbersInAStream(IntStream numbers) {
		return numbers.reduce(0, Integer::sum);
	}

	/**
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

	public List<Album> findSmallAlbums(Stream<Album> albums) {
		return albums.filter(album -> album.getTracks().count() < 3)
				.collect(Collectors.toList());
	}

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
	 */
	public Stream<Album> limitAListOfAlbums(Stream<Album> albumsIn) {
		return albumsIn.limit(2);
	}

}
