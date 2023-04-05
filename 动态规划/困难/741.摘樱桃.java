/*
 * @lc app=leetcode.cn id=741 lang=java
 *
 * [741] 摘樱桃
 */

// @lc code=start
class Solution {
    public int cherryPickup(int[][] grid) {
        // 从(N-1,N-1)返回(0,0), 等价于从(0,0)到(N-1,N-1)的路径, 
        // 因此问题等价于, 两个人从(0,0)出发, 向下或向右走到(N−1,N−1)时, 摘到的樱桃个数之和的最大值.
        // 不妨假设A、B两人是同步的, 即k时刻, 其坐标分别为(x1,y1)和(x2,y2), 且x1+y1=x2+y2=k
        int n = grid.length;
        // dp[k][x1][x2]: 当A在(x1, y1), B在(x2, y2)，且满足x1+y1=x2+y2=k时, 摘到的最大樱桃个数
        int[][][] dp = new int[2 * n - 1][n][n];
        // 初始化
        for(int k = 0; k < 2 * n - 1; k++) {
            for(int x1 = 0; x1 < n; x1++) {
                for(int x2 = 0; x2 < n; x2++) {
                    dp[k][x1][x2] = Integer.MIN_VALUE;
                }
            }
        }
        dp[0][0][0] = grid[0][0];
        // 递归
        for(int k = 1; k < 2 * n - 1; k++) {
            for(int x1 = 0; x1 < n; x1++) {
                for(int x2 = 0; x2 < n; x2++) {
                    int y1 = k - x1, y2 = k - x2;
                    // 判断是否越界
                    if(y1 < 0 || y1 >= n || y2 < 0 || y2 >= n)
                        continue; 
                    // 判断A(x1, y1)和B(x2, y2)处是否为荆棘
                    if( grid[x1][y1] == -1 || grid[x2][y2] == -1)
                        continue;
                    // 确定当前可摘取的樱桃
                    int cherry1 = -1, cherry2 = -1;
                    if(x1 != x2) {
                        cherry1 = grid[x1][y1];
                        cherry2 = grid[x2][y2];
                    } else {
                        cherry1 = grid[x1][y1];
                        cherry2 = 0; // 此时A(x1, y1)和B(x2, y2)重合, 只能摘取一次樱桃
                    }
                    // 四种前置情况, 取最大值
                    int a = dp[k - 1][x1][x2];
                    int b = x1 > 0 ? dp[k - 1][x1 - 1][x2] : Integer.MIN_VALUE;;
                    int c = x2 > 0 ? dp[k - 1][x1][x2 - 1] : Integer.MIN_VALUE;;
                    int d = (x1 > 0 && x2 > 0) ? dp[k - 1][x1 - 1][x2 - 1] : Integer.MIN_VALUE;;
                    dp[k][x1][x2] = Math.max(Math.max(a, b), Math.max(c, d)) + cherry1 + cherry2;
                }
            } 
        }
        return Math.max(dp[2 * n - 2][n - 1][n - 1], 0);
    }
}
// @lc code=end

