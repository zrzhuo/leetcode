/*
 * @lc app=leetcode.cn id=397 lang=java
 *
 * [397] 整数替换
 */

// @lc code=start
class Solution {
    // solved[i]: 整数i变为1所需的最小替换次数
    Map<Integer, Integer> solved = new HashMap<>();
    int solving(int x) {
        // 获取记忆
        if(solved.containsKey(x))
            return solved.get(x);
        int result = 0;
        // 偶数一步走: x --> x/2 
        if(x % 2 == 0)
            result = solving(x / 2) + 1;
        // 奇数两步走: x --> x-1或x+1 --> x/2或x/2+1
        else
            result = Math.min(solving(x/2), solving(x/2 + 1)) + 2;
        // 存储记忆
        solved.put(x, result);
        return result;
    }

    public int integerReplacement(int n) {
        solved.put(1, 0); // 初始化
        return solving(n);
    }
}
// @lc code=end

