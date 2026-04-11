import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        // Map to store indices of each number
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Collect indices for each number
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int minDistance = Integer.MAX_VALUE;
        
        // For each number, check if it appears at least 3 times
        for (List<Integer> indices : map.values()) {
            if (indices.size() >= 3) {
                // Sort indices (they are already in order due to iteration)
                for (int i = 0; i + 2 < indices.size(); i++) {
                    int left = indices.get(i);
                    int right = indices.get(i + 2);
                    // Distance formula simplifies to 2 * (max - min)
                    int distance = 2 * (right - left);
                    minDistance = Math.min(minDistance, distance);
                }
            }
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}
