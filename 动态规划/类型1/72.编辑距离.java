/*
 * @lc app=leetcode.cn id=72 lang=java
 *
 * [72] 编辑距离
 */

// 动态规划
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if(m == 0)
            return n;
        if(n == 0)
            return m;
        // dp[i][j]，表示word1[0...i]与word2[0...j]的编辑距离
        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
        for(int i = 1; i < m; i++)
            dp[i][0] = word1.charAt(i) == word2.charAt(0) ? i : dp[i - 1][0] + 1;
        for(int j = 1; j < n; j++)
            dp[0][j] = word1.charAt(0) == word2.charAt(j) ? j : dp[0][j - 1] + 1;
        // 递推
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 只需将word1[0...i-1]变为word2[0...j-1]即可
                } else {
                    int a = 1 + dp[i - 1][j]; // word1[0...i]删除最后一个字符, 变为word1[0..i-1], 再由word1[0...i-1]变为word2[0...j]
                    int b = 1 + dp[i][j - 1]; // word2[0...j]删除最后一个字符, 变为word2[0..j-1], 再由word2[0...j-1]变为word1[0...i]
                    int c = dp[i - 1][j - 1] + 1; // 将word1[0...i-1]变为word2[0...j-1], 再进行一次替换, 使得word1[i] == word2[j]
                    dp[i][j] = Math.min(Math.min(a, b), c);
                }
            }
        } 
        return dp[m - 1][n - 1];
    }
}

// @lc code=start
// 动态规划: 统一形式
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // dp[i][j]，表示word1[0...i-1]与word2[0...j-1]的编辑距离
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        dp[0][0] = 0;
        for(int i = 1; i <= m; i++)
            dp[i][0] = i;
        for(int j = 1; j <= n; j++)
            dp[0][j] = j;
        // 递推
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int a = 1 + dp[i - 1][j]; 
                    int b = 1 + dp[i][j - 1]; 
                    int c = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(a, b), c);
                }
            }
        } 
        return dp[m][n];
    }
}
// @lc code=end