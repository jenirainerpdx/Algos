package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;
import lambdas.domain.Track;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
				.flatMap(album -> album.getTracks().stream())
				.sorted(Comparator.comparing(Track::getName))
				.collect(Collectors.toList());
	}

	/**
	 * Somewhat convoluted for the sorting of the map.  Not sure if it is efficient.
	 *
	 * @param albums List of Album objects
	 * @return a linkedHashMap so that it holds sorting where the Album Name is key and the value is count of tracks.
	 * Sort order is by count of tracks in asc.
	 */
	public Map<String, Integer> getTrackCountsByAlbumName(List<Album> albums) {
		Map<String, Integer> unsorted = albums.stream()
				.collect(Collectors.toMap(
						album -> album.getName(),
						album -> album.getTracks().size()
				));
		return unsorted.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(entry -> entry.getKey(),
						entry -> entry.getValue(),
						(e1, e2) -> e1, LinkedHashMap::new));
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
