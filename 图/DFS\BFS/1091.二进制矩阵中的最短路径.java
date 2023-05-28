/*
 * @lc app=leetcode.cn id=1091 lang=java
 *
 * [1091] 二进制矩阵中的最短路径
 */

// @lc code=start
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        final int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) 
            return -1;
        Queue<Integer> que = new LinkedList<>();
        que.offer(0);
        grid[0][0] = 1; // 做已访问标记
        int depth = 0;
        while(!que.isEmpty()) {
            int size = que.size();
            depth++;
            for(int i = 0; i < size; i++) {
                int cur = que.poll();
                int row = cur / n, col = cur % n;
                if(row == n - 1 && col == n - 1)
                    return depth;
                for(int[] d : dirs) {
                    int r = row + d[0], c = col + d[1];
                    if(r >= 0 && r < n && c >= 0 && c < n && grid[r][c] == 0) {
                        que.offer(r * n + c);
                        grid[r][c] = 1; // 做已访问标记
                    }
                }
            }
        }
        return -1;
    }
}
// @lc code=end

