package lambdas;

import lambdas.domain.Album;
import lambdas.domain.Artist;
import lambdas.domain.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TestAlbums {

	private List<Album> testAlbumList;
	public static final String UNKNOWN_ORIGIN = "unknown";
	public static final String UNKNOWN_TRACK_LENGTH = "0:00";

	public TestAlbums() {
		buildTestAlbumList();
	}

	public List<Album> getTestAlbumList() {
		return testAlbumList;
	}

	public List<Artist> getAllArtists() {
		return this.testAlbumList
				.stream()
				.map(Album::getMusicians)
				.flatMap(Collection::stream)
				.distinct()
				.collect(Collectors.toList());
	}

	private void buildTestAlbumList() {
		testAlbumList = new ArrayList<>();
		Artist adele = new Artist("Adele", Collections.emptySet(), "London", false);
		Album adele21 = new Album("21",
				List.of(new Track("Rolling in the Deep", "3:49"),
						new Track("Rumour Has It", "3:43"),
						new Track("Turning Tables", "4:10"),
						new Track("Don't You Remember", "4:03"),
						new Track("Set Fire to the Rain", "4:01"),
						new Track("He Won't Go", "4:37"),
						new Track("I'll Be Waiting", "4:01"),
						new Track("Lovesong", "5:16"),
						new Track("Someone Like You", "4:47"),
						new Track("I found a Boy", "3:37")),
				List.of(adele));
		testAlbumList.add(adele21);
		Album adele25 = new Album("25",
				List.of(new Track("Hello", "4:55"),
						new Track("Send My Love (To Your New Lover)", "3:43"),
						new Track("I Miss You", "5:48"),
						new Track("When We Were Young", "4:51"),
						new Track("Remedy", "4:05"),
						new Track("Water Under the Bridge", "4:00"),
						new Track("River Lea", "3:45"),
						new Track("Love in the Dark", "4:46"),
						new Track("Million Years Ago", "3:46"),
						new Track("All I Ask", "4:32"),
						new Track("Sweetest Devotion", "4:12")),
				List.of(adele));
		testAlbumList.add(adele25);
		Artist donald = buildIndividualArtist("Donald Glover", Optional.of("California"));
		Artist jhene = buildIndividualArtist("Jhene Aiko", Optional.empty());
		Artist chance = buildIndividualArtist("Chance the Rapper", Optional.empty());
		Artist banks = buildIndividualArtist("Azealia Banks", Optional.empty());
		Artist misc = buildIndividualArtist("Not really an artist", Optional.empty());
		Artist childish = new Artist("Childish Gambino", Set.of(donald, jhene, chance, banks, misc), "California", true);
		Album gambinoBecause = new Album("Because the Internet",
				List.of(new Track("The Library (Intro)", UNKNOWN_TRACK_LENGTH),
						new Track("I.Crawl", UNKNOWN_TRACK_LENGTH),
						new Track("II.Worldstar", UNKNOWN_TRACK_LENGTH),
						new Track("Dial Up", UNKNOWN_TRACK_LENGTH),
						new Track("I. The Worst Guys (feat. Chance the Rapper)", UNKNOWN_TRACK_LENGTH),
						new Track("II. Shadows", UNKNOWN_TRACK_LENGTH),
						new Track("III. Telegraph Ave. (\"Oakland\" by Lloyd)", UNKNOWN_TRACK_LENGTH),
						new Track("IV. Sweatpants", UNKNOWN_TRACK_LENGTH),
						new Track("3005", UNKNOWN_TRACK_LENGTH),
						new Track("Playing Around Before the Party Starts", UNKNOWN_TRACK_LENGTH),
						new Track("I. The Party", UNKNOWN_TRACK_LENGTH),
						new Track("II. No Exit", UNKNOWN_TRACK_LENGTH),
						new Track("Death By Numbers", UNKNOWN_TRACK_LENGTH),
						new Track("I. Flight of the Navigator", UNKNOWN_TRACK_LENGTH),
						new Track("II. Zealots of Stockholm [Free Information]", UNKNOWN_TRACK_LENGTH),
						new Track("III. Urn", UNKNOWN_TRACK_LENGTH),
						new Track("I. Pink Toes (feat. Jhene Aiko)", UNKNOWN_TRACK_LENGTH),
						new Track("II. Earth: The Oldest Computer (The Last Night) [feat. Azealia Banks]", UNKNOWN_TRACK_LENGTH),
						new Track("III. Life: The Biggest Troll [Andrew Auernheimer]", UNKNOWN_TRACK_LENGTH)),
				List.of(childish, jhene, chance, banks, misc));
		testAlbumList.add(gambinoBecause);

		Artist bono = buildIndividualArtist("Bono", Optional.empty());
		Artist edge = buildIndividualArtist("Edge", Optional.empty());
		Artist adam = buildIndividualArtist("Adam Clayton", Optional.empty());
		Artist larry = buildIndividualArtist("Larry Mullen Jr.", Optional.empty());
		Artist U2 = new Artist("U2", Set.of(bono, edge, adam, larry), "Ireland", true);
		Album rattleAndHum = new Album("Rattle and Hum",
				List.of(
						new Track("Helter Skelter", "3:07"),
						new Track("Van Diemen's Land", "3:06"),
						new Track("Desire", "2:58"),
						new Track("Hawkmoon 269", "6:22"),
						new Track("All Along the Watchtower", "4:24"),
						new Track("I Still Haven't Found What I'm Looking For", "5:53"),
						new Track("Freedom for My People", "0:38"),
						new Track("Silver and Gold", "5:50"),
						new Track("Pride (In the Name of Love)", "4:27"),
						new Track("Angel of Harlem", "3:49"),
						new Track("Love Rescue Me", "6:24"),
						new Track("When Love Comes to Town", "4:14"),
						new Track("Heartland", "5:02"),
						new Track("God Part II", "3:15"),
						new Track("The Star Spangled Banner", "0:43"),
						new Track("Bullet the Blue Sky", "5:37"),
						new Track("All I want Is You", "6:30")
				),
				List.of(U2));
		testAlbumList.add(rattleAndHum);
		Album achtung = new Album("Achtung Baby",
				List.of(
						new Track("Zoo Station", "4:36"),
						new Track("Even Better Than the Real Thing", "3:41"),
						new Track("One", "4:36"),
						new Track("Until the End of the World", "4:39"),
						new Track("Who's Gonna Ride Your Wild Horses", "5:16"),
						new Track("So Cruel", "5:49"),
						new Track("The Fly", "4:29"),
						new Track("Mysterious Ways", "4:04"),
						new Track("Tryin' to Throw Your Arms Around the World", "3:53"),
						new Track("Ultraviolet (Light My Way)", "5:31"),
						new Track("Acrobat", "4:30"),
						new Track("Love is Blindness", "4:23")),
				List.of(U2));
		testAlbumList.add(achtung);
		Artist singer = buildIndividualArtist("Singer in Band1", Optional.of("Galway"));
		Artist drummer = buildIndividualArtist("Drummer in Band2", Optional.of("Sydney"));
		Artist band1 = new Artist("Band1", Set.of(singer), "Ireland", Boolean.TRUE);
		Artist band2 = new Artist("Band2", Set.of(drummer), "Australia", Boolean.TRUE);
		Artist guitar = buildIndividualArtist("Guitarist", Optional.empty());
		Artist compoundBand = new Artist("Band of Bands", Set.of(band1, band2, guitar), "Liverpool", Boolean.TRUE);
		Album fakeAlbum = new Album(
				"fake Album",
				List.of(
						new Track("Fake Track", "14:73")
				),
				List.of(compoundBand)
		);
		testAlbumList.add(fakeAlbum);
	}

	private Artist buildIndividualArtist(String name, Optional<String> location) {
		return new Artist(name, Collections.emptySet(), location.orElse(UNKNOWN_ORIGIN), Boolean.FALSE);
	}
}
