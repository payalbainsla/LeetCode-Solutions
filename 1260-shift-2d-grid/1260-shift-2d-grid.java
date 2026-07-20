import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int total = m * n;
        k %= total;  // extra shifts avoid karne ke liye
        
        int[][] result = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                int newIdx = (idx + k) % total;
                int newI = newIdx / n;
                int newJ = newIdx % n;
                result[newI][newJ] = grid[i][j];
            }
        }
        
        // int[][] ko List<List<Integer>> me convert karna hai return type ke liye
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(result[i][j]);
            }
            ans.add(row);
        }
        
        return ans;
    }
}