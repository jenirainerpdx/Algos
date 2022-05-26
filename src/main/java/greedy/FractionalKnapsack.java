package greedy;

import java.util.Scanner;

public class FractionalKnapsack {
	/**
	 * Don't forget to ensure at least 4 digits to right of decimal.
	 *
	 * @param capacity - how much weight can fit within knapsack.
	 * @param values   - the value of each element
	 * @param weights  - the weight of each element
	 * @return the sum of the value which can fit within the bag.
	 */
	public static double getOptimalValue(int capacity, int[] values, int[] weights) {
		double value = 0;
		// derive your value based order.
		Object[][] valueSorted = deriveValueBasedOrder(values, weights);
		for (Object[] thisElement : valueSorted) {
			int weightOfThisElement = (int) thisElement[1];
			int valueOfElement = (int) thisElement[0];
			if (capacity > weightOfThisElement) {
				capacity -= weightOfThisElement;
				value += valueOfElement;
			} else {
				double portionOfElement = (double) capacity / (double) weightOfThisElement;
				double valueToAdd = portionOfElement * valueOfElement;
				value += valueToAdd;
				break;
			}
		}

		return value;
	}

	/**
	 * @param values  - series of value elements
	 * @param weights - how much each value element weighs
	 * @return Object[][] :
	 * In order from largest to smallest ratio, each Object[] element contains:
	 * - position 0 == value
	 * - position 1 == weight
	 * - position 2 == ratio of value to weight.
	 */
	public static Object[][] deriveValueBasedOrder(int[] values, int[] weights) {
		int len = values.length;
		Object[][] triples = new Object[len][];
		for (int i = 0; i < len; i++) {
			double ratio = (double) values[i] / (double) weights[i];
			Object[] triple = new Object[]{values[i], weights[i], ratio};
			triples[i] = triple;
		}
		Object[][] tripleInOrder = descendingMatrixSort(triples);
		return tripleInOrder;
	}

	/**
	 * @param input
	 * @return
	 * @TODO - adjust and move some tests.
	 */
	public static Object[][] descendingMatrixSort(Object[][] input) {
		if (input.length == 1) return input;
		Object[][] ascSorted = insertionSortOnMatrix(input);
		Object[][] descending = new Object[ascSorted.length][];
		int i = 0;
		for (int j = ascSorted.length - 1; j >= 0; j--) {
			descending[i] = ascSorted[j];
			i++;
		}
		return descending;
	}

	public static Object[][] insertionSortOnMatrix(Object[][] input) {
		Object[] temp;
		for (int i = 1; i < input.length; i++) {
			for (int j = i; j > 0; j--) {
				double embeddedJ = (double) input[j][2];
				double embeddedJMinus1 = (double) input[j - 1][2];
				if (embeddedJ < embeddedJMinus1) {
					temp = input[j];
					input[j] = input[j - 1];
					input[j - 1] = temp;
				}
			}
		}
		return input;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int capacity = scanner.nextInt();
		int[] values = new int[n];
		int[] weights = new int[n];
		for (int i = 0; i < n; i++) {
			values[i] = scanner.nextInt();
			weights[i] = scanner.nextInt();
		}
		System.out.println(getOptimalValue(capacity, values, weights));
	}
}
