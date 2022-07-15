package lambdas.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Album {

	private String name;
	private Stream<Track> tracks;
	private List<Artist> musicians;

	public Album(String name, Stream<Track> trackStream, List<Artist> artists) {
		this.name = name;
		this.tracks = trackStream;
		this.musicians = artists;
	}

	public Album(String albumName, List<Track> tracks, List<Artist> artists) {
		this.name = albumName;
		this.tracks = tracks.stream();
		this.musicians = artists;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stream<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Stream<Track> tracks) {
		this.tracks = tracks;
	}

	public List<Artist> getMusicians() {
		return musicians;
	}

	public void setMusicians(List<Artist> musicians) {
		this.musicians = musicians;
	}
}
