class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // step 1: calculate total sum of grid
        long totalSum = 0;
        for (int[] row : grid) {
            for (int val : row) {
                totalSum += val;
            }
        }

        // step 2: check horizontal cuts
        long rowSum = 0;
        for (int i = 0; i < m - 1; i++) { // cut after row i
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum * 2 == totalSum) {
                return true;
            }
        }

        // step 3: check vertical cuts
        long[] colPrefix = new long[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                colPrefix[j] += grid[i][j];
            }
        }

        long colSum = 0;
        for (int j = 0; j < n - 1; j++) { // cut after column j
            colSum += colPrefix[j];
            if (colSum * 2 == totalSum) {
                return true;
            }
        }

        // step 4: no valid partition
        return false;
    }
}
