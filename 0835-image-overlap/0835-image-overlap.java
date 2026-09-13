import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> onesA = new ArrayList<>();
        List<int[]> onesB = new ArrayList<>();

        // Collect coordinates of 1s in both images
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) onesA.add(new int[]{r, c});
                if (img2[r][c] == 1) onesB.add(new int[]{r, c});
            }
        }

        // Count frequency of each (dx, dy) shift vector
        Map<String, Integer> shiftCount = new HashMap<>();
        int maxOverlap = 0;

        for (int[] a : onesA) {
            for (int[] b : onesB) {
                int dx = a[0] - b[0];
                int dy = a[1] - b[1];
                String key = dx + "," + dy;
                int count = shiftCount.getOrDefault(key, 0) + 1;
                shiftCount.put(key, count);
                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        return maxOverlap;
    }
}