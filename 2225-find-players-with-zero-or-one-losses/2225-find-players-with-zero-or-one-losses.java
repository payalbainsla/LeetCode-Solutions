class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        HashMap<Integer, Integer> lossCount = new HashMap<>();
        
        for (int i = 0; i < matches.length; i++) {
            int winner = matches[i][0];
            int loser = matches[i][1];
            
            // winner ko map me daalege agar already nahi hai (0 losses)
            if (!lossCount.containsKey(winner)) {
                lossCount.put(winner, 0);
            }
            
            // loser ka loss count +1 badhao
            lossCount.put(loser, lossCount.getOrDefault(loser, 0) + 1);
        }
        
        List<Integer> zeroLosses = new ArrayList<>();
        List<Integer> oneLoss = new ArrayList<>();
        
        for (Map.Entry<Integer, Integer> entry : lossCount.entrySet()) {
            if (entry.getValue() == 0) {
                zeroLosses.add(entry.getKey());
            } else if (entry.getValue() == 1) {
                oneLoss.add(entry.getKey());
            }
        }
        
        Collections.sort(zeroLosses);
        Collections.sort(oneLoss);
        
        List<List<Integer>> result = new ArrayList<>();
        result.add(zeroLosses);
        result.add(oneLoss);
        
        return result;
    }
}