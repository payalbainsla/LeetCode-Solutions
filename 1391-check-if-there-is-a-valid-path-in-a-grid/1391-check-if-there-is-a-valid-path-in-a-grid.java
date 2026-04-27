class Solution {
    // Directions for each street type
    private static final int[][][] dirs = {
        {}, // dummy index 0
        {{0, -1}, {0, 1}},     // 1: left, right
        {{-1, 0}, {1, 0}},     // 2: up, down
        {{0, -1}, {1, 0}},     // 3: left, down
        {{0, 1}, {1, 0}},      // 4: right, down
        {{0, -1}, {-1, 0}},    // 5: left, up
        {{0, 1}, {-1, 0}}      // 6: right, up
    };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfs(grid, 0, 0, visited);
    }

    private boolean dfs(int[][] grid, int r, int c, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (r == m - 1 && c == n - 1) return true; // reached destination
        visited[r][c] = true;

        for (int[] d : dirs[grid[r][c]]) {
            int nr = r + d[0], nc = c + d[1];
            if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc]) continue;

            // Check if next cell connects back properly
            for (int[] nd : dirs[grid[nr][nc]]) {
                if (nr + nd[0] == r && nc + nd[1] == c) {
                    if (dfs(grid, nr, nc, visited)) return true;
                }
            }
        }
        return false;
    }
}
