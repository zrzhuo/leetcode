/*
 * @lc app=leetcode.cn id=877 lang=java
 *
 * [877] 石子游戏
 */


// 动态规划
class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // dp[i][j]表示，当在piles[i...j]上进行该游戏时，先手玩家得分与后手玩家得分的差值
        int[][] dp = new int[n][n];
        // 初始化
        for(int i = 0; i < n; i++)
            dp[i][i] = piles[i]; // 先手玩家先取, 后手玩家没得取
        // 递推
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                // 当前先手选择左侧石堆，后手玩家成为先手，其从区间[i+1...j]中选择
                int left = piles[i] - dp[i + 1][j]; 
                // 当前先手选择右侧石堆，后手玩家成为先手，其从区间[i...j-1]中选择
                int right = piles[j] - dp[i][j - 1]; 
                dp[i][j] = Math.max(left, right);
            }
        }
        // dp[0][n-1]>0时, 先手必胜
        return dp[0][n - 1] > 0;
    }
}

// @lc code=start
// 记忆化搜索
class Solution {
    // solved[left][right]表示，当在piles[left...right]上进行该游戏时，"先手玩家得分 - 后手玩家得分"的最大值
    int[][] solved; 
    int solving(int[] piles, int left, int right) {
        if(left == right)
            return piles[left];
        // 获取记忆
        if(solved[left][right] != 0)
            return solved[left][right];
        // 当前先手选择左侧石堆，后手玩家成为先手，其从区间[left+1...j]中选择
        int leftVal = piles[left] - solving(piles, left + 1, right);
        // 当前先手选择右侧石堆，后手玩家成为先手，其从区间[i...right-1]中选择
        int rightVal = piles[right] - solving(piles, left, right - 1);
        // 取最大值
        int result = Math.max(leftVal, rightVal);
        // 存储记忆
        solved[left][right] = result;
        return result;
    }
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        solved = new int[n][n];
        return solving(piles, 0, n - 1) > 0;
    }
}
// @lc code=end