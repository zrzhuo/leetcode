/*
 * @lc app=leetcode.cn id=1510 lang=java
 *
 * [1510] 石子游戏 IV
 */

// @lc code=start
class Solution {
    // solved[i]: i个石子时, 是否先手必赢
    int[] solved;
    boolean solving(int i) {
        // 获取记忆
        if(solved[i] != -1)
            return solved[i] == 1;
        // 该游戏无论处于什么状态, 要么"先手必赢", 要么"先手必输", 其中:
        // 1. 所谓先手必赢, 即当前状态下存在一种操作, 使得后继状态为先手必输
        // 2. 所谓先手必输, 即当前状态下无论怎么操作, 所有后继状态都先手必赢
        // 枚举当前状态的所有后续状态
        for(int k = 1; k * k <= i; k++) {
            int nextState = i - k * k; // 后继状态
            if(solving(nextState) == false) {
                solved[i] = 1;
                return true;
            }
        }
        solved[i] = 0;
        return false;
    }

    public boolean winnerSquareGame(int n) {
        solved = new int[n + 1];
        Arrays.fill(solved, -1);
        return solving(n);
    }
}
// @lc code=end

