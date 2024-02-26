/*
 * @lc app=leetcode.cn id=1368 lang=java
 *
 * [1368] 使网格图至少有一条有效路径的最小代价
 */

// @lc code=start
class Solution {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final int INF = Integer.MAX_VALUE;
    public int minCost(int[][] grid) {
        return dijkstra(grid);
    }

    private int dijkstra(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dist = new int[m][n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        // 初始化
        for(int i = 0; i < m; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = 0;
        heap.offer(new int[]{0, 0, 0});
        while(!heap.isEmpty()) {
            // 处理堆顶结点: 该结点若为已确定结点, 则跳过, 若为未确定结点, 则标记为已确定结点, 并处理其后续结点
            int[] curr = heap.poll();
            int row = curr[0], col = curr[1];
            if(visited[row][col])
                continue;
            visited[row][col] = true; // 记录为已确定结点
            // 遍历curr的所有"未确定后续结点", 更新这些结点的当前路径长度
            for(int i = 0; i < 4; i++) {
                int r = row + DIRS[i][0], c = col + DIRS[i][1];
                if(r >= 0 && r < m && c >= 0 && c < n && !visited[r][c]) {
                    // 方向符合grid[row][col]则代价为0, 不符合则代价为1
                    int newDist = dist[row][col] + (grid[row][col] == i + 1 ? 0 : 1);
                    if(newDist < dist[r][c]) {
                        dist[r][c] = newDist;
                        heap.offer(new int[]{r, c, newDist});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}
// @lc code=end

