/*
 * @lc app=leetcode.cn id=1373 lang=java
 *
 * [1373] 二叉搜索子树的最大键值和
 */

// @lc code=start
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
    int result = 0;
    int[] postOrder(TreeNode root) {
        if(root == null) 
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1};
        // 递归
        int[] left = postOrder(root.left);
        int[] right = postOrder(root.right);
        // 当前结点
        int min = Math.min(root.val, Math.min(left[0], right[0])); // 以root为根的树的最小值
        int max = Math.max(root.val, Math.max(left[1], right[1])); // 以root为根的树的最大值
        int sum = root.val + left[2] + right[2]; // 以root为根的树的和
        int isBST = 0; // 以root为根的树是否是二叉搜索树
        if(left[1] < root.val && right[0] > root.val && left[3] == 1 && right[3] == 1)
            isBST = 1;
        // 更新result
        if(isBST == 1)
            result = Math.max(result, sum);
        // 返回
        return new int[]{min, max, sum, isBST};
    }

    public int maxSumBST(TreeNode root) {
        postOrder(root);
        return result;
    }
}
// @lc code=end

