/*
 * @lc app=leetcode.cn id=787 lang=java
 *
 * [787] K 站中转内最便宜的航班
 */

// @lc code=start
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
       final int INF = Integer.MAX_VALUE;
        // dp[t][i]: 从src结点出发, 经过t个航班(i条边), 到达i结点的最低价格
        int[][] dp = new int[k + 2][n];
        // 初始化
        for(int t = 0; t < k + 2; t++) {
            Arrays.fill(dp[t], INF);
        }
        dp[0][src] = 0;
        // 递推
        for(int t = 1; t < k + 2; t++) {
            for(int[] flight : flights) {
                int prev = flight[0], next = flight[1], cost = flight[2];
                if(dp[t - 1][prev] < INF) {
                    dp[t][next] = Math.min(dp[t][next], dp[t - 1][prev] + cost);
                }
            }
        }
        // 获取结果
        int result = INF;
        for(int t = 0; t < k + 2; t++) {
            result = Math.min(result, dp[t][dst]);
        }
        return result == INF ? -1 : result;
    }
}
// @lc code=end


class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = Integer.MAX_VALUE;
        // 建图: 逆邻接表
        List<int[]>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] f : flights) {
            graph[f[1]].add(new int[]{f[0], f[2]});
        }
        // dp[t][i]: 从src结点出发, 经过t个航班(i条边), 到达i结点的最低价格
        int[][] dp = new int[k + 2][n];
        // 初始化
        for(int t = 0; t < k + 2; t++) {
            Arrays.fill(dp[t], INF);
        }
        dp[0][src] = 0;
        // 递推
        for(int t = 1; t < k + 2; t++) {
            for(int i = 0; i < n; i++) {
                for(int[] node : graph[i]) {
                    int prev = node[0], dist = node[1];
                    if(dp[t - 1][prev] != INF)
                        dp[t][i] = Math.min(dp[t][i], dp[t - 1][prev] + dist);
                }
            }
        }
        int result = INF;
        for(int t = 0; t < k + 2; t++) {
            result = Math.min(result, dp[t][dst]);
        }
        return result == INF ? -1 : result;
    }
}