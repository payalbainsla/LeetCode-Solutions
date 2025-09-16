import java.util.*;

class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Long> stack = new ArrayList<>();
        for (int num : nums) {
            long current = num;
            while (!stack.isEmpty() && gcd(stack.get(stack.size() - 1), current) > 1) {
                current = lcm(stack.remove(stack.size() - 1), current);
            }
            stack.add(current);
        }

        List<Integer> result = new ArrayList<>();
        for (long val : stack) {
            result.add((int) val); // safely cast back to int
        }
        return result;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
