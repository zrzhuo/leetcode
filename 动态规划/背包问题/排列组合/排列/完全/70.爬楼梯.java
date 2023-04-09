/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 */

// @lc code=start
class Solution {
    public int climbStairs(int n) {
        // 转化为排列问题: 一个数组nums, 从中无限制的取数字, 求总和为n的不同排列数
        int[] nums = {1, 2};
        // dp[i]: 总和为i的排列数
        int[] dp = new int[n + 1];
        // 初始化: 由于数字都大于0, 故总和为0的排列只有一个空排列
        dp[0] = 1;
        // 递推: 排列问题, 和在外层, 数字在内层
        for(int i = 1; i <= n; i++) {
            for(int num : nums) {
                if(num <= i)
                    dp[i] += dp[i - num]; // 在总和为i-num的排列末尾加上num, 即构成总和为i的排列
            }
        }
        return dp[n];
    }
}
// @lc code=end

