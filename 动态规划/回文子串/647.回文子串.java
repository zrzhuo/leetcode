/*
 * @lc app=leetcode.cn id=647 lang=java
 *
 * [647] 回文子串
 */

// @lc code=start
class Solution {
    public int countSubstrings(String s) {
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
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 2; j < n; j++) {
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1];
            }
        }
        // 获取结果
        int result = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                result += dp[i][j] ? 1: 0;
            }
        }
        return result;
    }
}
// @lc code=end
