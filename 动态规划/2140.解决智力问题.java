/*
 * @lc app=leetcode.cn id=2140 lang=java
 *
 * [2140] 解决智力问题
 */

// @lc code=start
class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        // dp[i]: 解决问题[i...n-1]可以获得的最高分
        long[] dp = new long[n];
        // 初始化
        dp[n - 1] = questions[n - 1][0];
        // 递推
        for(int i = n - 2; i >= 0; i--) {
            // 解决问题i
            long s1 = i + questions[i][1] + 1 < n ? questions[i][0] + dp[i + questions[i][1] + 1] : questions[i][0];
            // 不解决问题i
            long s2 = dp[i + 1];
            dp[i] = Math.max(s1, s2);
        }
        return dp[0];
    }
}
// @lc code=end

