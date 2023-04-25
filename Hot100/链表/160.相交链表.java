/*
 * @lc app=leetcode.cn id=160 lang=java
 *
 * [160] 相交链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pa = headA, pb = headB;
        // 当pa已空时，链表A遍历完毕，pa转去遍历链表B
        // 当pb已空时，链表B遍历完毕，pb转去遍历链表A
        // 如此，pa和pb遍历的最长长度都为m+n，若有交点，pa和pb一定会相遇在交点, 若无交点, pa和pb同时为null
        while(pa != pb) {
            pa = pa != null ? pa.next : headB;
            pb = pb != null ? pb.next : headA;
        }
        return pa;
    }
}
// @lc code=end
