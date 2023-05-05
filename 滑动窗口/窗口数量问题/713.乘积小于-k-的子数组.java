/*
 * @lc app=leetcode.cn id=713 lang=java
 *
 * [713] 乘积小于 K 的子数组
 */


记prod为窗口[left, right)中所有数的乘积. 由于nums[i]均为正整数, 故:
    1. left固定时, 右移right则prod不变或增大
    2. right固定时, 右移left则prod不变或减小

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
            // 移动right
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



