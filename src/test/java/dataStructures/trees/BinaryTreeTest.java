package dataStructures.trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

	/**
	 * Tree should look like:
	 * A
	 * B      C
	 * D   E   F   G
	 *
	 * @throws PreexistingNodeException but it won't in this instance.
	 */
	@Test
	public void testTraversals() throws PreexistingNodeException {
		// this is not a good test; these are byproduct methods.
		TreeNode<String> root = new TreeNode<>("A", null);
		TreeNode<String> bNode = root.addLeftChild("B");
		TreeNode<String> dNode = bNode.addLeftChild("D");
		TreeNode<String> eNode = bNode.addRightChild("E");
		TreeNode<String> cNode = root.addRightChild("C");
		TreeNode<String> fNode = cNode.addLeftChild("F");
		TreeNode<String> gNode = cNode.addRightChild("G");
		BinaryTree<String> actual = new BinaryTree<>(root);
		actual.depthFirstTraverse(TraverseType.PRE_ORDER);
		System.out.println("Pre order traversal should look like: " +
				"A B D E C F G");
		actual.depthFirstTraverse(TraverseType.POST_ORDER);
		System.out.println("Post order traversal should look like:  " +
				"D E B F G C A");
		actual.depthFirstTraverse(TraverseType.IN_ORDER);
		System.out.println("In order traversal should look like: " +
				"D B E A F C G");
		actual.levelOrder();
		System.out.println("Level order traversal should be:  A B C D E F G");
		assertEquals(bNode, dNode.getParent());
		assertEquals(bNode, eNode.getParent());
		assertEquals(cNode, fNode.getParent());
		assertEquals(cNode, gNode.getParent());
	}


}