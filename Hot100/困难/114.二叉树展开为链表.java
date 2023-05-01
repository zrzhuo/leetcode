/*
 * @lc app=leetcode.cn id=114 lang=java
 *
 * [114] 二叉树展开为链表
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
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while(cur != null) {
            // 若左子树存在, 则将左子树合并到右子树, 并且保持前序序列不变
            if(cur.left != null) {
                TreeNode leftLast = findPreOrderLast(cur.left); // 寻找到左子树的前序序列中的最后一个结点
                leftLast.right = cur.right; // 将右子树和并到左子树上
                cur.right = cur.left; // 再将左子树转为右子树
                cur.left = null; // 左子树置空
            }
            cur = cur.right;
        }
        return;
    }

    // 寻找二叉树的前序序列中的最后一个结点
    TreeNode findPreOrderLast(TreeNode root) {
        TreeNode cur = root;
        while(cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }
}
// @lc code=end


class Solution {
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        // 前序遍历
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
            // 若cur的左子树存在，则左孩子就是cur的后续结点
            if(cur.left != null) 
                cur.right = cur.left;
            // 若cur的左子树不存在，则cur的后序结点是要遍历的下一个结点，即栈顶的结点
            else
                cur.right = stack.peek();
            cur.left = null; // 左子树置为null
        }
        return;
    }
}