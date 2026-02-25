class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] freq = new int[n+1]; // keep count 1 to 2
        int duplicate = -1, missing = -1;

        // Step 1: frequency count
        for(int num : nums) {
            freq[num]++;
        }

        // Step 2: find duplicate
        for(int i=1; i<=n; i++) {
            if(freq[i] == 2) {
                duplicate = i;
            }
            if(freq[i] == 0) {
                missing = i;
            }
        }

        return new int[]{duplicate, missing};

    }
}