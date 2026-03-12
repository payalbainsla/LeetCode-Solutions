import java.util.*;

class DSU {
    int[] parent, rank;
    DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return false;
        if (rank[px] < rank[py]) parent[px] = py;
        else if (rank[px] > rank[py]) parent[py] = px;
        else { parent[py] = px; rank[px]++; }
        return true;
    }
}

class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        DSU dsu = new DSU(n);
        List<int[]> mustEdges = new ArrayList<>();
        List<int[]> optionalEdges = new ArrayList<>();

        for (int[] e : edges) {
            if (e[3] == 1) mustEdges.add(e);
            else optionalEdges.add(e);
        }

        int stability = Integer.MAX_VALUE;
        List<int[]> chosen = new ArrayList<>();

        // Step 1: Add must edges
        for (int[] e : mustEdges) {
            if (!dsu.union(e[0], e[1])) return -1; // cycle
            chosen.add(e);
            stability = Math.min(stability, e[2]);
        }

        // Step 2: Sort optional edges by strength descending
        optionalEdges.sort((a, b) -> b[2] - a[2]);

        // Step 3: Add optional edges to complete spanning tree
        for (int[] e : optionalEdges) {
            if (dsu.union(e[0], e[1])) {
                chosen.add(e);
                stability = Math.min(stability, e[2]);
                if (chosen.size() == n - 1) break;
            }
        }

        if (chosen.size() != n - 1) return -1;

        // Step 4: Upgrade up to k optional edges in the tree
        List<Integer> optWeights = new ArrayList<>();
        for (int[] e : chosen) {
            if (e[3] == 0) optWeights.add(e[2]);
        }
        optWeights.sort(Comparator.naturalOrder());

        for (int i = 0; i < Math.min(k, optWeights.size()); i++) {
            optWeights.set(i, optWeights.get(i) * 2);
        }

        // Step 5: Compute final stability
        int finalStability = Integer.MAX_VALUE;
        for (int[] e : chosen) {
            if (e[3] == 1) finalStability = Math.min(finalStability, e[2]);
        }
        for (int w : optWeights) {
            finalStability = Math.min(finalStability, w);
        }

        return finalStability;
    }
}
