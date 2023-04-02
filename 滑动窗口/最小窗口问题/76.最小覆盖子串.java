/*
 * @lc app=leetcode.cn id=76 lang=java
 *
 * [76] 最小覆盖子串
 */

// @lc code=start
class Solution {
    public String minWindow(String s, String t) {
        int n = s.length(), m = t.length();
        if(n < m)
            return "";
        // 最小窗口问题: 滑动窗口[left, right)
        int left = 0, right = 0, ansLeft = -1, ansRight = n;
        // 定义条件指标: t中未被当前窗口覆盖的字符数量
        int count = 0;
        int[] counter = new int[128];
        for(int i = 0; i < m; ++i) {
            if(++counter[t.charAt(i)] == 1)
                count++;
        }
        // 滑动
        while(right < n) {
             // 移动right直到恰好满足条件
            while(right < n) {
                if(count == 0)
                    break;
                if(--counter[s.charAt(right)] == 0)
                    count--; // count递减
                right++;
            }
            // 移动left直到恰好不满足条件
            while(left < right) {
                if(count > 0)
                    break;
                if(++counter[s.charAt(left)] == 1)
                    count++; // count递增
                left++;
            }
            // 当前满足条件的窗口为[left-1, right)
            if(right - left + 1 < ansRight - ansLeft) {
                ansLeft = left - 1;
                ansRight = right;
            }
        }
        // 不存在满足条件的窗口
        if(ansLeft == -1)
            return "";
        return s.substring(ansLeft, ansRight);
    }
}
// @lc code=end
