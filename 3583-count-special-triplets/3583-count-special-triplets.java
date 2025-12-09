import java.util.*;

class Solution {
    public int specialTriplets(int[] nums) {
        final int MOD = 1_000_000_007;
        int n = nums.length;

        Map<Integer, Long> left = new HashMap<>();
        Map<Integer, Long> right = new HashMap<>();

        for (int num : nums) {
            right.put(num, right.getOrDefault(num, 0L) + 1);
        }

        long result = 0;

        for (int j = 0; j < n; j++) {
            int mid = nums[j];

            right.put(mid, right.get(mid) - 1);
            if (right.get(mid) == 0) right.remove(mid);

            int target = mid * 2;

            long leftCount = left.getOrDefault(target, 0L);
            long rightCount = right.getOrDefault(target, 0L);

            result = (result + (leftCount * rightCount) % MOD) % MOD;

            left.put(mid, left.getOrDefault(mid, 0L) + 1);
        }

        return (int) result;
    }
}