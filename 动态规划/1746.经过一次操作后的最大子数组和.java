/*
 * @lc app=leetcode.cn id=1746 lang=java
 *
 * [1746] 经过一次操作后的最大子数组和
 */

// 动态规划
class Solution {
    public int maxSumAfterOperation(int[] nums) {
        int n = nums.length;
        // dp1[i]: 以nums[i]结尾, 且未经操作的最大子数组和
        // dp2[i]: 以nums[i]结尾, 且已经操作的最大子数组和
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = nums[0];
        dp2[0] = nums[0] * nums[0];
        for (int i = 1; i < n; i++) {
            // dp1: 二种情况
            dp1[i] = Math.max(nums[i], dp1[i - 1] + nums[i]);
            // dp2: 三种情况
            int one = dp2[i - 1] + nums[i];
            int two = nums[i] * nums[i];
            int three = dp1[i - 1] + nums[i] * nums[i];
            dp2[i] = Math.max(one, Math.max(two, three));
        }
        int max = 0;
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, dp1[i]);
            max = Math.max(max, dp2[i]);
        }
        return max;
    }
}

// @lc code=start
// 动态规划: 空间优化到一维
class Solution {
    public int maxSumAfterOperation(int[] nums) {
        int n = nums.length;
        // dp1[i]: 以nums[i]结尾, 且未经操作的最大子数组和
        // dp2[i]: 以nums[i]结尾, 且已经操作的最大子数组和
        int dp1 = nums[0];
        int dp2 = nums[0] * nums[0];
        int max = dp2;
        for (int i = 1; i < n; i++) {
            // dp1: 二种情况
            int temp1 = Math.max(nums[i], dp1 + nums[i]);
            // dp2: 三种情况
            int one = dp2 + nums[i];
            int two = nums[i] * nums[i];
            int three = dp1 + nums[i] * nums[i];
            int temp2 = Math.max(one, Math.max(two, three));
            // 更新
            dp1 = temp1;
            dp2 = temp2;
            max = Math.max(max, dp2);
        }
        return max;
    }
}
// @lc code=end