import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
	static long getMaxPairwiseProduct(int[] numbers) {
		long max_product = 0;
		int n = numbers.length;

		for (int first = 0; first < n; ++first) {
			for (int second = first + 1; second < n; ++second) {
				max_product = Math.max(max_product,
						numbers[first] * numbers[second]);
			}
		}

		return max_product;
	}

	static long getMaxPairwiseProductFast(int[] nums) {
		if (nums.length < 2) {
			return 0; // invalid input.  Not sure how to handle this.
		} else if (nums.length == 2) {
			// cast these as longs first.
			long a = nums[0];
			long b = nums[1];
			return a * b;
		} else {
			// do the thing.
			long[] largestInts = findTwoLargest(nums);
			return largestInts[0] * largestInts[1];
		}
	}

	static long[] findTwoLargest(int[] nums) {
		long largest = -1;
		long secondLargest = -1;

		for (int num : nums) {
			if (num >= largest) {
				secondLargest = largest;
				largest = num;
			} else if (num >= secondLargest) {
				secondLargest = num;
			}
		}
		return new long[]{largest, secondLargest};
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = scanner.nextInt();
		}
		System.out.println(getMaxPairwiseProductFast(numbers));
	}

	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(InputStream stream) {
			try {
				br = new BufferedReader(new
						InputStreamReader(stream));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

}
