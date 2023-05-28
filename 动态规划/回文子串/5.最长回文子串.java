/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        // dp[i][j]: s[i...j]是否是回文子串
        boolean[][] dp = new boolean[n][n];
        // 初始化
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for(int i = 1; i < n; i++) {
            dp[i - 1][i] = s.charAt(i - 1) == s.charAt(i);
        }
        // 递推
        for(int i = n - 1; i >=0; i--) {
            for(int j = i + 2; j < n; j++) {
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1];
            }
        }
        // 获取结果
        int left = -1, right = -1, maxLen = -1;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(dp[i][j] && j - i + 1 > maxLen) {
                    left = i;
                    right = j;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(left, right + 1);
    }
}
// @lc code=end

