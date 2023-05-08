/*
 * @lc app=leetcode.cn id=1143 lang=java
 *
 * [1143] 最长公共子序列
 */

// 动态规划
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // dp[i][j]: text1[0...i]和text2[0...j]的最长公共子序列的长度，
        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for(int i = 1; i < m; i++)
            dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : dp[i - 1][0];
        for(int j = 1; j < n; j++)
            dp[0][j] = text1.charAt(0) == text2.charAt(j) ? 1 : dp[0][j - 1];
        // 递推
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(text1.charAt(i) == text2.charAt(j)) 
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        } 
        return dp[m - 1][n - 1];
    }
}

// @lc code=start
// 动态规划: 统一形式
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // dp[i][j]: text1[0...i-1]和text2[0...j-1]的最长公共子序列的长度
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        for(int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        // 递推
        for(int i = 1; i <= m; ++i) {
            for(int j = 1; j <= n; ++j) {
                if(text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }
}
// @lc code=end



