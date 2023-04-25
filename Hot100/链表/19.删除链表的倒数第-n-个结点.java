/*
 * @lc app=leetcode.cn id=19 lang=java
 *
 * [19] 删除链表的倒数第 N 个结点
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 统计链表长度
        int len = 0;
        ListNode p = head;
        while(p != null) {
            len++;
            p = p.next;
        }
        // 找到目标结点的前一个结点
        ListNode header = new ListNode(-1, head);
        ListNode pre = header;
        for(int i = 0; i < len - n; i++) {
            pre = pre.next;
        }
        // 删除
        pre.next = pre.next.next;
        return header.next;
    }
}
// @lc code=end

