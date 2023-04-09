/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 */

// @lc code=start
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        // dp[i]: 总和为i的组合的最小长度(凑成金额i需要的最少硬币个数)
        int[] dp = new int[amount + 1];
        // 初始化
        Arrays.fill(dp, amount + 1);
        dp[0] = 0; // 由于数字都大于0, 故总和为0的组合只有一个空组合
        // 递推, 组合问题, 数字在外层, 和在内层
        for(int coin: coins) {
            for(int i = 1; i <= amount; i++) {
                if(coin <= i)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); // 在总和为i-coin的组合末尾加上coin, 即构成总和为i的组合
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
// @lc code=end

