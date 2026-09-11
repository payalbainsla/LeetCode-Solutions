public class Solution {

    public static int totalNumbers(int[] digits) {
        Set<Integer> validNumbers = new TreeSet<>(); // sorted + unique
        boolean[] used = new boolean[digits.length];

        permute(digits, used, new int[3], 0, validNumbers);

        System.out.println("Valid numbers: " + validNumbers);
        return validNumbers.size();
    }

    // Backtracking se 3-digit combinations banao (digit repeat na ho)
    private static void permute(int[] digits, boolean[] used, int[] current, int depth, Set<Integer> result) {
        if (depth == 3) {
            if (current[0] == 0) return;   // leading zero invalid

            int num = current[0] * 100 + current[1] * 10 + current[2];

            if (num % 2 == 0) {            // even check
                result.add(num);
            }
            return;
        }

        for (int i = 0; i < digits.length; i++) {
            if (used[i]) continue;         // ek hi digit repeat na ho
            used[i] = true;
            current[depth] = digits[i];
            permute(digits, used, current, depth + 1, result);
            used[i] = false;               // backtrack
        }
    }
}