package dataStructures.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

	TreeNode<String> root, bNode, cNode, aNode, mNode, lNode, qNode;
	BinaryTree<String> simpleTree;

	@BeforeEach
	public void buildSimpleTree() throws PreexistingNodeException {
		root = new TreeNode<>("E", null);
		bNode = root.addLeftChild("B");
		aNode = bNode.addLeftChild("A");
		cNode = bNode.addRightChild("C");
		mNode = root.addRightChild("M");
		lNode = mNode.addLeftChild("L");
		qNode = mNode.addRightChild("Q");
		simpleTree = new BinaryTree<>(root);
	}


	@Test
	public void testTraversals() {
		// this is not a good test; these are byproduct methods.
		simpleTree.depthFirstTraverse(TraverseType.PRE_ORDER);
		System.out.println("Pre order traversal should look like: " +
				"E B A C M L Q");
		simpleTree.depthFirstTraverse(TraverseType.POST_ORDER);
		System.out.println("Post order traversal should look like:  " +
				"A C B L Q M E");
		simpleTree.depthFirstTraverse(TraverseType.IN_ORDER);
		System.out.println("In order traversal should look like: " +
				"A B C E L M Q");
		simpleTree.levelOrder();
		System.out.println("Level order traversal should be:  E B M A C L Q");
		assertEquals(bNode, aNode.getParent());
		assertEquals(bNode, cNode.getParent());
		assertEquals(mNode, lNode.getParent());
		assertEquals(mNode, qNode.getParent());
	}

	@Test
	public void binarySearchSimpleTest() {
		TreeNode<String> foundC = simpleTree.binarySearch("C");
		assertEquals(cNode, foundC);
		assertNull(simpleTree.binarySearch("Z"));
	}

	@Test
	public void testSimpleBinaryInsert() throws PreexistingNodeException {
		TreeNode<String> root = new TreeNode<>("F", null);
		BinaryTree<String> testTree = new BinaryTree<>(root);
		TreeNode<String> actual1 = testTree.binaryInsert("C");
		assertEquals(root, actual1.getParent());
		assertEquals(actual1, root.getLeftChild());
		TreeNode<String> actual2 = testTree.binaryInsert("M");
		assertEquals(root, actual2.getParent());
		assertEquals(actual2, root.getRightChild());
		TreeNode<String> actual3 = testTree.binaryInsert("G");
		assertEquals(actual2, actual3.getParent());
		assertEquals(actual3, actual2.getLeftChild());
		assertNull(actual2.getRightChild());
	}

	@Test
	public void testBinaryInsertNodeExists() {
		PreexistingNodeException thrown = assertThrows(
				PreexistingNodeException.class,
				() -> simpleTree.binaryInsert("M")
		);
		assertEquals("A node with the value to insert ( M ) already exists in the tree.", thrown.getMessage());
	}

	@Test
	public void testSimpleDeleteOfLeaf() throws CannotDetachRootException, ItemNotFoundException {
		simpleTree.deleteFromTree("Q");
		assertEquals(1, mNode.howManyChildren());
		assertEquals(mNode, root.getRightChild());
		assertEquals(bNode, root.getLeftChild());
	}

	@Test
	public void testDeleteNodeNotFound() {
		ItemNotFoundException thrown = assertThrows(
				ItemNotFoundException.class,
				() -> simpleTree.deleteFromTree("Z")
		);
		assertEquals("There is no element Z in tree.",
				thrown.getMessage());
	}

	@Test
	public void testDeleteRootNoLeaves() {
		TreeNode<Integer> root = new TreeNode<>(0, null);
		BinaryTree<Integer> testTree = new BinaryTree<>(root);
		CannotDetachRootException thrown = assertThrows(
				CannotDetachRootException.class,
				() -> testTree.deleteFromTree(0)
		);
		assertEquals("This is the root node.  No parent.", thrown.getMessage());
	}

	@Test
	public void testDeleteFromMiddleOfTree() throws PreexistingNodeException, CannotDetachRootException, ItemNotFoundException {
		qNode.addLeftChild("O");
		simpleTree.deleteFromTree("M");
		assertEquals("L", root.getRightChild().getValue());
		TreeNode<String> newLNode = root.getRightChild();
		assertEquals(2, root.howManyChildren());
		assertNull(newLNode.getLeftChild());
		assertEquals(qNode, newLNode.getRightChild());
		assertEquals(1, newLNode.howManyChildren());
		simpleTree.levelOrder();
		System.out.println("Breadth first traversal should read:  E B L A C Q O");
	}

	/**
	 * This test currently fails.  It may be that I have the tree set up improperly.
	 * Alternatively, my delete method is possibly (likely) incorrect.
	 * @throws PreexistingNodeException
	 * @throws CannotDetachRootException
	 * @throws ItemNotFoundException
	 */
	@Test
	public void testAnotherDeleteFromMiddle() throws PreexistingNodeException, CannotDetachRootException, ItemNotFoundException {
		TreeNode<Integer> twenty = new TreeNode<>(20, null);
		BinaryTree<Integer> actual = new BinaryTree<>(twenty);
		TreeNode<Integer> ten = twenty.addLeftChild(10);
		TreeNode<Integer> fifteen = ten.addRightChild(15);
		TreeNode<Integer> twelve = fifteen.addLeftChild(12);
		TreeNode<Integer> thirteen = twelve.addRightChild(13);
		actual.levelOrder();
		System.out.println("Breadth first traversal should read:  20 10 15 12 13");
		System.out.println("Before removing any elements.");
		actual.deleteFromTree(10);
		actual.levelOrder();
	}

}