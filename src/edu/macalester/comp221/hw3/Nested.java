package edu.macalester.comp221.hw3;

import java.util.Stack;

public class Nested {
    public static boolean isWellNested(String input) {
        char[] toArray = input.toCharArray();
        int counter = toArray.length - 1;

        if (input.length() % 2 != 0) return false;

        for (char i : toArray) {
            if (i == '(') {
                if (toArray[counter] != ')') return false;
            }

            if (i == '{') {
                if (toArray[counter] != '}') return false;
            }

            if (i == '[') {
                if (toArray[counter] != ']') return false;
            }

            counter--;
        }
        
        return true;
    }

    public static void main(String[] args) {
        Stack<Character> test = new Stack<Character>();

        test.push('(');
        test.push('(');
        test.push('{');
        test.push('{');
        test.push('}');
        test.push('}');
        test.push(')');
        test.push(')');

        String testInput = "(({{}}))";

        System.out.println(isWellNested(testInput));
    }
}
