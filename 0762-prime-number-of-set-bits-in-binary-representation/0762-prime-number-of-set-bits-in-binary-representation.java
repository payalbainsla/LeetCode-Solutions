class Solution {
    public int countPrimeSetBits(int left, int right) {
        // Prime numbers up to 32
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        java.util.Set<Integer> primeSet = new java.util.HashSet<>();
        for (int p : primes) {
            primeSet.add(p);
        }

        int count = 0;
        for (int num = left; num <= right; num++) {
            int setBits = Integer.bitCount(num); // count of 1s in binary
            if (primeSet.contains(setBits)) {
                count++;
            }
        }
        return count;
    }
}