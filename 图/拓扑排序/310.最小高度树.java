/*
 * @lc app=leetcode.cn id=310 lang=java
 *
 * [310] 最小高度树
 */

// @lc code=start
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if(n == 1) {
            res.add(0);
            return res;
        }
        // 建图并统计入度
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] inDegree = new int[n];
        for(int[] e : edges) {
            int u = e[0], v = e[1];
            graph[u].add(v);
            inDegree[v]++;
            graph[v].add(u);
            inDegree[u]++;
        }
        // 拓扑排序
        Queue<Integer> que = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(inDegree[i] == 1) {
                que.offer(i); // 叶子节点入队
            }
        }
        while(!que.isEmpty()) {
            res.clear(); // 清除上一层所有节点
            int size = que.size();
            for(int i = 0; i < size; i++) {
                int node = que.poll();
                res.add(node); // 记录当前层所有节点
                for(int next : graph[node]) {
                    if(--inDegree[next] == 1) {
                        que.offer(next); // 新的叶子节点入队
                    }
                }
            } 
        }
        return res;
    }
}
// @lc code=end

