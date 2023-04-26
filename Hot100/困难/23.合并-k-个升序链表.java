/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并 K 个升序链表
 */

// @lc code=start
// 分治, 时间复杂度更好
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if(n == 0)
            return null;
        while(n > 1) {
            // 两两一组进行合并
            for(int i = 0; i < n / 2; i++) {
                lists[i] = merge(lists[i], lists[i + n / 2]);
            }
            // 处理落单的链表
            if(n % 2 == 1) {
                lists[0] = merge(lists[0], lists[n - 1]);
            }
            n = n / 2; // 剩余一半链表
        }
        return lists[0];
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


// 顺序
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for(ListNode list : lists) {
            res = merge(res, list);
        }
        return res;
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
