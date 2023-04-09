/*
 * @lc app=leetcode.cn id=474 lang=java
 *
 * [474] 一和零
 */

class Solution {
    public int findMaxForm(String[] strs, int v, int w) {
        int n = strs.length;
        // 统计每个字符串0和1的个数
        int[] zero = new int[n], one = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < strs[i].length(); j++){
                if(strs[i].charAt(j) == '0')
                    zero[i]++;
                else
                    one[i]++;
            }
        }
        // dp[k][i][j]: 只从strs[0...i]中选择, 可以得到的"0的个数小于等于i, 1的个数小于等于j"的最大子集长度
        // 0-1背包问题: 一个字符串是一件物品, 物品价值均为1, 物品体积为0的个数, 物品重量为1的个数, 背包体积为v, 背包负重为w, 求可以装下的最大价值
        int[][][] dp = new int[n][v + 1][w + 1];
        // 初始化
        for(int i = 0; i <= v; i++) {
            for(int j = 0; j <= w; j++) {
                dp[0][i][j] = (i < zero[0] || j < one[0]) ? 0 : 1;
            }
        }
        // 递推
        for(int k = 1; k < n; k++) {
            for(int i = 0; i <= v; i++) {
                for(int j = 0; j <= w; j++) {
                    if(i < zero[k] || j < one[k])
                        dp[k][i][j] = dp[k - 1][i][j]; // 选不了strs[i], 故只有只用方案
                    else
                        dp[k][i][j] = Math.max(dp[k - 1][i][j], dp[k - 1][i - zero[k]][j - one[k]] + 1); // 两种方案: 选strs[i]和不选strs[i]
                }
            }
        }
        return dp[n - 1][v][w];
    }
}

// 统一形式
class Solution {
    public int findMaxForm(String[] strs, int v, int w) {
        int n = strs.length;
        // 统计每个字符串0和1的个数
        int[] zero = new int[n], one = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < strs[i].length(); j++){
                if(strs[i].charAt(j) == '0')
                    zero[i]++;
                else
                    one[i]++;
            }
        }
        // dp[k][i][j]: 只从strs[0...i-1]中选择, 可以得到的"0的个数小于等于i, 1的个数小于等于j"的最大子集长度
        int[][][] dp = new int[n + 1][v + 1][w + 1];
        // 初始化
       dp[0][0][0] = 0;
        // 递推
        for(int k = 1; k <= n; k++) {
            for(int i = 0; i <= v; i++) {
                for(int j = 0; j <= w; j++) {
                    if(i < zero[k - 1] || j < one[k - 1])
                        dp[k][i][j] = dp[k - 1][i][j]; // 选不了strs[i-1], 故只有只用方案
                    else
                        dp[k][i][j] = Math.max(dp[k - 1][i][j], dp[k - 1][i - zero[k - 1]][j - one[k - 1]] + 1); // 两种方案: 选strs[i-1]和不选strs[i-1]
                }
            }
        }
        return dp[n][v][w];
    }
}

// 空间优化1: dp[k]层只依赖于dp[k-1]层, 故可将空间降低一维
class Solution {
    public int findMaxForm(String[] strs, int v, int w) {
        int n = strs.length;
        // 统计每个字符串0和1的个数
        int[] zero = new int[n], one = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < strs[i].length(); j++){
                if(strs[i].charAt(j) == '0')
                    zero[i]++;
                else
                    one[i]++;
            }
        }
        // dp[k][i][j]: 只从strs[0...i-1]中选择, 可以得到的"0的个数小于等于i, 1的个数小于等于j"的最大子集长度
        int[][] dp = new int[v + 1][w + 1];
        // 初始化
       dp[0][0] = 0;
        // 递推
        for(int k = 1; k <= n; k++) {
            int[][] temp = new int[v + 1][w + 1];
            for(int i = 0; i <= v; i++) {
                for(int j = 0; j <= w; j++) {
                    if(i < zero[k - 1] || j < one[k - 1])
                        temp[i][j] = dp[i][j]; // 选不了strs[i-1], 故只有只用方案
                    else
                        temp[i][j] = Math.max(dp[i][j], dp[i - zero[k - 1]][j - one[k - 1]] + 1); // 两种方案: 选strs[i-1]和不选strs[i-1]
                }
            }
            dp = temp;
        }
        return dp[v][w];
    }
}

// 空间优化2: 在空间优化1的基础上, 由于temp[i][j]仅依赖于dp[i][j]和dp[i-zero[k-1]][j-nums[k-1]], 故可以从后往前更新dp数组, 而无需使用temp数组
class Solution {
    public int findMaxForm(String[] strs, int v, int w) {
        int n = strs.length;
        // 统计每个字符串0和1的个数
        int[] zero = new int[n], one = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < strs[i].length(); j++){
                if(strs[i].charAt(j) == '0')
                    zero[i]++;
                else
                    one[i]++;
            }
        }
        // dp[k][i][j]: 只从strs[0...i-1]中选择, 可以得到的"0的个数小于等于i, 1的个数小于等于j"的最大子集长度
        int[][] dp = new int[v + 1][w + 1];
        // 初始化
       dp[0][0] = 0;
        // 递推
        for(int k = 1; k <= n; k++) {
            for(int i = v; i >= 0; i--) {
                for(int j = w; j >= 0; j--) {
                    if(i < zero[k - 1] || j < one[k - 1])
                        dp[i][j] = dp[i][j]; // 选不了strs[i-1], 故只有只用方案
                    else
                        dp[i][j] = Math.max(dp[i][j], dp[i - zero[k - 1]][j - one[k - 1]] + 1); // 两种方案: 选strs[i-1]和不选strs[i-1]
                }
            }
        }
        return dp[v][w];
    }
}

// @lc code=start
// 最终版本
class Solution {
    public int findMaxForm(String[] strs, int v, int w) {
        int n = strs.length;
        // 统计每个字符串0和1的个数
        int[] zero = new int[n], one = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < strs[i].length(); j++){
                if(strs[i].charAt(j) == '0')
                    zero[i]++;
                else
                    one[i]++;
            }
        }
        // dp[k][i][j]: 只从strs[0...i-1]中选择, 可以得到的"0的个数小于等于i, 1的个数小于等于j"的最大子集长度
        int[][] dp = new int[v + 1][w + 1];
        // 初始化
       dp[0][0] = 0;
        // 递推
        for(int k = 1; k <= n; k++) {
            for(int i = v; i >= zero[k - 1]; i--) {
                for(int j = w; j >= one[k - 1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero[k - 1]][j - one[k - 1]] + 1); // 两种方案: 选strs[i-1]和不选strs[i-1]
                }
            }
        }
        return dp[v][w];
    }
}
// @lc code=end