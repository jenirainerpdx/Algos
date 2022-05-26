import java.util.*;

public class LCM {
	public static long lcm_naive(int a, int b) {
		for (long l = 1; l <= (long) a * b; ++l)
			if (l % a == 0 && l % b == 0)
				return l;

		return (long) a * b;
	}

	public static long lcm(int a, int b) {
		Long[] primesForA = reduceInputToPrimes(a);
		Long[] primesForB = reduceInputToPrimes(b);
		int aLen = primesForA.length;
		int bLen = primesForB.length;
		long product;
		if (aLen >= bLen) {
			product = multiplyItOut(primesForA, primesForB);
		} else {
			product = multiplyItOut(primesForB, primesForA);
		}

		return product;
	}

	public static long multiplyItOut(Long[] longer, Long[] shorter) {
		long result = 1;
		int indexShorter = 0;
		for (int indexLonger = 0; indexLonger < longer.length; indexLonger++) {
			// is there a bug here where we could run out of indexLonger before we hit all of shorter?
			// no.  It's a while loop.
			while (indexShorter < shorter.length && indexLonger < longer.length) {
				long x = longer[indexLonger];
				long y = shorter[indexShorter];
				if (x == y) {
					result = result * x;
					indexShorter++;
					indexLonger++;
				} else if (x > y) {
					// move the indexShorter up 1; leave indexLonger in same place.
					result = result * y;
					indexShorter++;
				} else {
					// leave indexShorter in same place; let indexLonger increment with loop.
					result = result * x;
					indexLonger++;
				}
			}
			// now we have gone through all values of shorter.  We may have values left in longer.
			// multiply those out.
			if (indexLonger < longer.length) {
				result = result * longer[indexLonger];
			} else {
				while (indexShorter < shorter.length) {
					result = result * shorter[indexShorter];
					indexShorter++;
				}
			}
		}

		return result;
	}

	/**
	 * This is never going to be huge, so we're taking the hit and putting it in ArrayList and then converting to simple array.
	 *
	 * @param input
	 * @return
	 */
	public static Long[] reduceInputToPrimes(int input) {
		List<Long> primes = new ArrayList();
		long divisible = input;
		for (long i = 2; i < divisible; i++) {
			while (divisible % i == 0) {
				primes.add(i);
				divisible = divisible / i;
			}
		}
		// add divisible to our primes list - it is the last standing prime.
		if (divisible > 1) {
			primes.add(divisible);
		}
		Long[] primesArray = new Long[primes.size()];
		primesArray = primes.toArray(primesArray);
		return primesArray;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();

		System.out.println(lcm(a, b));
	}
}
