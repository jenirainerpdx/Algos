package dataStructures.trees;

public class TreeNode<E> {
	private final E value;
	private final TreeNode<E> parent;
	private TreeNode<E> leftChild;
	private TreeNode<E> rightChild;

	public TreeNode(E val, TreeNode<E> parent) {
		this.value = val;
		this.parent = parent;
		this.leftChild = null;
		this.rightChild = null;
	}

	/**
	 * This is currently incorrect.  What if left child already exists?
	 * Do we simply traverse until leftChild is null first?  Or throw an error?
	 *
	 * @param val - value to add to the tree
	 * @return the node which was added.
	 */
	public TreeNode<E> addLeftChild(E val) throws PreexistingNodeException {
		if (this.leftChild != null) throw new PreexistingNodeException("there is already a left node here.");
		TreeNode<E> leftChild = new TreeNode<>(val, this);
		this.leftChild = leftChild;
		return leftChild;
	}

	public TreeNode<E> addRightChild(E val) throws PreexistingNodeException {
		if (this.rightChild != null) throw new PreexistingNodeException("a right node already exists.");
		TreeNode<E> right = new TreeNode<>(val, this);
		this.rightChild = right;
		return right;
	}

	public E getValue() {
		return value;
	}

	public TreeNode<E> getParent() {
		return parent;
	}

	public TreeNode<E> getLeftChild() {
		return leftChild;
	}

	public TreeNode<E> getRightChild() {
		return rightChild;
	}

	public void visit() {
		System.out.println("Visiting this node: " + getValue().toString());
	}
}
