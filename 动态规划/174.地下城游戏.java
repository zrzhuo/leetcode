/*
 * @lc app=leetcode.cn id=174 lang=java
 *
 * [174] 地下城游戏
 */

// 动态规划
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        // dp[i][j]: 从(i,j)到终点(m-1,n-1)所需要的最低血量
        int[][] dp = new int[m][n];
        // 初始化
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] > 0 ? 1 : 1 - dungeon[m - 1][n - 1];
        for(int i = m - 2; i >= 0; i--) {
            // 逻辑同递归时一致
            dp[i][n - 1] = dungeon[i][n - 1] >= dp[i + 1][n - 1] ? 1 : dp[i + 1][n - 1] - dungeon[i][n - 1];
        }
        for(int j = n - 2; j >= 0; j--) {
            // 逻辑同递归时一致
            dp[m - 1][j] = dungeon[m - 1][j] >= dp[m - 1][j + 1] ? 1 : dp[m - 1][j + 1] - dungeon[m - 1][j];
        }
        // 递推
        for(int i = m - 2; i >= 0; i--) {
             for(int j = n - 2; j >= 0; j--) {
                int pre = Math.min(dp[i + 1][j], dp[i][j + 1]); // 取所需初始血量较小的路径
                // 当前格子为正时, 补充血量
                if(dungeon[i][j] > 0) {
                    if(dungeon[i][j] >= pre)
                        dp[i][j] = 1; // 当前格子加的血量就已经足够救出公主, 只需要初始1滴血保证活着即可
                    else
                        dp[i][j] = pre - dungeon[i][j]; // 由于当前格子可以补充血量, 所有可以减少初始血量
                }
                // 当前格子为负时, 扣除血量
                else {
                    dp[i][j] = pre - dungeon[i][j]; // 由于当前格子要扣除血量, 所有需要增加初始血量
                }
             }
        }
        return dp[0][0];
    }
}

// @lc code=start
// 动态规划: 统一形式
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        // dp[i][j]: 从(i,j)到终点(m-1,n-1)所需要的最低血量
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        for(int i = m - 2; i >= 0; i--) {
            dp[i][n] = Integer.MAX_VALUE;
        }
        for(int j = n - 2; j >= 0; j--) {
            dp[m][j] = Integer.MAX_VALUE;
        }
        // 递推
        for(int i = m - 1; i >= 0; i--) {
             for(int j = n - 1; j >= 0; j--) {
                int pre = Math.min(dp[i + 1][j], dp[i][j + 1]); // 取所需初始血量较小的路径
                // 当前格子为正时, 补充血量
                if(dungeon[i][j] > 0) {
                    if(dungeon[i][j] >= pre)
                        dp[i][j] = 1; // 当前格子加的血量就已经足够救出公主, 只需要初始1滴血保证活着即可
                    else
                        dp[i][j] = pre - dungeon[i][j]; // 由于当前格子可以补充血量, 所有可以减少初始血量
                }
                // 当前格子为负时, 扣除血量
                else {
                    dp[i][j] = pre - dungeon[i][j]; // 由于当前格子要扣除血量, 所有需要增加初始血量
                }
             }
        }
        return dp[0][0];
    }
}
// @lc code=end