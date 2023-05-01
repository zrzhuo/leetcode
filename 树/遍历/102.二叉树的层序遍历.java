/*
 * @lc app=leetcode.cn id=102 lang=java
 *
 * [102] 二叉树的层序遍历
 */

// @lc code=start
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) 
            return res;
        // 层次遍历
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int size = que.size();
            for(int i = 0; i < size; ++i) {
                TreeNode node = que.poll();
                level.add(node.val);
                if(node.left != null)
                    que.add(node.left);
                if(node.right != null)
                    que.add(node.right);
            }
            res.add(level);
        }
        return res;
    }
}
// @lc code=end

