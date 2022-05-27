package lambdas.domain;

import java.util.List;

public class Album {

	private String name;
	private List<Track> tracks;
	private List<Artist> musicians;

	public Album(String albumName, List<Track> tracks, List<Artist> artists) {
		this.name = albumName;
		this.tracks = tracks;
		this.musicians = artists;
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
