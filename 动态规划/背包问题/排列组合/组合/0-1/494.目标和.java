/*
 * @lc app=leetcode.cn id=494 lang=java
 *
 * [494] 目标和
 */

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // 将数组分为两部分，一部分的和s，另一部则为sum-s，有
        //    s - (sum-s) = target，即s = (sum+target) / 2, 且s应该为偶数
        // 问题转化为求nums数组中"和为s的子数组"的个数
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(Math.abs(target) > sum || (target + sum) % 2 == 1)
            return 0;
        int s = (target + sum) / 2;
        // dp[i][j]: 只从nums[0...i]中选择数字, "和为j的子数组"的个数
        // 0-1背包问题: 一个数字为一类物品, 物品体积为nums[i], 背包容量为s, 求恰好装满背包的不同组合数
        int[][] dp = new int[n][s + 1];
        // 初始化
        for(int j = 0; j <= s; j++) {
            dp[0][j] = j == nums[0] ? 1 : 0;
        }
        dp[0][0] += 1;
        // 递推
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= s; j++) {
                if(j < nums[i])
                    dp[i][j] = dp[i - 1][j]; // 选不了nums[i], 故只有一种方案
                else
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j -  nums[i]]; // 两种方案: 选nums[i]和不选nums[i]
            }
        }
        return dp[n - 1][s];
    }
}

// 统一形式
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(Math.abs(target) > sum || (target + sum) % 2 == 1)
            return 0;
        int s = (target + sum) / 2;
        // dp[i][j]: 只从nums[0...i-1]中选择数字, "和为j的子数组"的个数
        int[][] dp = new int[n + 1][s + 1];
        // 初始化
        dp[0][0] = 1;
        // 递推
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= s; j++) {
                if(j < nums[i - 1])
                    dp[i][j] = dp[i - 1][j]; // 选不了nums[i-1], 故只有一种方案
                else
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j -  nums[i - 1]]; // 两种方案: 选nums[i-1]和不选nums[i-1]
            }
        }
        return dp[n][s];
    }
}

// @lc code=start
// 空间优化1: dp[i]行只依赖于dp[i-1]行, 故可将空间降低一维
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(Math.abs(target) > sum || (target + sum) % 2 == 1)
            return 0;
        int s = (target + sum) / 2;
        // dp[i][j]: 只从nums[0...i-1]中选择数字, "和为j的子数组"的个数
        int[] dp = new int[s + 1];
        // 初始化
        dp[0] = 1;
        // 递推
        for(int i = 1; i <= n; i++) {
            int[] temp = new int[s + 1]; // 临时数组
            for(int j = 0; j <= s; j++) {
                if(j < nums[i - 1])
                    temp[j] = dp[j]; // 选不了nums[i-1], 故只有一种方案
                else
                    temp[j] = dp[j] + dp[j -  nums[i - 1]]; // 两种方案: 选nums[i-1]和不选nums[i-1]
            }
            dp = temp;
        }
        return dp[s];
    }
}
// @lc code=end

// 空间优化2: 在空间优化1的基础上, 由于temp[j]仅依赖于dp[j]和dp[j-nums[i-1]], 故可以从后往前更新dp数组, 而无需使用temp数组
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(Math.abs(target) > sum || (target + sum) % 2 == 1)
            return 0;
        int s = (target + sum) / 2;
        // dp[i][j]: 只从nums[0...i-1]中选择数字, "和为j的子数组"的个数
        int[] dp = new int[s + 1];
        // 初始化
        dp[0] = 1;
        // 递推
        for(int i = 1; i <= n; i++) {
            // 从后往前更新
            for(int j = s; j >= 0; j--) {
                if(j < nums[i - 1])
                    dp[j] = dp[j]; // 选不了nums[i-1], 故只有一种方案
                else
                    dp[j] = dp[j] + dp[j - nums[i - 1]]; // 两种方案: 选nums[i-1]和不选nums[i-1]
            }
        }
        return dp[s];
    }
}

// @lc code=start
// 最终版本
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(Math.abs(target) > sum || (target + sum) % 2 == 1)
            return 0;
        int s = (target + sum) / 2;
        // dp[i][j]: 只从nums[0...i-1]中选择数字, "和为j的子数组"的个数
        int[] dp = new int[s + 1];
        // 初始化
        dp[0] = 1;
        // 递推
        for(int i = 1; i <= n; i++) {
            for(int j = s; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] + dp[j - nums[i - 1]]; // 两种方案: 选nums[i-1]和不选nums[i-1]
            }
        }
        return dp[s];
    }
}
// @lc code=end