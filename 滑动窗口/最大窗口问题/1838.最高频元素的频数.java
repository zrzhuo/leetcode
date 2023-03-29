/*
 * @lc app=leetcode.cn id=1838 lang=java
 *
 * [1838] 最高频元素的频数
 */

// @lc code=start
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        // 滑动窗口[left, right), 最大窗口问题
        int left = 0, right = 0, n = nums.length, ans = 0;
        // 定义条件指标: 当前窗口中所有数的和
        int sum = 0;
        while(right < n) {
            // 移动left直到恰好满足条件
            while(left < right) {
                if(right > 0 && nums[right - 1] * (right - left) - sum <= k)
                    break;
                sum -= nums[left];
                left++;
            }
            // 移动right直到恰好不满足条件
            while(right < n) {
                if(right > 0 && nums[right - 1] * (right - left) - sum > k)
                    break;
                sum += nums[right];
                right++;
            }
            // 当前满足条件的窗口为[left, right - 1)
            ans = Math.max(ans, right - 1 - left);
        }
        // 末尾特殊处理
        if(right > 0 && nums[right - 1] * (right - left) - sum <= k)
            ans = Math.max(ans, n - left);
        return ans;
    }
}
// @lc code=end
