import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];

        for (int i = 0; i <= m - k; i++) {
            TreeMap<Integer, Integer> freq = new TreeMap<>();
            for (int r = i; r < i + k; r++) {
                for (int c = 0; c < k; c++) {
                    freq.put(grid[r][c], freq.getOrDefault(grid[r][c], 0) + 1);
                }
            }
            ans[i][0] = computeMinDiff(freq);

            for (int j = 1; j <= n - k; j++) {
                for (int r = i; r < i + k; r++) {
                    int val = grid[r][j - 1];
                    freq.put(val, freq.get(val) - 1);
                    if (freq.get(val) == 0) freq.remove(val);
                }
                for (int r = i; r < i + k; r++) {
                    int val = grid[r][j + k - 1];
                    freq.put(val, freq.getOrDefault(val, 0) + 1);
                }
                ans[i][j] = computeMinDiff(freq);
            }
        }
        return ans;
    }

    private int computeMinDiff(TreeMap<Integer, Integer> freq) {
        int minDiff = Integer.MAX_VALUE;
        Integer prev = null;
        for (int val : freq.keySet()) {
            if (prev != null) {
                minDiff = Math.min(minDiff, val - prev);
            }
            prev = val;
        }
        return minDiff == Integer.MAX_VALUE ? 0 : minDiff;
    }
}
