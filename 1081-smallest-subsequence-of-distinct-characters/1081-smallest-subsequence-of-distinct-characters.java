class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];
        // Step 1: Har character ka LAST occurrence index store karo
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();
        boolean[] inStack = new boolean[26]; // konsa character abhi stack mein hai

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Rule 1: Agar already stack mein hai, skip karo
            if (inStack[c - 'a']) continue;

            // Rule 2: Jab tak top bada hai AUR uska future occurrence bacha hai, pop karo
            while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) {
                char removed = stack.pop();
                inStack[removed - 'a'] = false;
            }

            // Rule 3: Naya character push karo
            stack.push(c);
            inStack[c - 'a'] = true;
        }

        // Stack mein neeche se upar order reverse hota hai, isliye reverse karke string banao
        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c);
        return sb.reverse().toString();
    }
}