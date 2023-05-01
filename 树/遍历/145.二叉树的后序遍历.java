/*
 * @lc app=leetcode.cn id=145 lang=java
 *
 * [145] 二叉树的后序遍历
 */

// @lc code=start
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null)
            return res;
        // 后序遍历
        Deque<TreeNode> sta = new LinkedList<>();
        sta.push(root);
        while(!sta.isEmpty()) {
            TreeNode cur = sta.pop();
            res.add(cur.val);
            // 进栈时先左后右，遍历结果为先右后左，即中右左
            if(cur.left != null)
                sta.push(cur.left);
            if(cur.right != null)
                sta.push(cur.right);
        }
        // 中右左 --> 左右中
        Collections.reverse(res);
        return res;
    }
}
// @lc code=end

