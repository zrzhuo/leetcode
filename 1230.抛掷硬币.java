/*
 * @lc app=leetcode.cn id=1230 lang=java
 *
 * [1230] 抛掷硬币
 */

// @lc code=start
class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        // dp[i][j]表示使用前i个硬币，投出j个正面的概率
        double[][] dp = new double[n + 1][target + 1];
        // 初始化
        for(int i = 0; i <= n; i++) {
            for(int t = 0; t <= target; t++) {
                dp[i][t] = t <= i ? 1 : 0;
            }
        }
        // 递推
        for(int i = 1; i <= n; i++) {
            // 前i个硬币投出0个正面，相当于前i-1个硬币投出0个正面，且第i个硬币投出反面
            dp[i][0] = dp[i - 1][0] * (1 - prob[i - 1]);
            for(int t = 1; t <= target; t++) {
                if(t <= i) {
                    // 前i-1个硬币投出t-1个正面, 第i个硬币投出正面
                    double prob1 = dp[i - 1][t - 1] * prob[i - 1];
                    // 前i-1个硬币投出t个正面, 第i个硬币投出反面
                    double prob2 = dp[i - 1][t] * (1 - prob[i - 1]);
                    dp[i][t] = prob1 + prob2;
                }
                   
            }
        }
        return dp[n][target];
    }
}
// @lc code=end

