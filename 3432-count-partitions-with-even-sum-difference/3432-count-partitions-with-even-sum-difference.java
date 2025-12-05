class Solution {
    public int countPartitions(int[] nums) {
        int totalSum = 0;
        for(int x:nums) totalSum += x;

        if(totalSum % 2 != 0) return 0;

        return nums.length-1;
    }
}