/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
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
    public ListNode swapPairs(ListNode head) {
        ListNode header = new ListNode(-1, head);
        ListNode pre = header;
        while(pre.next != null && pre.next.next != null) {
            ListNode p1 = pre.next, p2 = pre.next.next;
            ListNode temp = p2.next;
            pre.next = p2;
            p2.next = p1;
            p1.next = temp;
            pre = p1;
        }
        return header.next;
    }
}
// @lc code=end

