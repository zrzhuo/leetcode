/*
 * @lc app=leetcode.cn id=375 lang=java
 *
 * [375] 猜数字大小 II
 */

// @lc code=start
// 动态规划
class Solution {
    public int getMoneyAmount(int n) {
        // dp[i][j]: 对于区间[i, j], 确保获胜需要的最小现金数
        int[][] dp = new int[n + 1][n + 1];
        // 初始化
        for(int i = 1; i <= n; i++) {
            dp[i][i] = 0;
        }
        for(int i = 2; i <= n; i++) {
            dp[i - 1][i] = i - 1;
        }
        // 递推
        for(int i = n; i >= 1; i--) {
            for(int j = i + 2; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i + 1; k <= j - 1; k++) {
                    int left = dp[i][k - 1]; // 在[i, k - 1]中确保获胜需要的最小金额
                    int right = dp[k + 1][j]; // 在[k + 1, j]中确保获胜需要的最小金额
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(left, right));
                }
            }
        }
        return dp[1][n];
    }
}
// @lc code=end

// 记忆化搜索
class Solution {
    // solved[left][right]: 数字范围是[left, right]时, 确保获胜需要的最小金额 
    int[][] solved;
    int solving(int left, int right) {
        // 获取记忆
        if(solved[left][right] != 0)
            return solved[left][right];
        if(left == right) 
            return 0; // 此时可以直接猜中答案, 无需在支付金额
        if(left == right - 1) 
            return left; // 此时最少需要支付left, 才可保证赢得游戏
        int result = Integer.MAX_VALUE;
        for(int i = left + 1; i <= right - 1; i++) {
            int leftRes = solving(left, i - 1); // 在[left, i - 1]中确保获胜需要的最小金额
            int rightRes = solving(i + 1, right); // 在[i + 1, right]中确保获胜需要的最小金额
            result = Math.min(result, i + Math.max(leftRes, rightRes));
        }
        // 存储记忆
        solved[left][right] = result;
        return result;
    }

    public int getMoneyAmount(int n) {
        solved = new int[n + 1][n + 1];
        return solving(1, n);
    }
}