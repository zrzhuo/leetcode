/*
 * @lc app=leetcode.cn id=643 lang=java
 *
 * [643] 子数组最大平均数 I
 */

// @lc code=start
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        // 固定窗口问题
        int left = 0, right = 0, n = nums.length;
        double ans = Double.NEGATIVE_INFINITY;
        double sum = 0.0; // 窗口内的数字之和
        // 初始化窗口
        while(right < k) {
            sum += nums[right];
            right++;
        }
        ans = Math.max(ans, sum / k);
        // 滑动
        while(right < n) {
            sum -= nums[left++];
            sum += nums[right++];
            ans = Math.max(ans, sum / k);
        }
        return ans;
    }
}
// @lc code=end

