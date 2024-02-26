/*
 * @lc app=leetcode.cn id=785 lang=java
 *
 * [785] 判断二分图
 */

使用图搜索算法从各个连通域的任一顶点开始遍历整个连通域，遍历的过程中用两种不同的颜色对顶点进行染色，相邻顶点染成相反的颜色。
这个过程中倘若发现相邻的顶点被染成了相同的颜色，说明一定不是二分图；反之，如果所有的连通域都染色成功，说明它是二分图。


// BFS
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // 0代表未访问，1代表属于子集1，2代表子集2
        int[] visited = new int[n];
        Deque<Integer> que = new ArrayDeque<>();
        // 遍历所有的连通分量
        for(int i = 0; i < n; i++) {
            if(visited[i] > 0)
                continue;
            que.offer(i);
            visited[i] = 1;
            while(!que.isEmpty()) {
                int u = que.poll();
                for(int v : graph[u]) {
                    // 若v和u处于同一个子集，则一定不是二分图
                    if(visited[v] == visited[u])
                        return false;
                    if(visited[v] == 0) {
                        visited[v] = 3 - visited[u]; // u和v处于不同的子集
                        que.offer(v);
                    }
                }
            }
        }
        return true;
    }
}

// dfs
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // 0代表未访问，1代表属于子集1，2代表子集2
        int[] visited = new int[n];
        for(int i = 0; i < n; i++) {
            if(visited[i] > 0)
                continue;
            if(!dfs(graph, visited,  i, 1))
                return false;
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] visited, int u, int color) {
        // 如果顶点已经被染色，则判断它的颜色是否与本次要染的颜色相同，不相同则说明此无向图无法被正确染色
        if(visited[u] > 0) {
            return visited[u] == color;
        }
        // 对当前顶点进行染色，并将当前顶点的所有邻接点染成相反的颜色。
        visited[u] = color;
        for(int v : graph[u]) {
            if(!dfs(graph, visited,  v, 3 - color))
                return false;
        }
        return true;
    }
}


// @lc code=start
// 并查集
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++) {
            // 将当前顶点的所有邻接点进行合并
            int[] next = graph[i];
            for(int j : next) {
                if(uf.find(i) == uf.find(j))
                    return false; // 邻接点与当前结点处于同一集合, 故一定不是二分图
                uf.union(next[0], j);
            }
        }
        return true;
    }
}

class UnionFind {
    private int[] father;

    public UnionFind(int n) {
        father = new int[n];
        for(int i = 0;i < n; i++) {
            father[i] = i;
        }
    }

    public int find(int x) {
        if(father[x] == x) {
            return x;
        }
        father[x] = find(father[x]);
        return father[x];
    }

    public void union(int x, int y) {
        int px = find(x), py = find(y);
        if(px != py) {
            father[px] = py;
        }
    }
}
// @lc code=end

