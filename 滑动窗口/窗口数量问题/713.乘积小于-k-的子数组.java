/*
 * @lc app=leetcode.cn id=713 lang=java
 *
 * [713] 乘积小于 K 的子数组
 */

// @lc code=start
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 窗口数量问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = nums.length, ans = 0;
        // 定义条件指标: 当前窗口中所有数的积
        int prod = 1;
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足要求
            while(left < right) {
                if(prod < k)
                    break;
                prod /= nums[left];
                left++;
            }
            // 累计满足要求的区间个数
            ans += right - left;
            prod *= nums[right];
            right++;
        }
        // 末尾特殊处理, 此时right == n
        while(left < right) {
            if(prod < k)
                break;
            prod /= nums[left];
            left++;
        }
        ans += right - left;
        return ans;
    }
}
// @lc code=end

