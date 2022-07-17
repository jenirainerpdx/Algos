package dataStructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> {

	TreeNode<E> root;

	public BinaryTree(TreeNode<E> root) {
		this.root = root;
	}

	/**
	 * For this tree, step depth first with the following logic:
	 * 1.  visit self
	 * 2.  visit left
	 * 3.  visit right
	 */
	public void depthFirstTraverse(TraverseType traverseType) {
		switch (traverseType) {
			case IN_ORDER:
				inOrder(root);
				break;
			case PRE_ORDER:
				preOrder(root);
				break;
			case POST_ORDER:
				postOrder(root);
				break;
		}
	}

	/**
	 * @param node node which serves as parent/self to start from.
	 * @TODO: find a looping or streaming way instead.
	 */
	private void preOrder(TreeNode<E> node) {
		if (node != null) {
			node.visit();
			preOrder(node.getLeftChild());
			preOrder(node.getRightChild());
		}
	}

	private void postOrder(TreeNode<E> node) {
		if (node != null) {
			postOrder(node.getLeftChild());
			postOrder(node.getRightChild());
			node.visit();
		}
	}

	private void inOrder(TreeNode<E> node) {
		if (node != null) {
			inOrder(node.getLeftChild());
			node.visit();
			inOrder(node.getRightChild());
		}
	}

	public void levelOrder() {
		Queue<TreeNode<E>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode<E> current = queue.remove();
			if (current != null) {
				current.visit();
				queue.add(current.getLeftChild());
				queue.add(current.getRightChild());
			}
		}
	}



}
