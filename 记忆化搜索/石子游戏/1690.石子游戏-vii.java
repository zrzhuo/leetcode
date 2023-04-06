/*
 * @lc app=leetcode.cn id=1690 lang=java
 *
 * [1690] 石子游戏 VII
 */

// @lc code=start
class Solution {
    // solved[i][j]: 当在stones[i...j]上进行该游戏时，先手玩家得分与后手玩家得分的差值
    int[][] solved;
    int[] prefix; // 前缀和
    int solving(int[] stones, int i, int j) {
        if(i > j)
            return 0;
        // 获取记忆
        if(solved[i][j] != Integer.MIN_VALUE)
            return solved[i][j];
        int sum = prefix[j + 1] - prefix[i]; // 当前数组的和
        int left = sum - stones[i] - solving(stones, i + 1, j); // 移除左侧石头
        int right = sum - stones[j] - solving(stones, i, j - 1); // 移除右侧石头
        int result = Math.max(left, right); // 取最大值
        // 存储记忆
        solved[i][j] = result;
        return result;
    }
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        solved = new int[n][n];
        for(int i = 0; i < n; i++)
            Arrays.fill(solved[i], Integer.MIN_VALUE);
        prefix = new int[n + 1];
        for(int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + stones[i];
        return solving(stones, 0, n - 1);
    }
}
// @lc code=end

