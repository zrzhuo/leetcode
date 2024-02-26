/*
 * @lc app=leetcode.cn id=882 lang=java
 *
 * [882] 细分图中的可到达节点
 */

// @lc code=start
// 题解: https://leetcode.cn/problems/reachable-nodes-in-subdivided-graph/solutions/1991509/tu-jie-zhuan-huan-cheng-dan-yuan-zui-dua-6l8o/
class Solution {
    private static final int INF = Integer.MAX_VALUE;
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        // 建图
        List<int[]>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2] + 1});
            graph[edge[1]].add(new int[]{edge[0], edge[2] + 1});
        }
        // dijkstra
        int[] dist = dijkstra(graph, 0);
        // 获取结果
        int res = 0;
        for(int i = 0; i < n; i++) {
            if(dist[i] <= maxMoves)
                res++;
        }
        for(int[] edge : edges) {
            int u = edge[0], v = edge[1], d = edge[2];
            int d1 = Math.max(0, maxMoves - dist[u]);
            int d2 = Math.max(0, maxMoves - dist[v]);
            res += Math.min(d, d1 + d2);
        }
        return res;
    }

    private int[] dijkstra(List<int[]>[] graph, int start) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // 初始化
        Arrays.fill(dist, INF);
        dist[start] = 0;
        heap.offer(new int[]{start, 0});
        // 处理堆顶结点: 该结点若为已确定结点, 则跳过, 若为未确定结点, 则标记为已确定结点, 并处理其后续结点
        while(!heap.isEmpty()) {
            int curr = heap.poll()[0];
            if(visited[curr])
                continue;
            visited[curr] = true; // 记录为已确认结点
            // 遍历curr的所有"未确定后续结点", 更新这些结点当前路径长度
            for(int[] node : graph[curr]) {
                int next = node[0];
                if(!visited[next]) {
                    int newDist = dist[curr] + node[1];
                    if(newDist < dist[next]) {
                        dist[next] = newDist;
                        heap.offer(new int[]{next, newDist}); // 加入堆
                    } 
                }
            }
        }
        return dist;
    }
}
// @lc code=end

