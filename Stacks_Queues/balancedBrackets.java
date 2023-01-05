package Stacks_Queues;

import java.util.*;

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
