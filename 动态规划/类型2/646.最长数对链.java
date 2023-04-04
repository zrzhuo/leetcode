/*
 * @lc app=leetcode.cn id=646 lang=java
 *
 * [646] 最长数对链
 */

// @lc code=start
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (int[] a, int[] b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return  a[1] - b[1];
        });
        int n = pairs.length;
        // dp[i]: 以pairs[i]结尾的最长数对链的长度
        int[] dp = new int[n];
        // 初始化
        Arrays.fill(dp, 1);
        // 递推
        for(int i = 0;i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 更新最大值
                }
            }
        }
        // 获取结果
        int result = 0;
        for(int i = 0; i < n; i++)
            result = Math.max(result, dp[i]);
        return result;
    }
}
// @lc code=end

