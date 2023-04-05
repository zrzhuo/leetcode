/*
 * @lc app=leetcode.cn id=516 lang=java
 *
 * [516] 最长回文子序列
 */

// @lc code=start
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // dp[i][j]: s[i...j]的最长回文子串的长度
        int[][] dp = new int[n][n];
        // 初始化
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for(int i = 1; i < n; i++) {
            dp[i - 1][i] = s.charAt(i - 1) == s.charAt(i) ? 2: 1;
        }
        // 递推
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 2; j < n; j++) {
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }
}
// @lc code=end

