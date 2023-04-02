/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 */

// @lc code=start
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        // 最小窗口问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = nums.length, ans = n + 1;
        // 定义条件指标: 当前窗口中的所有数字之和
        int sum = 0;
        // 滑动
        while(right < n) {
            // 移动right直到恰好满足条件
            while(right < n)  {
                if(sum >= target)
                    break;
                sum += nums[right]; // sum递增
                right++;
            }
            // 移动left直到恰好不满足条件
            while(left < right) {
                if(sum < target)
                    break;
                sum -= nums[left]; // sum递减
                left++;
            }
            // 当前满足条件的窗口为[left-1, right)
            ans = Math.min(ans, right - left + 1);
        }
        // 不存在满足条件的窗口
        if(ans == n + 1)
            return 0;
        return ans;
    }
}
// @lc code=end
