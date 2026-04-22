import java.util.*;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        
        for (String query : queries) {
            for (String dictWord : dictionary) {
                if (canMatchWithinTwoEdits(query, dictWord)) {
                    result.add(query);
                    break; // No need to check further dictionary words
                }
            }
        }
        
        return result;
    }
    
    private boolean canMatchWithinTwoEdits(String word1, String word2) {
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 2) return false;
            }
        }
        return true;
    }
}
