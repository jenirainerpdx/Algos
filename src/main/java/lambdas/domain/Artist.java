package lambdas.domain;

import java.util.Set;

public class Artist {

	private String name;
	private Set<Artist> members;
	private String origin;
	private boolean band;

	public Artist(String name, Set<Artist> members, String origin, boolean isArtistABand) {
		this.name = name;
		this.members = members;
		this.origin = origin;
		this.band = isArtistABand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Artist> getMembers() {
		return members;
	}

	public void setMembers(Set<Artist> members) {
		this.members = members;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public boolean isBand() {
		return band;
	}

	public void setBand(boolean band) {
		this.band = band;
	}
}
