import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class MyInteger {

	public static void main(String[] args) {
		// System.out.println(Math.sqrt(49));
		// System.out.println(MyInteger.findSqrt(49));
		// combine(0);
		// printFib();
		System.out.println(reverseString(s));
		//primeFactor(18);
		factor(30);
		//printParenthesis(new char[8], 4, 0, 0, 0);

	}

	public static void printParenthesis(char[] a, int n, int p, int open, int close){

		if(close == n){

			for(int j = 0 ; j < a.length; j++){
				System.out.print(a[j]);
			}
			System.out.println();

		}else{
			if(close < open){
				a[p] = '}';
				printParenthesis(a,n,p+1,open, close+1);
			}

			if(open < n){
				a[p] = '{';
				printParenthesis(a,n,p+1,open+1, close);
			}

		}
	}

	/*static final int[][] paths = {
      {0,0,1,0,1},
      {0,0,0,0,0},
      {0,1,1,1,0},
      {0,1,1,0,0},
      {0,1,1,0,0}

  };*/



	public static double findSqrt(double number) {
		if (number < 0) {
			return Double.NaN;
		}
		if (number == 0 || number == 1) {
			return 1;
		}

		double precision = 0.00001;
		double start = 0;
		double end = number;

		while ((end - start) > precision) {
			double mid = (start + end) / 2;
			double msqr = mid * mid;
			if (msqr == number) {
				return msqr;
			} else if (msqr < number) {
				start = mid;
			} else {
				end = mid;
			}

		}
		return (start + end) / 2;
	}

	// find the next heighest number
	int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 4, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

	// int[] data = {3,4,7,2,2,6,4,1};
	private void findNextHighestInteger() {
		int l = data.length;
		int s = 0;
		int m = -1;
		// int t = data[data.length -1];
		for (int i = data.length - 1; i >= 0; i--) {
			if (i - 1 > 0 && data[i] > data[i - 1]) {
				m = i;
				break;
			}

		}

		if (m != -1) {

			int[] right = new int[l - m];
			int[] left = new int[m];

			System.arraycopy(data, 0, left, 0, left.length);
			System.arraycopy(data, left.length, right, 0, right.length);

			System.out.println(left);
			System.out.println(right);

			int lastLeft = left[left.length - 1];
			int rightPosition = -1;
			int max = Integer.MAX_VALUE;

			for (int i = 0; i < right.length; i++) {
				if (right[i] > lastLeft && right[i] < max) {
					rightPosition = i;
				}
			}
			int temp = right[rightPosition];
			right[rightPosition] = lastLeft;
			left[left.length - 1] = temp;

			Arrays.sort(right);

			long finalNumber = 0;

			for (int ll : left) {
				finalNumber = finalNumber * 10;
				finalNumber = finalNumber + ll;
			}

			for (int r : right) {
				finalNumber = finalNumber * 10;
				finalNumber = finalNumber + r;
			}

			System.out.println(finalNumber);
		}

	}
	
	private void printfib(int n) {

		System.out.print("0,1,1");

		int f = 1;
		int s = 1;
		int o = 0;

		while (o < n) {
			System.out.print(",");
			o = f + s;
			f = s;
			s = o;
			System.out.print(o);
		}
	}

	private int printfibnachi(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (n > 1) {
			return printfibnachi(n - 1) + printfibnachi(n - 2);
		} else {
			return -1;
		}
	}

	public static boolean isPow2(int number) {

		if ((number & (number - 1)) == 0)
			return true;
		else
			return false;
	}
	
	/*
	1
	2 3
	4 5 6
	7 8 9 10
*/	
	public void printFloyd(){
		int number = 1;
		for(int i = 1; i <= 10; i++){		
			for(int j=1; j<=i; j++){
				System.out.println( number + " ");
			}
			System.out.println();
		}
	}


	public static int sum(int a, int b) {
		if (b == 0) {
			return a;
		}

		a = a ^ b;
		b = (a & b) << 1;
		return sum(a, b);
	}

	public static boolean isEven(int number) {
		if ((number & 1) == 1) {
			return false;
		} else {
			return true;
		}
	}

	static String s = "ABCD";
	static StringBuilder sb = new StringBuilder();
	static boolean[] used = new boolean[s.length()];

	public static void permute() {
		if (sb.length() == s.length()) {
			System.out.println();
		}

		for (int i = 0; i < s.length(); i++) {
			if (used[i]) {
				sb.append(s.charAt(i));
			}
			permute();
			used[i] = false;
			sb.setLength(sb.length() - 1);
		}
	}

	public static void combine(int start) {
		for (int i = start; i < s.length(); i++) {
			sb.append(s.charAt(i));
			System.out.println(sb);
			combine(start + 1);
			sb.setLength(sb.length() - 1);
		}
		/*
		 * sb.append(data.charAt(data.length() -1)); System.out.println(sb);
		 * sb.setLength(sb.length() - 1);
		 */
	}

	public static void printFib() {
		for (int i = 1; i < 10; i++) {
			System.out.print(fibn(i));
			System.out.print(" ");
		}
	}

	public static int fibn(int n) {
		if (n == 1 || n == 0) {
			return 1;
		}

		return fibn(n - 1) + fibn(n - 2);
	}

	public static String reverseString(String s) {
		if (s.length() < 2) {
			return s;
		}

		return reverseString(s.substring(1)) + s.charAt(0);
	}

	public static void primeFactor(int n){

		if(n == 0 || n == 1 ){
			System.out.println(n);
		}

		int copyNumber = n;
		for(int i = 2; i <= copyNumber; i++){
			if(copyNumber % i == 0){
				System.out.print(i+",");
				copyNumber = copyNumber / i;
				i = i-1;
			}
		}
	}

	public static void factor(int n){

		if(n == 0 || n == 1 ){
			System.out.println(n);
		}

		int copyNumber = n;
		for(int i = 2; i <= copyNumber; i++){
			if(copyNumber % i == 0){
				System.out.print(i+",");
				//copyNumber = copyNumber / i;
				//i = i-1;
			}
		}
	}



}
