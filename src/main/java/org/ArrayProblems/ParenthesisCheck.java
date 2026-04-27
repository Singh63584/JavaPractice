package org.ArrayProblems;

import java.util.Stack;

public class ParenthesisCheck {
    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                return false;
            } else if (ch == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                return false;
            } else if (ch == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("{{[()]}}"));   // true
        System.out.println(isBalanced("{[(])}"));   // false
        System.out.println(isBalanced("((()))}"));   // false
        System.out.println(isBalanced("{()){[})"));       // false
    }
}
