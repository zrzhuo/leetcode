/*
 * @lc app=leetcode.cn id=2642 lang=java
 *
 * [2642] 设计可以求最短路径的图类
 */

// @lc code=start
class Graph {
    private static final int INF = Integer.MAX_VALUE;
    private int n;
    private int[][] g;
    public Graph(int n, int[][] edges) {
        this.n = n;
        g = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(g[i], INF);
        }
        for(int[] edge : edges) {
            g[edge[0]][edge[1]] = edge[2];
        }
    }
    
    public void addEdge(int[] edge) {
        g[edge[0]][edge[1]] = edge[2];
    }
    
    // 不存在负权值，使用dijkstra
    public int shortestPath(int node1, int node2) {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, INF);
        dist[node1] = 0;
        for(int i = 0; i < n; i++) {
            int curr = -1, min = INF;
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
                if(!visited[next] && g[curr][next] < INF) 
                    dist[next] = Math.min(dist[next], dist[curr] + g[curr][next]);
            }
        }
        return dist[node2] == INF ? -1 : dist[node2];
    }

}
// @lc code=end

