class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] maxProd = new long[m][n];
        long[][] minProd = new long[m][n];
        
        maxProd[0][0] = minProd[0][0] = grid[0][0];
        
        // first row
        for (int j = 1; j < n; j++) {
            maxProd[0][j] = minProd[0][j] = maxProd[0][j-1] * grid[0][j];
        }
        
        // first column
        for (int i = 1; i < m; i++) {
            maxProd[i][0] = minProd[i][0] = maxProd[i-1][0] * grid[i][0];
        }
        
        // fill rest
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                long maxCandidate = Math.max(
                    Math.max(maxProd[i-1][j] * val, minProd[i-1][j] * val),
                    Math.max(maxProd[i][j-1] * val, minProd[i][j-1] * val)
                );
                long minCandidate = Math.min(
                    Math.min(maxProd[i-1][j] * val, minProd[i-1][j] * val),
                    Math.min(maxProd[i][j-1] * val, minProd[i][j-1] * val)
                );
                
                maxProd[i][j] = maxCandidate;
                minProd[i][j] = minCandidate;
            }
        }
        
        long result = maxProd[m-1][n-1];
        if (result < 0) return -1;
        return (int)(result % 1000000007);
    }
}
