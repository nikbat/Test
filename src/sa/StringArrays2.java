package sa;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

public class StringArrays2 {
	
	public static void main(String[] args){
		StringArrays2 sa = new StringArrays2();
		String a = "Battle of the Vowels: Hawaii vs. Grozny";
		String b = "aeiou";
		sa.deleteChars(a, b);
		sa.reverse();
		sa.stringToInteger();
		sa.integerToString();
		String p = "ABCDDCBA";
		System.out.println(sa.isPalindrome(p));
		sa.printfib(10);
		sa.concatStaring();
		sa.maxInt();
		sa.printDiagonal();
		sa.allPossiblePlaceOfQueen();
		
		
	}
	
	private void deleteChars(String a, String b){
		//convert all String value of b into 256 boolean array
		boolean[] cr = new boolean[256];
		for(char c : b.toCharArray()){
			cr[(int)c] = true;
		}
		
		char[] aA = a.toCharArray();
		
		for(int i = 0; i < aA.length; i++){
			if(cr[ (int)aA[i] ] ) {
				aA[i] = ' '; 
			}
		}
		System.out.println(new String(aA));		
	}
	
	//Do or do not, there is no	try." to "try. no is there not, do or Do".
	private void reverse(){
		String s = "Do or do not, there is no try.";
		//String s = "Test";
		boolean space = false;
		char[] sa = s.toCharArray();
		char[] fa = new char[sa.length];
		int to = sa.length;
		int facount = 0;
		
		for(int i=sa.length-1; i>=0; i--){
			//System.out.println(i +"------"+ sa[i]);
			if(sa[i] == ' '){
				// got space char
				for(int j = i+1; j < to; j++  ){
					fa[facount++] = sa[j];
				}				
				
				to = i;
				fa[facount++] = ' ';
			}
			if(i == 0){
				for(int j = i; j < to; j++  ){
					fa[facount++] = sa[j];
				}
			}
		}
		
		System.out.println(new String(fa));
		
	}
	
	private void stringToInteger(){
		
		String s = "123456078";
		char[] sa = s.toCharArray();
		int number = 0;
		int zp = (int)'0';
		
		for(int i = 0; i<sa.length; i++){
			int t = ((int)sa[i]) - zp;
			number = number*10 + t;
		}
		
		System.out.println(number);
		
	}
	
	private void integerToString(){
		int n = 123456;
		int nn = 0;
		StringBuilder sb = new StringBuilder();
		char[] ca = new char[10];
		int cc = 0;
		while(n != 0){
			int t = n % 10;
			sb.append( t);
			ca[cc++] = (char)(t + '0');
			nn = nn*10 + t;
			n = n/10;
		}
		
		//System.out.println(nn);
		System.out.println(sb.toString());
		System.out.println(new String(ca));
	}
	
	private boolean isPalindrome(String s){
		if(s.length() == 0 || s.length() < 2){
			return true;
		}
		
		boolean isPalindrome = true;
		
		int l = s.length();
		for(int i=0; i < (l/2 +1); i++){
			//System.out.println(i +" "+s.charAt(i)+" "+s.charAt(l-i-1));
			if(s.charAt(i) != s.charAt(l - i - 1)){
				return false;
			}
			
		}
		return isPalindrome;
	}
	
	private void printfib(int n){
		
		System.out.print("0,1,1");
		
		int f = 1;
		int s = 1;
		int o = 0;
		
		while(o < n){
			System.out.print(",");
			o = f + s;
			f = s;
			s = o;
			System.out.print(o); 
		}
	}
	
	private int printfibnachi(int n){
		if(n == 0){
			return 0;
		}else if(n == 1){
			return 1;
		}else if(n > 1){
			return printfibnachi(n-1)+printfibnachi(n-2);			
		}else{
			return -1;
		}
	}
	
	//aaaaa eeeee a5 e5 a4 e4
	
	
	private void concatStaring(){
		String s = "45eeeedfghfffeff62";
		StringBuilder sb = new StringBuilder();
		int mc = 1;
		boolean foundMatching = false;
		for(int i=0; i < s.length(); i++){
			char t = s.charAt(i);
			if(i != (s.length()-1) && t == s.charAt(i+1)){
				foundMatching = true;
				mc++;
			}else{
				foundMatching = false;
				if(mc>1){
					sb.append(mc);
					mc = 1;
				}
				sb.append(t);
			}
		}
		
		System.out.println();
		System.out.println(sb);
	}
	
	/*
	 * {10, 20, 30, 40}, 
		{15, 25, 35, 45},
		{27, 29, 37, 48},
		{32, 33, 39, 50},
		
		32,27,15,10
		27	 	 20
		39,	 	 30
		50,48,45,40
		
		0,0 0,length-1
		0,1 1,lenght-1
		0,2 2,lenght-1
		0,3 3,lenght-1
		
		(i+j), (j-1)
		
		1,0 1, lenght-1 - (i)

	 */
	int m[][] = { 
			{10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50},
    };
	
	private void shiftRotateArrayByLeft(){
		int e = m.length-1;
		int c = e / 2;
		int b = e % 2;
		
		for(int r = c; r >=0; r--){
			for(int d = c - r; d < c+r+b;  d++){
				int t = m[c-r][d];
				m[c - r][d] = m[d][e - c + r];
				m[d][e - c + r] = m[e - c + r][e - d];
	            m[e - c + r][e - d] = m[e - d][c - r];
	            m[e - d][c - r] = t;
			}
		}
	}
	
	/*The rotation can be performed in layers, where you perform a cyclic swap on the edges on each layer. In the first for loop, we rotate the first layer (outermost edges). We rotate the edges by doing a four-way swap first on the corners, then on the element clockwise from the edges, then on the element three steps away.
	Once the exterior elements are rotated, we then rotate the interior region’s edges.*/
	
	public static void rotate(int[][] matrix, int n) {
		for (int layer = 0; layer < n / 2; ++layer) {
			int first = layer;
			int last = n - 1 - layer;
			for(int i = first; i < last; ++i) {
				int offset = i - first;
				int top = matrix[first][i]; // save top
				// left -> top
				matrix[first][i] = matrix[last-offset][first];
				
				// bottom -> left
				matrix[last-offset][first] = matrix[last][last - offset];
				
				// right -> bottom
				matrix[last][last - offset] = matrix[i][last];
				
				// top -> right
				matrix[i][last] = top; // right <- saved top
			}
		}
	}
	
	
	
	// sort 2 dimensional like following array to 1 dimensional array, my suggestion is to take each array and merge sort   
	
	int ms[][] = { 
			{10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50},
    };

	private int search(int n, int x)
	{
	   int i = 0, j = n-1;  //set indexes for top right element
	   while ( i < n && j >= 0 )
	   {
	      if ( ms[i][j] == x )
	      {
	         System.out.printf("\n Found at %d, %d", i, j);
	         return 1;
	      }
	      if ( ms[i][j] > x )
	        j--;
	      else //  if mat[i][j] < x
	        i++;
	   }
	 
	   System.out.println("\n Element not found");
	   return 0;  // if ( i==n || j== -1 )
	}
	 
	
	int[][] sa ={ 
			{10, 20, 30, 40},
			{15, 25, 35, 45},
			{27, 29, 37, 48},
			{32, 33, 39, 50},
  		};
	
	private void printDiagonal(){
		Set<Integer> s = new HashSet<Integer>();
		
		for(int i=0;i < sa.length; i++){
			
			for(int j= 0; j<sa[0].length; j++){
				
				if(!s.contains(sa[i][j])){
					s.add(sa[i][j]);
					System.out.print(sa[i][j]);
				}
				int k = i+1;
				int l = j-1;
				
				while(k < sa.length && l >= 0 ){
					System.out.print(",");
					if(!s.contains(sa[k][l])){
						s.add(sa[k][l]);
						System.out.print(sa[k][l]);
					}
					k++;
					l--;
				}
				
				System.out.println();
				
			}
			
			
		}
	}
	
	private void allPossiblePlaceOfQueen(){
		int[][] game = new int[8][8];
		int[] rows = new int[8];
		int[] columns = new int[8];
		
		for(int i=0; i < game.length; i++){
			for (int j=0; j < game.length;j++){
				
				if(rows[i] == 0 && columns[j] == 0){
					//check digonal
					if(checkDiognal(i, j, rows, columns, game)){
						game[i][j] = 1;
						rows[i] = 1;
						columns[j] = 1;
					}
					//move the row and column by one space since you cannot write in same row and column 
				}
			}
		}
		
		System.out.println(ArrayUtils.toString(game));
	}
	
	private boolean checkDiognal(int k, int l, int[] rows, int[] columns,int[][] game){
		int i = k;
		int j = l; 
		
		while (--i >=0 && --j >= 0){
			if(game[i][j] == 1){
				return false;
			}
		}
		
		i = k;
		j = l; 
		
		while (--i >=0 && ++j < game[0].length){
			if(game[i][j] == 1){
				return false;
			}
		}
		
		
		
		return true;
	}
	
	public static void multTables ( int max )
    {
        for ( int i = 1; i <= max; i++ ) {
            for ( int j = 1; j <= max; j++ ) {
                System.out.print ( String.format ( "%4d", j * i ));
            }
            System.out.println();
        }
    }
	
	public String formatRGB ( int r, int g, int b ) {
        return (toHex(r) + toHex(g) + toHex(b)).toUpperCase();
    }

    public String toHex ( int c ) {
        String s = Integer.toHexString ( c );
        return ( s.length() == 1 ) ? "0" + s : s;
    }
	
	
	private void maxInt(){
		int i = (~0);
		int j = i >>> 1;
		System.out.println(i);
		System.out.println(j);
	}
	
	private void test(){
		int[] i = {1,2,3,4,5,6,7,8};
		int[] j = {1,2,3,4,5,6,7,8};
	}
	
	
	
	
	
		
}
