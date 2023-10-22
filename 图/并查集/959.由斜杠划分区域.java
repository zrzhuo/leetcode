/*
 * @lc app=leetcode.cn id=959 lang=java
 *
 * [959] 由斜杠划分区域
 */

// @lc code=start
// 题解: https://leetcode.cn/problems/regions-cut-by-slashes/solutions/571382/you-xie-gang-hua-fen-qu-yu-by-leetcode-67xb/
class Solution {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int size = n * n * 4;
        UnionFind uf = new UnionFind(size);
        // 格子(i,j)被对角线分为上下左右四个三角形区域
        // 格子编号为i*n+j, 则上下左右四个区域编号分别为4*(i*n+j)+0, 4*(i*n+j)+1, 4*(i*n+j)+2, 4*(i*n+j)+3
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int idx = i * n + j;
                int up = 4 * idx + 0, down = 4 * idx + 1;
                int left = 4 * idx + 2, right = 4 * idx + 3;
                // 合并格子内部四个区域
                if(grid[i].charAt(j) == '/') {
                    uf.union(up, left); // 上左、下右
                    uf.union(down, right);
                } else if (grid[i].charAt(j) == '\\') {
                    uf.union(up, right); // 上右、下左
                    uf.union(down, left);
                } else {
                    uf.union(up, down); // 上下左右
                    uf.union(up, left);
                    uf.union(up, right);
                }
                // 合并相邻格子的相邻区域
                int upDown = 4 * (idx - n) + 1, downUp = 4 * (idx + n) + 0;
                int leftRight = 4 * (idx - 1) + 3, rightLeft = 4 * (idx + 1) + 2;
                if(i > 0) {
                    uf.union(up, upDown);
                }
                // if(i < n - 1) {
                //     uf.union(down, downUp);
                // }
                if(j > 0) {
                    uf.union(left, leftRight);
                }
                // if(j < n - 1) {
                //     uf.union(right, rightLeft);
                // }
            }
        }
        return uf.count();
    }
}

class UnionFind{
    private int[] father; // 父结点
    private int count; // 子集数量

    public UnionFind(int n){
        father = new int[n];
        for(int i = 0; i < n; ++i){
            father[i] = i; // 初始时，每个结点为一个集合
        }
        count = n;
    }

    public int find(int x){
        if(father[x] == x)
            return x; // 递归出口
        father[x] = find(father[x]); // 路径压缩：使parent[x]存放的是x所处集合的根结点
        return father[x];
    }

    public void union(int x, int y){
        int px = find(x), py = find(y);
        if(px != py){
            father[px] = py; // 将px的父结点置为py
            count--;
        }
    }

    public int count() {
        return count;
    }
}
// @lc code=end

