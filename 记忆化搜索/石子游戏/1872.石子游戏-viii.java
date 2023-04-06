/*
 * @lc app=leetcode.cn id=1872 lang=java
 *
 * [1872] 石子游戏 VIII
 */



// @lc code=start
// 可行但超时
class Solution {
    // solved[i]: 当在prefix[i...n-1]上进行该游戏时，先手玩家得分与后手玩家得分的差值
    int[] solved;
    int solving(int[] prefix, int idx) { 
        int n = prefix.length;
        if(idx == n - 1)
            return 0; // idx==n-1时, 游戏结束
        // 获取记忆
        if(solved[idx] != Integer.MIN_VALUE)
            return solved[idx];
        int result = Integer.MIN_VALUE;
        // 枚举可以选择的下标i
        for(int i = idx + 1; i < n; i++) {
            int now = prefix[i] - solving(prefix, i); // 当前得分, 并换手
            result = Math.max(result, now);
        }
        // 存储记忆
        solved[idx] = result;
        return result;
    }
    public int stoneGameVIII(int[] stones) {
        /*
        * 计算stones的前缀和prefix, prefix[i]表示stones[0...i]之和, 则该游戏实际上等价于:
        * Alice和Bob轮流选择一个一个下标i, 将prefix[i]计入其得分, 下标i要满足:
        *     1. i从1开始
        *     2. i是递增的, 即当前玩家选择的i要在上一个玩家选择的i的后面
        *     3. 当i==n-1时, 游戏结束
        */
        int n = stones.length;
        // 将在stones数组上的游戏, 转化为在prefix上的游戏
        int[] prefix = new int[n];
        prefix[0] = stones[0];
        for(int i = 1; i < n; i++)
            prefix[i] = prefix[i - 1] + stones[i];
        solved = new int[n];
        Arrays.fill(solved, Integer.MIN_VALUE);
        return solving(prefix, 0);
    }
}
// @lc code=end

