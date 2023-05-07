/*
 * @lc app=leetcode.cn id=1631 lang=java
 *
 * [1631] 最小体力消耗路径
 */

// @lc code=start
class Solution {
    public int minimumEffortPath(int[][] heights) {
        final int INF = Integer.MAX_VALUE;
        final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = heights.length, n = heights[0].length;
        // dijkstra算法变形
        int[][] dist = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]); // 小根堆
        for(int i = 0; i < m; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = 0;
        heap.offer(new int[]{0, 0, 0});
        while(!heap.isEmpty()) {
            // 处理堆顶结点: 若该结点为已确定结点, 则跳过, 否则标记为已确定结点, 并处理其后续结点
            int[] curr = heap.poll();
            int row = curr[0], col = curr[1];
            if(visited[row][col])
                continue;
            visited[row][col] = true; // 记录为已确定结点
            // 遍历curr的所有"未确定后续结点", 更新这些结点当前路径长度
            for(int i = 0; i < 4; i++) {
                int r = row + dirs[i][0], c = col + dirs[i][1];
                if(r >= 0 && r < m && c >= 0 && c < n && !visited[r][c]) {
                    // 根据路径长度的具体定义计算新路径
                    int newDist = Math.max(dist[row][col], Math.abs(heights[row][col] - heights[r][c]));
                    if(newDist < dist[r][c]) {
                        dist[r][c] = newDist; // 更新为更短的路径
                        heap.offer(new int[]{r, c, newDist}); // 入堆
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}
// @lc code=end

