package Stacks_Queues;

// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing the prices of a share on n days.
// 3. You are required to find the stock span for n days.
// 4. Stock span is defined as the number of days passed between the current day and the first day before today when price was higher than today.

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


import java.util.*;

class Pair{
    int val;
    int idx;
    Pair(int val, int idx){
        this.val = val;
        this.idx = idx;
    }
}

public class StockSpan {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int a[] = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = input.nextInt();
        }
        int ans[] = stockSpan(a);
        for(int i = 0; i < n; i++){
            System.out.print(ans[i] + " ");
        }
    }
    public static int[] stockSpan(int[] arr) {
        int ans[]=new int [arr.length];
   Stack<Integer> stck=new Stack<>();
   stck.push(0);
   ans[0]=1;
   for(int i=1;i<arr.length;i++){
       while(stck.size()>0 && arr[i]>=arr[stck.peek()]){
         stck.pop();
       }
       if(stck.size()==0){
         ans[i]=i+1;
       }
       else{
         ans[i]=i-stck.peek();
       }
       stck.push(i);
   }
   return ans;
    }
}