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

	@Test
	public void testHowManyChildrenRootLeaf() {
		TreeNode<Integer> root = new TreeNode<>(5, null);
		assertEquals(0, root.howManyChildren());
	}

	@Test
	public void testHowManyChildrenLeftOnly() throws PreexistingNodeException {
		TreeNode<Integer> root = new TreeNode<>(5, null);
		root.addLeftChild(1);
		assertEquals(1, root.howManyChildren());
	}

	@Test
	public void testHowManyChildrenRightOnly() throws PreexistingNodeException {
		TreeNode<Integer> root = new TreeNode<>(5, null);
		root.addRightChild(7);
		assertEquals(1, root.howManyChildren());
	}

	@Test
	public void testHowManyChildrenFuller() throws PreexistingNodeException {
		TreeNode<Integer> root = new TreeNode<>(5, null);
		TreeNode<Integer> twoNode = root.addLeftChild(2);
		twoNode.addLeftChild(1);
		twoNode.addRightChild(3);
		root.addRightChild(8);
		assertEquals(2, root.howManyChildren());
	}

	@Test
	public void testDetatchFromParentWhileRoot() {
		TreeNode<String> root = new TreeNode<>("A", null);
		CannotDetachRootException thrown = assertThrows(
				CannotDetachRootException.class,
				() -> root.detachFromParent()
		);
		assertEquals("This is the root node.  No parent.", thrown.getMessage());
	}

	@Test
	public void testDetachLeftFromRoot() throws PreexistingNodeException, CannotDetachRootException {
		TreeNode<Integer> root = new TreeNode<>(5, null);
		TreeNode<Integer> three = root.addLeftChild(3);
		three.detachFromParent();
		assertNull(three.getParent());
		assertNull(root.getLeftChild());
		assertEquals(0, root.howManyChildren());
	}

	@Test
	public void testDetachRightFromRoot() throws PreexistingNodeException, CannotDetachRootException {
		TreeNode<Integer> root = new TreeNode<>(5, null);
		TreeNode<Integer> three = root.addLeftChild(3);
		TreeNode<Integer> seven = root.addRightChild(7);
		seven.detachFromParent();
		assertNull(seven.getParent());
		assertNull(root.getRightChild());
		assertEquals(1, root.howManyChildren());

	}
}

