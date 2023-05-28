/*
 * @lc app=leetcode.cn id=1080 lang=java
 *
 * [1080] 根到叶路径上的不足节点
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
    // 判断node结点是否为不足结点, 并删除其不足子结点
    boolean check(TreeNode node, int limit, int sum) {
        if(node == null) 
            return false;
        sum += node.val; // sum是从root结点到node结点的路径和
        if(node.left == null && node.right == null)
            return sum >= limit; // 若node是叶子节点, 直接判断其是否为不足结点
        // 递归判断左右子结点
        boolean left = check(node.left, limit, sum);
        boolean right = check(node.right, limit, sum);
        // 子结点为不足结点时, 进行删除
        if(!left) 
            node.left = null;
        if(!right)
            node.right = null;
        // 左右子结点都为不足结点是, node结点才是不足结点
        return left || right;
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return check(root, limit, 0) ? root : null;
    }
}
// @lc code=end

