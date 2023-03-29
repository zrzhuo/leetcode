/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 */

// @lc code=start
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        // 滑动窗口[left, right), 最小窗口问题
        int left = 0, right = 0, n = nums.length, ans = n + 1;
        while(right < n) {
            // 移动right直到恰好满足条件
            while(right < n)  {
                if(sum >= target)
                    break;
                sum += nums[right];
                right++;
            }
            // 移动left直到恰好不满足条件
            while(left < right) {
                if(sum < target)
                    break;
                sum -= nums[left];
                left++;
            }
            // 当前满足条件的窗口为[left-1, right)
            ans = Math.min(ans, right - left + 1);
        }
        return ans != n + 1 ? ans : 0;
    }
}
// @lc code=end
