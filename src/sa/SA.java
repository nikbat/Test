package sa;

import ll.LLFinal;
import ll.LNode;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class SA {

  private static boolean isAllCharsUnique1(String str){
    if(str == null){
      return true;
    }

    char[] strc = str.toCharArray();
    boolean unique = true;

    for(int i=0; i <strc.length; i++){
      for(int j = i+1; j<strc.length; j++){
        if(strc[j] ==  strc[i]){
          return false;
        }
      }
    }
    return unique;
  }

  private static boolean isAllCharsUnique2(String str){
    if(str == null){
      return true;
    }

    char[] strc = str.toCharArray();
    boolean unique = true;
    boolean[] b = new boolean[256];

    for(int i=0; i <strc.length; i++){
      if(b[strc[i]]){
        return false;
      }else{
        b[strc[i]] = true;
      }
    }
    return unique;
  }

  private static String  removeDuplicate(String str){
    char[] strc = str.toCharArray();
    boolean[] b = new boolean[256];
    int j = 0;

    for(int i = 0; i < strc.length; i++){
      if(!b[strc[i]] || strc[i] == ' '){
        strc[j++] = strc[i];
        b[strc[i]] = true;
      }
    }

    for(int i = j; j<strc.length; i++){
      strc[j++] = ' ';
    }

    String s = new String(strc);
    return s;

  }

  private static String  removeDuplicate1(String str){
    char[] strc = str.toCharArray();
    boolean[] b = new boolean[256];
    int j = 0;

    for(int i = 0; i < strc.length; i++){
      if(!b[strc[i]] || strc[i] == ' '){
        strc[j++] = strc[i];
        b[strc[i]] = true;
      }
    }

    for(int i = j; j<strc.length; i++){
      strc[j++] = ' ';
    }

    String s = new String(strc);
    return s;

  }

  public String removeDuplicate2(String str){
    char[] strc = str.toCharArray();
    int k = 1;
    for(int i = 1; i < strc.length; i++ ){
      boolean dup = false;
      for(int j = 0; j < i ; j++){

        if(strc[j] == strc[i]){
          dup = true;
          break;
        }
      }
      if(!dup){
        strc[k++] = strc[i];
      }
    }

    strc[k] = 0;
    /*for(int i = k; k<strc.length; i++){
      strc[k++] = ' ';
    }*/

    String s = new String(strc);
    return s;
  }

  private static boolean isAnnagram(String s1, String s2){

    if(s1 == null && s2 == null){
      return true;
    }else if(s1 == null || s2 == null){
      return false;
    }else if(s1.length() != s2.length()){
      return false;
    }

    int[] cc = new int[256];
    char[] cas1 = s1.toCharArray();
    char[] cas2 = s2.toCharArray();

    for(int i = 0; i < cas1.length; i++){
      cc[cas1[i]]++;
    }

    for(int i = 0; i < cas2.length; i++){
      cc[cas2[i]]--;
      if(cc[cas2[i]] < 0){
        return false;
      }
    }

    return true;
  }

  private static void fillSpace(String str){

    char[] strc = str.toCharArray();

    int spaceCount = 0;
    System.out.println(strc.length);

    for(int i = 0; i < strc.length; i++){
      if(strc[i] == ' '){
        spaceCount++;
      }
    }
    int newlength = strc.length + spaceCount*2;

    //strc[newlength] = '\0';
    //System.out.println(strc.length);
  }

  private static void updateMatrix(int[][] matrix){

    System.out.println(ArrayUtils.toString(matrix));

    //DO null check
    int[] rows = new int[matrix.length];
    int[] columns = new int[matrix[0].length];

    for(int i = 0; i<matrix.length; i++){
      for(int j = 0; j < matrix[i].length; j++){
         if(matrix[i][j] == 0){
           rows[i] = 1;
           columns[j] = 1;
         }
      }
    }
    for(int i = 0; i<matrix.length; i++){
      for(int j = 0; j < matrix[i].length; j++){
        if(rows[i] == 1 || columns[j] ==1){
          matrix[i][j] = 0;
        }
      }
    }

    System.out.println(ArrayUtils.toString(matrix));
  }

  public static void reverse(){

    String s = "Do or do not, there is no try";
    char[] cs = s.toCharArray();
    char[] fa = new char[cs.length];
    int l = cs.length;
    int c = 0;
    for(int i = cs.length - 1; i >= 0; i--){
      if(cs[i] == ' ' || i == 0 ){
        int k;
        if( i == 0){
          k = i;
        }else{
          k = i+1;
        }
        for(int j = k ; j<l; j++){
          fa[c++] = cs[j];
        }
        if(i != 0){
          fa[c++] = ' ';
        }
        l = i;
      }
    }

    System.out.println(String.valueOf(fa));
  }

  //int[] a = {1,2,3,4,5};
  //This is wrong solution, see below - https://www.geeksforgeeks.org/array-rotation/
  static void rotateArray(int[] a,  int N){
    for(int i=0; i<a.length; i++){
      int temp = a[(i+N) % N];
      a[ (i+N) % N ] = a[i];
      a[i] = temp;
    }
  }

  static void rotateArray1(int[] a,  int N){
    for(int i=0; i<a.length; i++){
      int t = Math.abs(i-N);
      int temp = a[t % N];
      a[ t % N ] = a[i];
      a[i] = temp;
    }
  }

  static void leftRotate(int arr[],  int n) {
    for (int i = 0; i < n; i++) {
      leftRotatebyOne(arr, arr.length);
    }
  }
  static void leftRotatebyOne(int arr[], int n) {
    int i, temp;
    temp = arr[0];
    for (i = 0; i < n - 1; i++) {
      arr[i] = arr[i + 1];
    }
    arr[i] = temp;
  }

  //https://www.geeksforgeeks.org/find-smallest-number-whose-digits-multiply-given-number-n/
  // Find the smallest number whose digits multiply to a given number n
  static int minimumIntegerToMultiplyToGetSum(int N) {
    PriorityQueue<Integer> digits = new PriorityQueue<Integer>();

    for (int factor = 9; factor > 1; factor--) {
      while (N % factor == 0) {
        N /= factor;
        digits.add(factor);
      }
    }
    if (N != 1)
      return -1;

    int number = 0;
    for (int i : digits) {
      number = number * 10;
      number = number + i;
    }
    return number;
  }

  static String concatStaring(String s) {
    //String s = "45eeeedfghfffeff62";
    char[] sa = s.toCharArray();

    StringBuilder sb = new StringBuilder();

    for(int i=0; i < sa.length; i++){
      sb.append(sa[i]);
      int count = 1;
      while(i < sa.length-1 && sa[i] == sa[i+1]){
        i++;
        count++;
      }
      if(count > 1){
        sb.append(count);
      }
    }

    return sb.toString();
  }

  static int[][] sa = {
      { 10, 20, 30, 40 },
      { 15, 25, 35, 45 },
      { 27, 29, 37, 48 },
      { 32, 33, 39, 50 },
  };

  static void printDigonal(){
    /*i=0; j=0;
    i=0; j=1; i=1;j=0
    i=0; j=2; i=1;j=1; i=2;j=0
    i=0; j=3; i=1;j=2; i=2;j=1; i=3; j=0;
    i=1; j=3; i=2;j=2; i=3;j=1

    */


    for(int i = 0; i <sa.length; i++){
      int r = 0;
      int c = i;
      while(c>=0 && r<sa.length){
        System.out.print(sa[r][c]);
        c--;
        r++;
      }
      System.out.println();
    }
    System.out.println("-");
    for(int i = 1; i <sa.length; i++){
      int r = i;
      int c = sa[i].length-1;
      while(c>=0 && r<sa.length){
        System.out.print(sa[r][c]);
        c--;
        r++;
      }
      System.out.println();
    }

  }

  //https://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/
  static boolean find(int[][] matrix, int n){

    /*int matrix1 [][] = {
        {10, 20, 30, 40},
        {15, 25, 35, 45},
        {27, 29, 37, 48},
        {32, 33, 39, 50}};*/

    int r = 0;
    int c = matrix[0].length -1;

    while(r < matrix.length && c>=0){
      if(matrix[r][c] == n){
        return true;
      }else if(matrix[r][c] > n){
        c--;
      }else if(matrix[r][c] < n){
        r++;
      }
    }

    return false;
  }

  //https://www.geeksforgeeks.org/calculate-angle-hour-hand-minute-hand/
  static void findAngle(int h, int m){

    // validate the input
    if (h <0 || m < 0 || h >12 || m > 60)
      System.out.println("Wrong input");

    double mpm = 360/60; // 6
    double hpm = 360.0/(12*60); //.5

    if (h == 12)
      h = 0;
    if (m == 60)
      m = 0;

    int hour_angle = (int)( (h*60 + m) *.5 );
    int minute_angle = (int) (6 * m);

    int angle = Math.abs(hour_angle - minute_angle);

    System.out.println(angle);

  }

  //https://www.geeksforgeeks.org/k-th-prime-factor-given-number/
  static void printKthprimeFactor(int n, int k){

  }

  static void printPrimeFactor(int n){
    List<Integer> l = new ArrayList<>();
    for(int i=2; i<=n; i++ ){
      if(n % i == 0 && i%2 == 0){
        l.add(i);
      }
    }
    System.out.println(l);
  }

  /*static final int[][] paths = {
      {0,0,1,0,1},
      {0,0,0,0,0},
      {0,1,1,1,0},
      {0,1,1,0,0},
      {0,1,1,0,0}

  };*/

  static int counter = 0;
  public static boolean solveMaze(int[][] paths, int x, int y, int i, int j){
    if(stepMaze(paths,x,y,i,j)){
      paths[x][y] = 5;
      return true;
    }
    return false;
  }



  public static boolean stepMaze(int[][] paths, int x, int y, int i, int j){
    counter++;
    if(x == i && y == j){
      return true;
    }

    if(x < 0 || x >= paths.length || y < 0 || y >= paths.length){
      return false;
    }

    if(paths[x][y] == 1 || paths[x][y] == 2){
      return false;
    }

    paths[x][y] = 2;
    if(stepMaze(paths, x, y+1, i, j)){
      return true;
    }

    if(stepMaze(paths, x-1, y, i, j)){
      return true;
    }

    if(stepMaze(paths, x+1, y, i, j)){
      return true;
    }

    if(stepMaze(paths, x, y-1, i, j)){
      return true;
    }

    paths[x][y] = 0;

    return false;
  }

  static boolean[] used;
  static StringBuilder sb = new StringBuilder();;

  public static void permute(String s){
    used = new boolean[s.length()];
    sb = new StringBuilder();
    permute1(s, sb);
  }

  public static void permute1( String s, StringBuilder sb){

    if (s.length() == sb.length()) {
      System.out.println(sb);
      return;
    }

    for(int i = 0; i < s.length(); i++) {
      if (used[i]) {
        continue;
      }
      used[i] = true;
      sb.append(s.charAt(i));
      permute1(s, sb);
      used[i] = false;
      sb.setLength(sb.length() - 1);
    }
  }

  static void compute(String s, int start){
    for(int i=start; i< s.length(); i++){
      sb.append(s.charAt(i));
      System.out.println(sb);
      if(i < s.length())
        compute(s, i+1);
      sb.setLength(sb.length() -1);
    }
  }

  //https://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/
  static int tp = 0;
  static void totalParenthesis(char[] a, int n, int i, int open, int close){
    if(close == n){
      for(int j = 0 ; j < a.length; j++){
        System.out.print(a[j]);
      }
      tp++;
      System.out.println(tp);
    }else {
      if (open < n) {
        a[i] = '{';
        totalParenthesis(a, n, i+1, open + 1, close);
      }
      if (open > close) {
        a[i] = '}';
        totalParenthesis(a, n, i+1, open, close + 1);
      }
    }
  }

  //https://www.geeksforgeeks.org/sort-matrix-row-wise-column-wise/
  static void sort2DArray(int[][] a){
    sort2DArrayByRow(a);
    transpose2DArray(a);
    sort2DArrayByRow(a);
    transpose2DArray(a);
  }

  static void sort2DArrayByRow(int[][] a){
    for(int i = 0; i< a.length; i++){
      Arrays.sort(a[i]);
    }
  }

  static void transpose2DArray(int[][] a){
    for(int i = 0; i< a.length; i++){
      for(int j = i + 1; j < a[i].length; j++){
        int t = a[i][j];
        a[i][j] = a[j][i];
        a[j][i] = t;
      }
    }
  }



  //https://www.geeksforgeeks.org/rearrange-array-arri/
  //int a[] = {-1, -1, 6, 1, 9, 3, 2, -1, 4, -1}
  static void arrangeArray(int[] a){
    int t = -1;
    for(int i = 0; i < a.length; i++){
      if(a[i] == -1){
        continue;
      }
      if(a[i] != i){
        t = a[i];
        a[i] = a[t];
        a[t] = t;
        i = i-1;
      }
    }
    System.out.println(Arrays.toString(a));

  }



  static String timeConversion(String s) {
    if(s == null){
      return "";
    }
    char[] t = s.toCharArray();
    int h1 = Character.getNumericValue(t[0]);
    int h2 = Character.getNumericValue(t[1]);
    if(h1 == 1 && h2 == 2){
      t[0] = (char)(0+'0');
      t[1] = (char)(0+'0');
    }else{
      h1 = h1+1;
      h2 = h2+2;
      t[0] = (char)(h1+'0');
      t[1] = (char)(h2+'0');
    }

    String s1 =  new String(t);
    return s1.substring(0,8);

  }

  //https://www.geeksforgeeks.org/reverse-an-array-in-groups-of-given-size/
  static int[] reverseArrayInGroup(int n, int k){
    int[] a = new int[n];

    for(int i = 0; i < n; i++){
      a[i] = i;
    }
    reverseArrayInGroup1(a, n, k);

    return a;
  }

  static void reverseArrayInGroup1(int[] a, int n, int k){
    for(int i = 0; i<a.length; i=i+k){
      int left = i;
      int right = Math.min(i+k, n-1);

      while(right > left){
        int t = a[left];
        a[left] = a[right];
        a[right] = t;
        left++;
        right--;
      }
    }
  }


  static int[] reverseArrayInGroup1(int n, int k){
    int[] a = new int[n];

    for(int i = 0; i < n; i++){
      a[i] = i;

    }

    /*if(a == null || a.length < 2 || k >= a.length-1 || k <= 1 ){
      return null;
    }*/

    for (int i = 0; i < n; i += k)
    {
      int left = i;

      // to handle case when k is not multiple
      // of n
      int right = Math.min(i + k - 1, n - 1);
      int temp;

      // reverse the sub-array [left, right]
      while (left < right)
      {
        temp=a[left];
        a[left]=a[right];
        a[right]=temp;
        left+=1;
        right-=1;
      }
    }

    return a;
  }



  public static void main(String[] args){
    System.out.println(SA.isAllCharsUnique1("This"));
    System.out.println(SA.isAllCharsUnique1("Thismaynotberu"));
    System.out.println(SA.isAllCharsUnique2("This"));
    System.out.println(SA.isAllCharsUnique2("Thismaynotberu"));

    System.out.println(SA.removeDuplicate("this is a test, lets see"));
    System.out.println(SA.removeDuplicate1("this is a test, lets see"));


    System.out.println(SA.isAnnagram("motherinlaw1", "hitlerwoman"));
    System.out.println(Arrays.toString(SA.reverseArrayInGroup(335, 36)));
    SA.fillSpace("Do it today instead of stalling it");



    int[][] matrix = {
                      {1,2,3,4,5,6},
        {1,2,3,4,5,6},
        {1,2,3,4,5,6},
        {1,2,3,0,5,6},
        {1,2,3,4,5,6},
        {0,2,3,4,5,6},
    };

    int arrangeArray[] = {-1, -1, 6, 1, 9, 3, 2, -1, 4, -1};
    SA.arrangeArray(arrangeArray);

    int arr[][] = {
        {11, 2, 4},
        {4, 5, 6},
        {10, 8, -12}
    };

    System.out.println(SA.timeConversion("06:40:03AM"));

    SA.updateMatrix(matrix);
    SA.reverse();
    int[] a = {1,2,3,4, 5, 6, 7};
    SA.rotateArray(a , 2);
    System.out.println(Arrays.toString(a));
    int[] b = {1,2,3,4, 5, 6, 7};
    SA.rotateArray1(b , 2);
    System.out.println(Arrays.toString(b));
    int[] c = {1,2,3,4, 5, 6, 7};
    SA.leftRotate(c , 2);
    System.out.println(Arrays.toString(c));
    System.out.println(SA.minimumIntegerToMultiplyToGetSum(36));
    System.out.println(SA.concatStaring("45eeeedfghfffeff62"));
    SA.printDigonal();

    int matrix1 [][] = {
        {10, 20, 30, 40},
        {15, 25, 35, 45},
        {27, 29, 37, 48},
        {32, 33, 39, 50}};

    System.out.println(SA.find(matrix1, 50));

    SA.findAngle(12,30);
    SA.findAngle(3,30);

    //SA.printKthprimeFactor(225, 2);
    //SA.printKthprimeFactor(31, 1);

    SA.printPrimeFactor(30);

    final int[][] paths = {
        {0,0,1,0,1},
        {0,0,1,0,0},
        {0,0,0,0,1},
        {0,1,0,0,1},
        {0,1,1,1,0}

    };

    System.out.println(SA.solveMaze(paths,0,0,4,4));

    //SA.permute("ABCD");
    //SA.compute("ABCD", 0);

    SA.totalParenthesis(new char[6], 3, 0, 0, 0);

  }

}
