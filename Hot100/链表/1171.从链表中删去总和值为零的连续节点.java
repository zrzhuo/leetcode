/*
 * @lc app=leetcode.cn id=1171 lang=java
 *
 * [1171] 从链表中删去总和值为零的连续节点
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode header = new ListNode(0, head);

        ListNode p = header;
        int prefix = 0;
        while(p != null) {
            prefix += p.val;
            map.put(prefix, p); // 建立prefix到结点的映射
            p = p.next;
        }
       
        p = header;
        prefix = 0;
        while(p != null) {
            prefix += p.val;
            p.next = map.get(prefix).next; // 删除和为零的区间
            p = p.next;
        }
        return header.next;
    }
}
// @lc code=end

