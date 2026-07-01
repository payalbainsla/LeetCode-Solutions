class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, -1); // -1 matlab "abhi visit nahi hua"

        Queue<int[]> queue = new LinkedList<>();

        // Step 1a: Saare thieves ko queue mein daalo, unka distance 0 set karo
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        // Step 1b: BFS chalao — saare thieves se ek saath spread hoga
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int[] dir : directions) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        // Step 2a: Max-heap use karenge — priority = "kitna safe hai abhi tak ka path"
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]); 
        // int[] = {safeness_so_far, x, y}

        boolean[][] visited = new boolean[n][n];
        pq.offer(new int[]{dist[0][0], 0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int safeness = curr[0], x = curr[1], y = curr[2];

            if (visited[x][y]) continue;
            visited[x][y] = true;

            // Agar destination pahunch gaye, to yehi answer hai
            if (x == n-1 && y == n-1) return safeness;

            for (int[] dir : directions) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                    int newSafeness = Math.min(safeness, dist[nx][ny]);
                    pq.offer(new int[]{newSafeness, nx, ny});
                }
            }
        }

        return 0; // yahan kabhi nahi pahunchega normally
    }
}