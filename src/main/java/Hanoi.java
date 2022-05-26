import java.util.Stack;

public class Hanoi {

	private final int discCount;
	public final Stack<Integer> towerA = new Stack<>();
	public final Stack<Integer> towerB = new Stack<>();
	public final Stack<Integer> towerC = new Stack<>();

	public Hanoi(int discs) {
		discCount = discs;
		for (int i = 0; i < discs; i++) {
			towerA.push(i);
		}
	}

	public Hanoi solve() {
		move(towerA, towerC, towerB, discCount);
		return this;
	}

	/**
	 * There has to be a more readable way to write this...
	 * @param begin
	 * @param end
	 * @param temp
	 * @param n
	 */
	private void move(Stack<Integer> begin, Stack<Integer> end, Stack<Integer> temp, int n) {
		if (n == 1) {
			end.push(begin.pop());
		} else {
			move(begin, temp, end, n - 1);
			move(begin, end, temp, 1);
			move (temp, end, begin, n - 1);
		}
	}
}
