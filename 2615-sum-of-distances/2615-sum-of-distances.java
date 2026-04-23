import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        
        // Map each value to the list of indices where it occurs
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // For each group of indices, compute prefix sums
        for (List<Integer> indices : map.values()) {
            int m = indices.size();
            long[] prefix = new long[m + 1];
            
            for (int i = 0; i < m; i++) {
                prefix[i + 1] = prefix[i] + indices.get(i);
            }

            for (int i = 0; i < m; i++) {
                int idx = indices.get(i);
                long left = (long) i * indices.get(i) - prefix[i];
                long right = (prefix[m] - prefix[i + 1]) - (long)(m - i - 1) * indices.get(i);
                ans[idx] = left + right;
            }
        }
        
        return ans;
    }
}
