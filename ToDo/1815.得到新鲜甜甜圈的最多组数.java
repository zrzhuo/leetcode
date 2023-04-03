/*
 * @lc app=leetcode.cn id=1815 lang=java
 *
 * [1815] 得到新鲜甜甜圈的最多组数
 */

// @lc code=start
class Solution {
    
    Map<Long, Integer> solved = new HashMap<>();
    int solving(int batchSize, long state, int rem) {
        // 获取记忆
        if(solved.containsKey(state))
            return solved.get(state);
        int result = 0;
        for(int i = 0; i < batchSize; i++) {
            // 当前余数的数量大于0
            if(((state >> (5*i)) & 31) > 0) {
                long nextState = state - (1L << (5*i));
                int nextRem = (rem + i) % batchSize;
                int nextResult = solving(batchSize, nextState, nextRem);
                result = Math.max(result, nextResult);
            }
        }
        // 存储记忆
        solved.put(state, result);
        return result;
    }

    public int maxHappyGroups(int batchSize, int[] groups) {
        int n = groups.length;
        // 统计余数的个数
        int[] counter = new int[batchSize]; 
        for(int i = 0; i < n; i++) {
            int rem = groups[i] % batchSize;
            counter[rem]++;
        }
        // 状态压缩: 由于 1 <= batchSize <= 9, 1 <= groups.length <= 30, 故最多9*5=45位比特位即可代表一个counter
        long state = 0;
        for(int i = 0; i < batchSize; i++)
            state |= (counter[i] << (5*i));
        return solving(batchSize, state, 0);        
    }
}
// @lc code=end

