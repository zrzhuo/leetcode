/*
 * @lc app=leetcode.cn id=1049 lang=java
 *
 * [1049] 最后一块石头的重量 II
 */

class Solution {
    public int lastStoneWeightII(int[] stones) {
        // 问题等价于将数组分为两部分并求和，求两个和的最小差值
        // 当其中一部分的和最接近总和的一半时，差值最小，转化为0-1背包问题
        int n = stones.length;
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        // dp[i][j]: 只从stones[0...i]中进行选择，可以得到的"小于等于j的数"的最大值
        // 0-1背包问题: 一块石头为一件物品, 物品价值为stones[i], 物品体积为stones[i], 背包大小为target, 求可以装下的最大价值
        int[][] dp = new int[n][target + 1];
        // 初始化
        for(int j = 0; j <= target; j++) {
            dp[0][j] = j < stones[0] ? 0 : stones[0];
        }
        // 递推
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= target; j++) {
                if(j < stones[i])
                    dp[i][j] = dp[i - 1][j]; // 选不了stones[i], 故只有只用方案
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]); // 两种方案: 选stone[i]和不选stones[i]
            }
        }
        return sum - 2 * dp[n - 1][target];
    }
}

// 统一形式
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        // dp[i][j]: 只从stones[0...i-1]中进行选择，可以得到的"小于等于j的数"的最大值
        int[][] dp = new int[n + 1][target + 1];
        // 初始化
        dp[0][0] = 0;
        // 递推
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= target; j++) {
                if(j < stones[i - 1])
                    dp[i][j] = dp[i - 1][j]; // 选不了stones[i-1], 故只有只用方案
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]); // 两种方案: 选stone[i-1]和不选stones[i-1]
            }
        }
        return sum - 2 * dp[n][target];
    }
}

// 空间优化1: dp[i]行只依赖于dp[i-1]行, 故可将空间降低一维
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        // dp[i][j]: 只从stones[0...i-1]中进行选择，可以得到的"小于等于j的数"的最大值
        int[] dp = new int[target + 1];
        // 初始化
        dp[0] = 0;
        // 递推
        for(int i = 1; i <= n; i++) {
            int[] temp = new int[target + 1]; // 临时数组
            for(int j = 0; j <= target; j++) {
                if(j < stones[i - 1])
                    temp[j] = dp[j]; // 选不了stones[i-1], 故只有只用方案
                else
                    temp[j] = Math.max(dp[j], dp[j - stones[i - 1]] + stones[i - 1]); // 两种方案: 选stone[i-1]和不选stones[i-1]
            }
            dp = temp;
        }
        return sum - 2 * dp[target];
    }
}


// 空间优化2: 在空间优化1的基础上, 由于temp[j]仅依赖于dp[j]和dp[j-nums[i-1]], 故可以从后往前更新dp数组, 而无需使用temp数组
class Solution {
   public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        // dp[i][j]: 只从stones[0...i-1]中进行选择，可以得到的"小于等于j的数"的最大值
        int[] dp = new int[target + 1];
        // 初始化
        dp[0] = 0;
        // 递推
        for(int i = 1; i <= n; i++) {
            // 从后往前更新
            for(int j = target; j >= 0; j--) {
                if(j < stones[i - 1])
                    dp[j] = dp[j]; // 选不了stones[i-1], 故只有只用方案
                else
                    dp[j] = Math.max(dp[j], dp[j - stones[i - 1]] + stones[i - 1]); // 两种方案: 选stone[i-1]和不选stones[i-1]
            }
        }
        return sum - 2 * dp[target];
    }
}

// @lc code=start
// 最终版本
class Solution {
   public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        // dp[i][j]: 只从stones[0...i-1]中进行选择，可以得到的"小于等于j的数"的最大值
        int[] dp = new int[target + 1];
        // 初始化
        dp[0] = 0;
        // 递推
        for(int i = 1; i <= n; i++) {
            // 从后往前更新
            for(int j = target; j >= stones[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i - 1]] + stones[i - 1]); // 两种方案: 选stone[i-1]和不选stones[i-1]
            }
        }
        return sum - 2 * dp[target];
    }
}
// @lc code=end



