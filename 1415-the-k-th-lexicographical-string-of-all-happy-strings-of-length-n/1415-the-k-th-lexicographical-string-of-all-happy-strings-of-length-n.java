import java.util.*;

class Solution {
    public String getHappyString(int n, int k) {
        int total = 3 * (1 << (n - 1)); // total happy strings
        if (k > total) return "";

        char[] chars = {'a','b','c'};
        StringBuilder sb = new StringBuilder();
        int blockSize = 1 << (n - 1); // size of each starting block

        // First character
        int firstIndex = (k - 1) / blockSize;
        sb.append(chars[firstIndex]);
        k -= firstIndex * blockSize;

        // Remaining characters
        for (int i = 1; i < n; i++) {
            blockSize >>= 1; // half each time
            char prev = sb.charAt(sb.length() - 1);
            char[] options = prev == 'a' ? new char[]{'b','c'} :
                             prev == 'b' ? new char[]{'a','c'} :
                                           new char[]{'a','b'};
            int choice = (k - 1) / blockSize;
            sb.append(options[choice]);
            k -= choice * blockSize;
        }

        return sb.toString();
    }
}
