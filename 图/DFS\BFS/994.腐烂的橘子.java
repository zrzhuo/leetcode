/*
 * @lc app=leetcode.cn id=994 lang=java
 *
 * [994] 腐烂的橘子
 */

// @lc code=start
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int fresh = 0; // 记录新鲜橘子的数量
        // 多源bfs, 源点入队
        Queue<Integer> que = new LinkedList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1)
                    fresh++;
                if(grid[i][j] == 2)
                    que.offer(i * n + j); // 腐烂的橘子入队
            }
        }
        // 一开始就没有新鲜橘子
        if(fresh == 0)
            return 0;
        int count = -1; // 轮数
        while(!que.isEmpty()) {
            // 多源bfs要求每轮循环遍历队列中所有结点, 以保持多源点之间的同步
            int size = que.size(); 
            for(int i = 0; i < size; i++) {
                int cur = que.poll();
                int row = cur / n, col = cur % n;
                for(int[] d : dirs) {
                    int r = row + d[0], c = col + d[1];
                    // 越界判断
                    if(r >= 0 && r < m && c >= 0 && c < n) {
                        // 新鲜橘子在本轮bfs中被腐烂
                        if(grid[r][c] == 1) {
                            que.offer(r * n + c);
                            grid[r][c] = 2; 
                            fresh--;
                        }
                    }
                }
            }
            count++;
        }
        return fresh == 0 ? count : -1;
    }
}
// @lc code=end

