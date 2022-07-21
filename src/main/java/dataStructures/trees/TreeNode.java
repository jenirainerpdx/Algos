package dataStructures.trees;

public class TreeNode<E extends Comparable<? super E>> {
	private E value;
	private TreeNode<E> parent;

	public void setLeftChild(TreeNode<E> leftChild) {
		this.leftChild = leftChild;
	}

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

	public int howManyChildren() {
		int childCount = 0;
		if (this.leftChild != null) {
			childCount++;
		}
		if (this.rightChild != null) {
			childCount++;
		}
		return childCount;
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

	/**
	 * Really a destructor of sorts. It will no longer be referenced in the tree.
	 */
	public void detachFromParent() throws CannotDetachRootException {
		if (parent == null) throw new CannotDetachRootException("This is the root node.  No parent.");
		if (isLeftChild()) {
			parent.leftChild = null;
		} else {
			parent.rightChild = null;
		}
		// for hygiene, set parent to null.
		parent = null;
	}

	private boolean isLeftChild() {
		Boolean left = Boolean.FALSE;
		E parentValue = parent.getValue();
		if (parentValue.compareTo(value) > 0) {
			left = Boolean.TRUE;
		}
		return left;
	}

	public void setParent(TreeNode<E> parent) {
		this.parent = parent;
	}

	public void setValue(E val) {
		value = val;
	}
}
