package dataStructures.trie;

/**
 * Open questions:
 * 1.  Is there any possible way to use a Trie for something other than a String?
 * 		a.  Would that even make sense?
 *
 */
public class Trie {

	private TrieNode root;

	/**
	 * Simple constructor - sets the root as a node with:
	 * - empty string for value
	 * - false for isWord flag and
	 * - initialized empty HashMap for children
	 */
	public Trie() {
		this.root = new TrieNode("", Boolean.FALSE);
	}

	public TrieNode addWord(String wordToAdd) {
		for (char c : wordToAdd.toCharArray()) {

		}
		return root;
	}

	public TrieNode findWord(String findMe) {
		// @TODO - implement me.
		return root;
	}

	// ?? is there need to delete a word?  possibly.


}
