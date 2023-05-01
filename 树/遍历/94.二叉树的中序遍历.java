/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
 */

// @lc code=start
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null)
            return res;
        // 中序遍历
        Deque<TreeNode> sta = new LinkedList<>();
        TreeNode p = root;
        while(p != null || !sta.isEmpty()) {
            while(p != null) {
                sta.push(p);
                p = p.left; // p指针不断左移，将途径的结点全部入栈
            }
            if(!sta.isEmpty()){
                TreeNode cur = sta.pop();  // 栈顶为当前最左结点，该结点一定没有“未遍历的左结点”，但可能有右结点
                res.add(cur.val); 
                p = cur.right;  // 该结点可能有右结点
            }
        }
        return res;
    }
}
// @lc code=end
