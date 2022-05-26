public class PiCalculator {

	/**
	 * 1.  Isn't there a more succinct statement of the for loop?
	 * 2.  How would we do this with a stream?
	 * 3.  What are the other ways to calculate pi - which may possibly be more effective...
	 * @param nTerms int for how many terms to tke pi to.
	 * @return approximation of pi as a double
	 */
	public static double calculatePi(int nTerms) {
		final double numerator = 4.0;
		double denominator = 1.0;
		double operation = 1.0;
		double pi = 0.0;
		for (int i = 0; i < nTerms; i++) {
			pi += operation * (numerator / denominator );
			denominator += 2.0;
			operation *= -1.0;
		}
		return pi;
	}
}
