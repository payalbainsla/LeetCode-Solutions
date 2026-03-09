class Solution {
    int mod = 1_000_000_007;
    // memo[zeros][ones][lastbit][streak]
    Integer[][][][] memo;

    public int numberOfStableArrays(int zero, int one, int limit) {
        memo = new Integer[zero+1][one+1][2][limit+1];
        int startWithZero = (zero > 0) ? helper(zero-1, one, 0, 1, limit) : 0;
        int startWithOne  = (one > 0)  ? helper(zero, one-1, 1, 1, limit) : 0;
        return (int)(((long)startWithZero + startWithOne) % mod);
    }

    int helper(int zeros, int ones, int lastbit, int streak, int limit) {
        if (streak > limit) return 0;
        if (zeros == 0 && ones == 0) return 1;

        if (memo[zeros][ones][lastbit][streak] != null)
            return memo[zeros][ones][lastbit][streak];

        long ways = 0;
        if (lastbit == 0) {
            if (zeros > 0)
                ways += helper(zeros-1, ones, 0, streak+1, limit);
            if (ones > 0)
                ways += helper(zeros, ones-1, 1, 1, limit);
        } else {
            if (zeros > 0)
                ways += helper(zeros-1, ones, 0, 1, limit);
            if (ones > 0)
                ways += helper(zeros, ones-1, 1, streak+1, limit);
        }

        return memo[zeros][ones][lastbit][streak] = (int)(ways % mod);
    }
}