/*
 * @lc app=leetcode.cn id=1559 lang=java
 *
 * [1559] 二维网格图中探测环
 */

// @lc code=start
class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(m * n);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int curr = i * n + j;
                // 向下
                if(i + 1 < m && grid[i + 1][j] == grid[i][j]) {
                    int next = (i + 1) * n + j;
                    if(uf.find(curr) == uf.find(next))
                        return true; // 已经连通
                    uf.union(curr, next);
                }
                // 向右
                if(j + 1 < n && grid[i][j + 1] == grid[i][j]) {
                    int next = i * n + j + 1;
                    if(uf.find(curr) == uf.find(next))
                        return true; // 已经连通
                    uf.union(curr, next);
                }
            }
        }
        return false;
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
// @lc code=end

