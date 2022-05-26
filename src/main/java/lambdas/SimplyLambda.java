package lambdas;

import java.util.function.Predicate;

public class SimplyLambda {

	public static void main(String[] args) {
		String name = "jeni";
		Predicate<String> longerThan3 = x -> x.length() > 3;
		Runnable noArgs = () -> {
			System.out.println("hello, world and ");
			System.out.println(name);
		};

		Thread t =  new Thread(noArgs);
		t.run();
	}
}
