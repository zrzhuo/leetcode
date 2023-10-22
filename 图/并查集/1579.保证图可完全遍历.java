/*
 * @lc app=leetcode.cn id=1579 lang=java
 *
 * [1579] 保证图可完全遍历
 */

// @lc code=start
class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        // 判断Alice能否完全遍历
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges) {
            int u = edge[1] - 1, v = edge[2] - 1, type = edge[0];
            if(type != 2)
                uf.union(u, v);
        }
        if(uf.getCount() > 1)
            return -1;

        // 判断Bob能否完全遍历
        uf.clear();
        for(int[] edge : edges) {
            int u = edge[1] - 1, v = edge[2] - 1, type = edge[0];
            if(type != 1)
                uf.union(u, v);
        }
        if(uf.getCount() > 1)
            return -1;
        
        // 根据共用边划分连通分量
        uf.clear();
        for(int[] edge : edges) {
            int u = edge[1] - 1, v = edge[2] - 1, type = edge[0];
            if(type == 3)
                uf.union(u, v);
        }
        int count = uf.getCount(), res = 0;
        res += n - count; // 连通分量内只保留size-1条共用边
        res += (count - 1) * 2; // 连通分量间只保留1条自用边
        return edges.length - res;
    }
}

class UnionFind {
    private int[] father;
    private int count;

    public UnionFind(int n) {
        father = new int[n];
        for(int i = 0; i < n; i++) {
            father[i] = i;
        }
        count = n;
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
            count--;
        }
    }

    public int getCount() {
        return count;
    }

    public void clear() {
        for(int i = 0; i < father.length; i++) {
            father[i] = i;
        }
        count = father.length;
    }
}
// @lc code=end

