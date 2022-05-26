package sorts;

public class SelectionSort {

	enum SortOrder {
		ASC,
		DESC
	}

	int findLargest(int[] input) {
		int highWater = 0;
		for (int i: input) {
			if (i > highWater) {
				highWater = i;
			}
		}
		return highWater;
	}

	int findSmallest(int[] input) {
		int lowWater = Integer.MAX_VALUE;
		for (int i: input) {
			if (i < lowWater) {
				lowWater = i;
			}
		}
		return lowWater;
	}

	int[] sort(int[] input, SortOrder order) {
		int lastIndex = input.length;
		for (int i = 0; i < lastIndex - 1; i++) {
			int swapIndex = i;
			for (int j = i+1; j < lastIndex; j++) {
				if (order.equals(SortOrder.DESC)) {
					if (input[swapIndex] < input[j]) {
						swapIndex = j;
					}
				} else {
					if (input[swapIndex] > input[j]) {
						swapIndex = j;
					}
				}
			}

			int tempVal = input[i];
			input[i] = input[swapIndex];
			input[swapIndex] = tempVal;
		}
		return input;
	}

}
