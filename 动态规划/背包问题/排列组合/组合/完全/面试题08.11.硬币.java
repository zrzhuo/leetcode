class Solution {
    public int waysToChange(int n) {
        int mod = 1000000007;
        int[] coins = {1, 5, 10, 25};
        // dp[i]: 总和为i的组合数(凑成金额i的不同方案数)
        int[] dp = new int[n + 1];
        // 初始化: 由于数字都大于0, 故总和为0的组合只有一个空组合
        dp[0] = 1; 
        // 递推, 组合问题, 数字在外层, 和在内层
        for(int coin : coins) {
            for(int i = 1; i <= n; i++) {
                if(coin <= i)
                    dp[i] = (dp[i] + dp[i - coin]) % mod; // 在总和为i-coin的组合末尾加上coin，即构成总和为i的组合
            }
        }
        return dp[n];
    }
}