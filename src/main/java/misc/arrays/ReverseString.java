package misc.arrays;

public class ReverseString {
	public char[] reverseString(char[] s) {
		char holder;
		for (int i = 0; i < s.length; i++) {
			holder = s[i];
			int analog = (s.length-1) - i;
			if (i >= analog) break;
			s[i] = s[analog];
			s[analog] = holder;
		}
		return s;
	}
}
