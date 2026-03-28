class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        Arrays.fill(word, '?');

        // Step 1: Validate diagonal
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }

        // Step 2: Assign characters using equivalence classes
        char currentChar = 'a';
        for (int i = 0; i < n; i++) {
            if (word[i] == '?') {
                if (currentChar > 'z') return ""; // ran out of letters
                word[i] = currentChar;
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) word[j] = word[i];
                }
                currentChar++;
            }
        }

        // Step 3: Validate by recomputing LCP
        String result = new String(word);
        if (!validate(result, lcp)) return "";
        return result;
    }

    private boolean validate(String s, int[][] lcp) {
        int n = s.length();
        int[][] check = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 < n && j + 1 < n) {
                        check[i][j] = 1 + check[i + 1][j + 1];
                    } else {
                        check[i][j] = 1;
                    }
                }
            }
        }
        return Arrays.deepEquals(check, lcp);
    }
}
