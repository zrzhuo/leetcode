/*
 * @lc app=leetcode.cn id=3 lang=java
 *
 * [3] 无重复字符的最长子串
 */

// @lc code=start
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] pre = new int[128]; // 记录字符上次出现的位置
        Arrays.fill(pre, -1);
        int start = 0; // 当前无重复字符子串的开始位置
        int result = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            start = Math.max(start, pre[ch] + 1); // 更新start, 当前无重复字符子串为s[start, i]
            result = Math.max(result, i - start + 1);   // 更新result
            pre[ch] = i; // 更新该字符的上次出现位置
        }
        return result;
    }
}
// @lc code=end


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