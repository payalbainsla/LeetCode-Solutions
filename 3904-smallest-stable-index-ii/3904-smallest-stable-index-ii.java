
class Solution {

    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        
        // Build suffix minimum array (min from i to n-1)
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }
        
        int prefixMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            int instabilityScore = prefixMax - suffixMin[i];
            if (instabilityScore <= k) {
                return i;
            }
        }
        return -1;
    }
}