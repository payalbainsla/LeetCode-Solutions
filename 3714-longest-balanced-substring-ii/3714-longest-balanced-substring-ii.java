import java.util.*;

class Solution {

    public int longestBalanced(String s) {

        int n = s.length();
        int maxlen = 0;

        // Case 1: only one distinct char
        for (int i = 0; i < n; ) {
            char ch = s.charAt(i);
            int len = 0;

            while (i < n && s.charAt(i) == ch) {
                i++;
                len++;
            }

            maxlen = Math.max(maxlen, len);
        }

        // Case 2: two distinct chars
        maxlen = Math.max(maxlen, getLength(s, 'a'));
        maxlen = Math.max(maxlen, getLength(s, 'b'));
        maxlen = Math.max(maxlen, getLength(s, 'c'));

        // Case 3: all three chars
        HashMap<String, Integer> prev = new HashMap<>();
        int c1 = 0, c2 = 0, c3 = 0;

        prev.put("0#0", -1);

        for (int i = 0; i < n; i++) {

            char ch = s.charAt(i);

            if (ch == 'a') c1++;
            else if (ch == 'b') c2++;
            else c3++;

            int diff1 = c1 - c2;
            int diff2 = c1 - c3;

            String key = diff1 + "#" + diff2;

            if (prev.containsKey(key)) {
                maxlen = Math.max(maxlen, i - prev.get(key));
            } else {
                prev.put(key, i);
            }
        }

        return maxlen;
    }

    int getLength(String s, char skip) {

        int n = s.length();
        int mlen = 0;
        int i = 0;

        char first = (skip == 'a') ? 'b' : 'a';
        char second = (skip == 'c') ? 'b' : 'c';

        while (i < n) {

            int c1 = 0, c2 = 0;
            HashMap<Integer, Integer> prev = new HashMap<>();
            prev.put(0, i - 1);

            while (i < n && s.charAt(i) != skip) {

                char ch = s.charAt(i);

                if (ch == first) c1++;
                else c2++;

                int diff = c1 - c2;

                if (prev.containsKey(diff)) {
                    mlen = Math.max(mlen, i - prev.get(diff));
                } else {
                    prev.put(diff, i);
                }

                i++;
            }

            i++; // skip the skipped character
        }

        return mlen;
    }
}
