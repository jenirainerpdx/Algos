package lambdas.domain;

public class Track {

	private String name;
	private Long trackLength;

	public Track(String name, String length) {
		this.name = name;
		this.trackLength = convertLengthFromString(length);
	}

	/**
	 * Scoped to package for unit testing purposes.
	 * Takes a parameter like 1:14 (1 minute 14 seconds) and transforms it into seconds.
	 * @param length String
	 * @return Long the count of seconds
	 */
	Long convertLengthFromString(String length) {
		String[] minSec = length.split(":");
		long lengthInSeconds = 0;
		if (minSec.length == 2) {
			long minutes = Long.valueOf(minSec[0]);
			long seconds = Long.valueOf(minSec[1]);
			lengthInSeconds = seconds + (60L * minutes);
		}
		return lengthInSeconds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTrackLength() {
		return trackLength;
	}

	public void setTrackLength(Long trackLength) {
		this.trackLength = trackLength;
	}
}
