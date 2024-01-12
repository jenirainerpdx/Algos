package dataStructures.trie;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TrieNode {

	private TrieNode parent;
	private final Map<Character, TrieNode> children;
	private final String value;
	private final Boolean isWord;

	public TrieNode(String value, Boolean word) {
		this.parent = null;
		this.value = value;
		children = new HashMap<>();
		isWord = word;
	}

	public TrieNode getParent() {
		return parent;
	}

	public Map<Character, TrieNode> getChildren() {
		return children;
	}

	public String getValue() {
		return value;
	}

	public Boolean isWord() {
		return isWord;
	}

	/**
	 * @param stemToAdd     the stem to add as the value in the children map
	 * @param word          boolean to indicate whether it will be a word or not
	 * @param caseSensitive whether to validate and store as case sensitive
	 * @return the TrieNode created as a child
	 * @throws InvalidInputException thrown when the stemToAdd is not equal to concat of the parent and 1 additional letter.
	 */
	public TrieNode addChild(String stemToAdd, Boolean word, Boolean caseSensitive) throws InvalidInputException {
		char addKey = getKeyIfAddable(stemToAdd, caseSensitive);
		TrieNode childNode = new TrieNode(stemToAdd, word);
		childNode.parent = this;
		children.put(addKey, childNode);
		return childNode;
	}

	/**
	 * Validates input to ensure it can be added to the parent.  If so, returns the char key to add.
	 *
	 * @param stemToAdd     String the stem (possible word) which will be added as value, if valid.
	 * @param caseSensitive does this need to be case-sensitive
	 * @return Character the key to add.
	 * @throws InvalidInputException
	 */
	char getKeyIfAddable(String stemToAdd, boolean caseSensitive) throws InvalidInputException {
		String parentStr, childStr;
		char keyToAdd = 0;
		boolean validForAdding = Boolean.FALSE;
		if (caseSensitive) {
			parentStr = value;
			childStr = stemToAdd;
		} else {
			parentStr = value.toLowerCase(Locale.ROOT);
			childStr = stemToAdd.toLowerCase(Locale.ROOT);
		}

		if (childStr.startsWith(parentStr)) {
			String remainder = childStr.substring(parentStr.length());

			if (remainder.length() == 1) {
				keyToAdd = remainder.charAt(0);
				validForAdding = Boolean.TRUE;
			}
		}
		if (!validForAdding) throw new InvalidInputException(stemToAdd + " is not a valid direct child of the parent.");
		return keyToAdd;
	}
}
