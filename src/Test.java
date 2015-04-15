import java.util.regex.Pattern;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//multiplecation by 2;
		
		
		int number = 8;
		System.out.println(number << 1);
		
		//division by 2 
		number = 8;
		System.out.println(number >> 1);
		
		number = 8;
		System.out.println(number >>> 1);
		
		/*int a = 9;
		int b = 5;
		
		a = a^b;
		System.out.println(a);
		b = a^b;
		System.out.println(b);
		a = a^b;
		System.out.println(a);
		
		a = 9;
		b = 5;
		
		a = a+b;
		b = a-b;
		a = a-b;
		
		System.out.println(a+" , "+b);*/
		
		
		//printSeries(50);
		//printSeries2(10);
		
		/*int i = 125/10;
		System.out.println(i);*/
		
		Pattern p = Pattern.compile(".*[1-9].*");
		System.out.println(p.matcher("1abcd").find());
		
		
		
	}
	
	private static void printSeries(int data){
		int l = 1;
		int m = 1;		
		System.out.print(l);
		System.out.print(",");
		System.out.print(m);
		System.out.print(",");
		for(int i = 3; i < data; i++){
			int fib = l + m;
			System.out.print(fib);
			System.out.print(",");
			l = m;
			m = fib;
		}
	}
	
	private static int printSeries2(int n){
		if(n == 0 || n == 1 ){
			return n;			
		}
		
		int fib = printSeries2(n-1) + printSeries2(n-2);
		System.out.print(fib+",");
		return fib;
	}

}
