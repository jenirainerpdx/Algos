package misc.arrays;

import java.util.Arrays;

public class SortedSquares {

	// trying this with an IntStream.
	// note: it doesn't work with much efficiency, as stated, here.  Perhaps there is a better
	// way to remove sorted... not sure the complexity (time or space) on that. (or toArray)
	public int[] sortedSquaresStream(int[] nums) {
		return Arrays.stream(nums).map(i -> i*i).sorted().toArray();
	}

	public int[] sortSq(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		int[] solved = new int[nums.length];
		for (int current = nums.length - 1; current >= 0; current--) {
			int leftVal = nums[left] * nums[left];
			int rightVal = nums[right] * nums[right];
			if (leftVal > rightVal) {
				solved[current] = leftVal;
				left++;
			} else {
				solved[current] = rightVal;
				right--;
			}
		}
		return solved;
	}

}
