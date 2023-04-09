/*
 * @lc app=leetcode.cn id=377 lang=java
 *
 * [377] 组合总和 Ⅳ
 */

// @lc code=start
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        // dp[i]: 总和为i的排列数
        int[] dp = new int[target + 1];
        // 初始化: 由于数字都大于0, 故总和为0的排列只有一个空排列
        dp[0] = 1;
        // 递推: 排列问题, 和在外层, 数字在内层
        for(int i = 1; i <= target; i++) {
            for(int num : nums) {
                if(num <= i)
                    dp[i] += dp[i - num]; // 在总和为i-num的排列末尾加上num, 即构成总和为i的排列
            }
        }
        return dp[target];
    }
}
// @lc code=end
