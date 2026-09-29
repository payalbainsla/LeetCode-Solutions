class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        // Path length must be even for a valid parentheses string
        if ((m + n - 1) % 2 != 0) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;
        
        // dp[i][j][k] = true if balance k is achievable at cell (i,j)
        boolean[][][] dp = new boolean[m][n][m + n];
        dp[0][0][1] = true; // grid[0][0] is '(' -> balance 1
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                int delta = grid[i][j] == '(' ? 1 : -1;
                
                for (int k = 0; k < m + n - 1; k++) {
                    boolean reachable = (i > 0 && dp[i - 1][j][k]) || 
                                         (j > 0 && dp[i][j - 1][k]);
                    if (reachable) {
                        int newBalance = k + delta;
                        if (newBalance >= 0 && newBalance < m + n) {
                            dp[i][j][newBalance] = true;
                        }
                    }
                }
            }
        }
        
        return dp[m - 1][n - 1][0];
    }
}