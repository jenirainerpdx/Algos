package doubleLinkedList;

class SLLNode<E> {

	SLLNode<E> next;
	E data;

	public SLLNode() {
		this.next = null;
		this.data = null;
	}

	public SLLNode(E theData) {
		this.data = theData;
	}

	public SLLNode(E theData,
				   SLLNode<E> prevNode) {
		this(theData);
		prevNode.next = this;
		this.next = prevNode.next;
	}

	public static void main(String[] args)
	{
		SLLNode<Integer> n0 = new SLLNode<Integer>();
		SLLNode<Integer> n1= new SLLNode(1,n0);
		SLLNode<Integer> n2 = new SLLNode(2,n0);
	}
}