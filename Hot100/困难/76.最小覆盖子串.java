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
        // 最大窗口问题: 滑动窗口[left, right)
        int left = 0, right = 0, start = -1, end = n + 1;
        // 定义条件指标: 
        int count = 0;
        int[] counter = new int[128];
        for(int i = 0; i < m; i++) {
            if(--counter[t.charAt(i)] == -1)
                count++;
        }
        // 滑动
        while(right < n) {
            // 移动right直到恰好满足条件
            while(right < n) {
                if(count <= 0)
                    break;
                if(++counter[s.charAt(right)] == 0)
                    count--;
                right++;
            }
            // 移动left直到恰好不满足条件
            while(left < right) {
                if(count > 0)
                    break;
                if(--counter[s.charAt(left)] == -1)
                    count++;
                left++;
            }
            // 当前满足条件的窗口为[left-1, right)
            if(right - left + 1 < end - start) {
                start = left - 1;
                end = right;
            }
        }
        // 不存在满足条件的窗口
        if(start == -1)
            return "";
        return s.substring(start, end);
    }
}
// @lc code=end

