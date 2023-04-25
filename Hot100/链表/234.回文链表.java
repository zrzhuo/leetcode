/*
 * @lc app=leetcode.cn id=234 lang=java
 *
 * [234] 回文链表
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
    public boolean isPalindrome(ListNode head) {
        // 统计链表的长度
        int count = 0;
        ListNode p = head;
        while(p != null) {
            count++;
            p = p.next;
        }
        // 翻转链表的后半部分, 不开辟额外空间
        ListNode middle = head;
        for(int i = 0; i < (count - 1) / 2; i++) { // !!!
            middle = middle.next;
        }
        ListNode cur = middle.next;
        middle.next = null; // !!!
        while(cur != null) {
            ListNode temp = cur.next;
            cur.next = middle.next;
            middle.next = cur;
            cur = temp;
        }
        // 比较前半部分和后半部分
        ListNode p1 = head, p2 = middle.next;
        while(p2 != null) { // !!!
            if(p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
}
// @lc code=end

class Solution {
    public boolean isPalindrome(ListNode head) {
        // 头插法求链表的翻转, 空间O(n)
        ListNode header = new ListNode();
        ListNode p = head;
        while(p != null) {
            ListNode cur = new ListNode(p.val);
            cur.next = header.next;
            header.next = cur;
            p = p.next;
        }
        // 比较两个链表是否相同
        ListNode p1 = head, p2 = header.next;
        while(p1 != null) {
            if(p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
}