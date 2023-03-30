/*
 * @lc app=leetcode.cn id=1004 lang=java
 *
 * [1004] 最大连续1的个数 III
 */

// @lc code=start
class Solution {
    public int longestOnes(int[] nums, int k) {
        // 最大窗口问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = nums.length, ans = 0;
        // 定义条件指标: 当前窗口内0的个数
        int count = 0;
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足条件
            while(left < right) {
                if(count <= k)
                    break;
                if(nums[left] == 0)
                    count--;
                left++;
            }
            // 移动right直到恰好不满足条件
            while(right < n) {
                if(count > k)
                    break;
                if(nums[right] == 0)
                    count++;
                right++;
            }
            // 当前满足条件的窗口为[left, right - 1)
            ans = Math.max(ans, right - 1 - left);
        }
        // 末尾特殊处理
        if(count <= k)
            ans = Math.max(ans, n - left);
        return ans;
    }
}
// @lc code=end

