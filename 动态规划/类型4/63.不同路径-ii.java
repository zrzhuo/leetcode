/*
 * @lc app=leetcode.cn id=63 lang=java
 *
 * [63] 不同路径 II
 */

// @lc code=start
class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // dp[i][j]: 从(0,0)到达(i,j)处的不同路径数
        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = grid[0][0] == 1 ? 0 : 1;
        for(int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] == 1 ? 0 : dp[i - 1][0];
        }
        for(int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] == 1 ? 0 : dp[0][j - 1];
        }
        // 递推
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
// @lc code=end

