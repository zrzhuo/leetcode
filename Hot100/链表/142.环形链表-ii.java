/*
 * @lc app=leetcode.cn id=142 lang=java
 *
 * [142] 环形链表 II
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode header = new ListNode(-1, head);
        // 找到快慢指针相遇点
        ListNode slow = header, fast = header;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                 // 发出一个新指针
                ListNode late = header;
                while(true) {
                    late = late.next;
                    slow = slow.next;
                    if(late == slow)
                        return late;
                }
            }
        }
        // 此时无环
        return null;
    }
}
// @lc code=end

快慢指针寻找环入口:

A     S1    B    S2
#####################
            #       # C
            #       #
            #########
               S3

开头A到入口处B的距离记为S1, 图中AB段
环入口B到快慢指针相遇点C的距离记为S2, 图中BC段
快慢指针相遇点C到环入口B的距离记为S3, 图中BC段
环的周长记为C, 则 C = S2 + S3

快慢指针相遇时, 一定位于环内, 此时:
    快指针走过的距离 F = S1 + m * C + S2
    慢指针走过的距离 S = S1 + n * C + S2
由 F = 2 * S, 得:
    S1  = (m - n) * C - S2 = (m - n - 1) * C + S3
后指针从A点, 慢指针从C点, 同时出发, 走S1距离后:
    后指针走到B处
    慢指针绕环走(m-n-1)圈, 仍位于C点, 再走S3的距离, 恰好也位于B点
即后指针和慢指针会在入口处B相遇
