/*
 * @lc app=leetcode.cn id=159 lang=java
 *
 * [159] 至多包含两个不同字符的最长子串
 */

// @lc code=start
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // 滑动窗口[left, right), 最大窗口问题
        int left = 0, right = 0, n = s.length(), ans = 0;
        // 定义条件指标
        int count = 0; // 当前窗口中的不同字符数
        int[] counter = new int[128];
        while(right < n) {
            // 移动left直到恰好满足条件
            while(left < right) {
                if(count <= 2)
                    break;
                if(--counter[s.charAt(left)] == 0)
                    count--;
                left++;
            }
            // 移动right直到恰好不满足条件
            while(right < n) {
                if(count > 2)
                    break;
                if(++counter[s.charAt(right)] == 1)
                    count++;
                right++;
            }
            // 当前满足条件的窗口为[left, right - 1)
            ans = Math.max(ans, right - 1 - left);
        }
        // 末尾特殊处理
        if(count <= 2)
            ans = Math.max(ans, n - left);
        return ans;
    }
}
// @lc code=end

