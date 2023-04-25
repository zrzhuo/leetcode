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
        ListNode starter = header, p = header;
        while(p != null) {
            for(int i = 0; i < k; i++) {
                p = p.next;
                if(p == null)
                    return header.next; // 剩余结点不足k个, 直接返回
            }
            // 头插法, 翻转本组k个结点: pre为本组结点的前置结点, p是本组结点的最后一个结点
            ListNode nextStarter = starter.next;
            ListNode ender = p.next;
            ListNode cur = starter.next;
            starter.next = ender;
            while(cur != ender) {
                ListNode temp = cur.next;
                cur.next = starter.next;
                starter.next = cur;
                cur = temp;
            }
            starter = nextStarter;
            p = nextStarter;
        }
        return header.next;
    }
}
// @lc code=end

