/*
 * @lc app=leetcode.cn id=2673 lang=java
 *
 * [2673] 使二叉树所有路径值相等的最小代价
 */

// @lc code=start
class Solution {
    public int minIncrements(int n, int[] cost) {
        int res = 0;
        for(int i = n / 2 - 1; i >= 0; i--) {
            res += Math.abs(cost[i * 2 + 1] - cost[i * 2 + 2]);
            cost[i] += Math.max(cost[i * 2 + 1], cost[i * 2 + 2]);
        }
        return res;
    }
}
// @lc code=end

class Solution {
    int res = 0;
    public int minIncrements(int n, int[] cost) {
        dfs(0, n, cost);
        return res;
    }

    int dfs(int root, int n, int[] cost) {
        if(root >= n / 2)
            return cost[root];
        int l = dfs(root * 2 + 1, n, cost);
        int r = dfs(root * 2 + 2, n, cost);
        res += Math.abs(l - r);
        return cost[root] + Math.max(l, r);
    }
}
