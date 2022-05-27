package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestAlbums {

	private List<Album> testAlbumList;
	public static final String UNKNOWN_ORIGIN = "unknown";

	public TestAlbums() {
		buildTestAlbumList();
	}

	public List<Album> getTestAlbumList() {
		return testAlbumList;
	}

	public List<Artist> getAllArtists() {
		return this.testAlbumList
				.stream()
				.map(album -> album.getMusicians())
				.flatMap(artists -> artists.stream())
				.distinct()
				.collect(Collectors.toList());
	}

	private void buildTestAlbumList() {
		testAlbumList = new ArrayList<>();
		Artist adele = new Artist("Adele", Set.of("Adele"), "London");
		Album adele21 = new Album(
				List.of("Rolling in the Deep",
						"Rumour Has It",
						"Turning Tables",
						"Don't You Remember",
						"Set Fire to the Rain",
						"He Won't Go",
						"I'll Be Waiting",
						"Lovesong",
						"Someone Like You",
						"I found a Boy"),
				List.of(adele),
				"21");
		testAlbumList.add(adele21);
		Album adele25 = new Album(
				List.of("Hello",
						"Send My Love (To Your New Lover)",
						"I Miss You",
						"When We Were Young",
						"Remedy",
						"Water Under the Bridge",
						"River Lea",
						"Love in the Dark",
						"Million Years Ago",
						"All I Ask",
						"Sweetest Devotion"),
				List.of(adele),
				"25"
		);
		testAlbumList.add(adele25);
		Artist childish = new Artist("Childish Gambino", Set.of("Donald Glover"), "California");
		Artist jhene = new Artist("Jhene Aiko", Set.of("Jhene"), UNKNOWN_ORIGIN);
		Artist chance = new Artist("Chance the Rapper", Set.of("Chance"), UNKNOWN_ORIGIN);
		Artist banks = new Artist("Azealia Banks", Set.of("Banks"), UNKNOWN_ORIGIN);
		Artist misc = new Artist("Not really an artist", Set.of("Unknown"), UNKNOWN_ORIGIN);
		Album gambinoBecause = new Album(
				List.of("The Library (Intro)",
						"I.Crawl",
						"II.Worldstar",
						"Dial Up",
						"I. The Worst Guys (feat. Chance the Rapper)",
						"II. Shadows",
						"III. Telegraph Ave. (\"Oakland\" by Lloyd)",
						"IV. Sweatpants",
						"3005",
						"Playing Around Before the Party Starts",
						"I. The Party",
						"II. No Exit",
						"Death By Numbers",
						"I. Flight of the Navigator",
						"II. Zealots of Stockholm [Free Information]",
						"III. Urn",
						"I. Pink Toes (feat. Jhene Aiko)",
						"II. Earth: The Oldest Computer (The Last Night) [feat. Azealia Banks]",
						"III. Life: The Biggest Troll [Andrew Auernheimer]"
				),
				List.of(childish, jhene, chance, banks, misc),
				"Because the Internet"
		);
		testAlbumList.add(gambinoBecause);
		Artist U2 = new Artist("U2", Set.of("Bono", "Edge", "Adam Clayton", "Larry Mullen Jr."), "Ireland");
		Album rattleAndHum = new Album(
				List.of(
						"Helter Skelter",
						"Van Diemen's Land",
						"Hawkmoon 269",
						"All Along the Watchtower",
						"I Still Haven't Found What I'm Looking For",
						"Freedom for My People",
						"Silver and Gold",
						"Pride (In the Name of Love)",
						"Angel of Harlem",
						"Love Rescue Me",
						"When Love Comes to Town",
						"Heartland",
						"God Part II",
						"The Star Spangled Banner",
						"Bullet the Blue Sky",
						"All I want Is You"
				),
				List.of(U2),
				"Rattle and Hum"
		);
		testAlbumList.add(rattleAndHum);
		Album achtung = new Album(
				List.of("Interference",
						"Even Better Than the Real Thing",
						"Interference",
						"Mysterious Ways",
						"One (version 1)",
						"The Fly",
						"Interference",
						"Even Better Than the Real Thing (dance mix)",
						"One (version 2)",
						"Even Better Than the Real Thing",
						"One (version 3)",
						"Until the End of the World"
				),
				List.of(U2),
				"Achtung Baby"
		);
		testAlbumList.add(achtung);
	}
}
