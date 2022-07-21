package dataStructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E extends Comparable<? super E>> {

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

	/**
	 * This is breadth first traversal.
	 */
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

	/**
	 * @param findElement the element to find.
	 * @return the node with the found element.  If not found, returns null.
	 */
	public TreeNode<E> binarySearch(E findElement) {
		TreeNode<E> foundNode = null;
		TreeNode<E> branchParent = root;
		while (branchParent != null) {
			E branchParentElement = branchParent.getValue();
			if (branchParentElement.equals(findElement)) {
				foundNode = branchParent;
				break;
			} else if (branchParentElement.compareTo(findElement) < 0) {
				// branchParent is less than the element to find
				branchParent = branchParent.getRightChild();
			} else {
				branchParent = branchParent.getLeftChild();
			}
		}
		return foundNode;
	}

	public TreeNode<E> binaryInsert(E toInsert) throws PreexistingNodeException {
		TreeNode<E> potentialParent = root;
		TreeNode<E> inserted = null;
		while (potentialParent != null) {
			E parentValue = potentialParent.getValue();
			int goLeftOrRight = parentValue.compareTo(toInsert);
			if (goLeftOrRight == 0) {
				throw new PreexistingNodeException("A node with the value to insert ( " + toInsert + " ) already exists in the tree."
				);
			} else if (goLeftOrRight > 0) {
				//parent value is greater than insert value.
				if (potentialParent.getLeftChild() == null) {
					inserted = potentialParent.addLeftChild(toInsert);
					break;
				} else {
					potentialParent = potentialParent.getLeftChild();
				}
			} else {
				if (potentialParent.getRightChild() == null) {
					inserted = potentialParent.addRightChild(toInsert);
					break;
				} else {
					potentialParent = potentialParent.getRightChild();
				}
			}
		}
		return inserted;
	}

	/**
	 * @param elementToDelete which element to remove.
	 */
	public void deleteFromTree(E elementToDelete) throws ItemNotFoundException, CannotDetachRootException {
		TreeNode<E> nodeToRemove = binarySearch(elementToDelete);
		if (nodeToRemove == null) {
			throw new ItemNotFoundException("There is no element " + elementToDelete + " in tree.");
		}
		int childCount = nodeToRemove.howManyChildren();
		if (childCount == 0) {
			nodeToRemove.detachFromParent();
		} else if (childCount == 1) {
			TreeNode<E> left = nodeToRemove.getLeftChild();
			if (left == null) {
				nodeToRemove.getRightChild().setParent(nodeToRemove.getParent());
			} else {
				nodeToRemove.getLeftChild().setParent(nodeToRemove.getParent());
			}
			nodeToRemove.detachFromParent();
		} else {
			// find smallest element in right subtree; i.e where left child is null.
			TreeNode<E> smallestOnRight = nodeToRemove.getLeftChild();
			while (smallestOnRight.getLeftChild() != null) {
				smallestOnRight = smallestOnRight.getLeftChild();
			}
			nodeToRemove.setValue(smallestOnRight.getValue());
			TreeNode<E> childOfSmallestRemoved = smallestOnRight.getRightChild();
			if (childOfSmallestRemoved != null) {
				// this has to be reparented.
				TreeNode<E> parentOfSmallest = smallestOnRight.getParent();
				childOfSmallestRemoved.setParent(parentOfSmallest);
			}
			// detach from parent no longer works, because we reset values.
			// we have to do this manually.
			smallestOnRight.setParent(null);
			nodeToRemove.setLeftChild(null);
		}
	}

}
