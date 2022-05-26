package searches;

import java.util.Arrays;

public class BinSearch {

	public int findIt(int[] listOfInts, int x) throws Exception {
		Arrays.sort(listOfInts); // does this really sort in place?

		int low = 0;
		int high = listOfInts.length;
		int oldMid = 0;
		while (high > low) {
			// is it >= or just > ??? test that.
			// what happens when it's lower than last element, but not included
			// what happens when it's higher than last element?
			int mid = (high + low) / 2;
			if (oldMid == mid) throw new Exception("foo");
			int candidate = listOfInts[mid];
			if (candidate == x) {
				return mid;
			} else if (candidate > x) {
				oldMid = mid;
				high = mid;
			} else {
				low = mid;
				oldMid = mid;
			}
		}
		throw new Exception("Item not found.");
	}

}
