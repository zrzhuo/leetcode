/*
 * @lc app=leetcode.cn id=892 lang=java
 *
 * [892] 三维形体的表面积
 */

// @lc code=start
class Solution {
    public int surfaceArea(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int result = 0;
        // 上下面
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] > 0) {
                    result += 2;
                }
            }
        }
        // 侧面
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // 枚举四个相邻点
                for(int k = 0; k < 4; k++) {
                    int r = i + dirs[k][0], c = j + dirs[k][1];
                    if(r >= 0 && r < m && c >= 0 && c < n) 
                        result += Math.max(0, grid[i][j] - grid[r][c]); // 非边界
                    else
                        result += grid[i][j]; // 边界
                }
            }
        }
        return result;
    }
}
// @lc code=end

