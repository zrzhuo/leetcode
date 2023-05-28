/*
 * @lc app=leetcode.cn id=221 lang=java
 *
 * [221] 最大正方形
 */

// @lc code=start
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // dp[i][j]: 以(i, j)为右下角的正方形的最大边长
        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = matrix[0][0] - '0';
        for(int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
        }
        for(int j = 1; j < n; j++) {
            dp[0][j] = matrix[0][j] - '0';
        }
        // 递推
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        // 获取结果
        int maxLen = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen * maxLen;
    }
}
// @lc code=end

