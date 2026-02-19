class Solution {
    public int countBinarySubstrings(String s) {
        int ans = 0;
        int prevGroup = 0;   // length of previous group of consecutive chars
        int currGroup = 1;   // length of current group (starts with first char)

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currGroup++;  // extend current group
            } else {
                ans += Math.min(prevGroup, currGroup); // count valid substrings
                prevGroup = currGroup; // shift current to previous
                currGroup = 1;         // reset current group
            }
        }
        ans += Math.min(prevGroup, currGroup); // final count
        return ans;
    }
}