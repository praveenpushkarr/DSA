package Stacks;

import java.io.*;
import java.util.*;

// ****************************************************************************************************************************
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

// ****************************************************************************************************************************
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

// ****************************************************************************************************************************
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

// ****************************************************************************************************************************
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

// ****************************************************************************************************************************
// Question : Largest Histogram Area
class LargestHistogramArea {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        // code
        Stack<Integer> stck = new Stack<>();
        stck.push(0);
        int area = Integer.MIN_VALUE;
        for (int i = 1; i < a.length + 1; i++) {
            while ((!stck.isEmpty() && (i == n || a[i] <= a[stck.peek()]))) {
                int h = a[stck.peek()];
                stck.pop();
                int w;
                if (stck.isEmpty()) {
                    w = i;
                } else {
                    w = i - stck.peek() - 1;
                }
                area = Integer.max(area, (h * w));
            }
            stck.push(i);
        }
        System.out.println(area);
    }
}

// ****************************************************************************************************************************
// Question Sliding window maximum
// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing the elements of array a.
// 3. You are given a number k, representing the size of window.
// 4. You are required to find and print the maximum element in every window of
// size k.
class SlidingWindowMax {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        int k = Integer.parseInt(br.readLine());

        // code
        int nge[] = new int[n];
        nge = nextGreaterElement(n, a);

        int ans[] = new int[n - 3];
        ans = windowMax(k, a, nge);

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }

    public static int[] nextGreaterElement(int n, int[] arr) {
        Stack<Integer> stck = new Stack<>();
        int nge[] = new int[n];
        stck.push(0);
        for (int i = 1; i < n; i++) {
            while (stck.size() > 0 && arr[stck.peek()] < arr[i]) {
                nge[stck.peek()] = i;
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

    public static int[] windowMax(int k, int[] arr, int[] nge) {
        int ans[] = new int[arr.length - k + 1];
        for (int i = 0; i <= arr.length - k; i++) {
            int j = i;
            while (nge[j] < i + k) {
                j = nge[j];
            }
            ans[i] = arr[j];
        }
        return ans;
    }
}

// ****************************************************************************************************************************
// Question : Infix evaluation
class InfixEvaluation {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        // code
        Stack<Integer> operand = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (ch == '(') {
                operators.push(ch);
            } else if (Character.isDigit(ch)) {
                operand.push(ch - '0');
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    char operator = operators.pop();
                    int v2 = operand.pop();
                    int v1 = operand.pop();

                    int ans = operation(v1, v2, operator);
                    operand.push(ans);
                }
                operators.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (operators.size() > 0 && operators.peek() != '('
                        && precedence(ch) <= precedence(operators.peek())) {
                    char operator = operators.pop();
                    int v2 = operand.pop();
                    int v1 = operand.pop();

                    int ans = operation(v1, v2, operator);
                    operand.push(ans);
                }
                operators.push(ch);
            }
        }
        while (operators.size() > 0) {
            char operator = operators.pop();
            int v2 = operand.pop();
            int v1 = operand.pop();

            int ans = operation(v1, v2, operator);
            operand.push(ans);
        }
        System.out.println(operand.peek());
    }

    public static int precedence(char operator) {
        if (operator == '+') {
            return 1;
        } else if (operator == '-') {
            return 1;
        } else if (operator == '*') {
            return 2;
        } else
            return 2;

    }

    public static int operation(int v1, int v2, char operator) {
        if (operator == '+') {
            return v1 + v2;
        } else if (operator == '-') {
            return v1 - v2;
        } else if (operator == '*') {
            return v1 * v2;
        } else
            return v1 / v2;
    }
}

// ****************************************************************************************************************************
// Question: Infix Conversion to postfix and prefix
// infix= (a+b)
// postfix= ab+
// prefix= +ab
class InfixConversions {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        // code
        Stack<String> pre = new Stack<>();
        Stack<String> post = new Stack<>();
        Stack<Character> op = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                pre.push("" + ch);
                post.push("" + ch);
            } else if (ch == '(') {
                op.push(ch);
            } else if (ch == ')') {
                while (op.peek() != '(') {
                    String pre_v2 = pre.pop();
                    String pre_v1 = pre.pop();
                    String post_v2 = post.pop();
                    String post_v1 = post.pop();
                    char operator = op.pop();

                    pre.push("" + operator + pre_v1 + pre_v2);
                    post.push(post_v1 + post_v2 + operator);
                }
                op.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (op.size() > 0 && op.peek() != '(' && precedence(ch) <= precedence(op.peek())) {
                    String pre_v2 = pre.pop();
                    String pre_v1 = pre.pop();
                    String post_v2 = post.pop();
                    String post_v1 = post.pop();
                    char operator = op.pop();

                    pre.push("" + operator + pre_v1 + pre_v2);
                    post.push(post_v1 + post_v2 + operator);
                }
                op.push(ch);
            }
        }
        while (op.size() > 0) {
            String pre_v2 = pre.pop();
            String pre_v1 = pre.pop();
            String post_v2 = post.pop();
            String post_v1 = post.pop();
            char operator = op.pop();

            pre.push("" + operator + pre_v1 + pre_v2);
            post.push(post_v1 + post_v2 + operator);
        }
        System.out.println(post.peek());
        System.out.println(pre.peek());
    }

    public static int precedence(char operator) {
        if (operator == '+') {
            return 1;
        } else if (operator == '-') {
            return 1;
        } else if (operator == '*') {
            return 2;
        } else
            return 2;

    }
}

// ****************************************************************************************************************************
// Qusetion: Postfix conversion and evaluation
// Sample Input
// 264*8/+3-
// Sample Output
// 2
// ((2+((6*4)/8))-3)
// -+2/*6483
class PostfixConversionEvaluation {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        // code
        Stack<Integer> val = new Stack<>();
        Stack<String> in = new Stack<>();
        Stack<String> pre = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (Character.isDigit(ch)) {
                in.push("" + ch);
                pre.push("" + ch);
                val.push(ch - '0');
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int v2 = val.pop();
                int v1 = val.pop();
                String in_v2 = in.pop();
                String in_v1 = in.pop();
                String pre_v2 = pre.pop();
                String pre_v1 = pre.pop();

                int ans = operation(v1, v2, ch);
                val.push(ans);
                in.push("(" + in_v1 + ch + in_v2 + ")");
                pre.push("" + ch + pre_v1 + pre_v2);
            }
        }
        System.out.println(val.peek());
        System.out.println(in.peek());
        System.out.println(pre.peek());

    }

    public static int operation(int v1, int v2, char operator) {
        if (operator == '+') {
            return v1 + v2;
        } else if (operator == '-') {
            return v1 - v2;
        } else if (operator == '*') {
            return v1 * v2;
        } else
            return v1 / v2;
    }
}

// ****************************************************************************************************************************
// Question: Prefix conversion and evaluation
class PrefixCOnversionEvalutaion {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        // code
        Stack<Integer> val = new Stack<>();
        Stack<String> in = new Stack<>();
        Stack<String> post = new Stack<>();
        for (int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);
            if (Character.isDigit(ch)) {
                in.push("" + ch);
                post.push("" + ch);
                val.push(ch - '0');
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int v1 = val.pop();
                int v2 = val.pop();
                String in_v1 = in.pop();
                String in_v2 = in.pop();
                String post_v1 = post.pop();
                String post_v2 = post.pop();

                int ans = operation(v1, v2, ch);
                val.push(ans);
                in.push("(" + in_v1 + ch + in_v2 + ")");
                post.push(post_v1 + post_v2 + ch);
            }
        }
        System.out.println(val.peek());
        System.out.println(in.peek());
        System.out.println(post.peek());

    }

    public static int operation(int v1, int v2, char operator) {
        if (operator == '+') {
            return v1 + v2;
        } else if (operator == '-') {
            return v1 - v2;
        } else if (operator == '*') {
            return v1 * v2;
        } else
            return v1 / v2;
    }
}

// ****************************************************************************************************************************
// Question: Celebrity Problem
class CelebrityProblem {
    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            for (int k = 0; k < n; k++) {
                arr[j][k] = line.charAt(k) - '0';
            }
        }
        findCelebrity(arr);
    }

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it''s index (not position), if there is not
        // then print "none"
        Stack<Integer> stck = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            stck.push(i);
        }
        while (stck.size() > 1) {
            int i = stck.pop();
            int j = stck.pop();
            if (arr[i][j] == 0) {
                stck.push(i);
            } else {
                stck.push(j);
            }
        }
        int suspectCel = stck.pop();
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if (i != suspectCel) {
                if (arr[suspectCel][i] == 1 || arr[i][suspectCel] == 0) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            System.out.println(suspectCel);
        } else
            System.out.println("none");
    }
}

// ****************************************************************************************************************************
// Question: Merge Overlapping Interval
class MergeOverlappingInterval {
    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            arr[j][0] = Integer.parseInt(line.split(" ")[0]);
            arr[j][1] = Integer.parseInt(line.split(" ")[1]);
        }
        mergeOverlappingIntervals(arr);
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        // merge overlapping intervals and print in increasing order of start time
        Pair[] pairs = new Pair[arr.length];
        for (int i = 0; i < arr.length; i++) {
            pairs[i] = new Pair(arr[i][0], arr[i][1]);
        }
        Arrays.sort(pairs);
        Stack<Pair> stck = new Stack<>();
        stck.push(pairs[0]);
        for (int i = 1; i < pairs.length; i++) {
            Pair top = stck.peek();
            if (pairs[i].st > top.et) {
                stck.push(pairs[i]);
            } else {
                top.et = Math.max(top.et, pairs[i].et);
            }
        }
        Stack<Pair> ans = new Stack<>();
        while (stck.size() > 0) {
            ans.push(stck.pop());
        }
        while (ans.size() > 0) {
            Pair top = ans.pop();
            System.out.println(top.st + " " + top.et);
        }
    }

    public static class Pair implements Comparable<Pair> {
        int st;
        int et;

        Pair(int st, int et) {
            this.st = st;
            this.et = et;
        }

        public int compareTo(Pair other) {
            if (this.st != other.st) {
                return this.st - other.st;
            } else {
                return this.et - other.et;
            }
        }
    }
}

// ****************************************************************************************************************************
// Question: Smallest Number Following Pattern
// ddddiiii
// 543216789
class SmallestNumberFollowingPattern {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        // code
        Stack<Integer> stck = new Stack<>();
        int i = 1;
        String ans = "";
        for (int j = 0; j < str.length(); j++) {
            char ch = str.charAt(j);
            if (ch == 'd') {
                stck.push(i);
                i++;
            } else {
                stck.push(i);
                i++;
                while (!stck.isEmpty()) {
                    ans += stck.pop();
                }
            }
        }
        stck.push(i);
        while (!stck.isEmpty()) {
            ans += stck.pop();
        }
        System.out.println(ans);
    }
}

// ****************************************************************************************************************************
// Question: Normal Stack
class NormalStack {
    public static class CustomStack {
        int[] data;
        int tos;

        public CustomStack(int cap) {
            data = new int[cap];
            tos = -1;
        }

        int size() {
            // write ur code here
            return tos + 1;
        }

        void display() {
            // write ur code here
            for (int i = tos; i >= 0; i--) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        void push(int val) {
            // write ur code here
            if (tos == data.length - 1) {
                System.out.println("Stack overflow");
            } else {
                tos++;
                data[tos] = val;
            }
        }

        int pop() {
            // write ur code here
            if (tos == -1) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                int val = data[tos];
                tos--;
                return val;
            }
        }

        int top() {
            // write ur code here
            if (tos == -1) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                return data[tos];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        CustomStack st = new CustomStack(n);

        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("push")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                st.push(val);
            } else if (str.startsWith("pop")) {
                int val = st.pop();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("top")) {
                int val = st.top();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("size")) {
                System.out.println(st.size());
            } else if (str.startsWith("display")) {
                st.display();
            }
            str = br.readLine();
        }
    }
}

// ****************************************************************************************************************************
//Quesion: Dynamic Stack
// Solution in push function if top of stack(tos) is on data.length-1 then make new array with double size
// copy old array in new and then point the old array to new array.

// ****************************************************************************************************************************
// Question: Minimun Stack with constant space
// example: 34 10 4 7 9 1
// Stack       min
//  -2           1               stack.peek()<min; original =_value= min ; previous_min=2*min - stack.peek() = 4
//  9            4
//  7            4
//  -2           4              stack.peek()<min; original =_value= min ; previous_min=2*min - stack.peek() = 10
//  -14         10              stack.peek()<min; original =_value= min ; previous_min=2*min - stack.peek() = 34
//  34          34              
class MinimumStackII {

  public static class MinStack {
    Stack<Integer> data;
    int min;

    public MinStack() {
      data = new Stack<>();
    }

    int size() {
      // write your code here
      return data.size();
    }

    void push(int val) {
      // write your code here
      // if stack is empty then just push the value and give min the new value
      if(data.isEmpty()){
          data.push(val);
          min=val;
      }
      // if new value is smaller than the min, then push a value smaller than new min & update the min 
      else{
          if(val<min){
              data.push(val+val-min);
              min=val;
          }
          else data.push(val);
      }
    }

    int pop() {
      // write your code here
      if(data.isEmpty()){
          System.out.println("Stack underflow");
          return -1;
      }
      else{
        // whenever we find a value smaller than the min value, that means its the point where min value was changed
        // so before popping tht value we need to send the correct value and update the min
          if(data.peek()<min){
              int val=min;
              min=min+min-data.pop();
              return val;
          }
          else return data.pop();
      }
    }

    int top() {
      // write your code here
      if(data.isEmpty()){
          System.out.println("Stack underflow");
          return -1;
      }
      else{
          if(data.peek()<min){
              return min;
          }
          else return data.peek();
      }
    }

    int min() {
      // write your code here
      return min;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    MinStack st = new MinStack();

    String str = br.readLine();
    while (str.equals("quit") == false) {
      if (str.startsWith("push")) {
        int val = Integer.parseInt(str.split(" ")[1]);
        st.push(val);
      } else if (str.startsWith("pop")) {
        int val = st.pop();
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("top")) {
        int val = st.top();
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("size")) {
        System.out.println(st.size());
      } else if (str.startsWith("min")) {
        int val = st.min();
        if (val != -1) {
          System.out.println(val);
        }
      }
      str = br.readLine();
    }
  }
}

// ****************************************************************************************************************************
// Question: 132 Pattern
class Pattern {
    public boolean find132pattern(int[] arr) {
        //Write code here
		Stack<Integer>stck=new Stack<>();
		int k=Integer.MIN_VALUE;
		for(int i=arr.length-1;i>=0;i--){
			if(k>arr[i]){
				return true;
			}
			while(!stck.isEmpty() && arr[i]>stck.peek()){
				k=stck.pop();
			}
			stck.add(arr[i]);
		}
		return false;
    }
}
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        Pattern Obj = new Pattern();
        boolean result = Obj.find132pattern(arr);
        if (result)
            System.out.println("true");
        else
            System.out.println("false");
        sc.close();
    }
}

// ****************************************************************************************************************************
