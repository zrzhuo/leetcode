/*
 * @lc app=leetcode.cn id=1061 lang=java
 *
 * [1061] 按字典序排列最小的等效字符串
 */

// @lc code=start
class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFind uf = new UnionFind(26);
        for(int i = 0; i < s1.length(); i++) {
            uf.union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < baseStr.length(); i++) {
            sb.append((char)('a' + uf.find(baseStr.charAt(i) - 'a')));
        }
        return sb.toString();
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
        // 将字典序小的作为根!!
        if(px > py) {
            father[px] = py;
        } else if(px < py) {
            father[py] = px;
        }
    }
}
// @lc code=end

