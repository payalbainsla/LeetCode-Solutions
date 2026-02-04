class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long presum[] = new long[n+1];

        for(int i = 1; i<=n; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }

        long maxsum = Long.MIN_VALUE;

        int i=1;

        //outer loop
        while( i < n-2) {
            int left = i - 1;

            // increase array
            while(i < n && nums[i] > nums[i-1])
            i++;
            if(i == left + 1) {
                i++;
                continue;
            }
            int pIndex = i - 1;

            //decreasing part array
            while(i<n && nums[i] < nums[i-1])
            i++;
            if(i==pIndex+1) {
                i++;
                continue;
            }

            int qIndex = i-1;

            //increasing array traversal
            while(i<n && nums[i] > nums[i-1])
            i++;
            if(i==qIndex + 1) {
                i++;
                continue;
            }

            //Now we have traversed trionic array
            int right = i-1;

            //[q..r] - maxprefixsum
            //[l..p] - minprefixsum
            //diff = max sum of current trionic array
            long maxprefixsum = Long.MIN_VALUE;
            long minprefixsum = Long.MAX_VALUE;
            //for [l..p]
            for(int j=left; j<pIndex; j++){
                minprefixsum = Math.min(minprefixsum, presum[j]);
            }
            //for [q..r]
            for(int j=qIndex+1; j<=right; j++) {
                maxprefixsum = Math.max(maxprefixsum, presum[j+1]);
            }
            maxsum = Math.max(maxsum, maxprefixsum-minprefixsum);
            i = qIndex+1;
        }
        return maxsum;
    }
}