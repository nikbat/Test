package GFG;


import java.util.Scanner;

public class SmallerElements {

  public static void main (String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = Integer.parseInt(sc.nextLine());
    while(t-- > 0){
      int n = Integer.parseInt(sc.nextLine());
      int[] a = new int[n];
      String[] str = sc.nextLine().split(" ");

      for(int i =0; i < n; i++){
        a[i] = Integer.parseInt(str[i]);
      }

      int val = Integer.parseInt(sc.nextLine());
      int r = 0;
      for(int i = 0; i < n; i++){
        if (a[i] <= val){
          r = r+1;
        }
      }
      System.out.println(r);
    }
  }

}
