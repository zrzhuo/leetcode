/*
 * @lc app=leetcode.cn id=287 lang=java
 *
 * [287] 寻找重复数
 */

// 快慢指针: 类似于寻找环形链表的入口
class Solution {
    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        while(true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(fast == slow)
                break;
        }
        int late = 0;
        while(true) {
            slow = nums[slow];
            late = nums[late];
            if(slow == late)
                break;
        }
        return late;
    }
}

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


// @lc code=start
class Solution {
    // 设g(x)为nums中小于等于x的数的个数
    // f(x) = g(x) - x, 则f(x)具有两段性: 随着x的增大, f(x)由0变为1, 即单调递增
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length;
        int target = 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            int now = func(nums, mid);
            if(now >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }

    int func(int[] nums, int x) {
        int count = 0;
        for(int num : nums) {
            if(num <= x)
                count++;
        }
        return count - x;
    }
}
// @lc code=end

