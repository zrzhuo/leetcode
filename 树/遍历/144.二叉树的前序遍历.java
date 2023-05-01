/*
 * @lc app=leetcode.cn id=144 lang=java
 *
 * [144] 二叉树的前序遍历
 */

// @lc code=start
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null)
            return res;
        // 前序遍历
        Deque<TreeNode> sta = new LinkedList<>();
        sta.push(root);
        while(!sta.isEmpty()) {
            TreeNode cur = sta.pop();
            res.add(cur.val);
            // 先右后左，遍历出的结果为先左后右，即中左右
            if(cur.right != null)
                sta.push(cur.right);
            if(cur.left != null)
                sta.push(cur.left);
        }
        return res;
    }
}
// @lc code=end

