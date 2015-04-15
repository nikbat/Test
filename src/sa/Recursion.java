package sa;

public class Recursion {

	public static void main(String[] args) {
		new Recursion().binarySearch();
		// new Recursion().printPermutation();
		System.out.print(new Recursion().printStringInAReverse("abcde"));
	}

	/*
	 * Binary search on a sorted array
	 */

	public void binarySearch() {
		int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int find = 5;
		int position = binarySearch2(numbers, find, 0);
		System.out.println(position);
	}

	public int binarySearch2(int[] numbers, int find, int l) {

		int position = 0;
		if (numbers.length > 1) {
			int half = numbers.length / 2;
			boolean odda = false;
			if (numbers.length % 2 != 0) {
				odda = true;
			}

			if (numbers[half] == find) {
				return half + l;
			} else if (numbers[half] > find) {
				int[] temp = new int[half];
				System.arraycopy(numbers, 0, temp, 0, temp.length);
				position = binarySearch2(temp, find, 0);
			} else {
				int[] temp = null;
				if (odda) {
					temp = new int[half + 1];
				} else {
					temp = new int[half];
				}
				System.arraycopy(numbers, half, temp, 0, temp.length);
				position = binarySearch2(temp, find, 0) + half;
			}
		}
		return position;

	}

	/*
	 * PROB LEM Implement a routine that prints all possible orderings of the
	 * characters in a string. In other words, print all permutations that use
	 * all the characters from the original string. For example, given the
	 * string “hat”, your function should print the strings “tha”, “aht”, “tah”,
	 * “ath”, “hta”, and “hat”. Treat each character in the input string as a
	 * distinct character, even if it is repeated. Given the string “aaa”, your
	 * routine should print “aaa” six times. You may print the permutations in
	 * any order you choose.
	 */
	private StringBuilder out = new StringBuilder();
	String in = "abcd";
	boolean[] used = new boolean[in.length()];

	public void printPermutation() {
		if (out.length() == in.length()) {
			System.out.println(out);
			return;
		}
		for (int i = 0; i < in.length(); ++i) {
			if (used[i]) {
				continue;
			}
			out.append(in.charAt(i));
			used[i] = true;
			printPermutation();
			used[i] = false;
			out.setLength(out.length() - 1);
		}
	}

	/*
	 * print a string in a reverse order
	 */

	public String printStringInAReverse(String data) {
		if (data.length() == 1) {
			return data;
		}
		return printStringInAReverse(data.substring(1)) + data.charAt(0);
	}

	private static final int PHONE_NUMBER_LENGTH = 7;
	private final int[] phoneNum = { 4, 0, 8, 5, 9, 3, 4, 2, 4, 9 };
	private char[] result = new char[PHONE_NUMBER_LENGTH];

	public void printWords() {
		// Initialize result with first telephone word
		for (int i = 0; i < PHONE_NUMBER_LENGTH; ++i)
			result[i] = getCharKey(phoneNum[i], 1);
		for (;;) { // Infinite loop
			for (int i = 0; i < PHONE_NUMBER_LENGTH; ++i) {
				System.out.print(result[i]);
			}
			System.out.print('\n');
			/*
			 * Start at the end and try to increment from right to left.
			 */
			for (int i = PHONE_NUMBER_LENGTH - 1; i >= -1; --i) {
				if (i == -1) // if attempted to carry past leftmost digit,
					return; // we're done, so return
				/*
				 * Start with high value, carry case so 0 and 1 special cases
				 * are dealt with right away
				 */
				if (getCharKey(phoneNum[i], 3) == result[i] || phoneNum[i] == 0
						|| phoneNum[i] == 1) {
					result[i] = getCharKey(phoneNum[i], 1);
					// No break, so loop continues to next digit
				} else if (getCharKey(phoneNum[i], 1) == result[i]) {
					result[i] = getCharKey(phoneNum[i], 2);
					break;
				} else if (getCharKey(phoneNum[i], 2) == result[i]) {
					result[i] = getCharKey(phoneNum[i], 3);
					break;
				}
			}
		}
	}

}
