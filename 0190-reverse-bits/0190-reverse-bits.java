public class Solution {
    // Function to reverse bits of a 32-bit unsigned integer
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // Shift result left to make space
            result <<= 1;
            // Add the last bit of n into result
            result |= (n & 1);
            // Shift n right to process next bit
            n >>= 1;
        }
        return result;
    }
}