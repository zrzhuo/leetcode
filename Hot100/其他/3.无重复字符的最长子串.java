/*
 * @lc app=leetcode.cn id=3 lang=java
 *
 * [3] 无重复字符的最长子串
 */

// @lc code=start
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 最大窗口问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = s.length(), ans = 0;
        // 定义条件指标: 当前窗口内的不重复字符数count
        int count = 0;
        int[] counter = new int[128];
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足条件
            while(left < right) {
                if(count <= 0)
                    break;
                if(--counter[s.charAt(left)] == 1)
                    count--;
                left++;
            }
            // 移动right直到恰好不满足条件
            while(right < n) {
                if(count > 0)
                    break;
                if(++counter[s.charAt(right)] == 2)
                    count++;
                right++;
            }
            // 满足条件的窗口为[left, right - 1)
            ans = Math.max(ans, right - 1 - left);
        }
        // 末尾特殊处理
        if(count == 0)
            ans = Math.max(ans, n - left);
        return ans;
    }
}
// @lc code=end
