class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int mod = 12345;
        int m = grid.length, n = grid[0].length;
        int total = m * n;

        // flatten matrix into array
        int[] arr = new int[total];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[idx++] = grid[i][j] % mod;
            }
        }

        // Prefix and Suffix products
        int[] prefix = new int[total];
        int[] suffix = new int[total];
        prefix[0] = arr[0] % mod;
        for (int i = 1; i < total; i++) {
            prefix[i] = (prefix[i - 1] * arr[i]) % mod;
        }
        suffix[total - 1] = arr[total - 1] % mod;
        for (int i = total - 2; i >= 0; i--) {
            suffix[i] = (suffix[i + 1] * arr[i]) % mod;
        }

        // build result
        int[][] result = new int[m][n];
        idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int left = (idx == 0) ? 1 : prefix[idx - 1];
                int right = (idx == total - 1) ? 1 : suffix[idx + 1];
                result[i][j] = (left * right) % mod;
                idx++;
            }
        }
        return result;
    }
}
