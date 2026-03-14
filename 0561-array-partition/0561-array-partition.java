import java.util.Arrays;

class Solution {
    public int arrayPairSum(int[] nums) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        
        int sum = 0;
        // Step 2: Har alternate element (even index) ka sum lo
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        
        return sum;
    }
}
