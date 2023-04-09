/*
 * @lc app=leetcode.cn id=416 lang=java
 *
 * [416] 分割等和子集
 */

class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 == 1)
            return false;
        int target = sum / 2;
        // dp[i][j]: 只从nums[0...i]中进行选择，可以得到的"小于等于j的数"的最大值
        // 0-1背包问题: 一个数字为一件物品, 物品价值为nums[i], 物品体积为nums[i], 背包大小为target, 求可以装下的最大价值
        int[][] dp = new int[n][target + 1];
        // 初始化
        for(int j = 0; j <= target; j++){
            dp[0][j] = j < nums[0] ? 0 : nums[0];
        }
        // 递推
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= target; j++) {
                if(j < nums[i])
                    dp[i][j] = dp[i - 1][j]; // 选不了nums[i], 故只有一种方案
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]); // 两种方案: 选nums[i]和不选nums[i]
            }
        }
        return dp[n - 1][target] == target;
    }
}

// 统一形式
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 == 1)
            return false;
        int target = sum / 2;
        // dp[i][j]: 只从nums[0...i-1]中进行选择，可以得到的"小于等于j的数"的最大值
        int[][] dp = new int[n + 1][target + 1];
        // 初始化
        dp[0][0] = 0;
        // 递推
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= target; j++) {
                if(j < nums[i - 1])
                    dp[i][j] = dp[i - 1][j]; // 选不了nums[i-1], 故只有一种方案
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]] + nums[i - 1]); // 两种方案: 选nums[i-1]和不选nums[i-1]
            }
        }
        return dp[n][target] == target;
    }
}

// 空间优化1: dp[i]行只依赖于dp[i-1]行, 故可将空间降低一维
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 == 1)
            return false;
        int target = sum / 2;
        // dp[i][j]: 只从nums[0...i-1]中进行选择，可以得到的"小于等于j的数"的最大值
        int[] dp = new int[target + 1];
        // 初始化
        dp[0] = 0;
        // 递推
        for(int i = 1; i <= n; i++) {
            int[] temp = new int[target + 1]; // 临时数组
            for(int j = 0; j <= target; j++) {
                if(j < nums[i - 1])
                    temp[j] = dp[j]; // 选不了nums[i-1], 故只有一种方案
                else
                    temp[j] = Math.max(dp[j], dp[j - nums[i - 1]] + nums[i - 1]); // 两种方案: 选nums[i-1]和不选nums[i-1]
            }
            dp = temp;
        }
        return dp[target] == target;
    }
}

// 空间优化2: 在空间优化1的基础上, 由于temp[j]仅依赖于dp[j]和dp[j-nums[i-1]], 故可以从后往前更新dp数组, 而无需使用temp数组
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 == 1)
            return false;
        int target = sum / 2;
        // dp[i][j]: 只从nums[0...i-1]中进行选择，可以得到的"小于等于j的数"的最大值
        int[] dp = new int[target + 1];
        // 初始化
        dp[0] = 0;
        // 递推
        for(int i = 1; i <= n; i++) {
            // 从后往前更新
            for(int j = target; j >= 0; j--) {
                if(j < nums[i - 1])
                    dp[j] = dp[j]; // 选不了nums[i-1], 故只有一种方案
                else
                    dp[j] = Math.max(dp[j], dp[j - nums[i - 1]] + nums[i - 1]); // 两种方案: 选nums[i-1]和不选nums[i-1]
            }
        }
        return dp[target] == target;
    }
}

// @lc code=start
// 最终版本
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 == 1)
            return false;
        int target = sum / 2;
        // dp[i][j]: 只从nums[0...i-1]中进行选择，可以得到的"小于等于j的数"的最大值
        int[] dp = new int[target + 1];
        // 初始化
        dp[0] = 0;
        // 递推
        for(int i = 1; i <= n; i++) {
            // 从后往前更新
            for(int j = target; j >= nums[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i - 1]] + nums[i - 1]); // 两种方案: 选nums[i-1]和不选nums[i-1]
            }
        }
        return dp[target] == target;
    }
}
// @lc code=end
