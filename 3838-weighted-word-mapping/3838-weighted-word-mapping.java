class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
    
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            int sum = 0;
            // Calculate weight of the word
            for (char c : word.toCharArray()) {
                sum += weights[c - 'a'];
            }

            // Take modulo 26
            int mod = sum % 26;

            // Map to reverse alphabetical order
            // 0 -> 'z', 1 -> 'y', ..., 25 -> 'a'
            char mappedChar = (char) ('z' - mod);

            result.append(mappedChar);
        }

        return result.toString();
    }
}