package misc;

import java.util.HashMap;

public class StringContains {

	public boolean canConstruct(String toFind, String dictionary) {
		HashMap<Character, Integer> lettersInDictionary = new HashMap<>();

		for (int i = 0; i < dictionary.length(); i++) {
			char letter = dictionary.charAt(i);

			int currentCount = lettersInDictionary.getOrDefault(letter, 0);
			lettersInDictionary.put(letter, currentCount + 1);
		}

		for (int j = 0; j < toFind.length(); j++) {
			char letterToFind = toFind.charAt(j);
			int currentCount = lettersInDictionary.getOrDefault(letterToFind, 0);
			if (currentCount == 0) {
				return false;
			}
			lettersInDictionary.put(letterToFind, currentCount - 1);
		}

		return true;
	}
}
