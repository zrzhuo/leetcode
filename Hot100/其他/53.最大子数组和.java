/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子数组和
 */

// @lc code=start
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length, result = Integer.MIN_VALUE;
        // dp[i]: 以nums[i-1]结尾的连续子数组中的最大和
        int[] dp = new int[n + 1];
        // 初始化
        dp[0] = 0;
        // 递推
        for(int i = 1; i <= n; i++) {
            if(dp[i - 1] > 0)
                dp[i] = dp[i - 1] + nums[i - 1];
            else
                dp[i] = nums[i - 1];
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
// @lc code=end

