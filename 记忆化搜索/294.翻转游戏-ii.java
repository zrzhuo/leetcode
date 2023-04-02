/*
 * @lc app=leetcode.cn id=294 lang=java
 *
 * [294] 翻转游戏 II
 */

// @lc code=start
class Solution {
    Map<Long, Boolean> solved = new HashMap<>(); // 某状态是否为"先手必赢"
    boolean solving(long state) {
        if(state == 0L)
            return false; // 全为负号时一定是"先手必输"的
        // 获取记忆
        if(solved.containsKey(state))
            return solved.get(state);
        // 对于某状态，其要么是“先手必赢”的，要么是“先手必输”的!!!
        // 因此, 对于某状态, 如果其某个后继状态是"先手必输"的, 则该状态一定是"先手必赢"的
        // 枚举当前状态的所有后续状态
        for(int i = 1; i < 60; ++i) {
            long first = (state >> (i - 1)) & 1L; // 第i-1位
            long second = (state >> i) & 1L; // 第i位
            // 第i-1位和第i位都是1, 即都是'+'号
            if(first + second == 2) {
                // 翻转两位, 形成后继状态
                long nextState = state ^ (1L << (i - 1)) ^ (1L << i);
                // 对于某状态, 如果其某个后继状态是"先手必输"的, 则该状态一定是"先手必赢"的
                // 也就是说, 存在某种决策能够使当前状态变为某个"先手必输"的后继状态时, 当前状态一定是"先手必赢"的
                if(solving(nextState) == false){
                    solved.put(state, true);
                    return true;
                }
            } 
        }
        solved.put(state, false);
        return false;
    }

    public boolean canWin(String currentState) {
        // 状态压缩: 由于1 <= currentState.length <= 60, 故可以使用60位比特位代表一个状态
        long state = 0L; // long类型有64个比特位
        for(int i = 0; i < currentState.length(); ++i) {
            state <<= 1;
            if(currentState.charAt(i) == '+')
                state += 1;
        }
        return solving(state);
    }
}
// @lc code=end

