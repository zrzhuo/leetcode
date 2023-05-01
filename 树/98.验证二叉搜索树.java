/*
 * @lc app=leetcode.cn id=98 lang=java
 *
 * [98] 验证二叉搜索树
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
// 迭代中序遍历
class Solution {
    public boolean isValidBST(TreeNode root) {
        TreeNode cur = root, pre = null;
        Deque<TreeNode> sta = new LinkedList<>();
        while(cur != null || !sta.isEmpty()) {
            while(cur != null){
                sta.push(cur);
                cur = cur.left;
            }
            if(!sta.isEmpty()) {
                cur = sta.pop();
                if(pre != null && cur.val <= pre.val)
                    return false;
                pre = cur;
                cur = cur.right;
            }
        }
        return true;
    }
}
// @lc code=end

// 递归中序遍历
class Solution {
    List<Integer> nums = new ArrayList<>();
    void inOrder(TreeNode root) {
        if(root == null)
            return;
        inOrder(root.left);
        nums.add(root.val);
        inOrder(root.right); 
    }
    public boolean isValidBST(TreeNode root) {
        inOrder(root);
        for(int i = 1; i < nums.size(); ++i) {
            if(nums.get(i) <= nums.get(i-1))
                return false;
        }
        return true;
    }   
}