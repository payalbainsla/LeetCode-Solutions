class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        // dp[i][j]: number of distinct subsequences of s[0..i) equal to t[0..j)
        long[][] dp = new long[m + 1][n + 1];
        
        // Base case: empty t can be formed exactly one way (delete all chars)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j]; // don't use s[i-1]
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1]; // use s[i-1] to match t[j-1]
                }
            }
        }
        
        return (int) dp[m][n];
    }
}