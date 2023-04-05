/*
 * @lc app=leetcode.cn id=1139 lang=java
 *
 * [1139] 最大的以 1 为边界的正方形
 */

// @lc code=start
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // dp1[i][j]: (i,j)左侧连续1的个数, 包含(i,j)
        // dp2[i][j]: (i,j)上方连续1的个数, 包含(i,j)
        int[][] dp1 = new int[m][n];
        int[][] dp2 = new int[m][n];
        // 初始化
        for (int i = 0; i < m; i++) {
            dp1[i][0] = grid[i][0];
        }
        for (int j = 0; j < n; j++) {
            dp2[0][j] = grid[0][j];
        }
        // 递推
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp1[i][j] = grid[i][j] == 0 ? 0 : dp1[i][j - 1] + 1;
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < m; i++) {
                dp2[i][j] = grid[i][j] == 0 ? 0 : dp2[i - 1][j] + 1;
            }
        }
        // 获取结果
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int maxSide = Math.min(dp1[i][j], dp2[i][j]);
                for (int k = maxSide; k >= 1; k--) {
                    if (dp1[i - k + 1][j] >= k && dp2[i][j - k + 1] >= k) {
                        result = Math.max(result, k);
                        break;
                    }
                }
            }
        }
        return result * result;
    }
}
// @lc code=end
