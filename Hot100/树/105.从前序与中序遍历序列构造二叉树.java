/*
 * @lc app=leetcode.cn id=105 lang=java
 *
 * [105] 从前序与中序遍历序列构造二叉树
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
    TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        // 递推出口
        if(preStart > preEnd)
            return null; 
        TreeNode node = new TreeNode(preorder[preStart]);
        // 寻找切分点
        int inCut = 0;
        for(int i = inStart; i <= inEnd; i++) {
            if(inorder[i] == preorder[preStart]) {
                inCut = i;
                break;
            }
        }
        int preCut =  preStart + inCut - inStart;
        // 构建左右子树
        node.left = build(preorder, preStart + 1, preCut, inorder, inStart, inCut - 1);
        node.right = build(preorder, preCut + 1, preEnd, inorder, inCut + 1, inEnd);
        return node;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int m = preorder.length, n = inorder.length;
        return build(preorder, 0, m - 1, inorder, 0, n - 1);
    }
}
// @lc code=end

