package Stacks_Queues;

import java.io.*;
import java.util.*;

public class nextGreaterElement {
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
