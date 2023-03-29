/*
 * @lc app=leetcode.cn id=424 lang=java
 *
 * [424] 替换后的最长重复字符
 */

// @lc code=start
class Solution {
    int findMax(int[] counter) {
        int max = 0;
        for(int i = 0; i < 26; ++i)
            max = Math.max(max, counter[i]);
        return max;
    }
    public int characterReplacement(String s, int k) {
        // 指标定义
        int[] counter = new int[26];
        int maxCount = 0;
        // 滑动窗口[left, right), 最大窗口问题
        int left = 0, right = 0, n = s.length(), ans = 0;
        while(right < n) {
            // 移动left直到恰好满足条件
            while(left < right) {
                if(maxCount + k >= right - left)
                    break;
                --counter[s.charAt(left) - 'A'];
                maxCount = findMax(counter);
                left++;
            }
            // 移动right直到恰好不满足条件
            while(right < n) {
                if(maxCount + k < right - left)
                    break;
                ++counter[s.charAt(right) - 'A'];
                maxCount = findMax(counter);
                right++;
            }
            // 当前满足条件的窗口为[left, right - 1)
            ans = Math.max(ans, right - 1 - left);
        }
        // 末尾特殊处理
        if(maxCount + k >= n - left)
            ans = Math.max(ans, n - left);
        return ans;
    }
}
// @lc code=end
