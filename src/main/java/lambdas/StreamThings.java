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
