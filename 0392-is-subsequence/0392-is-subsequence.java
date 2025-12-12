class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;  // empty string is always a subsequence
        int i = 0; // pointer for s
        int j = 0; // pointer for t

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++; // move pointer in s if characters match
            }
            j++; // always move pointer in t
        }

        return i == s.length(); // true if all chars in s matched
    }
}