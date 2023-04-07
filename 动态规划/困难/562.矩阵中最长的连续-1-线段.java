/*
 * @lc app=leetcode.cn id=562 lang=java
 *
 * [562] 矩阵中最长的连续1线段
 */

// @lc code=start
class Solution {
    public int longestLine(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        // dp[i][j][0]: 以(i-1,j-1)为结尾的"垂直"线段的最长长度
        // dp[i][j][1]: 以(i-1,j-1)为结尾的"水平"线段的最长长度
        // dp[i][j][2]: 以(i-1,j-1)为结尾的"主对角斜线"线段的最长长度
        // dp[i][j][3]: 以(i-1,j-1)为结尾的"副对角斜线"线段的最长长度
        int[][][] dp = new int[m + 1][n + 1][4];
        // 初始化: 全部初始化0
        // 递推
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (mat[i - 1][j - 1] == 1) {
                    dp[i][j][0] = dp[i - 1][j][0] + 1; // 垂直线
                    dp[i][j][1] = dp[i][j - 1][1] + 1; // 水平线
                    dp[i][j][2] = dp[i - 1][j - 1][2] + 1; // 主对角线
                    dp[i][j][3] = j < n ? dp[i - 1][j + 1][3] + 1 : 1; // 副对角线: j==n的情况实际已被主对角线涵盖
                }
            }
        }
        // 获取结果
        int result = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k < 4; k++) {
                    result = Math.max(result, dp[i][j][k]);
                }
            }
        }
        return result;
    }
}
// @lc code=end
