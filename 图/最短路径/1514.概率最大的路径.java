/*
 * @lc app=leetcode.cn id=1514 lang=java
 *
 * [1514] 概率最大的路径
 */

// @lc code=start
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        final double INF = -1.0;
        // 建图: 邻接表
        List<double[]>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(new double[]{edges[i][1], succProb[i]});
            graph[edges[i][1]].add(new double[]{edges[i][0], succProb[i]});
        }
        // dijkstra算法变形
        double[] dist = new double[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<double[]> heap = new PriorityQueue<>((a, b) -> b[1] < a[1] ? -1 : 1); // 大根堆
        Arrays.fill(dist, INF);
        dist[start] = 1.0;
        heap.offer(new double[]{start, 1.0});
        while(!heap.isEmpty()) {
            // 处理堆顶结点: 若该结点为已确定结点, 则跳过, 否则标记为已确定结点, 并处理其后续结点
            int curr = (int) heap.poll()[0];
            if(visited[curr])
                continue;
            visited[curr] = true; // 记录为已确定结点
            // 遍历curr的所有"未确定后续结点", 更新这些结点当前路径长度
            for(double[] node : graph[curr]) {
                int next = (int) node[0];
                if(!visited[next]) {
                    // 根据路径长度的具体定义计算新路径
                    double newDist = dist[curr] * node[1]; // 相乘而不是相加
                    if(newDist > dist[next]) {
                        dist[next] = newDist;  // 更新为更长的路径
                        heap.offer(new double[]{next, newDist}); // 入堆
                    }
                }
            }
        }
        return dist[end] == INF ? 0.0 : dist[end];
    }
}
// @lc code=end

