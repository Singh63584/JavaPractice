package org.ArrayProblems;

public class ValidParenthesis {
    public static boolean isValid(String s) {
        char[] valid = new char[s.length()];
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                valid[++top] = ch;
            }
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (top == -1) return false;
                char open = valid[top--];
                if (!isMatching(open, ch)) {
                    return false;
                }
            }
        }
        return top == -1;
    }

    private static boolean isMatching(char open, char close) {
        if (open == '(' && close == ')') return true;
        if (open == '{' && close == '}') return true;
        if (open == '[' && close == ']') return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isValid("{[()]}"));   // true
        System.out.println(isValid("{[(])}"));   // false
        System.out.println(isValid("(((}))"));   // false
        System.out.println(isValid("({[]})"));   // true
        System.out.println(isValid("({[})]"));    // false
    }
}
