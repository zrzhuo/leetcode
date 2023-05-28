/*
 * @lc app=leetcode.cn id=10 lang=java
 *
 * [10] 正则表达式匹配
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j]: s[0...i-1]与p[0...j-1]是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 初始化
        dp[0][0] = true;
        for(int j = 1; j <= n; j++) {
            if(j % 2 == 0 && p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2]; // 空字符串可以被类似于“a*b*c*”的模式相匹配
        }
        // 递推
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                char chs = s.charAt(i - 1), chp = p.charAt(j - 1);
                if(chp == '*') {
                    char pre = p.charAt(j - 2); // 前一个字符
                    if(pre == '.') {
                        // 向前匹配任意数量(大于0)的任意字符
                        dp[i][j] |= dp[i - 1][j - 2];
                        dp[i][j] |= dp[i - 1][j];

                    } else {
                        // 向前匹配任意数量(大于0)的字符pre
                        dp[i][j] |= (chs == pre && dp[i - 1][j - 2]); 
                        dp[i][j] |= (chs == pre && dp[i - 1][j]);
                    }
                    dp[i][j] |= dp[i][j - 2];  // 向前匹配0个字符
                } else if(chp == '.') {
                    dp[i][j] |= dp[i - 1][j - 1]; // '.'可以匹配任意单个字符
                } else {
                    dp[i][j] |= (chs == chp && dp[i - 1][j - 1]); // 普通字符
                }
            }
        }
        return dp[m][n];
    }
}
// @lc code=end

