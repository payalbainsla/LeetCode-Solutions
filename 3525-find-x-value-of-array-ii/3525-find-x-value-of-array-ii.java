class Solution {
    int k, n;
    int[] treeProd;
    int[][][] treeCnt;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        treeProd = new int[4 * n];
        treeCnt = new int[4 * n][k][k];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0], val = queries[i][1];
            int start = queries[i][2], x = queries[i][3];
            update(1, 0, n - 1, idx, val);
            int[][] q = query(1, 0, n - 1, start, n - 1);
            result[i] = q[1 % k][x];
        }
        return result;
    }

    private void setLeaf(int node, int m) {
        treeProd[node] = m;
        for (int[] row : treeCnt[node]) java.util.Arrays.fill(row, 0);
        for (int r = 0; r < k; r++) treeCnt[node][r][(r * m) % k] = 1;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) { setLeaf(node, nums[l] % k); return; }
        int mid = (l + r) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        pull(node);
    }

    private void pull(int node) {
        int L = 2 * node, R = 2 * node + 1;
        treeProd[node] = (treeProd[L] * treeProd[R]) % k;
        for (int r = 0; r < k; r++) {
            int shifted = (r * treeProd[L]) % k;
            for (int c = 0; c < k; c++) {
                treeCnt[node][r][c] = treeCnt[L][r][c] + treeCnt[R][shifted][c];
            }
        }
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) { setLeaf(node, val % k); return; }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node, l, mid, idx, val);
        else update(2 * node + 1, mid + 1, r, idx, val);
        pull(node);
    }

    // returns {prod (wrapped as [0]), cnt matrix} -- combine prod into cnt-returning pair via array trick
    private int[][] query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return null;
        if (ql <= l && r <= qr) return withProd(treeCnt[node], treeProd[node]);
        int mid = (l + r) / 2;
        int[][] left = query(2 * node, l, mid, ql, qr);
        int[][] right = query(2 * node + 1, mid + 1, r, ql, qr);
        if (left == null) return right;
        if (right == null) return left;
        return merge(left, right);
    }

    // pack prod as an extra row at index k (k+1 x k array), row k col 0 stores prod
    private int[][] withProd(int[][] cnt, int prod) {
        int[][] res = new int[k + 1][k];
        for (int r = 0; r < k; r++) res[r] = cnt[r].clone();
        res[k][0] = prod;
        return res;
    }

    private int[][] merge(int[][] left, int[][] right) {
        int leftProd = left[k][0], rightProd = right[k][0];
        int[][] res = new int[k + 1][k];
        for (int r = 0; r < k; r++) {
            int shifted = (r * leftProd) % k;
            for (int c = 0; c < k; c++) {
                res[r][c] = left[r][c] + right[shifted][c];
            }
        }
        res[k][0] = (leftProd * rightProd) % k;
        return res;
    }
}