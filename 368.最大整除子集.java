/*
 * @lc app=leetcode.cn id=368 lang=java
 *
 * [368] 最大整除子集
 */

// @lc code=start
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // dp[i]: 以nums[i]结尾的最大整除子集的长度
        int[] dp = new int[n];

        // 初始化
        Arrays.fill(dp, 1);
        // 递推
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 获取结果
    }
}
// @lc code=end

