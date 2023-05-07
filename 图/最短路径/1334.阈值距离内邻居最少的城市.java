/*
 * @lc app=leetcode.cn id=1334 lang=java
 *
 * [1334] 阈值距离内邻居最少的城市
 */

// @lc code=start
// dijsktra算法求最短路径, 区分为稠密图和稀疏图两种情况
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        final int INF = Integer.MAX_VALUE;
        int m = edges.length;
        int[][] dist = new int[n][];
        if(m < n * Math.log(n)) {
            // 稀疏图
            // 建图: 邻接表
            List<int[]>[] graph = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for(int[] edge : edges) {
                graph[edge[0]].add(new int[]{edge[1], edge[2]});
                graph[edge[1]].add(new int[]{edge[0], edge[2]});
            }
            // dijkstra_sparse
            for(int i = 0; i < n; i++) {
                dist[i] = dijkstra_sparse(graph, i);
            }
        } else {
            // 稠密图
            // 建图: 邻接矩阵
            int[][] graph = new int[n][n];
            for(int i = 0; i < n; i++) {
                Arrays.fill(graph[i], INF);
            }
            for(int[] edge : edges) {
                graph[edge[0]][edge[1]] = edge[2];
                graph[edge[1]][edge[0]] = edge[2];
            }
            // dijkstra_dense
            for(int i = 0; i < n; i++) {
                dist[i] = dijkstra_dense(graph, i);
            }
        }
        // 获取结果
        int result = -1, min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int count = 0;
            for(int j = 0; j < n; j++) {
                if(dist[i][j] <= distanceThreshold)
                    count++;
            }
            if(count <= min) {
                min = count;
                result = i;
            }
        }
        return result;
    }
    
    int[] dijkstra_dense(int[][] graph, int start) {
        final int INF = Integer.MAX_VALUE;
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        // 初始化
        Arrays.fill(dist, INF);
        dist[start] = 0;
        // 迭代n次, 每次可以求出一个结点的最短路径
        for(int i = 0; i < n; i++) {
            // 从未确定结点中, 选择当前路径长度最短的顶点, 将其转化为已确定结点
            int curr = -1;
            int min = INF;
            for(int node = 0; node < n; node++) {
                if(!visited[node] && dist[node] < min) {
                    curr = node;
                    min = dist[node];
                }
            }
            // 所有未确定都不可达, 结束
            if(curr == -1)
                break;
            visited[curr] = true; // 记录为已确定结点
            // 遍历curr的所有"未确定后续结点", 更新这些结点的当前路径长度
            for(int next = 0; next < n; next++) {
                if(graph[curr][next] < INF && !visited[next])
                    dist[next] = Math.min(dist[next], dist[curr] + graph[curr][next]); // 更新为更短的路径
            }
        }
        return dist;
    }

    int[] dijkstra_sparse(List<int[]>[] graph, int start) {
        final int INF = Integer.MAX_VALUE;
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);  // 小根堆, 用于存放未确定结点, 堆顶是当前路径长度最小的未确定结点
        // 初始化
        Arrays.fill(dist, INF);
        dist[start] = 0;
        heap.offer(new int[]{start, 0});
        // 处理堆顶结点: 该结点若为已确定结点, 则跳过, 若为未确定结点, 则标记为已确定结点, 并处理其后续结点
        while(!heap.isEmpty()) {
            int curr = heap.poll()[0];
            if(visited[curr])
                continue;
            visited[curr] = true; // 记录为已确定结点
            // 遍历curr的所有"未确定后续结点", 更新这些结点的当前路径长度
            for(int[] node : graph[curr]) {
                int next = node[0];
                if(!visited[next]) {
                    int newDist = dist[curr] + node[1];
                    if(newDist < dist[next]) {
                        dist[next] = newDist; // 更新为更短的路径
                        heap.offer(new int[]{next, newDist}); // 加入堆
                    }
                }
            }
        }
        return dist;
    }
}
// @lc code=end


// floyd算法求最短路径
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        final int INF = Integer.MAX_VALUE;
        // floyd算法
        int[][] dist = new int[n][n];
        // 初始化
        for(int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for(int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
            dist[edge[1]][edge[0]] = edge[2];
        }
        // 递推
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                if(dist[i][k] == INF)
                    continue;
                for(int j = 0; j < n; j++) {
                    if(dist[k][j] == INF)
                        continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        // 获取结果
        int result = -1, min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int count = 0;
            for(int j = 0; j < n; j++) {
                if(dist[i][j] <= distanceThreshold)
                    count++;
            }
            if(count <= min) {
                min = count;
                result = i;
            }
        }
        return result;
    }
}
