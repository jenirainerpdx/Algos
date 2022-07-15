package doubleLinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

	private MyLinkedList<Integer> testList;

	@Test
	public void testCreationOfList(){
		MyLinkedList<Integer> actual = new MyLinkedList<>();
		assertEquals(0, actual.getSize());
	}

	@BeforeEach
	public void setTestList(){
		testList = new MyLinkedList<>();
		testList.addNode(1);
		testList.addNode(2);
		testList.addNode(3);
	}

	@Test
	public void testBuildSmallishList(){
		assertEquals(3, testList.getSize());
		Node<Integer> theOneAfterHead = testList.getHead().getNext();
		assertEquals(Integer.valueOf(1), theOneAfterHead.getData());
		Node<Integer> theOneBeforeTail = testList.getTail().getPrevious();
		assertEquals(Integer.valueOf(3), theOneBeforeTail.getData());
		assertNull(testList.getHead().getPrevious());
		assertNull(testList.getTail().getNext());
	}

	@Test
	public void testAddNodeAtIndex(){
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
	public void testAddNodeAtInvalidIndex(int nodeVal, int index){
		IndexOutOfBoundsException thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
			testList.addNodeAtIndex(nodeVal, index);
		});
		assertNotNull(thrown);
	}

	@Test
	public void testAddNodeAtIndexInEmptyList(){
		MyLinkedList<String> empty = new MyLinkedList<>();
		Node<String> inserted = empty.addNodeAtIndex("A", 0);
		assertEquals(1, empty.getSize());
		assertNull(inserted.getPrevious().getPrevious());
		assertEquals(inserted, empty.getHead().getNext());
	}

}