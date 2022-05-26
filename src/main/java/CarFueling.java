import java.util.Arrays;
import java.util.Scanner;

public class CarFueling {

	static int computeMinRefills(int target, int tankSize, int[] stops) {
		if (tankSize > target) {
			return 0;
		}

		if (target < 0 || tankSize < 0) {
			return -1;
		}

		// make sure stops are in order.
		Arrays.sort(stops);
		int startFrom = 0, lastSafeMove = 0, nextStop = 0, stopsCount = 0;
		for (int n = 0; n < stops.length + 1; n++) {
			if (n == stops.length) {
				if (target - startFrom < tankSize) {
					return stopsCount;
				} else {
					if (lastSafeMove > startFrom) {
						stopsCount++;
					}
					if (target - lastSafeMove > tankSize) {
						stopsCount = -1;
					}
					return stopsCount;
				}
			} else {
				nextStop = stops[n];
				int distance = nextStop - startFrom;
				if (tankSize >= distance) {
					lastSafeMove = nextStop;
				} else {
					if (lastSafeMove > 0) {
						startFrom = lastSafeMove;
						stopsCount++;
						lastSafeMove = 0;
						n--;
					} else {
						return -1;
					}
				}
			}
		}

		return stopsCount;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int dist = scanner.nextInt();
		int tank = scanner.nextInt();
		int n = scanner.nextInt();
		int stops[] = new int[n];
		for (int i = 0; i < n; i++) {
			stops[i] = scanner.nextInt();
		}

		System.out.println(computeMinRefills(dist, tank, stops));
	}
}
