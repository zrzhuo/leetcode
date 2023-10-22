/*
 * @lc app=leetcode.cn id=2812 lang=java
 *
 * [2812] 找出最安全路径
 */

// @lc code=start
class Solution {
    public final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        // 多源bfs，求每个单元格到小偷格的最小曼哈顿距离
        int[][] dist = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }
        Deque<int[]> que = new ArrayDeque<>(); // 源点为所有的小偷位置
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid.get(i).get(j) == 1) {
                    que.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }
        int d = 0; // 当前距离
        while (!que.isEmpty()) {
            d++;
            int size = que.size();
            for(int i = 0; i < size; i++) {
                int[] cur = que.poll();
                for(int[] dir : DIRS) {
                    int r = cur[0] + dir[0], c = cur[1] + dir[1];
                    if(r >= 0 && r < n && c >= 0 && c < n && dist[r][c] == -1) {
                        que.offer(new int[]{r, c});
                        dist[r][c] = d;
                    }
                }
            }
        }
        // 二分查找: 随着d的增大，check(d)由true变为false
        int left = 0, right = d + 1; // 扩1
        while(left < right) {
            int mid = (right - left) / 2 + left;
            if(!check(dist, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return right == 0 ? 0 : right - 1;
    }

    // 并查集判断连通性： 随着d的增大，check(d)由true变为false
    private boolean check(int[][] dist, int d) {
        int n = dist.length;
        if(dist[0][0] < d || dist[n - 1][n - 1] < d) 
            return false;
        // union所有大于等于d的结点
        UnionFind uf = new UnionFind(n * n);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(dist[i][j] < d)
                    continue;
                for(int[] dir : DIRS) {
                    int r = i + dir[0], c = j + dir[1];
                    if(r >= 0 && r < n && c >= 0 && c < n && dist[r][c] >= d)
                        uf.union(i * n + j, r * n + c);
                }
            }
        }
        // 判断[0,0]和[n-1,n-1]是否连通
        return uf.find(0) == uf.find(n * n - 1);
    }
}

// 并查集模版
class UnionFind{
    private int[] parent;

    public UnionFind(int n){
        parent = new int[n];
        for(int i = 0; i < n; ++i){
            parent[i] = i;
        }
    }

    public int find(int x){
        if(parent[x] == x)
            return x; 
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y){
        int px = find(x), py = find(y);
        if(px != py){
            parent[px] = py;
        }
    }
}
// @lc code=end

