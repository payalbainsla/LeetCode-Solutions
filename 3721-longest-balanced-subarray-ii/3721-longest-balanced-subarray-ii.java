import java.util.*;

class SegmentTree {
    int n;
    int[] minBalance;
    int[] maxBalance;
    int[] lazyval;

    public SegmentTree(int n) {
        this.n = n;
        minBalance = new int[4 * n];
        maxBalance = new int[4 * n];
        lazyval = new int[4 * n];
    }

    private void push(int node, int start, int end) {
        if (lazyval[node] != 0) {

            // Apply lazy value to this node
            minBalance[node] += lazyval[node];
            maxBalance[node] += lazyval[node];

            // propagate to children if not a leaf
            if (start != end) {
                lazyval[node * 2] += lazyval[node];
                lazyval[node * 2 + 1] += lazyval[node];
            }

            // clear lazy value at this node
            lazyval[node] = 0;
        }
    } // ✅ MISSING BRACE FIXED HERE

    public void update(int node, int start, int end,
                       int l, int r, int val) {

        push(node, start, end);

        // No overlap
        if (start > r || end < l) return;

        // Fully inside range
        if (l <= start && end <= r) {
            lazyval[node] += val;
            push(node, start, end);
            return;
        }

        // Partial overlap
        int mid = (start + end) / 2;

        update(node * 2, start, mid, l, r, val);           // ✅ updateRange -> update
        update(node * 2 + 1, mid + 1, end, l, r, val);

        // Recompute
        minBalance[node] = Math.min(minBalance[node * 2], minBalance[node * 2 + 1]);
        maxBalance[node] = Math.max(maxBalance[node * 2], maxBalance[node * 2 + 1]); // ✅ fixed bracket + assignment
    }

    public int getLeftMost(int node, int start, int end) {
        push(node, start, end);

        if (minBalance[node] > 0 || maxBalance[node] < 0) {
            return -1;
        }

        // leaf node
        if (start == end) {
            return minBalance[node] == 0 ? start : -1;
        }

        int mid = (start + end) / 2;

        int left = getLeftMost(node * 2, start, mid);
        if (left != -1) return left;

        return getLeftMost(node * 2 + 1, mid + 1, end);
    }
}

public class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> prevIndex = new HashMap<>();

        SegmentTree st = new SegmentTree(n); // ✅ small s -> capital S

        int maxlen = 0;

        for (int i = 0; i < n; i++) {

            int val = (nums[i] % 2 == 0) ? 1 : -1; // ✅ fixed %== and num -> nums

            if (prevIndex.containsKey(nums[i])) {
                int prev = prevIndex.get(nums[i]); // ✅ prev missing tha
                st.update(1, 0, n - 1, 0, prev, -val);
            }

            st.update(1, 0, n - 1, 0, i, val);

            prevIndex.put(nums[i], i);

            int left = st.getLeftMost(1, 0, n - 1);

            if (left != -1 && left <= i) {
                maxlen = Math.max(maxlen, i - left + 1);
            }
        }

        return maxlen;
    }
}
