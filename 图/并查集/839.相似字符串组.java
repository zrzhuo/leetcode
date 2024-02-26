/*
 * @lc app=leetcode.cn id=839 lang=java
 *
 * [839] 相似字符串组
 */

// @lc code=start
class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(check(strs[i], strs[j]))
                    uf.union(i, j);
            }
        }
        return uf.getCount();
    }
    
    // 判断s1和s2是否相似
    private boolean check(String s1, String s2) {
        int cnt = 0;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i))
                cnt++;
            if(cnt == 3)
                return false;
        }
        return true;
    }
}

class UnionFind {
    private int[] father;
    private int count; // 子集的数量
    
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
}
// @lc code=end

