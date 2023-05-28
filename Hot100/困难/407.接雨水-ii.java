/*
 * @lc app=leetcode.cn id=407 lang=java
 *
 * [407] 接雨水 II
 */

// @lc code=start
class Solution {
    public int trapRainWater(int[][] height) {
        final int[][] dirs = {{-1, 0,}, {1, 0}, {0, 1}, {0, -1}};
        int m = height.length, n = height[0].length;
        // 用最小堆维护边界
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[m][n];
        // 初始边界
        for(int i = 0; i < m; i++) {
            heap.offer(new int[]{i, 0, height[i][0]});
            heap.offer(new int[]{i, n - 1, height[i][n - 1]});
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }
        for(int j = 1; j < n - 1; j++) {
            heap.offer(new int[]{0, j, height[0][j]});
            heap.offer(new int[]{m - 1, j, height[m - 1][j]});
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }
        // 每次选择最矮边界进行扩散
        int result = 0;
        int count = 0, targetCount = (m - 2) * (n - 2); // 内部位置共targetCount个
        while(!heap.isEmpty()) {
            int[] curr = heap.poll();
            int row = curr[0], col = curr[1];
            // 遍历该边界的“相邻内部位置”
            for(int[] d : dirs) {
                int r = row + d[0], c = col + d[1];
                if(r >= 0 && r < m && c >=0 && c < n && !visited[r][c]) {
                    // 当前位置可以存储的雨水体积
                    result += Math.max(0, height[row][col] - height[r][c]); 
                    // 将当前位置作为新的边界
                    height[r][c] = Math.max(height[r][c], height[row][col]); 
                    heap.offer(new int[]{r, c, height[r][c]});
                    visited[r][c] = true;
                    // 所有内部位置计算完毕，提前退出
                    if(++count == targetCount)
                        return result; 
                } 
            }
        }
        return result;
    }
}
// @lc code=end

