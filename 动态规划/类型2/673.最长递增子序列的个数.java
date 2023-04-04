/*
 * @lc app=leetcode.cn id=673 lang=java
 *
 * [673] 最长递增子序列的个数
 */

// @lc code=start
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]：以nums[i]为结尾的递增子序列的最长长度
        int[] dp = new int[n];
        int[] counter = new int[n]; // counter[i]: 以nums[i]结尾的最长递增子序列的个数
        // 初始化
        Arrays.fill(dp, 1);
        Arrays.fill(counter, 1);
        // 递推
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    if(dp[j] + 1 == dp[i]) {
                        counter[i] += counter[j]; // 累计counter
                    }
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1; // 最大长度更新
                        counter[i] = counter[j]; // 最大长度更新, 重置counter
                    }
                }
                    
            }
        }
        // 获取结果
        int maxLen = 0;
        for(int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, dp[i]); 
        }
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(dp[i] == maxLen)
                result += counter[i];
        }
        return result;
    }
}
// @lc code=end

