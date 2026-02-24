class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode root, int num) {
        if (root == null) return 0;

        // Update the current number by shifting left (multiply by 2) and adding the node value
        num = num * 2 + root.val;

        // If it's a leaf node, return the accumulated number
        if (root.left == null && root.right == null) {
            return num;
        }

        // Otherwise, recurse on both children
        return helper(root.left, num) + helper(root.right, num);
    }
}