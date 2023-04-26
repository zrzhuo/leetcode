/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] K 个一组翻转链表
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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode header = new ListNode(-1, head);
        ListNode p = header;
        while(p != null) {
            ListNode preHeader = p;
            for(int i = 0; i < k; i++) {
                p = p.next;
                if(p == null)
                    return header.next; // 剩余结点不足k个, 直接返回
            }
            // 头插法, 翻转本组k个结点, preHeader为本组结点的前置结点
            ListNode cur = preHeader.next;
            preHeader.next = p.next;
            p = cur; // 翻转本组k个结点后, p应该指向最后一个结点, 也就是翻转前的第一个结点
            for(int i = 0; i < k; i++) {
                ListNode temp = cur.next;
                cur.next = preHeader.next;
                preHeader.next = cur;
                cur = temp;
            }
        }
        return header.next;
    }
}
// @lc code=end

