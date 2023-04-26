/*
 * @lc app=leetcode.cn id=207 lang=java
 *
 * [207] 课程表
 */

// @lc code=start
// Kahn拓扑排序, 即bfs
class Solution {
    public boolean canFinish(int n, int[][] prerequisites) {
        // 建图: 邻接表
        List<List<Integer>> graph = new ArrayList<>(n);
        int[] inDegree = new int[n]; // 统计结点入度
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] prere : prerequisites) {
            int prev = prere[0], next = prere[1];
            graph.get(prev).add(next);
            inDegree[next]++;
        }
        // 入度为零的结点入队
        Queue<Integer> que = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(inDegree[i] == 0)
                que.offer(i);
        }
        // bfs
        int count = 0; // 入队(出队)结点数
        while(!que.isEmpty()) {
            int prev = que.poll();
            count++;
            // 删除prev结点和有关的边
            for(int next : graph.get(prev)) {
                if(--inDegree[next] == 0) 
                    que.offer(next); // 入度降为0即可入队
            }
        }
        return count == n;
    }
}
// @lc code=end

