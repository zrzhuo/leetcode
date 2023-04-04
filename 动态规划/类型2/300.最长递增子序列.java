/*
 * @lc app=leetcode.cn id=300 lang=java
 *
 * [300] 最长递增子序列
 */

// @lc code=start
// 动态规划
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n  = nums.length;
        // dp[i]：以nums[i]为结尾的递增子序列的最长长度
        int[] dp = new int[n];
        // 初始化
        Arrays.fill(dp, 1);
        // 递推
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        // 获取结果
        int result = 0;
        for(int i = 0; i < n; i++)
            result = Math.max(result, dp[i]);
        return result;
    }
}
// @lc code=end
