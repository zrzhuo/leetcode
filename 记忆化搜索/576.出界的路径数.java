/*
 * @lc app=leetcode.cn id=576 lang=java
 *
 * [576] 出界的路径数
 */

// 动态规划
class Solution {
    int mod = 1000000007;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        // dp[k][i][j]: 球位于(i, j)处, 且最多移动k次, 此时出界的路径总数
        int[][][] dp = new int[maxMove + 1][m][n];
        for(int k = 1; k <= maxMove; ++k) {
            for(int i = 0; i < m; ++i) {
                for(int j = 0; j < n; ++j) {
                    int total = 0;
                    // 邻接的四个方向
                    for(int[] d : directions) {
                        int r = i + d[0], c = j + d[1];
                        if(r >= 0 && r < m && c >= 0 && c < n)
                            total = (total + dp[k - 1][r][c]) % mod; // 未出界, 相加
                        else
                            total = (total + 1) % mod; // 出界, 加1
                    }
                    dp[k][i][j] = total;
                }
            }
        }
        return dp[maxMove][startRow][startColumn];
    }
}

// @lc code=start
// 动态规划: 空间优化至二维 + 提前结束循环 + 剪枝
class Solution {
    int mod = 1000000007;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        // dp[k][i][j]: 球位于(i, j)处, 且最多移动k次, 此时出界的路径总数
        int[][] dp = new int[m][n]; // 优化为二维数组
        for(int k = 1; k <= maxMove; ++k) {
            int[][] temp = new int[m][n];
            for(int i = 0; i < m; ++i) {
                for(int j = 0; j < n; ++j) {
                    // 剪枝: 此时的步数不足以走出边界
                    if(k <= i && k < m - i && k <= j && k < n - j)
                        continue;
                    int total = 0;
                    // 邻接的四个方向
                    for(int[] d : directions) {
                        int r = i + d[0], c = j + d[1];
                        if(r >= 0 && r < m && c >= 0 && c < n)
                            total = (total + dp[r][c]) % mod; // 未出界, 相加
                        else
                            total = (total + 1) % mod; // 出界, 加1
                    }
                    temp[i][j] = total;
                    // 提前结束循环
                    if(k == maxMove && i == startRow && j == startColumn)
                        return total;
                }
            }
            dp = temp;
        }
        return dp[startRow][startColumn];
    }
}
// @lc code=end

// 记忆化搜索
class Solution {
    int mod = 1000000007;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 

    int[][][] solved; // 记忆数组
    int solving(int m, int n, int move, int row, int col) {
        // 递归出口
        if(row < 0 || row >= m || col < 0 || col >= n)
            return 1;
        if(move == 0)
            return 0;
        // 获取记忆
        if(solved[move][row][col] != -1)
            return solved[move][row][col]; 
        // 剪枝: 此时的步数不足以走出边界
        if(move <= row && move < m - row && move <= col && move < n - col){
            solved[move][row][col] = 0; 
            return 0;
        }
        // 计算
        int total = 0;
        for(int[] d : directions) {
            int r = row + d[0], c = col + d[1];
            total = (total + solving(m, n, move - 1, r, c)) % mod;
        }
        // 存储记忆
        solved[move][row][col] = total;
        return total;
    }

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        solved = new int[maxMove + 1][m][n];
        for(int k = 0; k <= maxMove; ++k) {
            for(int i = 0; i < m; ++i) {
                for(int j = 0; j < n; ++j){
                    solved[k][i][j] = -1;
                }
            }
        }
        return solving(m, n, maxMove, startRow, startColumn);
    }
}
