/*
 * @lc app=leetcode.cn id=841 lang=java
 *
 * [841] 钥匙和房间
 */

// bfs
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> que = new LinkedList<>();
        que.offer(0);
        visited[0] = true;
        while(!que.isEmpty()) {
            int curr = que.poll();
            for(int next : rooms.get(curr)) {
                if(!visited[next]) {
                    que.offer(next);
                    visited[next] = true;
                }
            }
        }
        for(int i = 0; i < n; i++) {
            if(!visited[i])
                return false;
        }
        return true;
    }
}


// @lc code=start
// dfs
class Solution {
    boolean[] visited;
    void dfs(List<List<Integer>> rooms, int curr) {
        visited[curr] = true;
        for(int next : rooms.get(curr)) {
            if(!visited[next]) {
                dfs(rooms, next);
            }
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        visited = new boolean[n];
        dfs(rooms, 0);
        for(int i = 0; i < n; i++) {
            if(!visited[i])
                return false;
        }
        return true;
    }
}
// @lc code=end

