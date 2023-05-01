/*
 * @lc app=leetcode.cn id=103 lang=java
 *
 * [103] 二叉树的锯齿形层序遍历
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>>  res = new LinkedList<>();
        if(root == null) 
            return res;
        // 层次遍历
        Deque<TreeNode> que = new LinkedList<>();
        que.add(root);
        int k = 0;
        while(!que.isEmpty()) {
            int size = que.size();
            List<Integer> level = new LinkedList<>();
            for(int i = 0; i < size; ++i) {
                TreeNode cur = que.poll();
                level.add(cur.val);
                if(cur.left != null) 
                    que.add(cur.left);
                if(cur.right != null)
                    que.add(cur.right);   
            }
            if(k % 2 == 1)
                Collections.reverse(level);
            res.add(level);
            k++;
        }
        return res;
    }
}
// @lc code=end

