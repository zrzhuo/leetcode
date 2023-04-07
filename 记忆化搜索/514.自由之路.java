/*
 * @lc app=leetcode.cn id=514 lang=java
 *
 * [514] 自由之路
 */

// @lc code=start
class Solution {
    // solved[pos][idx]: 当前指针在ring[pos]处时, 拼写realKey[idx,n-1]所需要的最少移动步数
    int[][] solved;
    int solving(String ring, String realKey, int pos, int idx) {
        int m = ring.length(), n = realKey.length();
        if(idx >= n)
            return 0;
        // 获取记忆
        if(solved[pos][idx] != 0)
            return solved[pos][idx];
        char cur = realKey.charAt(idx);
        // 向左寻找cur
        int leftPos = pos, leftStep = 0;
        while(ring.charAt(leftPos) != cur) {
            leftStep++;
            leftPos--;
            if(leftPos == -1)
                leftPos = m - 1;
        }
        // 向右寻找cur
        int rightPos = pos, rightStep = 0;
        while(ring.charAt(rightPos) != cur) {
            rightStep++;
            rightPos++;
            if(rightPos == m)
                rightPos = 0;
        }
        // 取移动步数较小的方案
        int left = leftStep + solving(ring, realKey, leftPos, idx + 1);
        int right = rightStep + solving(ring, realKey, rightPos, idx + 1);
        int result = Math.min(left, right);
        // 存储记忆
        solved[pos][idx] = result;
        return result;
    }

    public int findRotateSteps(String ring, String key) {
        // 忽略key的连续重复字符
        StringBuilder sb = new StringBuilder();
        sb.append(key.charAt(0));
        for(int i = 1; i < key.length(); i++) {
            if(key.charAt(i) != key.charAt(i - 1))
                sb.append(key.charAt(i));
        }
        String realKey = sb.toString();
        // 将realKey作为目标字符串
        int m = ring.length(), n = realKey.length();
        solved = new int[m][n];
        return solving(ring, realKey, 0, 0) + key.length();
    }
}
// @lc code=end

