class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String ss = s + s; // concatenate to handle rotations
        char[] arr = ss.toCharArray();

        // Build two alternating patterns
        char[] alt1 = new char[2 * n];
        char[] alt2 = new char[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            alt1[i] = (i % 2 == 0) ? '0' : '1';
            alt2[i] = (i % 2 == 0) ? '1' : '0';
        }

        int res = Integer.MAX_VALUE;
        int diff1 = 0, diff2 = 0;
        int left = 0;

        // Sliding window of size n
        for (int right = 0; right < 2 * n; right++) {
            if (arr[right] != alt1[right]) diff1++;
            if (arr[right] != alt2[right]) diff2++;

            if (right - left + 1 > n) {
                if (arr[left] != alt1[left]) diff1--;
                if (arr[left] != alt2[left]) diff2--;
                left++;
            }

            if (right - left + 1 == n) {
                res = Math.min(res, Math.min(diff1, diff2));
            }
        }

        return res;
    }
}