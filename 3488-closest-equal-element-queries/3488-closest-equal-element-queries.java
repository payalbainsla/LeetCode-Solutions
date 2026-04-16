import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        // value -> list of indices
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> res = new ArrayList<>();

        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = pos.get(val);

            // only one occurrence
            if (list.size() == 1) {
                res.add(-1);
                continue;
            }

            // binary search to find position
            int idx = Collections.binarySearch(list, q);

            int size = list.size();
            int best = Integer.MAX_VALUE;

            // left neighbor (circular)
            int left = list.get((idx - 1 + size) % size);
            int distLeft = Math.abs(q - left);
            best = Math.min(best, Math.min(distLeft, n - distLeft));

            // right neighbor (circular)
            int right = list.get((idx + 1) % size);
            int distRight = Math.abs(q - right);
            best = Math.min(best, Math.min(distRight, n - distRight));

            res.add(best);
        }

        return res;
    }
}