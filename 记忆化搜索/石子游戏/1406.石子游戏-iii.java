/*
 * @lc app=leetcode.cn id=1406 lang=java
 *
 * [1406] 石子游戏 III
 */

// @lc code=start
class Solution {
    // solved[i]: 在数组stones[i...n-1]上做游戏, 先手玩家得分与后手玩家得分的差值
    int[] solved;
    int solving(int[] stones, int i) {
        int n = stones.length;
        if(i == n)
            return 0;
        // 获取记忆
        if(solved[i] != Integer.MIN_VALUE)
            return solved[i];
        // 分别尝试拿取1, 2, 3堆石子, 拿取完之后换手
        int result = Integer.MIN_VALUE;
        if(i < n)
            result = Math.max(result, stones[i] - solving(stones, i + 1));
        if(i + 1 < n)
            result = Math.max(result, stones[i] + stones[i + 1] - solving(stones, i + 2));
        if(i + 2 < n)
            result = Math.max(result, stones[i] + stones[i + 1] + stones[i + 2] - solving(stones, i + 3));
        // 存储记忆
        solved[i] = result;
        return result;
    }
    public String stoneGameIII(int[] stones) {
        int n = stones.length;
        solved = new int[n];
        Arrays.fill(solved, Integer.MIN_VALUE);
        int diff = solving(stones, 0);
        if(diff > 0)
            return "Alice";
        else if(diff < 0)
            return "Bob";
        else
            return "Tie";
    }
}
// @lc code=end
