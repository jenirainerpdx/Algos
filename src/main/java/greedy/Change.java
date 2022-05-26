package greedy;

import java.util.Scanner;

public class Change {

	private static final int FIVE = 5;
	private static final int TEN = 10;


	/**
	 * This should be O(some constant) as it will take 2 passes through and then have a few constant ops.
	 * Because the incoming param is an int, we should not have any issues with integer overflow.
	 * @param input - amount that change must total to.
	 * @return int - count of coins to return.
	 */
	public static int getChange(int input) {
		// divide by 10 - take the floor;
		// divide what's left by 5 - take the floor.
		// add what's left - no need to divide by 1.
		int coinCount = 0;
		int howManyTens = Math.floorDiv(input, TEN);
		coinCount += howManyTens;
		int howManyLeft = input - (TEN*howManyTens);
		int howManyFives = Math.floorDiv(howManyLeft, FIVE);
		coinCount += howManyFives;
		coinCount += (howManyLeft - (howManyFives*FIVE));
		//write your code here
		return coinCount;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		System.out.println(getChange(m));

	}
}

