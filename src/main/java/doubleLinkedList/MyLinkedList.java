package doubleLinkedList;

public class MyLinkedList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;

	public MyLinkedList(){
		head = new Node<>(null);
		tail = new Node<>(null);
		head.setNext(tail);
		tail.setPrevious(head);
		size = 0;
	}

	/**
	 * This adds a node to the end of the list.
	 * @param nodeValue - the value to add to the list.
	 */
	public Node<E> addNode(E nodeValue){
		Node<E> lastBeforeAdd = tail.getPrevious();
		Node<E> nodeToAdd = new Node<>(nodeValue);
		nodeToAdd.setPrevious(lastBeforeAdd);
		nodeToAdd.setNext(tail);
		lastBeforeAdd.setNext(nodeToAdd);
		tail.setPrevious(nodeToAdd);
		size++;
		return nodeToAdd;
	}

	public Node<E> addNodeAtIndex(E nodeValue, int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (size == 0) {
			return addNode(nodeValue);
		}
		Node<E> nodeToAdd = new Node<>(nodeValue);
		Node<E> nodePreviouslyAtIndex = getNode(index); // this will be the next
		Node<E> prevOfPrevious = nodePreviouslyAtIndex.getPrevious();
		nodeToAdd.setPrevious(prevOfPrevious);
		nodeToAdd.setNext(nodePreviouslyAtIndex);
		prevOfPrevious.setNext(nodeToAdd);
		nodePreviouslyAtIndex.setPrevious(nodeToAdd);
		size++;
		return nodeToAdd;
	}

	/**
	 * We don't know what type E is.  If it's numeric, we can determine
	 * whether we should search from head or tail; but since we don't,
	 * it's almost impossible to figure out the mid-value.
	 * Just search from head for now.
	 * @param value
	 * @return the node in the list which holds that value.
	 * @throws ItemNotFoundException
	 */
	public Node<E> find(E value) throws ItemNotFoundException {
		throw new ItemNotFoundException("not yet implemented");
	}

	private Node<E> getNode(int index){
		Node<E> nodeAtIndex = head;
		for (int i = 0; i < index; i++){
			nodeAtIndex = nodeAtIndex.getNext();
		}
		return nodeAtIndex;
	}

	public E get(int index) {
		Node<E> nodeAtIndex = getNode(index);
		return nodeAtIndex.getData();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Node<E> getHead() {
		return head;
	}

	public void setHead(Node<E> head) {
		this.head = head;
	}

	public Node<E> getTail() {
		return tail;
	}

	public void setTail(Node<E> tail) {
		this.tail = tail;
	}

	public void prettyPrintList(){
		System.out.println("Printing nodes from this list:  ");
		Node<E> nextNode = head.getNext();
		while (!nextNode.equals(tail)) {
			System.out.println(nextNode.getData().toString());
			nextNode = nextNode.getNext();
		}
		System.out.println("end of the list");
	}
}
