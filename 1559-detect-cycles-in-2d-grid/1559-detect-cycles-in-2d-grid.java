class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][] visited;

    public boolean containsCycle(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        this.visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, int px, int py, char target) {
        if (visited[x][y]) return true; // cycle detected

        visited[x][y] = true;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int[] d : dirs) {
            int nx = x + d[0], ny = y + d[1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            if (grid[nx][ny] != target) continue;
            if (nx == px && ny == py) continue; // skip parent

            if (dfs(nx, ny, x, y, target)) {
                return true;
            }
        }
        return false;
    }
}
