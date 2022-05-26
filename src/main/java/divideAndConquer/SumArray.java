package divideAndConquer;

/**
 * Sum all elements of an array.
 * (@Todo: look at what options exist in the language and what algo they use under the hood; e.g streams
 */
public class SumArray {

	/**
	 * Basically, we are not popping from the stack; so we have to iterate through the indices.
	 *
	 * @param input array of type int
	 * @return int
	 */
	public int sumElements(int[] input) {
		return sum(input, 0);
	}

	public int sum(int[] input, int index) {
		if (input.length == 0) return 0;
		if (index == input.length - 1) {
			return input[index];
		}
		return (input[index] + sum(input, index + 1));

	}

}
