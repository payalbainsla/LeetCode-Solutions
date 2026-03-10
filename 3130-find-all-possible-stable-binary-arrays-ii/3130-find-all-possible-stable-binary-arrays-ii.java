class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
       // dp[onesLeft][zerosLeft][lastPlaced]
       int [][][] dp = new int[one + 1][zero + 1][2];
       // base cases (only zeros)
       for(int zerosLeft = 1; zerosLeft <= Math.min(zero, limit);
       zerosLeft++
       )
       dp[0][zerosLeft][0] = 1;
       //base case (only ones)
       for(int onesLeft = 1; onesLeft <= Math.min(one, limit); onesLeft++)
       dp[onesLeft][0][1] = 1;
       for(int onesLeft = 0; onesLeft <= one; onesLeft++) {
        for(int zerosLeft = 0; zerosLeft <= zero; zerosLeft++) {
            if(onesLeft == 0 || zerosLeft == 0)
            continue;
            // end with 0
            dp[onesLeft][zerosLeft][0] = (dp[onesLeft][zerosLeft - 1][0] + 
            dp[onesLeft][zerosLeft - 1][1]) % MOD;
            if(zerosLeft > limit)
              dp[onesLeft][zerosLeft][0] = (dp[onesLeft][zerosLeft][0] - dp[onesLeft][zerosLeft - 1 - limit][1] + MOD) % MOD;
              //end with 1
              dp[onesLeft][zerosLeft][1] = (dp[onesLeft - 1][zerosLeft][0] + 
              dp[onesLeft - 1][zerosLeft][1]) % MOD;
            if(onesLeft > limit)
             dp[onesLeft][zerosLeft][1] = (dp[onesLeft][zerosLeft][1] - dp[onesLeft - 1 - limit][zerosLeft][0] + MOD) % MOD;  
        }
       }
       return (dp[one][zero][0] + dp[one][zero][1]) % MOD;
    }
}
