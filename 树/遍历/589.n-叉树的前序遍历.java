/*
 * @lc app=leetcode.cn id=589 lang=java
 *
 * [589] N 叉树的前序遍历
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
    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        if(root == null)
            return res;
        // 前序遍历
        Deque<Node> sta = new LinkedList<>();
        sta.push(root);
        while(!sta.isEmpty()) {
            Node cur = sta.pop();
            res.add(cur.val);
            for(int i = cur.children.size() - 1; i >= 0 ; i--) {
                sta.push(cur.children.get(i));
            }
        }
        return res;
    }
}
// @lc code=end

