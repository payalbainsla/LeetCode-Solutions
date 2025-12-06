class Solution {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;   // smallest number so far
        int second = Integer.MAX_VALUE;  // second smallest number greater than 'first'

        for (int num : nums) {
            if (num <= first) {
                first = num;  // update smallest
            } else if (num <= second) {
                second = num; // update second smallest
            } else {
                // found a number greater than both first and second
                return true;  // triplet exists
            }
        }
        return false; // no triplet found
    }
}