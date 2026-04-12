class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        int[][][] dp = new int[n + 1][27][27];

        for (int i = n - 1; i >= 0; i--) {
            int ch = word.charAt(i) - 'A';
            for (int pos1 = 0; pos1 < 27; pos1++) {
                for (int pos2 = 0; pos2 < 27; pos2++) {
                    // move finger 1
                    int d1 = getDist(ch, pos1 - 1) + dp[i + 1][ch + 1][pos2];
                    // move finger 2
                    int d2 = getDist(ch, pos2 - 1) + dp[i + 1][pos1][ch + 1];
                    dp[i][pos1][pos2] = Math.min(d1, d2);
                }
            }
        }

        return dp[0][0][0]; // start with both fingers unplaced
    }

    int getDist(int ch, int pos) {
        if (pos == -1) return 0;
        int x1 = ch / 6, y1 = ch % 6;
        int x2 = pos / 6, y2 = pos % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
