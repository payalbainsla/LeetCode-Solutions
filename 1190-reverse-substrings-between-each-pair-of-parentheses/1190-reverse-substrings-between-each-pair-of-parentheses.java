import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        Deque<StringBuilder> stack = new ArrayDeque<>();
        stack.push(new StringBuilder());

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(new StringBuilder()); // start a new segment
            } else if (c == ')') {
                StringBuilder top = stack.pop();
                top.reverse(); // reverse the innermost completed segment
                stack.peek().append(top); // merge into the outer segment
            } else {
                stack.peek().append(c);
            }
        }

        return stack.pop().toString();
    }
}