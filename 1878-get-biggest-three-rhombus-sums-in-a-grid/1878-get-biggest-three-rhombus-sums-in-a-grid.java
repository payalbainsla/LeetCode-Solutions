import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // size 0 rhombus
                set.add(grid[i][j]);

                // larger rhombus
                for (int k = 1; i - k >= 0 && i + k < m && j - k >= 0 && j + k < n; k++) {
                    int sum = 0;

                    // top-right side
                    for (int d = 0; d < k; d++) sum += grid[i - k + d][j + d];
                    // right-bottom side
                    for (int d = 0; d < k; d++) sum += grid[i + d][j + k - d];
                    // bottom-left side
                    for (int d = 0; d < k; d++) sum += grid[i + k - d][j - d];
                    // left-top side
                    for (int d = 0; d < k; d++) sum += grid[i - d][j - k + d];

                    set.add(sum);
                }
            }
        }

        int[] res = new int[Math.min(3, set.size())];
        int idx = 0;
        for (int val : set) {
            if (idx == 3) break;
            res[idx++] = val;
        }
        return res;
    }
}
