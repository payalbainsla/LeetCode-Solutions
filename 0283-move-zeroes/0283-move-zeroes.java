class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0; // pointer for placing non-zero elements

        // Step 1: Move all non-zero elements to the front
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }

        // Step 2: Fill the remaining positions with zeros
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}