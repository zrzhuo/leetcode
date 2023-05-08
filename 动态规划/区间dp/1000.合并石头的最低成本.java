/*
 * @lc app=leetcode.cn id=1000 lang=java
 *
 * [1000] 合并石头的最低成本
 */


假设共n堆石头, 目标是将其通过合并减少n-1堆, 每次合并k堆石头后, 石头会减少k-1堆, 故:
        仅当(n-1)%(k-1)为0时, 才能将石头合并为1堆, 否则不存在合并方案

将闭区间[i, j]合并为t>1堆石头, 需要如下步骤:
    1. 将闭区间[i, s]合并为1堆, 支付相应的成本
    2. 将闭区间[s + 1, j]合并为t-1堆, 支付相应的成本
将闭区间[i, j]合并为1堆石头, 需要如下步骤:
    1. 将闭区间[i, j]合并为k堆, 支付相应的成本
    2. 再将得到的k堆石头合并为一堆, 此时需要支付sum(i,j)的成本


// @lc code=start
// 记忆化搜索
class Solution {
    final int INF = Integer.MAX_VALUE;
    int k; // 要求的k
    int[] prefix;  // 前缀和, 方便求区间和: 区间[i, j]之和 = prefix[j + 1] - prefix[i]
    int[][][] solved; // solved[i][j][t]: 将闭区间[i, j]合并为t堆石头的最低成本, INF表示无法将合并
    int solving(int i, int j, int t) {
        // 获取记忆
        if(solved[i][j][t] != -1)
            return solved[i][j][t];
        int result = INF;
        if(t == 1) {
            // 尝试先合并为k堆, 再合并为1堆
            int oneCost = solving(i, j, k);
            if(oneCost == INF)
                return INF; // 无法合并为k堆, 进而无法合并为1堆
            result = solving(i, j, k) + prefix[j + 1] - prefix[i]; // 计算成本
        } else {
            // 枚举所有可行切分点
            for(int s = i; s < j; s += (k - 1)) {
                int left = solving(i, s, 1); // 将闭区间[i, s]合并为1堆
                int right = solving(s + 1, j, t - 1); // 将闭区间[s + 1, j]合并为t-1堆
                if(left != INF && right != INF)
                    result = Math.min(result, left + right); // 更新最低成本
            }
        }
        // 存储记忆
        solved[i][j][t] = result;
        return result;
    }

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        this.k = k;
        // 判断是否能合并为1堆
        if((n - 1) % (k - 1) != 0)
            return -1;
        // 初始化prefix
        prefix = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + stones[i - 1];
        }
        // 初始化solved
        solved = new int[n][n][k + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int t = 1; t <= k; t++) {
                    if(j - i + 1 < t)
                        solved[i][j][t] = INF; // 区间内的石头不足t堆, 无法合并
                    else if(t == 1 && (j - i) % (k - 1) != 0)
                        solved[i][j][t] = INF; // 区间内的石头堆数无法合并为1堆
                    else if(t == 1 && i == j)
                        solved[i][j][t] = 0;   // 区间内只有一堆石头, 无需合并, 成本为0
                    else
                        solved[i][j][t] = -1;  // 初始化为-1, 表示未求出
                }
            }
        }
        return solving(0, n - 1, 1);
    }
}
// @lc code=end


// @lc code=start
// 动态规划
class Solution {
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        // 判断是否能合并为1堆
        if((n - 1) % (k - 1) != 0)
            return -1;
        // 前缀和, 方便求区间和: 区间[i, j]之和 = prefix[j + 1] - prefix[i]
        int[][] prefix = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + stones[i - 1];
        }
        // dp[i][j][t]: solved[i][j][t]: 将闭区间[i, j]合并为t堆石头的最低成本, INF表示无法将合并
        int[][][] dp = new int[n][n][k + 1];
        // 初始化
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        for(int i = 0; i < n; i++) {
            dp[i][i][1] = 0; // 区间内只有一堆石头, 无需合并, 成本为0
        }
        // 递推
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                for(int t = 2; t <= k; t++) {
                    // 枚举所有可行切分点
                    for(int s = i; s < j; s += (k - 1)) {
                        int left = dp[i][s][1]; // 将闭区间[i, s]合并为1堆
                        int right = dp[s + 1][j][t - 1]; // 将闭区间[s + 1, j]合并为t-1堆
                        if(left != INF && right != INF)
                            dp[i][j][t] = Math.min(dp[i][j][t], left + right);
                    }
                }
                // 尝试先合并为k堆, 再合并为1堆
                dp[i][j][1] = dp[i][j][k] == INF ? INF : dp[i][j][k] + prefix[j + 1] - prefix[i];
            }
        }
        return dp[0][n - 1][1];
    }
}
// @lc code=end