package dataStructures.trie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class TrieNodeTest {

	private TrieNode cut;

	@Test
	void testGetKeyIfAddableToRoot() throws InvalidInputException {
		cut = new TrieNode("", Boolean.FALSE);
		char actual = cut.getKeyIfAddable("a", Boolean.FALSE);
		assertEquals('a', actual, "The returned key should be a");
	}

	@ParameterizedTest
	@CsvSource({
			"able,true,e,The last character of able is e",
			"Able,false,e,The last character of able is e and this was not case sensitive",
			"ablo,false,o,The last character of ablo is o"
	})
	void testGetKeyIfAddableForSimulatedNonRoot(String input, boolean caseSensitive, char expected, String message) throws InvalidInputException {
		cut = new TrieNode("abl", Boolean.FALSE);
		char actual = cut.getKeyIfAddable(input, caseSensitive);
		assertEquals(expected, actual, message);
	}

	@ParameterizedTest
	@CsvSource({
			"Able,true",
			"a,false",
			"a,true",
			"Car,false",
			"cars,false"
	})
	void testGetKeyIfAddableThrowsForFailedValidation(String stem, boolean caseSensitive) {
		cut = new TrieNode("abl", Boolean.FALSE);
		assertThrows(InvalidInputException.class, () -> {
			cut.getKeyIfAddable(stem, caseSensitive);
		});
	}

	@ParameterizedTest
	@CsvSource({
			"a,true,true",
			"A,true,true",
			"A,true,false",
			"Z,true,true"
	})
	void testAddChildSimple(String stem, boolean word, boolean caseSensitive) throws InvalidInputException {
		cut = new TrieNode("", Boolean.FALSE);
		TrieNode actual = cut.addChild(stem, word, caseSensitive);
		assertEquals(cut, actual.getParent());
		assertTrue(actual.getChildren().isEmpty());
		assertEquals(word, actual.isWord());
		assertEquals(stem, actual.getValue());
		String adjusted = caseSensitive ? stem : stem.toLowerCase(Locale.ROOT);
		char key = adjusted.charAt(0);
		assertEquals(actual, cut.getChildren().get(key));
	}

}
