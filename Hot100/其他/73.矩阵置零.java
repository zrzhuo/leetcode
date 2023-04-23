/*
 * @lc app=leetcode.cn id=73 lang=java
 *
 * [73] 矩阵置零
 */

// @lc code=start
// 选择某个0, 用该0所在的行和列, 作为辅助记录空间, 这样就无需额外空间
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 获取一个0的位置
        int r = -1, c = -1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    r = i;
                    c = j;
                }
            }
        }
        // matrix中不存在0, 无需处理
        if(r == -1)
            return;
        // 利用r行和c列中做标记
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) { 
                if(matrix[i][j] == 0) {
                    matrix[i][c] = 0; // 为i行做标记
                    matrix[r][j] = 0; // 为j列做标记
                }
            }
        }
        // 根据r行和c列的标记进行置0处理
        for(int i = 0; i < m; i++) {
            if(i == r)
                continue; // 跳过r行
            if(matrix[i][c] == 0)
                doRow(matrix, i);
        }
        for(int j = 0; j < n; j++) {
            if(j == c)
                continue; // 跳过c列
            if(matrix[r][j] == 0)
                doCol(matrix, j);
        }
        // 将r行和c列进行置0处理
        doRow(matrix, r);
        doCol(matrix, c);
    }

    void doRow(int[][] matrix, int row) {
        for(int j = 0; j < matrix[0].length; j++) {
            matrix[row][j] = 0;
        }
    }
    void doCol(int[][] matrix, int col) {
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }
}
// @lc code=end

