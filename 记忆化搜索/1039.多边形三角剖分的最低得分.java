/*
 * @lc app=leetcode.cn id=1039 lang=java
 *
 * [1039] 多边形三角剖分的最低得分
 */

一个由闭区间[i, j]构成的凸j-i+1边形, 将其剖分为j-i-1个三角形, 需要如下步骤:
    1. 选择一个顶点k, i<k<j, 将ikj组成一个三角形, 获得valuse[i] * values[k] * values[j]分
    2. 将闭区间[i, k]构成的凸边形剖分为三角形, 获得对应的分数
    3. 将闭区间[k, j]构成的凸边形剖分为三角形, 获得对应的分数

// 记忆化搜索
class Solution {
    // solved[i][j]: 剖分闭区间values[i, j]构成的凸j-i+1边形, 可以获得的最低分数
    int[][] solved;
    int solving(int[] values, int i, int j) {
        // 获取记忆
        if(solved[i][j] != -1)
            return solved[i][j];
        // 只有两个顶点, 无法构成三角形
        if(i == j - 1) {
            solved[i][j] = 0;
            return 0;
        }
        int result = Integer.MAX_VALUE;
        // 枚举可以与ij构成三角形的顶点k
        for(int k = i + 1; k <= j - 1; k++) {
            int curr = values[i] * values[k] * values[j]; // 获取三角形ijk的分数
            int left = solving(values, i, k);   // 将闭区间[i, k]构成的凸边形剖分为三角形, 获得对应的分数
            int right = solving(values, k , j);  // 将闭区间[k, j]构成的凸边形剖分为三角形, 获得对应的分数
            result = Math.min(result, curr + left + right);
        }
        // 存储记忆
        solved[i][j] = result;
        return result;
    }

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        solved = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(solved[i], -1);
        }
        return solving(values, 0, n - 1);
    }
}


// @lc code=start
// 动态规划
class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        // dp[i][j]: 剖分闭区间values[i, j]构成的凸j-i+1边形, 可以获得的最低分数
        int[][] dp = new int[n][n];
        // 初始化
        // for(int i = 0; i < n - 1; i++) {
        //     dp[i][i + 1] = 0; // 只有两个顶点, 无法构成三角形
        // }
        // 递推
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 2; j < n; j++) {
                int min = Integer.MAX_VALUE;
                // 枚举可以与ij构成三角形的顶点k
                for(int k = i + 1; k <= j - 1; k++) {
                    int curr = values[i] * values[k] * values[j]; // 获取三角形ijk的分数
                    int left = dp[i][k];   // 将闭区间[i, k]构成的凸边形剖分为三角形, 获得对应的分数
                    int right = dp[k][j];  // 将闭区间[k, j]构成的凸边形剖分为三角形, 获得对应的分数
                    min = Math.min(min, curr + left + right);
                }
                dp[i][j] = min;
            }
        }
        return dp[0][n - 1];
    }
}
// @lc code=end