/*
 * @lc app=leetcode.cn id=445 lang=java
 *
 * [445] 两数相加 II
 */

// @lc code=start
// 栈 + 头插法
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> sta1 = new LinkedList<>(), sta2 = new LinkedList<>();
        while(l1 != null) {
            sta1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            sta2.push(l2.val);
            l2 = l2.next;
        }
        // 头插法
        ListNode header = new ListNode();
        int carry = 0;
        while(!sta1.isEmpty() || !sta2.isEmpty() || carry != 0) {
            int sum = carry;
            if(!sta1.isEmpty())
                sum += sta1.pop();
            if(!sta2.isEmpty())
                sum += sta2.pop();
            ListNode curr = new ListNode(sum % 10);
            curr.next = header.next;
            header.next = curr;
            carry = sum / 10;
        }
        return header.next;
    }
}
// @lc code=end


// 翻转 + 2.两数相加
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        return reverseList(add(l1, l2));
    }

    ListNode add(ListNode l1, ListNode l2) {
        ListNode header = new ListNode();
        ListNode p = header, p1 = l1, p2 = l2;
        int carry = 0;
        while(p1 != null || p2 != null || carry != 0) {
            int sum = carry;
            if(p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }
            if(p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }
            p.next = new ListNode(sum % 10);
            p = p.next;
            carry = sum / 10;
        }
        return header.next;
    }
    
    ListNode reverseList(ListNode head) {
        ListNode header = new ListNode();
        ListNode curr = head;
        // 头插法
        while(curr != null) {
            ListNode temp = curr.next;
            curr.next = header.next;
            header.next = curr;
            curr = temp;
        }
        return header.next;
    }
}