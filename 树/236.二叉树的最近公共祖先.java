/*
 * @lc app=leetcode.cn id=236 lang=java
 *
 * [236] 二叉树的最近公共祖先
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
    }
}
// @lc code=end

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p == q)
            return p;
        Queue<TreeNode> que = new LinkedList<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>(); // 记录每个结点的父结点
        que.offer(root);
        parent.put(root, null);
        while(!que.isEmpty()) {
            TreeNode node = que.poll();
            if(node.left != null) {
                que.offer(node.left);
                parent.put(node.left, node);
            }
            if(node.right != null) {
                que.offer(node.right);
                parent.put(node.right, node);
            }
        }
        // 类似于两个链表寻找交点
        TreeNode tp = p, tq = q;
        while(tp != tq) {
            tp = tp != null ? parent.get(tp) : q;
            tq = tq != null ? parent.get(tq) : p;
        }
        return tp;
    }
}