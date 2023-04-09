/*
 * @lc app=leetcode.cn id=518 lang=java
 *
 * [518] 零钱兑换 II
 */



/**
 * 组合的角度
 */ 

// @lc code=start
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i]: 总和为i的组合数(凑成金额i的不同方案数)
        int[] dp = new int[amount + 1];
        // 初始化: 由于数字都大于0, 故总和为0的组合只有一个空组合
        dp[0] = 1; 
        // 递推, 组合问题, 数字在外层, 和在内层
        for(int coin : coins) {
            for(int i = 1; i <= amount; i++) {
                if(coin <= i)
                    dp[i] = dp[i] + dp[i - coin]; // 在总和为i-coin的组合末尾加上coin，即构成总和为i的组合
            }
        }
        return dp[amount];
    }
}
// @lc code=end



/**
 * 完全背包的角度
 */ 

class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j]，只使用coins[0...i-1]中的金币，可以凑出金额j的方案数
        // 完全背包问题: 一个硬币为一类物品, 物品体积为coins[i], 背包容量amount, 求恰好装满背包的不同组合数
        int[][] dp = new int[n + 1][amount + 1];
        // 初始化
        dp[0][0] = 1;
        // 递推
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= amount; j++) {
                if(j < coins[i - 1])
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]]; // 完全背包
            }
        }
        return dp[n][amount];
    }
}

// 空间优化: dp[i][j]只依赖于dp[i-1][j]和dp[i][j-coins[i-1]], 故可将空间降低一维
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j]，只使用coins[0...i-1]中的金币，可以凑出金额j的方案数
        int[] dp = new int[amount + 1];
        // 初始化
        dp[0] = 1;
        // 递推
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= amount; j++) {
                if(j < coins[i - 1])
                    dp[j] = dp[j];
                else
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
            }
        }
        return dp[amount];
    }
}

// 最终版本
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j]，只使用coins[0...i-1]中的金币，可以凑出金额j的方案数
        int[] dp = new int[amount + 1];
        // 初始化
        dp[0] = 1;
        // 递推
        for(int i = 1; i <= n; i++) {
            for(int j = coins[i - 1]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i - 1]];
            }
        }
        return dp[amount];
    }
}


// 另一种版本: 容易理解但时间效率较差
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j]，只使用coins[0...i-1]中的金币，可以凑出金额j的方案数
        // 完全背包问题: 硬币为物品, 物品价值为coins[i], 物品体积为coins[i], 背包体积为amout, 求总价值为amount的组合数
        int[][] dp = new int[n + 1][amount + 1];
        // 初始化
        dp[0][0] = 1;
        // 递推
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= amount; j++) {
                // 枚举coins[i]的可能个数
                for(int k = 0; k * coins[i - 1] <= j; k++){
                    dp[i][j] += dp[i - 1][j - k * coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }
}

// 空间优化1: dp[i]行只依赖于dp[i-1]行, 故可将空间降低一维
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j]，只使用coins[0...i-1]中的金币，可以凑出金额j的方案数
        int[] dp = new int[amount + 1];
        // 初始化
        dp[0] = 1;
        // 递推
        for(int i = 1; i <= n; i++) {
            int[] temp = new int[amount + 1];
            for(int j = 0; j <= amount; j++) {
                // 枚举coins[i]的可能个数
                for(int k = 0; k * coins[i - 1] <= j; k++){
                    temp[j] += dp[j - k * coins[i - 1]];
                }
            }
            dp = temp;
        }
        return dp[amount];
    }
}

// 空间优化2: 在空间优化1的基础上, 由于temp[j]仅依赖于dp[j-k*coins[i-1]], 故可以从后往前更新dp数组, 而无需使用temp数组
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j]，只使用coins[0...i-1]中的金币，可以凑出金额j的方案数
        int[] dp = new int[amount + 1];
        // 初始化
        dp[0] = 1;
        // 递推
        for(int i = 1; i <= n; i++) {
            for(int j = amount; j >= 0; j--) {
                // 枚举coins[i]的可能个数: 注意这里dp[j]本身就是k=0时的方案数, 故不能再计入k=0时的情况
                for(int k = 1; k * coins[i - 1] <= j; k++) {
                    dp[j] += dp[j - k * coins[i - 1]];
                }
            }
        }
        return dp[amount];
    }
}