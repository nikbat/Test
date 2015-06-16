package sa;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

//import org.apache.commons.lang.ArrayUtils; 

public class Sort2 {

	public static void main(String[] args) {
		Sort2 so = new Sort2();
		
		//int[] data = {1,2,3,5,6,7,9,10};
		//System.out.println(so.bsbs(data, 4, 0, data.length));
		
		//so.findNextHighestInteger();
		
		/*int[] array = { 6, 4, 9, 5, 1, 3, 2, 7, 8, 10 };
		int[] data = so.mergeSort(array);
		//System.out.println(ArrayUtils.toString(data));
		int[] array2 = { 6, 4, 9, 5, 1, 3, 2, 7, 8, 10 };
		int[] data2 = so.quickSort(array2);
		//System.out.println("q " + ArrayUtils.toString(data2));

		int[] a = { 0, 11, 12, 13, 14, 15 };
		int[] fa = new int[array.length + a.length];
		int[] oa = so.merge(fa, array, a);
		//System.out.println(ArrayUtils.toString(oa));
		System.out.println(so.bs(oa, 14));
		System.out.println(so.bs(oa, 11));
		System.out.println(so.bs(oa, 15));
		System.out.println(so.bs(oa, 2));
		System.out.println(so.bs(oa, 1));
		System.out.println(so.bs(oa, 0));
		System.out.println(so.bs(oa, 16));

		int[] aa = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 15 };
		System.out.println(so.msbs(aa, 20));

		String[] sa = { "at", "", "", "", "", "ball", "car", "", "", "dad", "",
				"" };
		System.out.println("sa    " + so.bsss(sa, "ball", 0, sa.length - 1));

		// int i = 7516192768;
		int i = 939524102;

		// int[] a1 = {23,27,29,31,37,1,4,11,14,15,17,19};
		// so.pivotedSearch(A, s, h, elem)
		// int[] a1 = {23,27,29,31,37,9,11,14,15,17};
		System.out.println(so.pivotedSearch(a1, 0, a1.length - 1, 27));*/
		
	}

	private int[] mergeSort(int[] data) {
		if (data.length < 2) {
			return data;
		}

		int mid = data.length / 2;
		int[] left = new int[mid];
		int[] right = new int[data.length - mid];

		System.arraycopy(data, 0, left, 0, left.length);
		System.arraycopy(data, mid, right, 0, right.length);

		left = mergeSort(left);
		right = mergeSort(right);

		return merge(data, left, right);
	}
	
	private int[] merge(int[] data, int[] left, int[] right) {

		int di = 0;
		int li = 0;
		int ri = 0;

		while (li < left.length && ri < right.length) {
			if (left[li] <= right[ri]) {
				data[di++] = left[li++];
			} else {
				data[di++] = right[ri++];
			}
		}

		while (li < left.length) {
			data[di++] = left[li++];
		}

		while (ri < right.length) {
			data[di++] = right[ri++];
		}

		return data;
	}

	private int[] quickSort(int data[]) {

		if (data.length < 2) {
			return data;
		}
		int pIndex = (data.length) / 2;
		int p = data[pIndex];
		// count how many are less than pivot
		int leftCount = 0;
		for (int i = 0; i < data.length; ++i) {
			if (data[i] < p) {
				++leftCount;
			}
		}

		System.out.println(data.length);
		System.out.println(data.length - (leftCount + 1));
		int[] left = new int[leftCount];
		int[] right = new int[data.length - (leftCount + 1)];

		int l = 0;
		int r = 0;

		for (int i = 0; i < data.length; i++) {
			if (i == pIndex) {
				continue;
			}
			if (data[i] < p) {
				left[l++] = data[i];
			} else {
				right[r++] = data[i];
			}
		}

		left = quickSort(left);
		right = quickSort(right);

		System.arraycopy(left, 0, data, 0, left.length);
		data[left.length] = p;
		System.arraycopy(right, 0, data, left.length + 1, right.length);

		return data;

	}
	
	private int bs(int[] data, int e) {
		int p = -1;

		int s = 0;
		int l = data.length - 1;
		int m = data.length / 2;

		while (s <= l) {
			if (e == data[m]) {
				// found element break;
				p = m;
				break;
			} else if (e > data[m]) {
				// element is in a right block, so move s and find new middle
				// element
				s = m + 1;
			} else {
				l = m - 1;
			}

			m = (s + l) / 2;

		}

		return p;
	}
	

	
	private int msbs(int[] data, int e) {
		int p = -1;

		int l = 0;
		int u = data.length - 1;
		int m = (l + u) / 2;

		while (l <= u) {
			if (e == data[m]) {
				// found element break;
				p = m;
				break;
			} else if (data[u] <= data[m]) {
				if (e > data[m]) {
					l = m + 1;
				} else if (e >= data[l]) {
					u = m - 1;
				} else {
					l = m + 1;
				}
			} else if (e > data[m]) {
				// element is in a right block, so move s and find new middle
				// element
				l = m + 1;
			} else {
				u = m - 1;
			}

			m = (l + u) / 2;

		}

		return p;
	}

	private int bsss(String[] data, String x, int start, int last) {
		int p = -1;

		while (start <= last) {

			while (data[last].equals("")) {
				last--;
				if (last < start) {
					return -1;
				}
			}

			// find mid
			int m = (start + last) / 2;
			while (data[m].equals("")) {
				m++;
			}

			if (data[m].equals(x)) {
				p = m;
				break;
			} else if (data[m].compareTo(x) > 0) {
				last = m - 1;
			} else {
				start = m + 1;
			}
		}

		return p;
	}

	// {23,27,29,31,37,1,4,11,14,15,17,19}

	/*
	 * private int searchRotatedSortedArray(int[] A, int s, int l, int elem){
	 * 
	 * int mid = (s+l)/2;
	 * 
	 * 
	 * while(s < l){ if(A[mid] == elem){ return mid; }else if(A[mid] > elem ){
	 * return (A[s] <= A[mid] && A[s] < elem) ? searchRotatedSortedArray(A,
	 * mid+1, l, elem): searchRotatedSortedArray(A, mid-1, l, elem); }else
	 * return ( A[mid] <= A[l] && A[l] < elem )? searchRotatedSortedArray(A, s,
	 * mid-1, elem): searchRotatedSortedArray(A, mid+1, l, elem); }
	 * 
	 * return -1;
	 * 
	 * }
	 */

	// {23,27,29,31,37,1,4,11,14,15,17,19}
	// int[] a1 = {23,27,29,31,37,1,4,11,14,15,17,19};
	// int[] a1 = {23,27,29,31,37,9,11,14,15,17};
	static int[] a1 = { 11, 14, 15, 17, 23, 27, 29, 31, 37, 9 };

	private int pivotedSearch(int[] A, int s, int h, int elem) {
		int p = findPivot(A, s, h);
		if (p == -1) {
			binarySearch(A, s, h, elem);
		}

		if (A[p] == elem) {
			return p;
		} else if (A[0] <= elem) {
			return binarySearch(A, 0, p - 1, elem);
		} else {
			return binarySearch(A, p + 1, h, elem);
		}
	}

	private int findPivot(int[] A, int s, int h) {
		if (h < s)
			return -1;
		if (h == s)
			return s;

		int mid = (s + h) / 2;

		// if(mid < h && A[mid] > A[mid+1] ) return mid;
		// if(mid > s && A[mid] < A[mid -1]) return mid-1;
		if (A[mid] > A[mid + 1])
			return mid;
		if (A[mid] < A[mid - 1])
			return mid - 1;
		if (A[s] >= A[mid]) {
			return findPivot(A, s, mid - 1);
		} else {
			return findPivot(A, mid + 1, h);
		}
	}

	private int binarySearch(int[] A, int s, int h, int elem) {

		if (h < s) {
			return -1;
		}

		int m = (s + h) / 2;
		if (A[m] == elem) {
			return m;
		} else if (A[m] > elem) {
			return binarySearch(A, s, m - 1, elem);
		} else {
			return binarySearch(A, m + 1, h, elem);
		}

	}
	
	private int bsbs(int[] data, int e, int s, int l){
		
		if(l < s){
			return -1;
		}
		
		int mid = (l+s)/2;
				
		if(data[mid] == e){
			return mid;
		}else if(e < data[mid]){
			return bsbs(data,e,s,mid-1);			
		}else{
			return bsbs(data,e,mid+1,l);
		}
	
		
	}
	
	private int bsrw(int[] A, int s, int h, int e){
		if( h < s){
			return -1;
		}
		
		int mid = (h+s)/2;
		if(A[mid] == e){
			return mid;
		}else if(A[mid] <= e){
			bsrw(A,s,mid-1,e);
		}else{
			bsrw(A, mid+1, h, e);
		}
		
		return -1;
		
	}

	// odd even sort
	Integer[] oe = { 1, 2, 3, 4, 5, 6, 7, 8 };

	private void sortOddEvenNumber() {
		List<Integer> oelist = Arrays.asList(oe);
		Collections.sort(oelist, new MyOddEvenComparator());
		System.out.println(oelist);
	}

	int[][] sa = { { 10, 20, 30, 40 }, { 15, 25, 35, 45 }, { 27, 29, 37, 48 },
			{ 32, 33, 39, 50 }, };

	private void sortedTwoDimensionalArray(int element) {
		int i = 0;
		int j = sa[0].length - 1;

		while (i < sa.length && j >= 0) {
			if (sa[i][j] == element) {
				System.out.println("foundElement " + i + j);
				break;
			}

			if (sa[i][j] > element) {
				j--;
			} else {
				i++;
			}
		}
	}
	
	// sort stack
	public static Stack<Integer> sort(Stack<Integer> s) {
		Stack<Integer> r = new Stack<Integer>();
		while (!s.isEmpty()) {
			int tmp = s.pop();
			while (!r.isEmpty() && r.peek() > tmp) {
				s.push(r.pop());
			}
			r.push(tmp);
		}
		return r;
	}
	
	
	private static int rotatedaarray(int[] a, int e){
		
		int s = 0;
		int l = a.length - 1;
		
		
		while(s <= l){
			int m = (s+l)/2;
			if(a[m] == e){
				return m;
			}else if(a[s] <= a[m]){
				if(a[s] <= e && e < a[m]){
					l = m-1;
				}else{
					s = m+1;
				}
			}else{
				if(a[m] < e && e <= a[l]){
					s = m + 1;
				}else{
					l = m - 1;
				}
			}
			
		}
		return -1;
		
	}
	
	static int rotated_binary_search(int A[], int N, int key) {
		  int L = 0;
		  int R = N - 1;
		 
		  while (L <= R) {
		    // Avoid overflow, same as M=(L+R)/2
		    int M = L + ((R - L) / 2);
		    if (A[M] == key) return M;
		 
		    // the bottom half is sorted
		    if (A[L] <= A[M]) {
		      if (A[L] <= key && key < A[M]) 
		        R = M - 1;
		      else
		        L = M + 1;
		    }
		    // the upper half is sorted
		    else {
		      if (A[M] < key && key <= A[R])
		        L = M + 1;
		      else 
		        R = M - 1;
		    }
		  }
		  return -1;
	}
	
	static int A[] = {4, 5, 6, 7, 0, 1, 2};
	
	public int fr(int e){
		int s = 0;
		int l = A.length;
		
		while(s < l ){
			int m = (s+l)/2;
			
			if(A[m] == e){
				return e;
			}
			
			if(A[s] < A[m]){
				if(A[s] <= e && e < A[m]){
					l = m-1;
				}else{
					s = m+1;
				}
			}else{
				if(A[m] < e && e <= A[l]){
					s = m+1;
				}else{
					l = m-1;
				}
			}
		}
		
		return -1;
	}
	

}

class MyOddEvenComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer n1, Integer n2) {
		if (n1 % 2 == 0 && n2 % 2 == 0) {
			return n1.compareTo(n2);
		} else if (n1 % 2 == 0) {
			return 1;
		} else if (n2 % 2 == 0) {
			return -1;
		} else {
			return 0;
		}
	}

}