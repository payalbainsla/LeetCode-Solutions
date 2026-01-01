class Solution {
    public int maxVowels(String s, int k) {
        // Define vowels
        String vowels = "aeiou";
        int count = 0, maxCount = 0;

        // First window of size k
        for (int i = 0; i < k; i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                count++;
            }
        }
        maxCount = count;

        // Sliding window
        for (int i = k; i < s.length(); i++) {
            // Remove leftmost char
            if (vowels.indexOf(s.charAt(i - k)) != -1) {
                count--;
            }
            // Add new char
            if (vowels.indexOf(s.charAt(i)) != -1) {
                count++;
            }
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }
}