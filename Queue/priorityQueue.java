package Queue;

//priority queue is like a normal queue but in pq we can control the priority order of the elements.
// add and remove has O(log n)
// peek has O(n)
import java.util.*;
import java.io.*;

public class priorityQueue {
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		PriorityQueue<Integer> reversepq = new PriorityQueue<>(Collections.reverseOrder()); // reverses the priority
																							// order
		int ranks[] = { 7, 4, 2, 1, 5, 3, 6 };
		for (int val : ranks) {
			pq.add(val);
			reversepq.add(val);
		}
		while (pq.size() > 0) {
			System.out.print(pq.peek() + " ");
			pq.remove();
		}
		System.out.println();
		while (reversepq.size() > 0) {
			System.out.print(reversepq.peek() + " ");
			reversepq.remove();
		}
	}
}

// ******************************************************************************************************************************
// Question: K-Largest Numbers
class KLargestNumbers {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = scn.nextInt();
		}
		int k = scn.nextInt();
		solve(n, num, k);
	}

	public static void solve(int n, int[] arr, int k) {
		// Write your code here
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			pq.add(arr[i]);
		}
		for (int i = k; i < n; i++) {
			if (pq.peek() < arr[i]) {
				pq.remove();
				pq.add(arr[i]);
			}
		}
		int ans[] = new int[k];
		int i = k - 1;
		while (pq.size() > 0) {
			ans[i] = pq.remove();
			i--;
		}
		for (int val : ans) {
			System.out.print(val + " ");
		}
	}
}

// ***********************************************************************************************************************
// Question : Sort K Sorted Array
class SortKSortedArray {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int k = Integer.parseInt(br.readLine());
		// write your code here
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i <= k; i++) {
			pq.add(arr[i]);
		}
		for (int i = k + 1; i < n; i++) {
			System.out.println(pq.remove());
			pq.add(arr[i]);
		}
		while (pq.size() > 0) {
			System.out.println(pq.remove());
		}
	}
}

//********************************************************************************************************************* 
//Question : 