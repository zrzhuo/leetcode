/*
 * @lc app=leetcode.cn id=739 lang=java
 *
 * [739] 每日温度
 */

// @lc code=start
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        // 单调栈: 栈底到栈顶温度递减, 实际存的是下标
        Deque<Integer> sta = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            int cur = temperatures[i];
            // 当前温度大于栈顶温度
            while(!sta.isEmpty() && cur > temperatures[sta.peek()]) {
                res[sta.peek()] = i - sta.peek(); // 当前温度即为第一个比栈顶温度高的温度
                sta.pop();
            }
            sta.push(i);
        }
        return res;
    }
}
// @lc code=end

