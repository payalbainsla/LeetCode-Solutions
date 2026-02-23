class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k; // total codes = 2^k
        boolean[] seen = new boolean[need];
        int allOnes = need - 1;
        int hashVal = 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            // shift left and add current bit
            hashVal = ((hashVal << 1) & allOnes) | (s.charAt(i) - '0');

            // start checking only after we have k bits
            if (i >= k - 1 && !seen[hashVal]) {
                seen[hashVal] = true;
                count++;
                if (count == need) return true;
            }
        }
        return false;
    }
}