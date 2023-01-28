package Stacks;

import java.io.*;
import java.util.*;

// Question : BALANCED BRACKETTS
class Solution {
   public void balancedBrackets(String str, int n) {
      // write code here
      boolean flag = true;
      Stack<Character> stck = new Stack<>();
      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if (ch == '[' || ch == '{' || ch == '(') {
            stck.push(ch);
         } else if (ch == ')') {
            flag = check(stck, '(');
         } else if (ch == '}') {
            flag = check(stck, '{');
         } else if (ch == ']') {
            flag = check(stck, '[');
         }
         if (flag == false) {
            System.out.println("NO");
            return;
         }
      }
      if (stck.size() != 0)
         flag = false;
      if (flag) {
         System.out.println("YES");
      } else
         System.out.println("NO");
   }

   public boolean check(Stack<Character> stck, char ch) {
      if (stck.size() == 0) {
         return false;
      } else if (stck.peek() != ch) {
         return false;
      } else
         stck.pop();
      return true;
   }
}

public class balancedBrackets {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      String s = sc.next();
      Solution Obj = new Solution();
      Obj.balancedBrackets(s, n);
   }
}

// Question : next greater element
class nextGreaterElement {
   public static void display(int[] a) {
      StringBuilder sb = new StringBuilder();

      for (int val : a) {
         sb.append(val + " ");
      }
      System.out.println(sb);
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int n = Integer.parseInt(br.readLine());
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
         a[i] = Integer.parseInt(br.readLine());
      }

      int[] nge = solve(a);
      display(nge);
   }

   public static int[] solve(int[] arr) {
      // solve
      int ans[] = new int[arr.length];
      Stack<Integer> stck = new Stack<>();
      stck.push(0);
      for (int i = 1; i < arr.length; i++) {
         while (stck.size() > 0 && arr[stck.peek()] < arr[i]) {
            ans[stck.peek()] = arr[i];
            stck.pop();
         }
         stck.push(i);
      }
      for (int i = 0; i < arr.length; i++) {
         if (ans[i] == 0) {
            ans[i] = -1;
         }
      }
      return ans;
   }

}

// Question: Previous greater element
class previousGreaterElement {
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine().trim());
      String inputLine[] = br.readLine().trim().split(" ");
      int[] arr = new int[n];
      for (int i = 0; i < n; i++)
         arr[i] = Integer.parseInt(inputLine[i]);
      int[] res = prevGreater(arr, n);
      for (int i = 0; i < n; i++)
         System.out.print(res[i] + " ");
      System.out.println();
   }

   public static int[] prevGreater(int[] arr, int n) {
      int ans[] = new int[arr.length];
      Stack<Integer> stck = new Stack<>();
      stck.push(0);
      ans[0] = -1;
      for (int i = 1; i < arr.length; i++) {
         while (stck.size() > 0 && arr[i] > arr[stck.peek()]) {
            stck.pop();
         }
         if (stck.size() == 0) {
            ans[i] = -1;
         } else {
            ans[i] = arr[stck.peek()];
         }
         stck.push(i);
      }
      return ans;
   }
}

// Question : Stock span
// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing the prices of a share on n days.
// 3. You are required to find the stock span for n days.
// 4. Stock span is defined as the number of days passed between the current day
// and the first day before today when price was higher than today.

// e.g.
// for the array [2 5 9 3 1 12 6 8 7]
// span for 2 is 1
// span for 5 is 2
// span for 9 is 3
// span for 3 is 1
// span for 1 is 1
// span for 12 is 6
// span for 6 is 1
// span for 8 is 2
// span for 7 is 1
class Pair {
   int val;
   int idx;

   Pair(int val, int idx) {
      this.val = val;
      this.idx = idx;
   }
}

class StockSpan {
   public static void main(String args[]) {
      Scanner input = new Scanner(System.in);
      int n = input.nextInt();
      int a[] = new int[n];
      for (int i = 0; i < n; i++) {
         a[i] = input.nextInt();
      }
      int ans[] = stockSpan(a);
      for (int i = 0; i < n; i++) {
         System.out.print(ans[i] + " ");
      }
   }

   public static int[] stockSpan(int[] arr) {
      int ans[] = new int[arr.length];
      Stack<Integer> stck = new Stack<>();
      stck.push(0);
      ans[0] = 1;
      for (int i = 1; i < arr.length; i++) {
         while (stck.size() > 0 && arr[i] >= arr[stck.peek()]) {
            stck.pop();
         }
         if (stck.size() == 0) {
            ans[i] = i + 1;
         } else {
            ans[i] = i - stck.peek();
         }
         stck.push(i);
      }
      return ans;
   }
}

//Question : Largest Histogram Area
class LargestHistogramArea{

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    for(int i = 0; i < n; i++){
       a[i] = Integer.parseInt(br.readLine());
    }

    // code
    Stack<Integer> stck =new Stack<>();
    stck.push(0);
    int area=Integer.MIN_VALUE;
    for(int i=1;i<a.length+1;i++){
       while((!stck.isEmpty() && (i==n || a[i]<=a[stck.peek()]))){
          int h=a[stck.peek()];
          stck.pop();
          int w;
          if(stck.isEmpty()){
             w=i;
          }
          else{
             w=i-stck.peek()-1;
          }
          area=Integer.max(area,(h*w));
       }
      stck.push(i);
    }
    System.out.println(area);
 }
}

// Question Sliding window maximum
// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing the elements of array a.
// 3. You are given a number k, representing the size of window.
// 4. You are required to find and print the maximum element in every window of size k.
class SlidingWindowMax{
public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    for(int i = 0; i < n; i++){
       a[i] = Integer.parseInt(br.readLine());
    }
    int k = Integer.parseInt(br.readLine());

    // code
    int nge[]=new int[n];
    nge=nextGreaterElement(n,a);

    int ans[]=new int[n-3];
    ans=windowMax(k,a,nge);

    for(int i=0;i<ans.length;i++){
       System.out.println(ans[i]);
    }
}
 public static int[] nextGreaterElement(int n, int[]arr){
      Stack<Integer> stck=new Stack<>();
      int nge[]=new int[n];
      stck.push(0);
      for(int i=1;i<n;i++){
         while(stck.size()>0 && arr[stck.peek()]<arr[i]){
            nge[stck.peek()]=i;
            stck.pop();
         }
         stck.push(i);
      }
      for (int i = 0; i < arr.length; i++) {
            if (nge[i] == 0) {
            nge[i] = arr.length;
            }
         }
         return nge;
   }
   public static int[] windowMax(int k,int[] arr,int[] nge){
      int ans[]=new int[arr.length-k+1];
      for(int i=0;i<=arr.length-k;i++){
         int j=i;
         while(nge[j]<i+k){
            j=nge[j];
         }
         ans[i]=arr[j];
      }
      return ans;
   }
}

//Question : Infix evaluation
class Main{ 
public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();

    // code
    Stack<Integer> operand=new Stack<>();
    Stack<Character> operators=new Stack<>();
    for(int i=0;i<exp.length();i++){
        char ch=exp.charAt(i);
        if(ch=='('){
           operators.push(ch); 
        }
        else if(Character.isDigit(ch)){
            operand.push(ch-'0');
        }
        else if(ch==')'){
            while(operators.peek()!='('){
                char operator=operators.pop();
                int v2=operand.pop();
                int v1=operand.pop();

                int ans=operation(v1,v2,operator);
                operand.push(ans);
            }
            operators.pop();
        }
        else if(ch=='+' || ch=='-' || ch=='*' || ch=='/'){
            while(operators.size()>0 && operators.peek()!='(' && precedence(ch)<=precedence(operators.peek())){
                char operator=operators.pop();
                int v2=operand.pop();
                int v1=operand.pop();

                int ans=operation(v1,v2,operator);
                operand.push(ans);
            }
            operators.push(ch);
        }
    }
    while(operators.size()>0){
                char operator=operators.pop();
                int v2=operand.pop();
                int v1=operand.pop();

                int ans=operation(v1,v2,operator);
                operand.push(ans);
            }
    System.out.println(operand.peek());
 }
 public static int precedence(char operator){
     if(operator=='+'){
         return 1;
     }
     else if(operator=='-'){
         return 1;
     }
     else if(operator=='*'){
         return 2;
     }
     else return 2;
    
 }
 public static int operation(int v1, int v2 , char operator){
     if(operator=='+'){
         return v1+v2;
     }
     else if(operator=='-'){
         return v1-v2;
     }
     else if(operator=='*'){
         return v1*v2;
     }
     else return v1/v2;
}
}
