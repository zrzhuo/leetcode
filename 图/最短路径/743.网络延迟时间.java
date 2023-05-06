/*
 * @lc app=leetcode.cn id=743 lang=java
 *
 * [743] 网络延迟时间
 */

// @lc code=start
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE;
        // 建图: 邻接矩阵
        int[][] graph = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for(int[] time : times) {
            graph[time[0] - 1][time[1] - 1] = time[2];
        }
        // dijkstra算法: 堆优化
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]); // 小根堆, 用于存放未确定结点, 堆顶是当前路径长度最小的未确定结点
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        heap.offer(new int[]{k - 1, 0});
        while(!heap.isEmpty()) {
            // 处理堆顶结点: 若该结点为已确定结点, 则跳过, 否则标记为已确定结点, 并处理其后续结点
            int curr = heap.poll()[0];
            if(visited[curr])
                continue;
            visited[curr] = true; // 记录为已确定结点
            // 遍历curr的所有"未确定后续结点", 更新这些结点当前路径长度
            for(int next = 0; next < n; next++) {
                if(graph[curr][next] < INF && !visited[next]) {
                    int newDist = dist[curr] + graph[curr][next];
                    if(newDist < dist[next]) {
                        dist[next] = newDist; // 更新为更短的路径
                        heap.offer(new int[]{next, newDist});
                    }
                }
            }
        }
        // 获取结果
        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, dist[i]);
        }
        return max == INF ? -1 : max;
    }
}
// @lc code=end


class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE;
        // 建图: 邻接矩阵
        int[][] graph = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for(int[] time : times) {
            graph[time[0] - 1][time[1] - 1] = time[2];
        }
        // dijkstra算法
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        for(int i = 0; i < n; i++) {
            // 从"未曾确定最短路径的结点"中, 选择当前路径长度最短的顶点, 该结点的当前路径长度即为其最短路径长度
            int curr = -1, min = INF;
            for(int node = 0; node < n; node++) {
                if(!visited[node] && dist[node] < min) {
                    curr = node;
                    min = dist[node];
                }
            }
            // 所有"未曾确定最短路径的结点"都不可达, 结束
            if(curr == -1)
                break;
            // 记录为"已经确定最短路径的结点"
            visited[curr] = true;
            // 遍历curr的所有"未曾确定最短路径的后续结点", 更新这些节点当前路径长度
            for(int next = 0; next < n; next++) {
                if(graph[curr][next] < INF && !visited[next]) {
                    dist[next] = Math.min(dist[next], dist[curr] + graph[curr][next]); // 更新为更短的路径
                }
            }
        }
        // 获取结果
        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, dist[i]);
        }
        return max == INF ? -1 : max;
    }
}