/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
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
    public ListNode reverseList(ListNode head) {
        ListNode header = new ListNode();
        ListNode cur = head;
        // 头插法
        while(cur != null) {
            ListNode temp = cur.next;
            cur.next = header.next;
            header.next = cur;
            cur = temp;
        }
        return header.next;
    }
}
// @lc code=end
