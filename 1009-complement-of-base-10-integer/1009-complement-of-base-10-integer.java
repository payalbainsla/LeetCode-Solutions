class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) return 1; //special case 

        int mask = (Integer.highestOneBit(n) << 1) - 1;

       return mask ^ n;
    }
}