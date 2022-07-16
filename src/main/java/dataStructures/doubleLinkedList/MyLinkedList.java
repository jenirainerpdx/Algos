package dataStructures.doubleLinkedList;

public class MyLinkedList<E> {
	private final Node<E> head;
	private final Node<E> tail;
	private int size;

	public MyLinkedList() {
		head = new Node<>(null);
		tail = new Node<>(null);
		head.setNext(tail);
		tail.setPrevious(head);
		size = 0;
	}

	/**
	 * This adds a node to the end of the list.
	 *
	 * @param nodeValue - the value to add to the list.
	 */
	public Node<E> addNode(E nodeValue) {
		Node<E> lastBeforeAdd = tail.getPrevious();
		Node<E> nodeToAdd = new Node<>(nodeValue);
		nodeToAdd.setPrevious(lastBeforeAdd);
		nodeToAdd.setNext(tail);
		lastBeforeAdd.setNext(nodeToAdd);
		tail.setPrevious(nodeToAdd);
		size++;
		return nodeToAdd;
	}

	/**
	 * clean this up for readability when you don't have COVID-19 brain fog.
	 *
	 * @param nodeValue the value to set on the node to insert.
	 * @param index     where to insert the value in the list.
	 * @return Node of type E
	 */
	public Node<E> addNodeAtIndex(E nodeValue, int index) {
		Node<E> nodeToAdd = new Node<>(nodeValue);
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else if (size == 0) {
			nodeToAdd = addNode(nodeValue);
		} else {
			Node<E> nodePreviouslyAtIndex = getNode(index);
			Node<E> prevOfPrevious = nodePreviouslyAtIndex.getPrevious();
			nodeToAdd.setPrevious(prevOfPrevious);
			nodeToAdd.setNext(nodePreviouslyAtIndex);
			prevOfPrevious.setNext(nodeToAdd);
			nodePreviouslyAtIndex.setPrevious(nodeToAdd);
			size++;
		}
		return nodeToAdd;
	}

	/**
	 *
	 * @param toRemove the element to remove from the list
	 * @throws ItemNotFoundException when the item to remove is not found.
	 */
	public void removeElement(E toRemove) throws ItemNotFoundException {
		Node<E> nodeToRemove = find(toRemove);
		Node<E> nodeBefore = nodeToRemove.getPrevious();
		Node<E> nodeAfter = nodeToRemove.getNext();
		nodeBefore.setNext(nodeAfter);
		nodeAfter.setPrevious(nodeBefore);
		size--;
	}

	/**
	 * We don't know what type E is.  If it's numeric, we can determine
	 * whether we should search from head or tail; but since we don't,
	 * it's almost impossible to figure out the mid-value.
	 * Just search from head for now.
	 *
	 * @param value the element to find in the list
	 * @return the node in the list which holds that value.
	 * @throws ItemNotFoundException if the item is not found in the list an exception will be thrown.
	 */
	public Node<E> find(E value) throws ItemNotFoundException {
		Node<E> found = null;
		Node<E> currentNode = head;
		for (int i = 0; i < this.size; i++){
			currentNode = currentNode.getNext();
			if (currentNode.getData().equals(value)) {
				found = currentNode;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException(value.toString() + " was not found.");
		} else {
			return found;
		}
	}

	/**
	 * This is protected so that it can be used in testing.
	 *
	 * @param index which node to pull out of the list
	 * @return Node of type E
	 */
	protected Node<E> getNode(int index) {
		var nodeAtIndex = head;
		for (int i = 0; i < index; i++) {
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

	public Node<E> getHead() {
		return head;
	}

	public Node<E> getTail() {
		return tail;
	}


	/**
	 * Used for debugging purposes.
	 */
	public void prettyPrintList() {
		System.out.println("Printing nodes from this list:  ");
		Node<E> nextNode = head.getNext();
		while (!nextNode.equals(tail)) {
			System.out.println(nextNode.getData().toString());
			nextNode = nextNode.getNext();
		}
		System.out.println("end of the list");
	}
}
