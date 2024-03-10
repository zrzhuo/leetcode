/*
 * @lc app=leetcode.cn id=894 lang=java
 *
 * [894] 所有可能的真二叉树
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
    Map<Integer, List<TreeNode>> solved = new HashMap<>();
    List<TreeNode> solving(int n) {
        if(n % 2 == 0) {
            return new ArrayList<>();
        }
        // 获取记忆
        if(solved.containsKey(n)) {
            return solved.get(n);
        }
        List<TreeNode> result = new ArrayList<>();
        // 枚举左右子树的结点个数，个数只能为奇数，且相加为n-1
        for(int i = 1; i < n; i += 2) {
            for(TreeNode left : solving(i)) { // 左子树列表
                for(TreeNode right : solving(n - 1 - i)) { // 右子树列表
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        // 存储记忆
        solved.put(n, result);
        return result;
    }

    public List<TreeNode> allPossibleFBT(int n) {
        // 初始化，以solved[1]作为递归出口
        List<TreeNode> list = new ArrayList<>();
        list.add(new TreeNode(0));
        solved.put(1, list);
        return solving(n);
    }
}
// @lc code=end

