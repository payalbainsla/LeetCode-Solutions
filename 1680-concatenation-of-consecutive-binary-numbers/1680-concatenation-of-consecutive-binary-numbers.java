class Solution {
    int mod = 1_000_000_007;
    public int concatenatedBinary(int n) {
        long ans = 0, shift = 0;

        for(int i=1; i<=n; i++) {
            //if power of 2
            if(Integer.bitCount(i)==1) {
                shift++;
            }
            ans = ((ans<<shift) + i)%mod;
        }
        return (int)ans;
    }
}