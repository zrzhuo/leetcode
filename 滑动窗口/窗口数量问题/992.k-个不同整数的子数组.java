/*
 * @lc app=leetcode.cn id=992 lang=java
 *
 * [992] K 个不同整数的子数组
 */

// @lc code=start
class Solution {
    // 求nums中"不同整数个数小于k的子数组"的个数
    int numsLessThan(int[] nums, int k) {
        // 窗口数量问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = nums.length, ans = 0;
        // 定义条件指标: 当前子数组中不同整数的个数
        int count = 0;
        int[] counter = new int[n + 1];
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足要求
            while(left < right) {
                if(count < k)
                    break;
                if(--counter[nums[left]] == 0)
                    count--;
                left++;
            }
            // 累计满足要求的区间个数
            ans += right - left;
            if(++counter[nums[right]] == 1)
                count++;
            right++;
        }
        // 末尾特殊处理, 此时right == n
        while(left < right) {
            if(count < k)
                break;
            if(--counter[nums[left]] == 0)
                count--;
            left++;
        }
        ans += right - left;
        return ans;
    }
    public int subarraysWithKDistinct(int[] nums, int k) {
        return numsLessThan(nums, k + 1) - numsLessThan(nums, k);
    }
}
// @lc code=end

