class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int ans = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int diff = Math.abs(i - startIndex);
                int circularDiff = n - diff;
                ans = Math.min(ans, Math.min(diff, circularDiff));
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
