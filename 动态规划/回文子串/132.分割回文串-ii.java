/*
 * @lc app=leetcode.cn id=132 lang=java
 *
 * [132] 分割回文串 II
 */

// @lc code=start
class Solution {
    public int minCut(String s) {
        int n = s.length();
        // is[i][j]: s[i...j]是否是回文串
        boolean[][] isTrue = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            isTrue[i][i] = true;
        }
        for(int i = 0; i < n - 1; i++) {
            isTrue[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 2; j < n; j++) {
                isTrue[i][j] = (isTrue[i + 1][j - 1] && s.charAt(i) == s.charAt(j));
            }
        }
        // dp[i]: s[0...i]需要的分割次数
        int[] dp = new int[n];
        dp[0] = 0;
        for(int i = 1; i < n; i++) {
            if(isTrue[0][i])
                continue; // s[0...i]是回文串，不用分割
            dp[i] = Integer.MAX_VALUE;
            for(int j = 1; j <= i; j++) {
                if(isTrue[j][i])
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1); // s[j...i]是回文串，将s[0...i]分割为s[0...j-1]和s[j...i]
            }
        }
        return dp[n - 1];
    }
}
// @lc code=end

