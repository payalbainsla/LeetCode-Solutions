class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] res = new long[k];
        long[] cur = new long[k];
        long[] nxt = new long[k];

        for (int a : nums) {
            int m = a % k;

            for (int r = 0; r < k; r++) nxt[r] = 0;

            // extend all previous subarrays with a
            for (int r = 0; r < k; r++) {
                nxt[(r * m) % k] += cur[r];
            }
            // start a new subarray [a]
            nxt[m]++;

            // accumulate answer
            for (int r = 0; r < k; r++) res[r] += nxt[r];

            // swap buffers
            long[] tmp = cur;
            cur = nxt;
            nxt = tmp;
        }
        return res;
    }
}