/*
 * @lc app=leetcode.cn id=199 lang=java
 *
 * [199] 二叉树的右视图
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
    public List<Integer> rightSideView(TreeNode root) {
        // 层次遍历
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()) {
            int size = que.size();
            int num = -1;
            for(int i = 0; i < size; i++) {
                TreeNode cur = que.poll();
                num = cur.val;
                if(cur.left != null)
                    que.offer(cur.left);
                if(cur.right != null)
                    que.offer(cur.right);
            }
            result.add(num);
        }
        return result;
    }
}
// @lc code=end

