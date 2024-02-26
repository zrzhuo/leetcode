/*
 * @lc app=leetcode.cn id=2290 lang=java
 *
 * [2290] 到达角落需要移除障碍物的最小数目
 */

// @lc code=start
class Solution {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int INF = Integer.MAX_VALUE;
    public int minimumObstacles(int[][] grid) {
        return dijkstra(grid);
    }

    private int dijkstra(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        // 初始化
        for(int i = 0; i < m; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = 0;
        heap.offer(new int[]{0, 0, 0});
        while(!heap.isEmpty()) {
            // 处理堆顶结点: 若该结点为已确定结点, 则跳过, 否则标记为已确定结点, 并处理其后续结点
            int[] curr = heap.poll();
            int row = curr[0], col = curr[1], d = curr[2];
            if(visited[row][col])
                continue;
            visited[row][col] = true; // 记录为已确定结点
            // 遍历curr的所有"未确定后续结点", 更新这些结点当前路径长度
            for(int[] dir : DIRS) {
                int r = row + dir[0], c = col + dir[1];
                if(r >= 0 && r < m && c >= 0 && c < n && !visited[r][c]) {
                    int newDist = dist[row][col] + grid[r][c];
                    if(newDist < dist[r][c]) {
                        dist[r][c] = newDist;  // 更新为更短的路径
                        heap.offer(new int[]{r, c, dist[r][c]}); // 入堆
                    }
                }
            } 
        }
        return dist[m - 1][n - 1];
    }
}
// @lc code=end

