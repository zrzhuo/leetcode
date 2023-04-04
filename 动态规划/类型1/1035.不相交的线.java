/*
 * @lc app=leetcode.cn id=1035 lang=java
 *
 * [1035] 不相交的线
 */


// 动态规划
class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // dp[i][j]: nums1[0...i]和nums2[0...j]可以绘制的最大连线数
        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = nums1[0] == nums2[0] ? 1 : 0;
        for(int i = 1; i < m; i++)
            dp[i][0] = nums1[i] == nums2[0] ? 1 : dp[i - 1][0];
        for(int j = 1; j < n; j++)
            dp[0][j] = nums1[0] == nums2[j] ? 1 : dp[0][j - 1];
        // 递推
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(nums1[i] == nums2[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        } 
        return dp[m - 1][n - 1];
    }
}

// @lc code=start
// 动态规划: 统一形式
class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // dp[i][j]: nums1[0...i-1]和nums2[0...j-1]可以绘制的最大连线数
        int[][] dp = new int[m + 1][n + 1];
        // (省略)初始化: dp[i][0] = 0, dp[0][j] = 0
        // 递推
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(nums1[i - 1] == nums2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        } 
        return dp[m][n];
    }
}
// @lc code=end

