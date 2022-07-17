package dataStructures.trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {

	/**
	 * I find it odd that this simpleRoot var reinitializes between every test...
	 * There is no need for a BeforeEvery annotation.
	 */
	private final TreeNode<Integer> simpleRoot = new TreeNode<>(5, null);

	@Test
	public void testSimpleStructure() {
		assertEquals(5, simpleRoot.getValue());
		assertNull(simpleRoot.getParent());
		assertNull(simpleRoot.getLeftChild());
		assertNull(simpleRoot.getRightChild());
	}

	@Test
	public void testConstructor() {
		TreeNode<Integer> actual = new TreeNode<>(8, simpleRoot);
		assertEquals(8, actual.getValue());
		assertEquals(simpleRoot, actual.getParent());
		assertNull(actual.getLeftChild());
		assertNull(actual.getRightChild());
	}

	@Test
	public void testAddLeftWithAPreexistingLeft() throws PreexistingNodeException {
		simpleRoot.addLeftChild(9);
		PreexistingNodeException thrown = assertThrows(PreexistingNodeException.class, () ->
				simpleRoot.addLeftChild(12));
		assertEquals("there is already a left node here.", thrown.getMessage());
	}

	@Test
	public void testAddLeft() throws PreexistingNodeException {
		TreeNode<Integer> leftActual = simpleRoot.addLeftChild(12);
		assertEquals(leftActual, simpleRoot.getLeftChild());
		assertEquals(simpleRoot, leftActual.getParent());
	}

}

