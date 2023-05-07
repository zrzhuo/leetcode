/*
 * @lc app=leetcode.cn id=1000 lang=java
 *
 * [1000] 合并石头的最低成本
 */


假设共n堆石头, 目标是将其通过合并减少n-1堆, 每次合并k堆石头后, 石头会减少k-1堆, 故:
        仅当(n-1)%(k-1)为0时, 才能经过(n-1)/(k-1)次合并将石头合并为1堆

将闭区间[i, j]合并为t>1堆石头, 需要如下步骤:
    1. 将闭区间[i, s]合并为1堆, 支付相应的成本
    2. 将闭区间[s + 1, j]合并为t-1堆, 支付相应的成本

将闭区间[i, j]合并为1堆石头, 需要如下步骤:
    1. 将闭区间[i, s]合并为1堆, 支付相应的成本
    2. 将闭区间[s + 1, j]合并为t-1堆, 支付相应的成本
    3. 再将得到的t堆石头合并为一堆, 支付相应的成本


// @lc code=start
class Solution {
    int[] prefix;  // 前缀和, 方便求区间和: 区间[i, j]之和 = prefix[j + 1] - prefix[i]
    int[][][] solved; // solved[i][j][t]: 将闭区间[i, j]合并为t堆石头的最低成本
    int solving(int i, int j, int t, int k) {
        // 获取记忆
        if(solved[i][j][t] != Integer.MAX_VALUE)
            return solved[i][j][t];
        int result = Integer.MAX_VALUE;
        if(t == 1) {
            // t为1时, 需要将
            result = solving(i, j, k, k) + prefix[j + 1] - prefix[i];
        } else {
            // 枚举切分点
            for(int s = i; s < j; s++) {
                int left = solving(i, s, 1, k); // 将闭区间[i, s]合并为1堆
                int right = solving(s + 1, j, t - 1, k); // 将闭区间[s + 1, j]合并为t-1堆
                result = Math.min(result, left + right);
            }
        }
        // 存储记忆
        solved[i][j][t] = result;
        return result;
    }

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        // 判断是否能合并为1堆
        if((n - 1) % (k - 1) != 0)
            return -1;
        // 初始化prefix
        prefix = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + stones[i - 1];
        }
        // 初始化solved
        solved = new int[n][n][k + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int t = 1; t <= k; t++) {
                    solved[i][j][t] = Integer.MAX_VALUE;
                }
            }
        }
        return solving(0, n - 1, 1, k);
    }
}
// @lc code=end
