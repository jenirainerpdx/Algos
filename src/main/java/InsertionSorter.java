import java.util.List;

public class InsertionSorter {

	// We cannot do this with a List.  Needs to be an array, so that we
	// can specifically place items in a position within the array.
	// Is there a way to do this with ArrayList?
	// Also the abstract type Number would require a comparator.
	// feeling lazy, so for now, will just use int.
	public int[] sort(int[] toSort) {
		int inputLength = toSort.length;
		for (int current = 2; current < inputLength; current++) {
			int sortMe = toSort[current];
			int justLeftOfCurrentIndex = current - 1;
			int justLeftOfCurrent = toSort[justLeftOfCurrentIndex];
			while (justLeftOfCurrentIndex > 0 && justLeftOfCurrent > sortMe) {
				toSort[justLeftOfCurrentIndex +1] = toSort[justLeftOfCurrentIndex];
				justLeftOfCurrentIndex = justLeftOfCurrentIndex -1;
			}
			toSort[justLeftOfCurrentIndex] = sortMe;
		}
		return toSort;
	}

}
