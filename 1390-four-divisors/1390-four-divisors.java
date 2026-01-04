class Solution {
    public int sumFourDivisors(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += getDivisorSum(num);
        }
        return total;
    }

    private int getDivisorSum(int n) {
        int sum = 1 + n; // 1 and n are always divisors
        int count = 2;   // already counted 1 and n
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                count++;
                sum += i;
                if (i != n / i) {
                    count++;
                    sum += n / i;
                }
                if (count > 4) return 0; // more than 4 divisors
            }
        }
        return count == 4 ? sum : 0;
    }
}