/*
 * @lc app=leetcode.cn id=712 lang=java
 *
 * [712] 两个字符串的最小ASCII删除和
 */


// @lc code=start
// 动态规划: 统一形式
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // dp[i][j]: 使s1[0...i-1]和s2[0...j-1]相等的最小删除和
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        dp[0][0] = 0;
        for(int i = 1; i <= m; i++)
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1); // 将s1[0..i-1]所有字符都删除
        for(int j = 1; j <= n; j++)
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1); // 将s2[0..j-1]所有字符都删除
        // 递推
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 由于s1[i-1]==s2[j-1], 故不用删除字符
                } else {
                    int a = dp[i - 1][j] + s1.charAt(i - 1);  // 删除s1[i-1]
                    int b = dp[i][j - 1] + s2.charAt(j - 1);  // 删除s2[j-1]
                    dp[i][j] = Math.min(a, b);
                }
            }
        }
        return dp[m][n];
    }   
}
// @lc code=end

