package Stacks_Queues;

import java.util.*;
import java.io.*;

public class previousGreaterElement {
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