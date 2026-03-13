class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 1, right = (long) 1e18;
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (canFinish(mountainHeight, workerTimes, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private boolean canFinish(int mountainHeight, int[] workerTimes, long timeLimit) {
        long totalWork = 0;
        for (int t : workerTimes) {
            // Solve inequality: t * k*(k+1)/2 <= timeLimit
            // => k^2 + k - 2*timeLimit/t <= 0
            long maxK = (long) Math.floor(
                (-1.0 + Math.sqrt(1.0 + 8.0 * timeLimit / t)) / 2.0
            );
            totalWork += maxK;
            if (totalWork >= mountainHeight) return true;
        }
        return totalWork >= mountainHeight;
    }
}
