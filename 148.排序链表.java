/*
 * @lc app=leetcode.cn id=148 lang=java
 *
 * [148] 排序链表
 */

// @lc code=start
// 自顶向下的归并排序: 递归
class Solution {
    public ListNode sortList(ListNode head) {
        return sort(head);
    }

    // 对链表进行排序, 该链表的第一个结点为head
    ListNode sort(ListNode head) {
        // 递归出口: 没有节点或只有一个结点
        if(head == null || head.next == null)
            return head;
        // 快慢指针法求链表的中点, 将链表分为两段
        ListNode header = new ListNode(-1, head);
        ListNode slow = header, fast = header;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null; 
        // 排序并归并
        return merge(sort(head), sort(mid));
    }

    // 将两个增序链表进行合并
    ListNode merge(ListNode l1, ListNode l2) {
        ListNode header = new ListNode();
        ListNode p = header, p1 = l1, p2 = l2;
        while(p1 != null && p2 != null) {
            if(p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if(p1 != null) 
            p.next = p1;
        else
            p.next = p2;
        return header.next;
    }
}
// @lc code=end

