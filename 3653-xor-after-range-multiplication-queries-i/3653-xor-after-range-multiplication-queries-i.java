class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        final int MOD = 1_000_000_007;

        // Process each query
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int idx = l; idx <= r; idx += k) {
                long updated = (1L * nums[idx] * v) % MOD; // use long to avoid overflow
                nums[idx] = (int) updated;
            }
        }

        // Compute XOR of all elements
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }
}
