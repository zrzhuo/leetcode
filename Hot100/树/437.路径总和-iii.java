/*
 * @lc app=leetcode.cn id=437 lang=java
 *
 * [437] 路径总和 III
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
    // key为前缀和, value为该前缀和出现的次数
    Map<Long, Integer> prefix = new HashMap<>();
    int ans = 0;
    void dfs(TreeNode root, long pre, int targetSum) {
        if(root == null)
            return;
        // 前缀和
        long cur = pre + root.val;
        // 累计
        ans += prefix.getOrDefault(cur - targetSum, 0);
        // 递归
        prefix.put(cur, prefix.getOrDefault(cur, 0) + 1);
        dfs(root.left, cur, targetSum);
        dfs(root.right, cur, targetSum);
        prefix.put(cur, prefix.getOrDefault(cur, 0) - 1);
    }

    public int pathSum(TreeNode root, int targetSum) {
        prefix.put(0L, 1);
        dfs(root, 0L, targetSum);
        return ans;
    }
}
// @lc code=end

