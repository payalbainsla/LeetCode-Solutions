
import java.util.*;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        
        // Step 1: Union-Find setup
        UnionFind uf = new UnionFind(n);
        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }
        
        // Step 2: Group indices by connected component
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }
        
        // Step 3: For each group, compare frequency counts
        int hammingDistance = 0;
        for (List<Integer> indices : groups.values()) {
            Map<Integer, Integer> countSource = new HashMap<>();
            Map<Integer, Integer> countTarget = new HashMap<>();
            
            for (int idx : indices) {
                countSource.put(source[idx], countSource.getOrDefault(source[idx], 0) + 1);
                countTarget.put(target[idx], countTarget.getOrDefault(target[idx], 0) + 1);
            }
            
            // Subtract common counts
            for (Map.Entry<Integer, Integer> entry : countSource.entrySet()) {
                int val = entry.getKey();
                int freqSource = entry.getValue();
                int freqTarget = countTarget.getOrDefault(val, 0);
                hammingDistance += Math.max(0, freqSource - freqTarget);
            }
        }
        
        return hammingDistance;
    }
    
    // Union-Find helper class
    static class UnionFind {
        int[] parent;
        
        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }
}
