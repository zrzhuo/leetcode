/*
 * @lc app=leetcode.cn id=990 lang=java
 *
 * [990] 等式方程的可满足性
 */

// @lc code=start
class Solution {
    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        // 合并相等的变量
        for(String equa : equations) {
            int a = equa.charAt(0) - 'a', b = equa.charAt(3) - 'a';
            if(equa.charAt(1) == '=') {
                uf.union(a, b);
            } 
        }
        // 检查是否有矛盾
        for(String equa : equations) {
            int a = equa.charAt(0) - 'a', b = equa.charAt(3) - 'a';
            if(equa.charAt(1) == '!' && uf.find(a) == uf.find(b)) {
                return false;
            }
        }
        return true;
    }
}

class UnionFind {
    private int[] father;

    public UnionFind(int n) {
        father = new int[n];
        for(int i = 0;i < n; i++) {
            father[i] = i;
        }
    }

    public int find(int x) {
        if(father[x] == x) {
            return x;
        }
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

