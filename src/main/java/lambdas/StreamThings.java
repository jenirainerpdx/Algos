package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class StreamThings {

	/**
	 * Simple filter operation
	 *
	 * @param location a String to filter on with the artist origin property.  The filtering is case insensitive.
	 * @param artists  List of Artist objects
	 * @return The list of artists who have the origin specified.
	 * @TODO: code defensively against null origin on artist.
	 */
	public List<Artist> findArtistsFromLocation(String location, List<Artist> artists) {
		return artists.stream()
				.filter(artist -> artist.getOrigin().equalsIgnoreCase(location))
				.collect(Collectors.toList());
	}

	public List<String> getAllAlbumNamesInAllCaps(List<Album> albums) {
		return albums.stream()
				.map(a -> getAlbumNameInAllCaps(a))
				.collect(Collectors.toList());
	}


	/**
	 * @param a - Album
	 * @return String - the album name in upper case.
	 */
	private String getAlbumNameInAllCaps(Album a) {
		if (null != a) {
			String name = a.getName();
			if (null != name && !name.isEmpty()) {
				return name.toUpperCase(Locale.ROOT);
			} else {
				throw new IllegalArgumentException("The Name of the album was null or empty.");
			}
		} else {
			throw new IllegalArgumentException("The album passed in was null.");
		}
	}


}
