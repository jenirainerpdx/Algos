package fibonacci;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fibonacci {

	static Map<Integer, BigInteger> memo = new HashMap<>(Map.of(0, BigInteger.ZERO, 1, BigInteger.ONE));

	public static BigInteger calc_fib_with_table(int n) {
		if (!memo.containsKey(n)) {
			memo.put(n, calc_fib_with_table(n-1).add(calc_fib_with_table(n-2)));
		}
		return memo.get(n);
	}

	/**
	 * No recursion in this one; also no memoization... Big o is linear and therefore very efficient.
	 * @param n the index of the Fib series for which you want the value.
	 * @return BigInteger for the fibonnaci value at a specific integer.
	 */
	public static BigInteger simpleFib(int n) {
		BigInteger last = BigInteger.ZERO;
		BigInteger next = BigInteger.ONE;
		for (int i = 0; i < n; i++) {
			BigInteger oldLast = last;
			last = next;
			next = oldLast.add(next);
		}
		return last;
	}

	private BigInteger last = BigInteger.ZERO, next = BigInteger.ONE;

	/**
	 * I feel confident this could be optimized with a call to max.
	 * Would need a comparator or else use this:
	 * 		return stream.limit(n+1).collect(Collectors.toList()).get(n); (which is more efficient?)
	 * @param n the integer index for which the Fibonacci value is requested.
	 * @return BigInteger - fibonacci value at requested integer, n.
	 */
	public BigInteger streamingFib(int n) {
		Stream<BigInteger> stream = Stream.generate(() -> {
			BigInteger oldLast = last;
			last = next;
			next = oldLast.add(last);
			return oldLast;
		} );
		return stream.limit(n+1).max(Comparator.comparing(val -> val.floatValue())).get();
		//return stream.limit(n+1).collect(Collectors.toList()).get(n);
	}

	public static void main(String... args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		System.out.println(calc_fib_with_table(n));
	}
}
