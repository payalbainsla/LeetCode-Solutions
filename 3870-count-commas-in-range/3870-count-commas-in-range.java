class Solution {
    public int countCommas(int n) {
        long total = 0;
        long low = 1;
        long high = 9;
        int digits = 1;

        while (low <= n) {
            long rangeHigh = Math.min(high, n);
            long count = rangeHigh - low + 1;
            int commasPerNumber = (digits - 1) / 3;

            total += count * commasPerNumber;

            // agla digit-length range
            low = high + 1;
            high = high * 10 + 9;
            digits++;
        }

        return (int) total;
    }
}