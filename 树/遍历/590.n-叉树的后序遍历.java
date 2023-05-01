/*
 * @lc app=leetcode.cn id=590 lang=java
 *
 * [590] N 叉树的后序遍历
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
    public List<Integer> postorder(Node root) {
        List<Integer> res = new LinkedList<>();
        if(root == null)
            return res;
        // 后序遍历
        Deque<Node> sta = new LinkedList<>();
        sta.push(root);
        while(!sta.isEmpty()) {
            Node cur = sta.pop();
            res.add(cur.val);
            for(Node child : cur.children) {
                sta.push(child);
            }
        }
        Collections.reverse(res); // 翻转
        return res;
    }
}
// @lc code=end

