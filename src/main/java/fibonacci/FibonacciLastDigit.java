package fibonacci;

import java.math.BigInteger;
import java.util.*;

/**
 * Is there something interesting here mathematically?  Or are we simply trying to
 * improve space optimization?
 */
public class FibonacciLastDigit {
	public static int getFibonacciLastDigitNaive(int n) {
		if (n <= 1)
			return n;

		BigInteger previous = BigInteger.ZERO;
		BigInteger current = BigInteger.ONE;

		for (int i = 0; i < n - 1; ++i) {
			BigInteger tmp_previous = previous;
			previous = current;
			current = tmp_previous.add(current);
		}
		BigInteger last = current.mod(BigInteger.TEN);
		return last.intValue();
	}

	public static int getFibLastDigit(int n) {
		// all I really need to store on this is last digit.
		// (therefore, I don't need to use BigInteger.)
		if (n <= 1)
			return n;

		int previous = 0;
		int curr = 1;
		for (int i = 0; i < n -1; i++) {
			int tmp_previous = previous;
			previous = curr;
			curr = (tmp_previous + curr) % 10;
		}
		return curr;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int c = getFibLastDigit(n);
		System.out.println(c);
	}
}

