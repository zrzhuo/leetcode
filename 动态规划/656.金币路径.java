/*
 * @lc app=leetcode.cn id=656 lang=java
 *
 * [656] 金币路径
 */

// @lc code=start
class Solution {
    // 要求必须输出字典序最小的路径, 故必须逆向dp
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length, INF = Integer.MAX_VALUE;
        // dp[i]: 从位置i跳到末尾需要的最小金币
        int[] dp = new int[n];
        int[] next = new int[n]; // 用于记录路径
        // 初始化
        Arrays.fill(dp, INF);
        dp[n - 1] = coins[n - 1];
        Arrays.fill(next, INF);
        next[n - 1] = n;
        // 递推
        for(int i = n - 2; i >= 0; i--) {
            if(coins[i] == -1)
                continue;
            // 位置i只能跳到i+1, i+2, .... , i+maxJump处
            int longest = Math.min(i + maxJump, n - 1);
            int min = INF, idx = INF;
            for(int j = i + 1; j <= longest; j++) {
                if(coins[j] == -1)
                    continue;
                if(dp[j] < min) {
                    min = dp[j];
                    idx = j;
                }
            }
            dp[i] = min == INF ? min : min + coins[i];
            next[i] = idx;
        }
        // 获取结果
        if(next[0] == INF)
            return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int idx = 0;
        while(idx < n) {
            result.add(idx + 1);
            idx = next[idx];
        }
        return result;
    }
}
// @lc code=end