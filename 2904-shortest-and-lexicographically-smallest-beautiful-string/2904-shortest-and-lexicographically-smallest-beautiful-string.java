class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String best = "";

        for (int start = 0; start < n; start++) {
            int ones = 0;
            for (int end = start; end < n; end++) {
                if (s.charAt(end) == '1') {
                    ones++;
                }
                if (ones == k) {
                    String current = s.substring(start, end + 1);
                    if (best.isEmpty() 
                        || current.length() < best.length() 
                        || (current.length() == best.length() && current.compareTo(best) < 0)) {
                        best = current;
                    }
                    break; // is start ke liye chhoti substring nahi milegi ab, agla start try karege
                }
            }
        }

        return best;
    }
}