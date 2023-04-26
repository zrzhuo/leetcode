/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿数量
 */

// @lc code=start
// dfs
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};    
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }
    void dfs(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        grid[i][j] = '0';
        for(int k = 0; k < 4; k++) {
            int r = i + dirs[k][0], c = j + dirs[k][1];
            // 越界检查
            if(r >= 0 && r < m && c >= 0 && c < n) {
                // 是否未曾遍历
                if(grid[r][c] == '1') {
                    grid[r][c] = '0';
                    dfs(grid, r, c);
                }
            }
        }
    }
}
// @lc code=end


class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};    
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    bfs(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }
    void bfs(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        Queue<Integer> que = new LinkedList<>();
        que.offer(i * n + j);
        grid[i][j] = '0';
        while(!que.isEmpty()) {
            int cur = que.poll();
            int row = cur / n, col = cur % n;
            // 4个方向
            for(int k = 0; k < 4; k++) {
                int r = row + dirs[k][0];
                int c = col + dirs[k][1];
                // 越界检查
                if(r >= 0 && r < m && c >= 0 && c < n) {
                    // 是否未曾遍历过
                    if(grid[r][c] == '1') {
                        que.offer(r * n + c);
                        grid[r][c] = '0';
                    }
                }
            }
        }
    }
}
