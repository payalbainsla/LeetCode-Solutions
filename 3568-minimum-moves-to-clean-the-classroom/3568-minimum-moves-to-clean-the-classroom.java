import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int sx = 0, sy = 0;
        List<int[]> litters = new ArrayList<>();
        
        // locate start and litter positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    sx = i; sy = j;
                } else if (c == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }
        
        int fullMask = (1 << litters.size()) - 1;
        int[][][][] bestEnergy = new int[m][n][1 << litters.size()][energy + 1];
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy, 0, energy, 0}); // (x, y, mask, energy, steps)
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], mask = cur[2], e = cur[3], steps = cur[4];
            
            if (mask == fullMask) return steps;
            
            if (bestEnergy[x][y][mask][e] == 1) continue;
            bestEnergy[x][y][mask][e] = 1;
            
            int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                char c = classroom[nx].charAt(ny);
                if (c == 'X') continue;
                
                int ne = e - 1;
                if (ne < 0) continue;
                
                int nmask = mask;
                if (c == 'L') {
                    for (int k = 0; k < litters.size(); k++) {
                        if (litters.get(k)[0] == nx && litters.get(k)[1] == ny) {
                            nmask |= (1 << k);
                        }
                    }
                }
                if (c == 'R') ne = energy;
                
                if (bestEnergy[nx][ny][nmask][ne] == 0) {
                    q.offer(new int[]{nx, ny, nmask, ne, steps + 1});
                }
            }
        }
        return -1;
    }
}
