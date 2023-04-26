/*
 * @lc app=leetcode.cn id=138 lang=java
 *
 * [138] 复制带随机指针的链表
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Integer> index = new HashMap<>();
        List<Node> nodes = new ArrayList<>();
        Node header = new Node(0);
        Node cur = head, p = header;
        int idx = 0;
        while(cur != null) {
            Node node = new Node(cur.val);
            index.put(cur, idx++); // 保存原链表中结点的位置
            nodes.add(node); // 按顺序保存新链表
            p.next = node;
            p = p.next;
            cur = cur.next;
        }
        cur = head;
        p = header.next;
        while(cur != null) {
            if(cur.random != null) {
                idx = index.get(cur.random); // 获取旧结点random指向的结点的位置下标
                p.random = nodes.get(idx); // 根据下标获取新结点的random
            }
            p = p.next;
            cur = cur.next;
        }
        return header.next;
    }
}
// @lc code=end

