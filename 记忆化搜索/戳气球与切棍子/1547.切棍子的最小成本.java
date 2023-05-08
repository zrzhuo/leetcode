/*
 * @lc app=leetcode.cn id=1547 lang=java
 *
 * [1547] 切棍子的最小成本
 */


切割棍子(i, j), 分为如下三步骤:
    1. 从切割点k出切割, 支付成本j-i
    2. 切割棍子(i, k), 支付相应的成本
    3. 切割棍子(k, j), 支付相应的成本


// 记忆化搜索
class Solution {
    final int INF = Integer.MAX_VALUE;
    // solved[i][j]: 切割开区间(cutPoints[i], cutPoints[j])的最小成本
    int[][] solved;
    int solving(int[] cutPoints, int i, int j) {
        if(solved[i][j] != INF)
            return solved[i][j];
        // 开区间(cutPoints[i], cutPoints[j])内没有切割点, 不用切割, 返回0;
        if(i == j - 1) {
            solved[i][j] = 0;
            return 0;
        }
        int result = INF;
        // 枚举所有可能的切割点
        for(int k = i + 1; k <= j - 1; k++) {
            int curr = cutPoints[j] - cutPoints[i]; // 从切割点cutPoints[k]出切割开区间(cutPoints[i], cutPoints[j]), 支付成本cutPoints[j] - cutPoints[i]
            int left = solving(cutPoints, i, k);    // 切割开区间(cutPoints[i], cutPoints[k]), 支付相应的成本
            int right = solving(cutPoints, k, j);   // 切割开区间(cutPoints[k], cutPoints[j]), 支付相应的成本
            result = Math.min(result, curr + left + right); // 跟新最小值
        }
        solved[i][j] = result;
        return result;
    }
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        Arrays.sort(cuts);
        int[] cutPoints = new int[m + 2];
        cutPoints[0] = 0;
        cutPoints[m + 1] = n;
        System.arraycopy(cuts, 0, cutPoints, 1, m);
        m += 2;
        solved = new int[m][m];
        for(int i = 0; i < m; i++) {
            Arrays.fill(solved[i], INF);
        }
        return solving(cutPoints, 0, m - 1);
    }
}


// @lc code=start
// 动态规划
class Solution {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        Arrays.sort(cuts);
        int[] cutPoints = new int[m + 2];
        cutPoints[0] = 0;
        cutPoints[m + 1] = n;
        System.arraycopy(cuts, 0, cutPoints, 1, m);
        m += 2;
        // dp[i][j]: 切割开区间(cutPoints[i], cutPoints[j])的最小成本
        int[][] dp = new int[m][m];
        // 初始化
        // 递推
        for(int i = m - 1; i >= 0; i--) {
            for(int j = i + 2; j < m; j++) {
                int min = Integer.MAX_VALUE;
                // 枚举所有可能的切割点
                for(int k = i + 1; k <= j - 1; k++) {
                    int curr = cutPoints[j] - cutPoints[i]; // 从切割点cutPoints[k]出切割开区间(cutPoints[i], cutPoints[j]), 支付成本cutPoints[j] - cutPoints[i]
                    int left = dp[i][k];    // 切割开区间(cutPoints[i], cutPoints[k]), 支付相应的成本
                    int right = dp[k][j];   // 切割开区间(cutPoints[k], cutPoints[j]), 支付相应的成本
                    min = Math.min(min, curr + left + right); // 跟新最小值
                }
                dp[i][j] = min;
            }
        }
        return dp[0][m - 1];
    }
}
// @lc code=end