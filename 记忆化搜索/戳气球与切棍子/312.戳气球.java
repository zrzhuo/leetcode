/*
 * @lc app=leetcode.cn id=312 lang=java
 *
 * [312] 戳气球
 */


戳破开区间(i, j)中所有气球, 分为三步:
    1. 戳破开区间(i, k)中所有气球, 获得相应的价值
    2. 戳破开区间(k, j)中所有气球, 获得相应的价值
    3. 此时开区间(i, k)的只剩一个气球, 戳破位置k的气球, 获得val(i) * val(k) * val(j)的价值
------------------TENET------------------
还原开区间(i, j)中所有气球, 分为三步:
    1. 此时开区间(i, k)的没有任何气球, 还原位置k的气球, 获得val(i) * val(k) * val(j)的价值
    2. 还原开区间(i, k)中所有气球, 获得相应的价值
    3. 还原开区间(k, j)中所有气球, 获得相应的价值


// 记忆化搜索
class Solution {
    // solved[i][j]: 还原开区间(i, j)可以获取的最大价值
    int[][] solved;
    int solving(int[] val, int i, int j) {
        // 获取记忆
        if(solved[i][j] > -1)
            return solved[i][j];
        int result = 0;
        // 枚举第一个还原的气球
        for(int k = i + 1; k <= j - 1; k++) {
            int curr = val[i] * val[k] * val[j]; // 此时开区间(i, k)的没有任何气球, 还原位置k的气球, 获得val(i) * val(k) * val(j)的价值
            int left = solving(val, i, k); // 还原开区间(i, k)中所有气球, 获得相应的价值
            int right = solving(val, k, j); // 还原开区间(k, j)中所有气球, 获得相应的价值
            result = Math.max(result, curr + left + right); // 记录最大值
        }
        // 存储记忆
        solved[i][j] = result;
        return result;
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 在原本的nums前后各加一个1，方便首尾的统一处理
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        System.arraycopy(nums, 0, val, 1, n);
        n += 2; // 长度加2
        // 初始化solved数组
        solved = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(solved[i], -1);
        }
        return solving(val, 0, n - 1);
    }
}


// @lc code=start
// 动态规划
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 在原本的nums前后各加一个1，方便首尾的统一处理
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        System.arraycopy(nums, 0, val, 1, n);
        n += 2; // 长度加2
        // dp[i][j]: 还原开区间(i, j)可以获取的最大价值
        int[][] dp = new int[n][n];
        // 递推
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 2; j < n; j++) {
                // 枚举第一个还原的气球
                for(int k = i + 1; k <= j - 1; k++) {
                    int curr = val[i] * val[k] * val[j]; // 此时开区间(i, k)的没有任何气球, 还原位置k的气球, 获得val(i) * val(k) * val(j)的价值
                    int left = dp[i][k]; // 还原开区间(i, k)中所有气球, 获得相应的价值
                    int right = dp[k][j]; // 还原开区间(k, j)中所有气球, 获得相应的价值
                    dp[i][j] = Math.max(dp[i][j], curr + left + right); // 记录最大值
                }
            }
        }
        return dp[0][n - 1];
    }
}
// @lc code=end
