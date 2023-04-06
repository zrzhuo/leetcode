/*
 * @lc app=leetcode.cn id=1563 lang=java
 *
 * [1563] 石子游戏 V
 */

// @lc code=start
class Solution {
    // solved[i][j]: 从stones[i...j]中, alice可以获得的最大分数
    int[][] solved;
    int[] prefix; // 前缀和
    int solving(int[] stones, int i, int j) {
        // 获取记忆
        if(solved[i][j] > 0)
            return solved[i][j];
        int result = 0;
        for(int k = i; k < j; ++k) {
            // k将[i,j]分为[i,k]和[k+1,j]
            int leftSum = prefix[k + 1] - prefix[i]; 
            int rightSum = prefix[j + 1] - prefix[k + 1];
            int point = 0;
            // 只能留下较小的组
            if(leftSum < rightSum) 
                point = leftSum + solving(stones, i, k);
            // 只能留下较小的组
            else if(leftSum > rightSum) 
                point = rightSum + solving(stones, k + 1, j);
            // 若两组值相等, 则留下能产生更高分数的组
            else
                point = rightSum + Math.max(solving(stones, i, k),  solving(stones, k + 1, j));
            result = Math.max(result, point);
        }
        // 存储记忆
        solved[i][j] = result;
        return result;
        
    }
    public int stoneGameV(int[] stones) {
        int n = stones.length;
        solved = new int[n][n];
        prefix = new int[n + 1];
        for(int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + stones[i];
        return solving(stones, 0, n - 1);
    }
}
// @lc code=end

