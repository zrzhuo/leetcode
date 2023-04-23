/*
 * @lc app=leetcode.cn id=48 lang=java
 *
 * [48] 旋转图像
 */

// @lc code=start
class Solution {
    public void rotate(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 先上下对称
        int up = 0, down = m - 1;
        while(up < down) {
            for(int j = 0; j < n; j++) {
                swap(matrix, up, j, down, j);
            }
            up++;
            down--;
        }
        // 再主对角线对称
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    void swap(int[][] matrix, int r1, int c1, int r2, int c2) {
        int temp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = temp;
    }
}
// @lc code=end


先上下对称, 再主对角线对称
1 2 3       7 8 9       7 4 1
4 5 6  -->  4 5 6  -->  8 5 2
7 8 9       1 2 3       9 6 3

先左右对称, 再副对角线对称
1 2 3       3 2 1       7 4 1
4 5 6  -->  6 5 4  -->  8 5 2
7 8 9       9 8 7       9 6 3

先主对角线对称, 再左右对称
1 2 3       1 4 7       7 4 1
4 5 6  -->  2 5 8  -->  8 5 2
7 8 9       3 6 9       9 6 3

先副对角线对称, 再上下对称
1 2 3       9 6 3       7 4 1
4 5 6  -->  8 5 2  -->  8 5 2
7 8 9       7 4 1       9 6 3

