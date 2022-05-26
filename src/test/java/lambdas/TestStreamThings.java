package lambdas;

import lambdas.domain.Artist;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStreamThings {

	private TestAlbums testAlbums = new TestAlbums();

	@Test
	public void testGetArtists() {
		List<Artist> actual = testAlbums.getAllArtists();
		assertEquals(6, actual.size(),
				"The artists list includes: adele, childish, jhene, chance, banks and u2");
		List<Artist> adeleList = actual
				.stream()
				.filter(artist ->
						artist.getName().equalsIgnoreCase("Adele")
				)
				.collect(Collectors.toList());
		assertEquals(1, adeleList.size(),
				"We should find a single artist named Adele.");
		Artist adele = adeleList.get(0);
		assertEquals("Adele", adele.getName());
		assertEquals("London", adele.getOrigin());
		assertEquals(1, adele.getMemberNames().size());
	}

	@Test
	public void testFindLondonArtists() {
		List<Artist> artistsFromLondon = testAlbums.getAllArtists()
				.stream()
				.filter(a -> a.getOrigin() == "London")
				.collect(Collectors.toList());
		assertEquals(1, artistsFromLondon.size(),
				"Only Adele is from London.");
	}

}
