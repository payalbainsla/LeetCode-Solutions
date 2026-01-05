class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long absSum = 0;          // Sum of absolute values
        int minAbs = Integer.MAX_VALUE; 
        int oddNeg = 0;           // Tracks parity of negatives

        for (int[] row : matrix) {
            for (int num : row) {
                absSum += Math.abs(num);
                minAbs = Math.min(minAbs, Math.abs(num));
                if (num < 0) oddNeg ^= 1;  // Flip parity if negative
            }
        }

        // If odd number of negatives, subtract twice the smallest absolute value
        return absSum - (oddNeg * minAbs * 2L);
    }
}