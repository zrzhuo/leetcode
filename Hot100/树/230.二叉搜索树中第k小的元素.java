/*
 * @lc app=leetcode.cn id=230 lang=java
 *
 * [230] 二叉搜索树中第K小的元素
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
    // 中序遍历
    public int kthSmallest(TreeNode root, int k) {
        int i = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while(p != null || !stack.isEmpty()) {
            // p指针不断左移，将途径的结点全部入栈
            while(p != null) {
                stack.push(p);
                p = p.left; // 左
            }
            if(!stack.isEmpty()){
                // 栈顶为当前最左结点，该结点一定没有“未遍历的左结点”，但可能有右结点
                TreeNode cur = stack.pop(); // 中 
                if(++i == k)
                    return cur.val;
                // 该结点可能有右结点，处理
                if(cur.right != null)
                    p = cur.right; // 右
            }
        }
        return -1;
    }
}
// @lc code=end

