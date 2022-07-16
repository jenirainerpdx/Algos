package dataStructures.doubleLinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

	private MyLinkedList<Integer> testList;

	@Test
	public void testCreationOfList() {
		MyLinkedList<Integer> actual = new MyLinkedList<>();
		assertEquals(0, actual.getSize());
	}

	@BeforeEach
	public void setTestList() {
		testList = new MyLinkedList<>();
		testList.addNode(1);
		testList.addNode(2);
		testList.addNode(3);
	}

	@Test
	public void testBuildSmallishList() {
		assertEquals(3, testList.getSize());
		Node<Integer> theOneAfterHead = testList.getHead().getNext();
		assertEquals(Integer.valueOf(1), theOneAfterHead.getData());
		Node<Integer> theOneBeforeTail = testList.getTail().getPrevious();
		assertEquals(Integer.valueOf(3), theOneBeforeTail.getData());
		assertNull(testList.getHead().getPrevious());
		assertNull(testList.getTail().getNext());
	}

	@Test
	public void testAddNodeAtIndex() {
		Node<Integer> actual = testList.addNodeAtIndex(5, 2);
		assertEquals(4, testList.getSize());
		assertEquals(5, testList.get(2));
		Node<Integer> before = actual.getPrevious();
		Node<Integer> after = actual.getNext();
		assertEquals(1, before.getData());
		assertEquals(2, after.getData());
	}

	@ParameterizedTest
	@CsvSource({
			"5,7",
			"4,-5"
	})
	public void testAddNodeAtInvalidIndex(int nodeVal, int index) {
		IndexOutOfBoundsException thrown = assertThrows(IndexOutOfBoundsException.class, () ->
				testList.addNodeAtIndex(nodeVal, index)
		);
		assertNotNull(thrown);
	}

	@Test
	public void testAddNodeAtIndexInEmptyList() {
		MyLinkedList<String> empty = new MyLinkedList<>();
		Node<String> inserted = empty.addNodeAtIndex("A", 0);
		assertEquals(1, empty.getSize());
		assertNull(inserted.getPrevious().getPrevious());
		assertEquals(inserted, empty.getHead().getNext());
	}

	@Test
	public void testFindSimple() throws ItemNotFoundException {
		Node<Integer> one = testList.getNode(1);
		Node<Integer> actual = testList.find(1);
		assertEquals(one, actual);
	}

	@Test
	public void testFindWithoutElementThrowsException() {
		ItemNotFoundException thrown = assertThrows(ItemNotFoundException.class, () ->
				testList.find(8)
		);
		assertEquals("8 was not found.", thrown.getMessage());
	}

	@Test
	public void testSimpleRemove() throws ItemNotFoundException {
		testList.removeElement(2);
		testList.prettyPrintList();
		assertEquals(2, testList.getSize());
		ItemNotFoundException thrown = assertThrows(ItemNotFoundException.class, () -> testList.find(2));
		assertEquals("2 was not found.", thrown.getMessage());
		Node<Integer> oneNode = testList.getNode(1);
		Node<Integer> threeNode = testList.getNode(2);
		assertEquals(oneNode, testList.getHead().getNext());
		assertEquals(oneNode, threeNode.getPrevious());
		assertEquals(threeNode, oneNode.getNext());
		assertEquals(testList.getTail(), threeNode.getNext());
	}

	@Test
	public void testRemoveElementWhichIsNotInList() {
		ItemNotFoundException thrown = assertThrows(ItemNotFoundException.class, () ->
				testList.removeElement(99));
		assertEquals("99 was not found.", thrown.getMessage());
	}
}