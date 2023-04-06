/*
 * @lc app=leetcode.cn id=1140 lang=java
 *
 * [1140] 石子游戏 II
 */

// @lc code=start
class Solution {
    // solved[i][m]: 剩余数组为piles[i, n-1], 且M的值为m时, 先手玩家得到的数量与后手玩家得到的数量的差值
    int[][] solved;
    int[] prefix;  // 前缀和, 方便计算区间和
    int solving(int[] piles, int i, int m) {
        int n = piles.length;
        if(i == n)
            return 0;
        // 获取记忆
        if(solved[i][m] != 0)
            return solved[i][m];
        int result = Integer.MIN_VALUE; // 由于差值可以为负数, 故应该初始化为最小整数值
        // 枚举先手玩家可以选择的x
        for(int x = 1; x <= 2 * m; x++) {
            if(x > n - i)
                break; // 剩余数字数量不足x
            int now = prefix[i + x] - prefix[i]; // 先手玩家当前取走piles[i...i+x-1], 通过prefix快速计算
            int diff = solving(piles, i + x, Math.max(m, x)); // 换手
            result = Math.max(result, now - diff);
        }
        // 存储记忆
        solved[i][m] = result;
        return result;
    }

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        solved = new int[n][n + 1];
        prefix = new int[n + 1];
        for(int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + piles[i];
        }
        return (prefix[n] + solving(piles, 0, 1)) / 2;
    }
}
// @lc code=end

