/*
 * @lc app=leetcode.cn id=778 lang=java
 *
 * [778] 水位上升的泳池中游泳
 */

// @lc code=start
// dijkstra
class Solution {
    final static int INF = Integer.MAX_VALUE;
    final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        // dijkstra算法变形: 路径长度定义为"路径中结点值的最大值"
        int[][] dist = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]); // 小根堆
        // 初始化
        for(int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = grid[0][0];
        heap.offer(new int[]{0, 0, grid[0][0]});
        while(!heap.isEmpty()) {
            // 处理堆顶结点: 若该结点为已确定结点, 则跳过, 否则标记为已确定结点, 并处理其后续结点
            int[] curr = heap.poll();
            int row = curr[0], col = curr[1];
            if(visited[row][col])
                continue;
            visited[row][col] = true; // 记录为已确定结点
            // 遍历curr的所有"未确定后续结点", 更新这些结点当前路径长度
            for(int[] dir : DIRS) {
                int r = row + dir[0], c = col + dir[1];
                if(r >= 0 && r < n && c >= 0 && c < n && !visited[r][c]) {
                    // 根据路径长度的具体定义计算新路径
                    int newDist = Math.max(dist[row][col], grid[r][c]);
                    if(newDist < dist[r][c]) {
                        dist[r][c] = newDist; // 更新为更短的路径
                        heap.offer(new int[]{r, c, newDist}); // 入堆
                    }
                }
            }
        }
        return dist[n - 1][n - 1];
    }   
}
// @lc code=end



// 并查集
class Solution {
    final static int INF = Integer.MAX_VALUE;
    final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        // 已知条件：grid[i][j]中每个值均无重复
        int[] idx = new int[n * n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                idx[grid[i][j]] = i * n + j; 
            }
        }
        UnionFind uf = new UnionFind(n * n); // 并查集
        // 水位不断上升
        for(int i = 0; i < n * n; i++) {
            int row = idx[i] / n, col = idx[i] % n; // 当前水位新覆盖的位置
            for(int[] dir : DIRS) {
                int r = row + dir[0], c = col + dir[1];
                if(r >= 0 && r < n && c >= 0 && c < n && grid[r][c] <= i) {
                    uf.union(row * n + col, r * n + c); // 该位置已经被覆盖，合并
                }
                if(uf.find(0) == uf.find(n * n - 1)) {
                    return i; // 当前已经覆盖目标位置
                }
            }
        }
        return -1;
    }   
}

class UnionFind {
    private int[] father;

    public UnionFind(int n) {
        father = new int[n];
        for(int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    public int find(int x) {
        if(father[x] == x)
            return x;
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
