/*
 * @lc app=leetcode.cn id=124 lang=java
 *
 * [124] 二叉树中的最大路径和
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
    int result;
    // 后序遍历, 计算以root结点为起点的最大路径和, 同时更新result的最大值
    int postTravel(TreeNode root) {
        if(root == null)
            return 0; // 递归出口
        int left = postTravel(root.left); // 以root.left结点为起点的最大路径和
        int right = postTravel(root.right);// 以root.right结点为起点的最大路径和
        // 计算以root为转折点的的最大路径和
        int cur = root.val;
        if(left > 0)
            cur += left;
        if(right > 0)
            cur += right;
        result = Math.result(result, cur); // 更新result
        // 返回以root结点为起点的最大路径和
        return root.val + Math.max(0, Math.max(left, right));
    }
    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;
        postTravel(root);
        return result;
    }
}
// @lc code=end

