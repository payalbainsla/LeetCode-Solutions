class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        Deque<String> deque = new ArrayDeque<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            while (right < s.length() && s.charAt(right) != ' ') right++;
            deque.addFirst(s.substring(left, right));
            while (right < s.length() && s.charAt(right) == ' ') right++;
            left = right;
        }
        return String.join(" ", deque);
    }
}