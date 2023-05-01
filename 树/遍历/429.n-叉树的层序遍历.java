/*
 * @lc app=leetcode.cn id=429 lang=java
 *
 * [429] N 叉树的层序遍历
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null)
            return res;
        // 层次遍历
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int size = que.size();
            for(int i = 0; i < size; ++i) {
                Node cur = que.poll();
                level.add(cur.val);
                for(Node child : cur.children) {
                    if(child != null)
                        que.add(child);
                }
            }
            res.add(level);
        }
        return res;
    }
}
// @lc code=end

