/*
 * @lc app=leetcode.cn id=2312 lang=java
 *
 * [2312] 卖木头块
 */

// @lc code=start
// 记忆化搜索
class Solution {
    long[][] solved; // solved[i][j]: 出售高为i宽为j的木头可以得到最大利润
    int[][] price; // 用于快速获取prices中的价格
    long solving(int h, int w) {
        // 获取记忆
        if(solved[h][w] != -1) {
            return solved[h][w];
        }
        long res = 0;
        // 将当前木头整块卖出
        res = Math.max(res, price[h][w]);
        // 将当前木头分成上下两块分别卖出
        for(int up = 1; up <= h / 2; up++) {
            res = Math.max(res, solving(up, w) + solving(h - up, w));
        }
        // 将当前木头分为左右两块分别卖出
        for(int le = 1; le <= w / 2; le++) {
            res = Math.max(res, solving(h, le) + solving(h, w - le));
        }
        // 存储记忆
        solved[h][w] = res;
        return res;
    } 
    
    public long sellingWood(int m, int n, int[][] prices) {
        solved = new long[m + 1][n + 1];
        for(long[] row : solved) {
            Arrays.fill(row, -1);
        }
        price = new int[m + 1][n + 1];
        for(int[] p : prices) {
            price[p[0]][p[1]] = p[2];
        }
        return solving(m, n);
    }
}
// @lc code=end


// 动态规划
class Solution {
    public long sellingWood(int m, int n, int[][] prices) {
        // dp[i][j]: 出售高为i宽为j的木头能得到的最大利润
        long[][] dp = new long[m + 1][n + 1];
        // 初始化：可以整块卖出的木头
        for(int[] p : prices) {
            dp[p[0]][p[1]] = p[2];
        }
        // 递推
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                // 将当前木头整块卖出
                long max = dp[i][j]; 
                // 将当前木头分成上下两块分别卖出
                for(int up = 1; up <= i / 2; up++) {
                    max = Math.max(max, dp[up][j] + dp[i - up][j]);
                }
                // 将当前木头分为左右两块分别卖出
                for(int le = 1; le <= j / 2; le++) {
                    max = Math.max(max, dp[i][le] + dp[i][j - le]);
                }
                dp[i][j] = max;
            }
        }
        return dp[m][n];
    }
}
