/*
 * @lc app=leetcode.cn id=1976 lang=java
 *
 * [1976] 到达目的地的方案数
 */

// @lc code=start
// 题解：https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination/solutions/2668041/zai-ji-suan-zui-duan-lu-de-tong-shi-dpfu-g4f3/?envType=daily-question&envId=2024-03-05
class Solution {
    private final static long INF = Long.MAX_VALUE;
    private final static int MOD = 1000000007;
    public int countPaths(int n, int[][] roads) {
        // 建图
        long[][] graph = new long[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for(int[] road : roads) {
            int u = road[0], v = road[1], d = road[2];
            graph[u][v] = graph[v][u] = d;
        }
        // dijkstra
        long[] dist = new long[n];
        boolean[] visited = new boolean[n];
        int[] ways = new int[n]; // ways[i]表示源点到点i的最短的路径数目
        // 初始化
        Arrays.fill(dist, INF);
        dist[0] = 0;
        Arrays.fill(ways, 0);
        ways[0] = 1;
        // 迭代n次
        for(int i = 0; i < n; i++) {
            int curr = -1;
            long min = INF;
            for(int node = 0; node < n; node++) {
                if(!visited[node] && dist[node] < min) {
                    curr = node;
                    min = dist[node];
                }
            } 
            if(curr == -1)
                break;
            visited[curr] = true;
            for(int next = 0; next < n; next++) {
                if(graph[curr][next] < INF && !visited[next]) {
                    long newDist = dist[curr] + graph[curr][next];
                    if(newDist < dist[next]) {
                        dist[next] = newDist;
                        ways[next] = ways[curr]; // 此时，到达next结点的最短路径一定经过curr结点，故ways[next] = ways[curr]
                    } else if(newDist == dist[next]) {
                        ways[next] = (ways[next] + ways[curr]) % MOD;
                    }
                }
            }
        }
        return ways[n - 1];
    }
}
// @lc code=end


// 题解: https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination/solutions/1641204/by-ac_oier-4ule/
class Solution {
    private final static long INF = Long.MAX_VALUE;
    private final static int MOD = 1000000007;
    public int countPaths(int n, int[][] roads) {
        // 建图
        long[][] graph = new long[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for(int[] road : roads) {
            int u = road[0], v = road[1], d = road[2];
            graph[u][v] = graph[v][u] = d;
        }
        // dijkstra求最短路径
        long[] dist = dijkstra(graph, 0);
        // 利用dist重新建图，并统计入度
        int[] inDegree = new int[n];
        for(int[] road : roads) {
            int u = road[0], v = road[1], d = road[2];
            graph[u][v] = graph[v][u] = INF;
            // 边u->v包含在最短路径中
            if(dist[u] + d == dist[v]) {
                graph[u][v] = d;
                inDegree[v]++;
            } 
            // 边v->u包含在最短路径中
            if(dist[v] + d == dist[u]) {
                graph[v][u] = d;
                inDegree[u]++;
            }
        }
        // 拓扑排序 + dp
        int[] dp = new int[n]; // dp[i]: 从结点0到结点i的路径数
        dp[0] = 1; // 初始化
        Deque<Integer> que = new ArrayDeque<>();
        for(int node = 0; node < n; node++) {
            if(inDegree[node] == 0)
                que.offer(node);
        }
        while(!que.isEmpty()) {
            int curr = que.poll();
            for(int next = 0; next < n; next++) {
                if(graph[curr][next] < INF) {
                    dp[next] = (dp[next] + dp[curr]) % MOD; // 递推
                    if(--inDegree[next] == 0)
                        que.offer(next);
                }
            }
        }
        return dp[n - 1];
    }
    
    private long[] dijkstra(long[][] graph, int start) {
        int n = graph.length;
        long[] dist = new long[n];
        boolean[] visited = new boolean[n];
        // 初始化
        Arrays.fill(dist, INF);
        dist[start] = 0;
        // n次循环
        for(int i = 0; i < n; i++) {
            int curr = -1;
            long min = INF;
            for(int node = 0; node < n; node++) {
                if(!visited[node] && dist[node] < min) {
                    curr = node;
                    min = dist[node];
                }
            }
            if(curr == -1)
                break;
            visited[curr] = true;
            for(int next = 0; next < n; next++) {
                if(graph[curr][next] < INF && !visited[next]) {
                    long newDist = dist[curr] + graph[curr][next];
                    if(newDist < dist[next]) {
                        dist[next] = newDist;
                    }
                }
            }
        }
        return dist;
    }
}

