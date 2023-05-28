/*
 * @lc app=leetcode.cn id=279 lang=java
 *
 * [279] 完全平方数
 */

// @lc code=start
class Solution {
    public int numSquares(int n) {
        // dp[i]: 总和为i的组合的最小长度
        int[] dp = new int[n + 1];
        // 初始化
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        // 递推: 组合问题, 数字在外, 总和在内
        for(int num = 1; num * num <= n; ++num) {
            for(int i = 1; i <= n; i++) {
                if(i >= num * num)
                    dp[i] = Math.min(dp[i], dp[i - num * num] + 1); // 在总和为i-num*num的组合末尾加上num*num, 即构成总和为i的组合
            }
        } 
        return dp[n];
    }
}
// @lc code=end

