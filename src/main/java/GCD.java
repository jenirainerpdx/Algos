import java.util.*;

public class GCD {
	public static int gcd_naive(int a, int b) {
		int current_gcd = 1;
		for (int d = 2; d <= a && d <= b; ++d) {
			if (a % d == 0 && b % d == 0) {
				if (d > current_gcd) {
					current_gcd = d;
				}
			}
		}
		return current_gcd;
	}

	public static int gcd_attempt2(int a, int b){
		int big = 0, smaller = 0;
		if (a > b) {
			big = a;
			smaller = b;
		} else {
			// if they are equal, then it doesn't actually matter.
			big = b;
			smaller = a;
		}

		int mod = big % smaller;
		while (mod != 0) {
			// move smaller to big and mod to smaller.
			big = smaller;
			smaller = mod;
			mod = big % smaller;
		}
		return smaller;
	}

	public static int gcd(int a, int b) {
		//

		if (a == 1 || b == 1) {
			return 1;
		}

		// keep taking a % b until no modulus.
		int aModB = a % b;
		int tempA = a;
		int tempB = b;
		while (aModB != 0 && tempB > 1) {
			aModB = tempA % tempB;
			tempA = tempB;
			tempB = aModB;
		}

		int gcd = aModB;
		if (aModB == 0){
			gcd = tempA;
		}
		return gcd;
	}


	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();

		System.out.println(gcd_attempt2(a, b));
	}
}
