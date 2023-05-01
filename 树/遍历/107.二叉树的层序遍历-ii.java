/*
 * @lc app=leetcode.cn id=107 lang=java
 *
 * [107] 二叉树的层序遍历 II
 */

// @lc code=start
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if(root == null)
            return res;
        // 层次遍历
        Deque<TreeNode> que = new LinkedList<>();
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
            res.addFirst(level);
        }
        return res;
    }
}
// @lc code=end

