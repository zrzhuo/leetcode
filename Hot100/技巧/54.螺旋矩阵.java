/*
 * @lc app=leetcode.cn id=54 lang=java
 *
 * [54] 螺旋矩阵
 */

// @lc code=start
class Solution {
    List<Integer> result;
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        result = new ArrayList<>(m * n);
        // 该矩阵的层数
        int level = Math.min(m + 1, n  + 1) / 2; 
        // 遍历每一层
        for(int i = 0; i < level; i++) {
            doCircle(matrix, i);
        }
        return result;
    }

    // 遍历第k层
    void doCircle(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        // 定界
        int up = k, down = m - 1 - k;
        int left = k, right = n - 1 - k;
        // 当前层是个矩形
        if(up != down && left != right) {
            for(int j = left; j < right; j++) {
                result.add(matrix[up][j]); // 左上角 -> 右上角
            }
            for(int i = up; i < down; i++) {
                result.add(matrix[i][right]); // 右上角 -> 右下角
            }
            for(int j = right; j > left; j--) {
                result.add(matrix[down][j]); // 右下角 -> 左下角
            }
            for(int i = down; i > up; i--) {
                result.add(matrix[i][left]); // 左下角 -> 左上角
            }
        }
        // 当前层是个横线
        else if (up == down) {
            for(int j = left; j <= right; j++) {
                result.add(matrix[up][j]); // 左到右
            }
        }
        // 当前层是个竖线
        else if(left == right) {
            for(int i = up; i <= down; i++) {
                result.add(matrix[i][right]); // 上到下
            }
        }
        // 当前层是个点
        else {
            result.add(matrix[up][left]); // 单独一个点
        }
    }
}
// @lc code=end

