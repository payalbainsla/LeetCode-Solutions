class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        // 32-bit integer ke liye loop
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int n : nums) {
                if ((n >> i & 1) == 1) {
                    count++;
                }
            }
            // Agar count % 3 != 0 hai to single number ka bit set hoga
            if (count % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}