class Solution {
    public int numOfWays(int n) {
        long mod = 1000000007;
        
        // For n = 1, both ABA and ABC patterns have 6 ways each
        long aba = 6, abc = 6;
        
        for (int i = 2; i <= n; i++) {
            long newAba = (aba * 3 + abc * 2) % mod;
            long newAbc = (aba * 2 + abc * 2) % mod;
            aba = newAba;
            abc = newAbc;
        }
        
        return (int)((aba + abc) % mod);
    }
}