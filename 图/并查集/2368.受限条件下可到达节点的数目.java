/*
 * @lc app=leetcode.cn id=2368 lang=java
 *
 * [2368] 受限条件下可到达节点的数目
 */

// @lc code=start
// 并查集
class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] isRes = new boolean[n];
        for(int node : restricted) {
            isRes[node] = true;
        }
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if(!isRes[u] && !isRes[v]) {
                uf.union(u, v); // 边不包含受限节点时，合并
            }
        }
        return uf.getSize(0); // 节点0所属集合的size
    }
}

class UnionFind {
    private int[] father;
    private int[] size;

    public UnionFind(int n) {
        father = new int[n];
        size = new int[n];
        for(int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if(father[x] == x)
            return x;
        father[x] = find(father[x]);
        return father[x];
    }

    public void union(int x, int y) {
        int fx = find(x), fy = find(y);
        if(fx != fy) {
            father[fx] = fy;
            size[fy] += size[fx];
        }
    }

    public int getSize(int x) {
        return size[find(x)];
    }
}
// @lc code=end


// dfs
class Solution {
    List<Integer>[] graph;
    boolean[] visited;
    Set<Integer> resNode = new HashSet<>(); // 记录受限节点
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        for(int num : restricted) {
            resNode.add(num);
        }
        // 建图
        graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        // dfs
        visited = new boolean[n];
        dfs(0);
        // 统计结果
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i])
                count++;
        }
        return count;
    }

    private void dfs(int node) {
        if(resNode.contains(node) || visited[node]) {
            return;
        }
        visited[node] = true;
        for(int next : graph[node]) {
            dfs(next);
        }
        return;
    }
}