class Solution {
    public long minimumCost(int[] nums, int k, int dist) {

        TreeSet<Integer> currset = new TreeSet<>(
            (a, b) -> nums[a] != nums[b]
                ? Integer.compare(nums[a], nums[b])
                : Integer.compare(a, b)
        );

        TreeSet<Integer> futset = new TreeSet<>(
            (a, b) -> nums[a] != nums[b]
                ? Integer.compare(nums[a], nums[b])
                : Integer.compare(a, b)
        );

        int n = nums.length;
        long currsum = 0;
        long cost = Long.MAX_VALUE;

        // Initial window [1 ... dist+1]
        for (int i = 1; i <= dist + 1; i++) {
            currset.add(i);
            currsum += nums[i];
        }

        // Keep only k-1 smallest in currset
        while (currset.size() > k - 1) {
            int ind = currset.pollLast();
            currsum -= nums[ind];
            futset.add(ind);
        }

        cost = currsum;

        // Sliding window
        for (int i = 1; i + dist + 1 < n; i++) {
            int leftInd = i;
            int rightInd = i + dist + 1;

            if (currset.contains(leftInd)) {
                currsum -= nums[leftInd];
                currset.remove(leftInd);
                currset.add(rightInd);
                currsum += nums[rightInd];
            } else {
                futset.remove(leftInd);
                futset.add(rightInd);
            }

            // Rebalance sets
            while (!futset.isEmpty() && !currset.isEmpty()) {
                int minF = futset.first();
                int maxC = currset.last();

                if (nums[minF] < nums[maxC]) {
                    currsum -= nums[maxC];
                    currset.remove(maxC);
                    futset.add(maxC);

                    currset.add(minF);
                    currsum += nums[minF];
                    futset.remove(minF);
                } else {
                    break;
                }
            }

            cost = Math.min(cost, currsum);
        }

        return cost + nums[0];
    }
}
