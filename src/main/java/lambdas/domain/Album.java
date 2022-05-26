package lambdas.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Album {

	private String name;
	private List<Track> tracks;
	private List<Artist> musicians;

	public Album(String albumName, List<Track> tracks, List<Artist> artists) {
		this.name = albumName;
		this.tracks = tracks;
		this.musicians = artists;
	}

	/**
	 * Note:  something with type inference will not allow me to put the name param first.
	 * I would like to understand why.
	 *
	 * @param tracks
	 * @param musicians
	 * @param albumName
	 */
	public Album(List<? extends String> tracks, List<Artist> musicians, String albumName) {
		this(albumName, tracks
						.stream()
						.map(t -> new Track(t))
						.collect(Collectors.toList()),
				musicians);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public List<Artist> getMusicians() {
		return musicians;
	}

	public void setMusicians(List<Artist> musicians) {
		this.musicians = musicians;
	}
}
