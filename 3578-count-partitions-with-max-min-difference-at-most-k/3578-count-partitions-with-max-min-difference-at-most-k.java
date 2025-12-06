public class Solution {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        int MOD = 1_000_000_007;

        // dp[i] = number of ways to partition nums[0..i]
        long[] dp = new long[n + 1];
        long[] prefix = new long[n + 1]; // prefix sum for fast range sum
        dp[0] = 1;
        prefix[0] = 1;

        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;
        for (int right = 0; right < n; right++) {
            // Maintain maxDeque
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);

            // Maintain minDeque
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);

            // Shrink window until condition holds
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
                left++;
                if (maxDeque.peekFirst() < left) maxDeque.pollFirst();
                if (minDeque.peekFirst() < left) minDeque.pollFirst();
            }

            // dp[right+1] = sum of dp[left..right]
            dp[right + 1] = (prefix[right] - (left == 0 ? 0 : prefix[left - 1]) + MOD) % MOD;
            prefix[right + 1] = (prefix[right] + dp[right + 1]) % MOD;
        }

        return (int) dp[n];
    }
}