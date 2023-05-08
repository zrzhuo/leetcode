/*
 * @lc app=leetcode.cn id=1312 lang=java
 *
 * [1312] 让字符串成为回文串的最少插入次数
 */

class Solution {
    // solved[i][j]: 使字符串s[i...j]成为回文串需要的最少插入次数
    int[][] solved;
    int solving(String s, int i, int j) {
        // 获取记忆
        if(solved[i][j] != -1)
            return solved[i][j];
        // 已经是回文串, 无需再插入字符, 返回0
        if(i >= j) {
            solved[i][j] = 0;
            return 0;
        }
        int result = Integer.MAX_VALUE;
        result = Math.min(result, 1 + solving(s, i, j - 1)); // 在字符串s[i...j]左侧添加s[j], 并将字符串s[i...j-1]变为回文串 
        result = Math.min(result, 1 + solving(s, i + 1, j)); // 在字符串s[i...j]右侧添加s[i], 并将字符串s[i+1...j]变为回文串 
        if(s.charAt(i) == s.charAt(j))
            result = Math.min(result, solving(s, i + 1, j - 1)) ; // s[i]==s[j]时, 只需要将字符串s[i+1...j-1]变为回文串即可
        // 存储记忆
        solved[i][j] = result;
        return result;
    }

    public int minInsertions(String s) {
        int n = s.length();
        solved = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(solved[i], -1);
        }
        return solving(s, 0, n - 1);
    }
}

// @lc code=start
// 动态规划
class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        // dp[i][j]: 使字符串s[i...j]成为回文串需要的最少插入次数
        int[][] dp = new int[n][n];
        // 初始化
        for(int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        // 递推
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                int min = Integer.MAX_VALUE;
                min = Math.min(min, 1 + dp[i][j - 1]); // 在字符串s[i...j]左侧添加s[j], 并将字符串s[i...j-1]变为回文串 
                min = Math.min(min, 1 + dp[i + 1][j]); // 在字符串s[i...j]右侧添加s[i], 并将字符串s[i+1...j]变为回文串 
                if(s.charAt(i) == s.charAt(j))
                    min = Math.min(min, dp[i + 1][j - 1]); // s[i]==s[j]时, 只需要将字符串s[i+1...j-1]变为回文串即可
                dp[i][j] = min;
            }
        }
        return dp[0][n - 1];
    }
}
// @lc code=end

