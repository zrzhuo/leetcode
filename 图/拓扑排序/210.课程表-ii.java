/*
 * @lc app=leetcode.cn id=210 lang=java
 *
 * [210] 课程表 II
 */

// @lc code=start
class Solution {
    public int[] findOrder(int n, int[][] prerequisites) {
        // 建图: 邻接表
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n]; // 用于统计结点入度
        for(int i = 0; i < n; i++)  {
            graph.add(new ArrayList<>());
        }
        for(int[] prere : prerequisites) {
            int prev = prere[1], next = prere[0];
            graph.get(prev).add(next);
            inDegree[next]++;
        }
        // 所有为0的结点入队
        Queue<Integer> que = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(inDegree[i] == 0)
                que.offer(i);
        }
        // bfs
        List<Integer> result = new ArrayList<>();
        while(!que.isEmpty()) {
            int prev = que.poll();
            result.add(prev);
            // 删除prev结点的出边
            for(int next : graph.get(prev)) {
                if(--inDegree[next] == 0) 
                    que.offer(next);
            }
        }
        // 获取结果
        if(result.size() < n)
            return new int[0];
        int[] array = new int[n];
        for(int i = 0; i < n; i ++) {
            array[i] = result.get(i);
        }
        return array;
    }
}
// @lc code=end

