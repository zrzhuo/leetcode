/*
 * @lc app=leetcode.cn id=1377 lang=java
 *
 * [1377] T 秒后青蛙的位置
 */

// @lc code=start
class Solution {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        // 键树（图）
        List<Integer>[] tree = new List[n + 1];
        for(int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for(int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
        // bfs
        int time = -1; // 当前时间
        boolean[] visited = new boolean[n + 1]; // 记录已经访问过的结点
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{1, 1});
        visited[1] = true;
        while(!que.isEmpty()) {
            int size = que.size();
            time++;
            if(time > t)
                return 0;
            for(int i = 0; i < size; i++) {
                int[] head = que.poll();
                int curr = head[0], p = head[1];
                // 统计当前结点的子结点个数
                int len = 0;
                for(int next : tree[curr]) {
                    if(!visited[next])
                        len++;
                }
                // 寻找到目标结点
                if(curr == target)
                    return time == t || len == 0 ? 1.0 / p : 0.0; // 时间和t一致，或被困在叶子节点走不出去
                // 遍历子结点（已经访问过的结点不是子结点而是父结点）
                for(int next : tree[curr]) {
                    if(!visited[next]) {
                        que.offer(new int[]{next, p * len});
                        visited[next] = true;
                    }
                }
            }
        }
        return 0.0;
    }   
}
// @lc code=end

