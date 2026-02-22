class Solution {
    public int binaryGap(int n) {
        int last = -1;   // last '1' ka position
        int maxGap = 0;  // maximum distance
        
        int pos = 0;     // current bit position
        while (n > 0) {
            if ((n & 1) == 1) {   // agar current bit '1' hai
                if (last != -1) {
                    maxGap = Math.max(maxGap, pos - last);
                }
                last = pos;  // update last position
            }
            n >>= 1;   // next bit check karne ke liye right shift
            pos++;
        }
        return maxGap;
    }
}