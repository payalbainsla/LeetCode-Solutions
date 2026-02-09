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
    List<Integer> list = new ArrayList<>();
    public TreeNode balanceBST(TreeNode root) {
        inorder(root) ;
        return createBST(0, list.size()-1);
    }

    void inorder(TreeNode root) {
        if(root == null) 
        return;
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }

    TreeNode createBST(int start, int end) {
        if(start > end)
        return null;
        int mid = start + (end-start)/2;
        TreeNode left = createBST(start, mid-1);
        TreeNode right = createBST(mid+1, end);
        TreeNode root = new TreeNode(list.get(mid), left, right);
        return root;
    }
}