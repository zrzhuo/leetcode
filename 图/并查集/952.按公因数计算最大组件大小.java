/*
 * @lc app=leetcode.cn id=952 lang=java
 *
 * [952] 按公因数计算最大组件大小
 */

// @lc code=start
class Solution {
    public int largestComponentSize(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        UnionFind uf = new UnionFind(max + 1);
        for(int num : nums) {
            // 以因数为中介结点
            for(int i = 2; i * i <= num; i++) {
                if(num % i == 0) {
                    uf.union(num, i);
                    uf.union(num, num / i);
                }
            }
        }
        // 获取结果
        int[] count = new int[max + 1];
        for(int num: nums){
            count[uf.find(num)]++;
        }
        return Arrays.stream(count).max().getAsInt();
    }
}

// 并查集
class UnionFind {
    private int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if(parent[x] == x)
            return x; 
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int px = find(x), py = find(y);
        if(px != py) {
            parent[px] = py;
        }
    }
}
// @lc code=end

