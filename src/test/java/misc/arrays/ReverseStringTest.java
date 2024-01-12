package misc.arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class ReverseStringTest {

	private ReverseString cut = new ReverseString();

	@Test
	public void testReverse() {
		char[] input = new char[] {'a', 'b', 'c', 'd', 'e'};
		char[] expected = new char[]{'e','d','c','b','a'};
		assertArrayEquals(expected, cut.reverseString(input));
	}

	@Test
	public void testReverseEvenCount(){
		char[] input = new char[]{'a','b','c','d'};
		char[] expected = new char[]{'d','c','b','a'};
		assertArrayEquals(expected, cut.reverseString(input));

	}

}