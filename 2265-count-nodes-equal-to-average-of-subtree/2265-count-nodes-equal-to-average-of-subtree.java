/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int count = 0;

    public int averageOfSubtree(TreeNode root) {
        helper(root);
        return count;
    }

    private int[] helper(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};  // {count, sum}
        }

        int[] left = helper(node.left);
        int[] right = helper(node.right);

        int totalCount = left[0] + right[0] + 1;
        int totalSum = left[1] + right[1] + node.val;

        int average = totalSum / totalCount;

        if (node.val == average) {
            count++;
        }

        return new int[]{totalCount, totalSum};
    }
}