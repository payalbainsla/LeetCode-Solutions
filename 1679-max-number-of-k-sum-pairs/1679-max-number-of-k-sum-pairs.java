class Solution {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            int complement = k - num;
            if (map.getOrDefault(complement, 0) > 0) {
                // Found a pair
                count++;
                map.put(complement, map.get(complement) - 1);
            } else {
                // Store current number
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        return count;
    }
}