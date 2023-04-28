/*
 * @lc app=leetcode.cn id=329 lang=java
 *
 * [329] 矩阵中的最长递增路径
 */

// @lc code=start
class Solution {
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // solved[i][j]: 从(i, j)处出发的路径中, 最长递增路径的长度
    int[][] solved;
    int solving(int[][] matrix, int row, int col){
        int m = matrix.length, n = matrix[0].length;
        // 获取记忆
        if(solved[row][col] > 0)
            return solved[row][col];
        int result = 1;
        for(int[] direction : directions) {
            int r = row + direction[0], c = col + direction[1];
            if(r >= 0 && r < m && c >= 0 && c < n) {
                // 满足递增路径的要求
                if(matrix[r][c] > matrix[row][col])
                    result = Math.max(result, solving(matrix, r, c) + 1);
            }
        }
        // 存储记忆
        solved[row][col] = result;
        return result;
    }

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        solved = new int[m][n];
        int max = 0;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                max = Math.max(max, solving(matrix, i, j));
            }
        }
        return max;
    }
}
// @lc code=end

