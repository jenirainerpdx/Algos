package dataStructures.doubleLinkedList;

public class Node<E> {
	private Node<E> previous;
	private Node<E> next;
	private final E data;

	public Node(E dta){
		data = dta;
	}

	public Node<E> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<E> previous) {
		this.previous = previous;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public E getData() {
		return data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Node<?> node = (Node<?>) o;

		if (previous != null ? !previous.equals(node.previous) : node.previous != null) return false;
		if (next != null ? !next.equals(node.next) : node.next != null) return false;
		return data != null ? data.equals(node.data) : node.data == null;
	}

	@Override
	public int hashCode() {
		int result = previous != null ? previous.hashCode() : 0;
		result = 31 * result + (next != null ? next.hashCode() : 0);
		result = 31 * result + (data != null ? data.hashCode() : 0);
		return result;
	}
}
