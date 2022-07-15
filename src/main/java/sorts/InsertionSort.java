package sorts;

public class InsertionSort {

	public static int[] insertionSort(int input[]) {
		int temp;
		for (int i = 1; i< input.length; i++) {
			for (int j = i; j > 0; j--) {
				if (input[j] < input[j-1]) {
					temp = input[j];
					input[j] = input[j-1];
					input[j-1] = temp;
				}
			}
		}
		return input;
	}

	public static int[][] insertionSortOnMatrix(int[][] input) {
		int[] temp;
		for (int i = 1; i< input.length; i++) {
			for (int j = i; j > 0; j--) {
				int embeddedJ = input[j][2];
				int embeddedJMinus1 = input[j-1][2];
				if (embeddedJ < embeddedJMinus1) {
					temp = input[j];
					input[j] = input[j-1];
					input[j-1] = temp;
				}
			}
		}
		return input;
	}

	public static int[][] descendingMatrixSort(int[][] inp) {
		int[][] ascSort = insertionSortOnMatrix(inp);
		int[][] descending = new int[ascSort.length][];
		int i = 0;
		for (int j = ascSort.length - 1; j >= 0; j--) {
			descending[i] = ascSort[j];
			i++;
		}
		return descending;
	}

	public static int[] insertionSortDescending(int input[]) {
		// just invert results from above.
		// to be lazy.
		int[] ascending = insertionSort(input);
		int[] descending = new int[input.length];
		int i = 0;
		for (int j = ascending.length -1; j >= 0; j--) {
			descending[i] = ascending[j];
			i++;
		}

		return descending;
	}
}
