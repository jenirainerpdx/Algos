package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;
import lambdas.domain.Track;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamThings {

	/**
	 * Simple filter operation
	 *
	 * @param location a String to filter on with the artist origin property.  The filtering is case insensitive.
	 * @param artists  List of Artist objects
	 * @return The list of artists who have the origin specified.
	 * TODO: code defensively against null origin on artist.
	 */
	public List<Artist> findArtistsFromLocation(String location, List<Artist> artists) {
		return artists.stream()
				.filter(artist -> artist.getOrigin().equalsIgnoreCase(location))
				.collect(Collectors.toList());
	}

	public Optional<Album> findAlbumByName(String albumName, List<Album> albums) {
		return albums.stream()
				.filter(album -> album.getName().equalsIgnoreCase(albumName))
				.findFirst();
	}

	/**
	 * Simple map operation
	 *
	 * @param albums a List of Album objects
	 * @return a list of Strings which are the names of the Album objects transformed to UpperCase.
	 */
	public List<String> getAllAlbumNamesInAllCaps(List<Album> albums) {
		return albums.stream()
				.map(this::getAlbumNameInAllCaps)
				.collect(Collectors.toList());
	}

	/**
	 * A flatmap operation to combine the lists of tracks within the list of albums and then sort by track name.
	 *
	 * @param albums List of type Album
	 * @return List of Track sorted by trackName.
	 * TODO: figure out why flatMapToDouble, flatMapToInt, flatMapToLong exist
	 * is it simply to allow stream of primitives rather than objects?
	 */
	public List<Track> getAllTracksSortedByTrackName(List<Album> albums) {
		return albums.stream()
				.flatMap(Album::getTracks)
				.sorted(Comparator.comparing(Track::getName))
				.collect(Collectors.toList());
	}

	/**
	 * I feel confident I could simply call getAllTracksSortedByTrackName and then take the list size.
	 * This is simply to exercise using reduce.
	 * I need to use a reducing collector to get there, rather than simply calling reduce.
	 *
	 * @param albums List of Album objects in the collection
	 * @return Long a count of all the tracks within the collection of Albums.
	 * TODO: come back to this later.  I can't quite get beyond the acc needing to be different than the element, in terms of type.
	 */
	public Long getCountOfAllTracksInCollection(List<Album> albums) {
		long acc = 0L;
		for (Album album : albums) {
			acc = (acc + album.getTracks().count());
		}
		return acc;
	}

	/**
	 * Somewhat convoluted for the sorting of the map.  Not sure if it is efficient.
	 *
	 * @param albums List of Album objects
	 * @return a linkedHashMap so that it holds sorting where the Album Name is key and the value is count of tracks.
	 * Sort order is by count of tracks in asc.
	 */
	public Map<String, Long> getTrackCountsByAlbumName(List<Album> albums) {
		Map<String, Long> unsorted = albums.stream()
				.collect(Collectors.toMap(
						Album::getName,
						album -> album.getTracks().count()
				));
		return unsorted.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey,
						Map.Entry::getValue,
						(e1, e2) -> e1, LinkedHashMap::new));
	}

	/**
	 * I don't know how to do this with a reduce...
	 * I have to get the max from a Stream of Streams.
	 * Nested stream collect type ops don't work.
	 * At this point, the only option I can see as viable is calling max on
	 * the values of the map returned by the getTrackCountsByAlbumName.
	 *
	 * @param albums List of Album objects
	 * @return Album the album with the most tracks.
	 */
	public Album findAlbumWithMostTracks(List<Album> albums) throws Exception {
		Album albumWithMost;
		// treating the map as unsorted for learning purposes.
		Map<String, Long> trackCountByAlbumName = getTrackCountsByAlbumName(albums);
		Optional<Map.Entry<String, Long>> mostTracks = trackCountByAlbumName
				.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue());
		if (mostTracks.isPresent()) {
			String albumName = mostTracks.get().getKey();
			albumWithMost = findAlbumByName(albumName, albums).get();
		} else {
			throw new Exception("Unable to find album with tracks in this list.");
		}
		return albumWithMost;
	}

	/**
	 * TODO: Investigate how to cleanly handle the optional.
	 *
	 * @param albums List of Album objects
	 * @return an Album - the one with the most musicians.
	 * @throws Exception - if no Album is found while doing this evaluation.
	 */
	public Album findAlbumWithBiggestBand(List<Album> albums) throws Exception {
		Optional<Album> biggestBandOption = albums.stream()
				.max(Comparator.comparing(album -> getListOfMusiciansOnAlbum(album).size()));
		if (biggestBandOption.isPresent()) {
			return biggestBandOption.get();
		} else {
			throw new Exception("No Album found.");
		}

	}

	/**
	 * @param album an Album object
	 * @return list of Musicians that are in all the Artists associated with the album.
	 * Structure is Album -> [Artist] where each Artist -> [Musician]
	 */
	List<String> getListOfMusiciansOnAlbum(Album album) {
		return album.getMusicians()
				.stream()
				.flatMap(musician -> musician.getMembers().stream())
				.filter(artist -> !artist.isBand())
				.map(Artist::getName)
				.collect(Collectors.toList());
	}

	public Set<String> getListOfBandLocationsForAnAlbum(Album album) {
		return album.getMusicians()
				.stream()
				.flatMap(musician -> musician.getMembers().stream())
				.filter(Artist::isBand)
				.map(Artist::getOrigin)
				.collect(Collectors.toUnmodifiableSet());
	}

	/**
	 * @param a - Album
	 * @return String - the album name in upper case.
	 * TODO: see if there is a way to update this to make things non-nullable
	 */
	private String getAlbumNameInAllCaps(Album a) {
		String upperName;
		if (null != a) {
			String name = a.getName();
			if (null != name && !name.isEmpty()) {
				upperName = name.toUpperCase(Locale.ROOT);
			} else {
				upperName = "NO NAME";
			}
		} else {
			upperName = "NULL ALBUM";
		}
		return upperName;
	}


}
