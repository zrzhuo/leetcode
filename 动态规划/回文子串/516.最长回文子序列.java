/*
 * @lc app=leetcode.cn id=516 lang=java
 *
 * [516] 最长回文子序列
 */

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


// @lc code=start
// 转化为求最长公共子序列的问题, 效率并不好
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        String t = sb.reverse().toString();
        // dp[i][j]: s[0...i-1]和t[0...j-1]的最长公共子序列的长度
        int[][] dp = new int[n + 1][n + 1];
        // 初始化
        for(int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        // 递推
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n][n];
    }
}
// @lc code=end