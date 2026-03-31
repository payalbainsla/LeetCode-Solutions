import java.util.*;

class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        char[] word = new char[n + m - 1];
        Arrays.fill(word, '?');

        // Step 1: Handle 'T'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != '?' && word[i + j] != str2.charAt(j)) {
                        return "";
                    }
                    word[i + j] = str2.charAt(j);
                }
            }
        }

        // Step 2: Fill remaining '?' with 'a'
        for (int i = 0; i < word.length; i++) {
            if (word[i] == '?') word[i] = 'a';
        }

        // Step 3: Handle 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean match = true;

                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    // Need to break match
                    boolean changed = false;

                    for (int j = m - 1; j >= 0; j--) {
                        char original = word[i + j];
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c != original) {
                                word[i + j] = c;

                                // Check if still valid for all 'T'
                                boolean valid = true;
                                for (int k = 0; k < n; k++) {
                                    if (str1.charAt(k) == 'T') {
                                        for (int x = 0; x < m; x++) {
                                            if (word[k + x] != str2.charAt(x)) {
                                                valid = false;
                                                break;
                                            }
                                        }
                                    }
                                    if (!valid) break;
                                }

                                if (valid) {
                                    changed = true;
                                    break;
                                } else {
                                    word[i + j] = original; // revert
                                }
                            }
                        }
                        if (changed) break;
                    }

                    if (!changed) return "";
                }
            }
        }

        return new String(word);
    }
}