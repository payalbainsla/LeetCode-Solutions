class Solution {
    public int[] sortByBits(int[] arr) {
        // Convert array to Integer list for sorting
        Integer[] nums = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        // Custom sort: first by bit count, then by value
        Arrays.sort(nums, (a, b) -> {
            int bitA = Integer.bitCount(a);
            int bitB = Integer.bitCount(b);
            if (bitA == bitB) {
                return a - b; // sort by value if bit counts are equal
            } else {
                return bitA - bitB; // sort by bit count
            }
        });

        // Convert back to int[]
        return Arrays.stream(nums).mapToInt(Integer::intValue).toArray();

    }
}