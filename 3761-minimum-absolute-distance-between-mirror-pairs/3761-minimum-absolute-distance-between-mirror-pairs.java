class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            // If we’ve seen this number before as a reverse of some earlier element
            if (map.containsKey(val)) {
                ans = Math.min(ans, i - map.get(val));
            }
            // Store/update the index of reverse(val)
            map.put(reverse(val), i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int reverse(int x) {
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }
        return rev;
    }
}
